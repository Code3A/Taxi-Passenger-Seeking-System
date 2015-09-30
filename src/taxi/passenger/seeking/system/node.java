/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi.passenger.seeking.system;
import java.util.Random;
/**
 *
 * @author archit08jain
 */
public class node {
    
    double time,lat,lon;
    
    node(double t,double la,double lo)
    {
        time = t;
        lat = la;
        lon = lo;
    }
    node(String s)
    {
        String arr[] = s.split(" ");
        time = Double.parseDouble(arr[0]);
        lat = Double.parseDouble(arr[1]);
        lon = Double.parseDouble(arr[2]);
    }
    
    node (node d)
    {
        time = d.time;
        lat = d.lat;
        lon = d.lon;
    }

    @Override
    public String toString() {
        return "node{" + "time=" + time + ", lat=" + lat + ", lon=" + lon + '}';
    }
    
    public static double distance(node a,node b)
    {
        double result = 0.0;
        result = (a.time-b.time)*(a.time-b.time) + (a.lat-b.lat)*(a.lat-b.lat) + (a.lon-b.lon)*(a.lon-b.lon);
        result = Math.sqrt(result);
        return result;
    }
}
