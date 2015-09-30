/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi.passenger.seeking.system;

/**
 *
 * @author archit08jain
 */
public class PredictLocation {
    
    private double lat,lon,currTime;
    private double timeSlot; // in Minutes
    private node[] dataset;
    private node[] window;
    private int samples;
    
    private int clusters,maxClusters;
    private node[] clusterCentroids;
    
    PredictLocation(double lat,double lon,double curr,double timeSlot,node[] data)
    {
        this.lat = lat;
        this.lon = lon;
        this.currTime = curr;
        this.timeSlot = timeSlot;
        this.dataset = data;
        clusters = 1;
        maxClusters = 20;
        window = new node[KMeans.MAX_DATASET_SIZE];
        samples = 0;
        fillWindow();
        start();
        //displayWindow();
    }
    
    private void fillWindow()
    {
        for(int i = 0;i<KMeans.MAX_DATASET_SIZE;i++)
        {
            if(dataset[i].time>=currTime && dataset[i].time<=currTime + timeSlot)
                window[samples++] = dataset[i]; 
        }
    }
    
     void displayWindow()
    {
        for(int i = 0;i<samples;i++)
            System.out.println(window[i]);
    }
    
    void start()
    {
        double minimum = 1e12;
        node[] result = null;
        for(int i = 1;i<maxClusters;i++)
        {
            KMeans classifier = new KMeans(window,i,samples);
            double cost = classifier.cost();
            
            System.out.println(cost);
            if(cost<minimum)
            {
                minimum = cost;
                clusters = i;
                result = classifier.clusterCentroids();
            }
        }
       clusterCentroids = result;
    }
    
    node[]  getClusters()
    {
        return clusterCentroids;
    }
    int getClusterCount()
    {
        return clusters;
    }
}
