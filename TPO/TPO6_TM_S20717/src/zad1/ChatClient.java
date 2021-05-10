/**
 *
 *  @author Tolak Maciej S20717
 *
 */

package zad1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.nio.ByteBuffer.allocateDirect;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ChatClient {
    private final InetSocketAddress inetSocketAddress;
    private final StringBuilder clientView;
    private final String clientId;
    private SocketChannel channel;
    private final Thread receivingThread = new Thread(this::run);

    private final Lock lock = new ReentrantLock();

    public ChatClient(String host, int port, String id) {
        this.inetSocketAddress = new InetSocketAddress(host, port);
        this.clientId = id;

        clientView = new StringBuilder("=== " + clientId + " chat view\n");
    }

    public void login() {

        try {
            channel = SocketChannel.open(inetSocketAddress);
            channel.configureBlocking(false);

            send("log in " + clientId);

            receivingThread.start();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void logout() {

        send("log out" + "#");

        try {
            lock.lock();
            receivingThread.interrupt();

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            lock.unlock();
        }
    }

    public void send(String req) {

        try {
            channel.write(UTF_8.encode(req + "#"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String getChatView() {

        return clientView.toString();
    }

    private void run() {

        ByteBuffer buffer = allocateDirect(2048);
        int bytesRead = 0;

        while (!receivingThread.isInterrupted()) {
            do {
                try {
                    lock.lock();
                    bytesRead = channel.read(buffer);

                } catch (Exception exception) {
                    exception.printStackTrace();

                } finally {
                    lock.unlock();
                }
            } while (bytesRead == 0 && !receivingThread.isInterrupted());

            buffer.flip();
            String response = UTF_8.decode(buffer).toString();
            StringBuilder stringBuilder = clientView.append(response);
            buffer.clear();
        }
    }
}
