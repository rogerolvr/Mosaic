package mosaic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class HttpServer {

    private String host;
    private int port;

    public HttpServer(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }

    public void run() {

        ServerSocket serverSocket = null;

        try {
            // Create the socket with the port informed in the constructor
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Start the sockets and wait for connections
        while (true) {

            System.out.println("\nWaiting for connections...");
            Socket socket;
            InputStream in;
            OutputStream out;

            try {

                // Get the streams from the socket
                socket = serverSocket.accept();
                in = socket.getInputStream();
                out = socket.getOutputStream();

                // Receive the browser's request and manipulate it
                Request request = new Request();
                request.parse(in);

                // Create the reply and send it back to the browser
                Reply reply = new Reply(request);
                String replyString = reply.dummyReply();
                out.write(replyString.getBytes());

                // Close the socket connection
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
