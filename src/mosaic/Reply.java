package mosaic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reply {

    private Request request;
    protected static final DateFormat HTTP_DATE_FORMAT = new SimpleDateFormat( "EEE, dd MMM yyyy HH:mm:ss z");

    public Reply(Request request) {
        this.request = request;
    }
    
    // Creates a dummy reply to test
    public String dummyReply() {
        
        StringBuilder sb = new StringBuilder();
        
        // Insert the headers to the string
        sb.append("HTTP/1.1 200 OK").append("\r\n");
        sb.append("Date: ").append(HTTP_DATE_FORMAT.format(new Date())).append("\r\n");
        sb.append("Server: Mosaic Web Server").append("\r\n");
        sb.append("Connection: Close").append("\r\n");
        sb.append("Content-Type: text/html; charset=UTF-8").append("\r\n");
        sb.append("\r\n");

        // Insert the data in Html format
        sb.append("<html><head><title>Dummy Response</title></head><body><h1>HttpServer Response</h1>");
        sb.append("Method: ").append(request.getMethod()).append("<br/>");
        sb.append("URI: ").append(request.getUri()).append("<br/>");
        sb.append("Protocol: ").append(request.getProtocol()).append("<br/>");
        sb.append("</body></html>");
        
        return sb.toString();
    }
}
