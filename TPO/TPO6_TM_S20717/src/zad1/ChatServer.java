/**
 *
 *  @author Tolak Maciej S20717
 *
 */

package zad1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.nio.ByteBuffer.allocateDirect;
import static java.nio.channels.ServerSocketChannel.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ChatServer implements Runnable {
    private final Thread thread;
    private final StringBuilder serverLog;
    private final InetSocketAddress inetSocketAddress;
    private final Map<SocketChannel, String> clients = new HashMap<>();
    private final Lock lock = new ReentrantLock();
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public ChatServer(String host, int port) {
        inetSocketAddress = new InetSocketAddress(host, port);
        serverLog = new StringBuilder();
        thread = serverThread();
    }

    private Thread serverThread() {

        return new Thread(this::run);
    }

    private StringBuilder requestHandler(SocketChannel clientSocket, String str) throws IOException {

        StringBuilder response = new StringBuilder();

        if (str.matches("log in .+")) {
            clients.put(clientSocket, str.substring(7));

            serverLog.append(DateTimeFormatter.ofPattern("hh:mm:ss.SSS").format(LocalTime.now()));
            serverLog.append(" ");
            serverLog.append(str.substring(7));
            serverLog.append(" logged in");
            serverLog.append("\n");
            response.append(str.substring(7)).append(" logged in").append("\n");

        } else if (str.matches("log out")) {
            serverLog.append(DateTimeFormatter.ofPattern("hh:mm:ss.SSS").format(LocalTime.now()));
            serverLog.append(" ");
            serverLog.append(clients.get(clientSocket));
            serverLog.append(" logged out");
            serverLog.append("\n");
            response.append(clients.get(clientSocket)).append(" logged out").append("\n");

            ByteBuffer byteBuffer;
            byteBuffer = UTF_8.encode(response.toString());
            clientSocket.write(byteBuffer);

            clients.remove(clientSocket);

        } else {
            serverLog.append(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss.SSS")));
            serverLog.append(" ");
            serverLog.append(clients.get(clientSocket));
            serverLog.append(": ");
            serverLog.append(str);
            serverLog.append("\n");
            response.append(clients.get(clientSocket)).append(": ").append(str).append("\n");
        }

        return response;
    }

    public void startServer() {

        thread.start();

        System.out.println("Server started\n");
    }

    public void stopServer() {

        try {
            lock.lock();
            thread.interrupt();
            selector.close();
            serverSocketChannel.close();

            System.out.println("Server stopped");

        } catch (IOException exception) {
            exception.printStackTrace();

        }finally {
            lock.unlock();
        }
    }

    String getServerLog() {

        return serverLog.toString();
    }

    public void run() {
        try {
            selector = Selector.open();

            serverSocketChannel = open();
            serverSocketChannel.bind(inetSocketAddress);
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, serverSocketChannel.validOps());

            while (!thread.isInterrupted()) {
                selector.select();

                if (thread.isInterrupted()) {
                    break;
                }

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key;
                    key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        SocketChannel clientSocket = serverSocketChannel.accept();
                        SelectableChannel selectableChannel = clientSocket.configureBlocking(false);
                        clientSocket.register(selector, SelectionKey.OP_READ);
                    }

                    if (key.isReadable()) {
                        SocketChannel clientSocket = (SocketChannel) key.channel();


                        ByteBuffer buffer = allocateDirect(2048);

                        StringBuilder clientRequest = new StringBuilder();

                        int readBytes = 0;
                        do {
                            try {
                                lock.lock();

                                readBytes = clientSocket.read(buffer);
                                buffer.flip();
                                clientRequest.append(UTF_8.decode(buffer).toString());
                                buffer.clear();
                                readBytes = clientSocket.read(buffer);

                            } catch (Exception exception) {

                                exception.printStackTrace();
                            } finally {
                                lock.unlock();
                            }
                        } while (readBytes != 0);

                        String[] parts = clientRequest.toString().split("#");

                        for (String req : parts) {
                            String clientResponse = requestHandler(clientSocket, req).toString();


                            for (Map.Entry<SocketChannel, String> entry : clients.entrySet()) {
                                ByteBuffer byteBuffer = UTF_8.encode(clientResponse);
                                entry.getKey().write(byteBuffer);
                            }
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
