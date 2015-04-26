package mosaic;

public class Mosaic {

    public static void main(String[] args) {

        // Starts the server in Http mode.
        HttpServer mosaicServer = new HttpServer("localhost", 8000);
        mosaicServer.run();

    }
}
