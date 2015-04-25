package mosaic;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reply {

    private Request request;
    protected static final DateFormat timestamp = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");

    public Reply(Request request) {
        this.request = request;
    }

    public void sendReply(OutputStream out, int log) {

        String replyString = dummyReply();
        try {
            out.write(replyString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Creates a dummy reply to test
    public String dummyReply() {

        StringBuilder sb = new StringBuilder();

        // Insert the headers to the string
        sb.append("HTTP/1.1 200 OK").append("\r\n");
        sb.append("Date: ").append(timestamp.format(new Date())).append("\r\n");
        sb.append("Server: Mosaic Web Server").append("\r\n");
        sb.append("Connection: Close").append("\r\n");
        sb.append("Content-Type: text/html; charset=UTF-8").append("\r\n");
        sb.append("\r\n");

        // Insert the data in Html format
        sb.append("<html><head><title>Dummy Response</title></head><body><h1>HttpServer Response</h1>");
        sb.append("Method: ").append(request.getMethod()).append("<br/>");
        sb.append("URI: ").append(request.getUri()).append("<br/>");
        sb.append("Protocol: ").append(request.getProtocol()).append("<br/>");
        sb.append("<img src=\"s.png\" class=\"img\" width=\"200\">");
        sb.append("</body></html>");

        return sb.toString();
    }
}
