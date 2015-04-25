package mosaic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private final String host;
    private final int port;

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
                request.getRequest(in , 1);
                
                // Create the reply and send it back to the browser
                Reply reply = new Reply(request);
                reply.sendReply(out , 0);

                // Close the socket connection
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
