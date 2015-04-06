/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosaic;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author rogerolvr
 */
public class Mosaic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9094);
            Socket s = ss.accept();
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();
            int nTrocas = 10;
            int valor = 0;

            do {
                valor = in.read();
                System.out.print(valor);
                Thread.sleep((int)(Math.random() * 1000));
                out.write(valor + 1);
                out.flush();
            } while (--nTrocas > 0);

            out.close();
            in.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
 
}
