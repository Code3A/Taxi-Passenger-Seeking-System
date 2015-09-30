/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi.passenger.seeking.system;
import java.util.*;
import java.io.*;
/**
 *
 * @author archit08jain
 */
public class KMeans {
    
    public static final int MAX_DATASET_SIZE = 20000;
    private int clusters,samples,features;
    node[] clusterCentroid,dataset;
    Integer[] clusterDensity,clusterNumber; 

    KMeans(node data[],int clusters)
    {
        this.clusters = clusters;
        this.features = 3;
        this.samples = MAX_DATASET_SIZE;
        dataset = data;
        init();
        start(20,100);
    }
    private void init()
    {
        Random rand = new Random();
        clusterCentroid = new node[clusters];
        for(int i = 0;i<clusters;i++)
            clusterCentroid[i] = new node(dataset[rand.nextInt(samples)]);
        clusterDensity = new Integer[clusters];
        clusterNumber = new Integer[samples];
    }
    
    private void randomInitCluster()
    {
         Random rand = new Random();
         for(int i = 0;i<clusters;i++)
            clusterCentroid[i] = dataset[rand.nextInt(samples)];
    }
    private void assignClusters()
    {
        for(int i = 0;i<clusters;i++)
            clusterDensity[i] = 0;
        for(int i = 0;i<samples;i++)
        {
            double minimum = node.distance(dataset[i], clusterCentroid[0]);
            int pos = 0;
            
            for(int j = 1;j<clusters;j++)
            {
                double dist = node.distance(dataset[i],clusterCentroid[j]);
                if(dist<minimum)
                {
                    minimum = dist;
                    pos = j;
                }
            }
             clusterNumber[i] = pos;
             clusterDensity[pos]++;
        }
    }
    
    private double cost()
    {
        double c = 0.0;
        for(int i = 0;i<samples;i++)
        {
           double temp = node.distance(clusterCentroid[clusterNumber[i]],dataset[i])/10000;
           double v = (temp*temp);
           c += v;
        }
        return c;
    }
    
    void moveClusters()
    {
        //Evaluate Mean
        node[] mean = new node[clusters];
        for(int i = 0;i<clusters;i++)
            mean[i] = new node(0,0,0);
        for(int i = 0;i<samples;i++)
        {
            int k = clusterNumber[i];
            mean[k].time += dataset[i].time;
            mean[k].lat += dataset[i].lat;
            mean[k].lon += dataset[i].lon;
        }
        for(int i = 0;i<clusters;i++)
        {
            clusterCentroid[i].time = mean[i].time/clusterDensity[i];
            clusterCentroid[i].lat = mean[i].lat/clusterDensity[i];
            clusterCentroid[i].lon = mean[i].lon/clusterDensity[i];
        }
    }
    private void start(int outerIterations,int innerIterations)
    {
         node[] result = new node[clusters];
         
         double minimumCost = 1e12; //INF
         
         for(int i = 0;i<outerIterations;i++)
         {
             randomInitCluster();
             assignClusters();
             //print();
             for(int j = 0;j<innerIterations;j++)
             {
                 moveClusters();
                 assignClusters();
                 
             }
             for(int j = 0;j<clusters;j++)
                 result[j] = new node(clusterCentroid[j]);
             
             double effectiveCost = cost();
             System.out.println(effectiveCost);
             
             if(minimumCost<effectiveCost)
             {
                 minimumCost = effectiveCost;
                 result = clusterCentroid;
             }
         }
         clusterCentroid = result;
    }  

    
}
