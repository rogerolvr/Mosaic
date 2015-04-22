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

    // Parse the entire stream in a string adding the number of each row.
    public void parse(InputStream is) {

        System.out.println("\nConnection established.\n \nRequest:");

        if (is != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[2048];

            try {
                Reader reader = new BufferedReader(new InputStreamReader(is));
                int i = reader.read(buffer);
                writer.write(buffer, 0, i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String input = writer.toString();

            BufferedReader br = new BufferedReader(new StringReader(input));
            String line = null;

            int lineNumber = 0;
            try {
                while ((line = br.readLine()) != null) {
                    System.out.println(lineNumber + " " + line);
                    if (lineNumber == 0) {
                        String[] values = line.split(" ");
                        if (values.length == 3) {
                            this.method = values[0];
                            this.uri = values[1];
                            this.protocol = values[2];
                        }
                    } else {
                    }
                    lineNumber++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

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
