/**
 * @author Tolak Maciej S20717
 */

package zad1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Server implements Runnable {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    LocalDateTime date;
    private boolean active = true;
    private String host;
    private int port;
    private StringBuilder log;
    private Map<java.lang.String, StringBuilder> logs;
    ServerSocketChannel serverSocketChannel;
    Thread thread;
    Selector selector;
    public Server(String host, int port) {
        this.host = host;
        this.port = port;
        log = new StringBuilder();
        logs = new HashMap<>();

    }

    public void startServer() {
        try {


            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(host, port));
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


            thread = new Thread(this);
            thread.start();

//            new Thread(() -> {
//                while (active) {
//                    try {
//                        selector.select();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    Set<SelectionKey> keys = selector.selectedKeys();
//                    Iterator<SelectionKey> iterator = keys.iterator();
//
//                    while (iterator.hasNext()) {
//                        SelectionKey myKey = iterator.next();
//
//                        if (myKey.isAcceptable()) {
//                            try {
//                                SocketChannel socketChannel = serverSocketChannel.accept();
//                                socketChannel.configureBlocking(false);
//                                socketChannel.register(selector, SelectionKey.OP_READ);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        if (myKey.isReadable()) {
//                            SocketChannel socketChannel = (SocketChannel) myKey.channel();
//
//                            try {
//
//                                ByteBuffer req = ByteBuffer.allocate(4096);
//                                socketChannel.read(req);
//                                String message = new String(req.array()).trim();
//                                req.clear();
//                                String nowPort = socketChannel.getRemoteAddress().toString().split(":")[1];
//
//                                if (!logs.containsKey(nowPort)) {
//                                    logs.put(nowPort, new StringBuilder());
//                                }
//
//                                if (logs.containsKey(nowPort)) {
//                                    logs.get(nowPort).append(log(message, nowPort));
//                                }
//
//
//                                ByteBuffer response = ByteBuffer.wrap(response(message, nowPort).getBytes("UTF-8"));
//                                socketChannel.write(response);
//                                response.flip();
//
//                                if (message.equals("bye and log transfer") || message.equals("bye")) {
//                                    socketChannel.close();
//
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        iterator.remove();
//                    }
//                }
//            }).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void stopServer() {
        active = false;
        try{
            serverSocketChannel.close();
            thread.interrupt();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getServerLog() {
        return log.toString();
    }

    private String log(java.lang.String string, java.lang.String port) {
        String out = "";
        boolean isgood = true;
        while (isgood) {

            date = LocalDateTime.now();
            java.lang.String user;

            if (string.equals("bye and log transfer")) {
                user = logs.get(port).toString().split("\n")[0].split("\\s")[1];
                out = "\n" + "logged out" + "\n" + "=== " + user + " log end ===" + "\n";

                log.append(user + " logged out at " + dateFormat.format(date) + "\n");

            } else if (string.contains("login")) {
                user = string.split("\\s")[1];
                out = "=== " + user + " log start ===" + "\n" + "logged in";

                log.append(user + " logged in at " + dateFormat.format(date) + "\n");
            } else {
                user = logs.get(port).toString().split("\n")[0].split("\\s")[1];
                java.lang.String[] dates = string.split("\\s");
                out = "\n" + "Request: " + string + "\n" + "Result:" + "\n" + Time.passed(dates[0], dates[1]);

                log.append(user + " request at " + dateFormat.format(date) + ": \"" + string + "\"" + "\n");
            }
            isgood = false;
        }

        return out;


    }

    private java.lang.String response(java.lang.String string, java.lang.String port) {
        String out = "";

        if (string.equals("bye and log transfer")) {
            out = logs.get(port).toString();
        } else if (string.equals("bye")) {
            out = "";
        } else {
            java.lang.String[] dates = string.split("\\s");
            out = "\n" + Time.passed(dates[0], dates[1]);
        }

        return out;
    }

    @Override
    public void run() {
        while (active) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey myKey = iterator.next();

                if (myKey.isAcceptable()) {
                    try {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (myKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) myKey.channel();

                    try {

                        ByteBuffer req = ByteBuffer.allocate(4096);
                        socketChannel.read(req);
                        String message = new String(req.array()).trim();
                        req.clear();
                        String nowPort = socketChannel.getRemoteAddress().toString().split(":")[1];

                        if (!logs.containsKey(nowPort)) {
                            logs.put(nowPort, new StringBuilder());
                        }

                        if (logs.containsKey(nowPort)) {
                            logs.get(nowPort).append(log(message, nowPort));
                        }


                        ByteBuffer response = ByteBuffer.wrap(response(message, nowPort).getBytes("UTF-8"));
                        socketChannel.write(response);
                        response.flip();

                        if (message.equals("bye and log transfer") || message.equals("bye")) {
                            socketChannel.close();

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                iterator.remove();
            }
        }
    }
}
