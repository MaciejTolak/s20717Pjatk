/**
 * @author Tolak Maciej S20717
 */

package zad1;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Client {

    String host, id;
    int port;
    SocketChannel socketChannel;
    Selector selector;


    public Client(String host, int port, String id) {
        this.host = host;
        this.id = id;
        this.port = port;
    }

    public void connect() {

        try {

            socketChannel = SocketChannel.open();
            socketChannel.socket().connect(new InetSocketAddress(host, port));
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String send(String req) {
        String result = "";
        try {
            ByteBuffer buffer = ByteBuffer.wrap(req.getBytes(StandardCharsets.UTF_8));
            socketChannel.write(buffer);
            ByteBuffer allocate = ByteBuffer.allocate(4096);

            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            iterator.next();

          //  boolean responce = false;
           // while (!responce) {
                socketChannel.read(allocate);
                allocate.clear();
                result = new String(allocate.array());
                //responce = true;
           // }

            if (req.trim().equals("bye and log transfer") || req.trim().equals("bye")) {
                socketChannel.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;

    }

}
