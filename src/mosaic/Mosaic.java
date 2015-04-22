package mosaic;

public class Mosaic {

    public static void main(String[] args) {

        // Starts the server to Http requests.
        HttpServer mosaicServer = new HttpServer("localhost", 8000);
        mosaicServer.run();

    }
}
