package mosaic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class Request {

    private String method;
    private String uri;
    private String protocol;

    public Request() {
    }

    public void getRequest(InputStream is, int log) {

        if (is != null) {
            
            if (log == 1) { System.out.println("\nConnection established.\n \nRequest:");}
                                    
            char[] buffer = new char[10240];
            String line = null;
            int lineNumber = 0;

            try {
                
                // Receive the request and transforms into a string
                Reader r = new BufferedReader(new InputStreamReader(is));
                int i = r.read(buffer);
                Writer writer = new StringWriter();
                writer.write(buffer, 0, i);
                String input = writer.toString();

                // Read the request(string)and split the information to be handle.
                BufferedReader br = new BufferedReader(new StringReader(input));
                while ((line = br.readLine()) != null) {
                    switch (lineNumber) {
                        case 0:
                            String[] values = line.split(" ");
                            this.method = values[0];
                            this.uri = values[1];
                            this.protocol = values[2];
                            break;
                    }
                    lineNumber++;

                    if (log == 1) { logRequest(line); }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Log all the requests received 
    public void logRequest(String requestLine) {
       
        System.out.println(requestLine);
        
        // Todo another ways of logs
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }
}