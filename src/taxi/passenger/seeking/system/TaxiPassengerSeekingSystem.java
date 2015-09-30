/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi.passenger.seeking.system;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author archit08jain
 */
public class TaxiPassengerSeekingSystem {

    /**
     * @param args the command line arguments
     */
      public static void main(String args[]) throws IOException
    {
     
        Scanner scan = new Scanner(new File("Data.txt"));
        node[] data = new node[20000];
        
        int k = 0;
        while(scan.hasNext())
        {
            String gData = scan.nextLine();
           // System.out.println(gData);
            data[k++] = new node(gData);
        }
        scan.close();
        
        KMeans cluster = new KMeans(data,5);
        
    }
}
