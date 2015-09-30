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
        
        //loading dataset
        Scanner scan = new Scanner(new File("Data.txt"));
        node[] data = new node[20000];
        
        int k = 0;
        while(scan.hasNext())
        {
            String gData = scan.nextLine();
            data[k++] = new node(gData);
        }
        scan.close();
        
       //data loaded
        
        PredictLocation predictor = new PredictLocation(757866,421176,0,1000,data);
        node[] result = predictor.getClusters();
        int cnt = predictor.getClusterCount();
        
       //KMeans c = new KMeans(data,5,KMeans.MAX_DATASET_SIZE);
        
        System.out.println(cnt);
        for(int i = 0;i<cnt;i++)
            System.out.println(result[i]);
        
    }
}
