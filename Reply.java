package mosaic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reply {

    private final Request request;
    protected static final DateFormat timestamp = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");

    public Reply(Request request) {
        this.request = request;
    }

    // Main method that process the reply based on the request received
    public void sendReply(OutputStream out, int log) {

        File file;
        byte[] reply = null;

        // Check if the file requested exists
        if (request.getUri().equals("/")) {
            file = new File(HttpServer.root + "/index.html");
        } else {
            file = new File(HttpServer.root + request.getUri());
        }

        if (file.exists()) {
            reply = buildReply(200, file);
        } else {
            // If the path is unavailable send the 404 page.
            file = new File(HttpServer.root + "/404.html");
            reply = buildReply(404, file);
        }

        // Send the reply to the client
        try {
            out.write(reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Build the reply and return the array of bytes to be sent
    public byte[] buildReply(int status, File file) {

        byte[] buffer = new byte[(int) file.length()];
        byte[] header = new byte[(int) file.length()];
        byte[] reply = null;
        InputStream fileIn;
        
        // Get the headers with the right status
        switch (status) {
            case 200:
                header = getHeaders(200).getBytes();
                break;

            case 404:
                header = getHeaders(404).getBytes();
                break;
        }

        // Get the bytes of the file to be added into the reply
        try {
            fileIn = new FileInputStream(file);

            try {
                fileIn.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Mount the reply, putting togheter the headers and the file
        reply = new byte[buffer.length + header.length];
        System.arraycopy(header, 0, reply, 0, header.length);
        System.arraycopy(buffer, 0, reply, header.length, buffer.length);
        return reply;
    }

    //Create the headers for the reply
    public String getHeaders(int status) {

        StringBuilder headers = new StringBuilder();

        // Set the reply status based on status of each request 
        switch (status) {
            case 200:
                headers.append("HTTP/1.1 200 OK").append("\r\n");
                break;
            case 404:
                headers.append("HTTP/1.1 404 Not found").append("\r\n");
                break;

        }
        headers.append("Date: ").append(timestamp.format(new Date())).append("\r\n");
        headers.append("Server: Mosaic Web Server").append("\r\n");
        headers.append("Connection: Close").append("\r\n");
        headers.append("Content-Type: text/html; charset=UTF-8").append("\r\n");
        headers.append("\r\n");

        return headers.toString();
    }
}
