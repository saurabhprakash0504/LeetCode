package com.searching;

public class MinimizeMaxDistanceOfAdjacentGasStations {

    public static void main(String[] args) {
        int stations[] = {1, 2, 3, 4, 5};
        int k = 2;

        MinimizeMaxDistanceOfAdjacentGasStations obj = new MinimizeMaxDistanceOfAdjacentGasStations();
        obj.minMaxDist(stations, k);
    }

    public double minMaxDist(int[] stations, int k) {
        // code here
        double l = 0;
        double h = 0;
        for (int i = 1; i < stations.length; i++) {
            h = Double.max(h, stations[i] - stations[i - 1]);
        }
        double ans = h;
        // while(l<=h){
        while (h - l > 1e-7) {
            double m = l + (h - l) / 2;
            if (isPossible(stations, k, m)) {
                ans = m;
                // h= m+1;
                h = m;
            } else {
                //  l=m-1;
                l = m;
            }
        }

        return ans;
    }

    boolean isPossible(int[] stations, int k, double m) {

        int station = 0;
        for (int i = 1; i < stations.length; i++) {
            if (stations[i] - stations[i - 1] >= m) {
                int count = (int) Math.floor((stations[i] - stations[i - 1]) / (m * 1.0));
                station = station + count;
               /* if(station >= k){
                    return true;
                }*/
            }
        }

        //   return false;
        return station <= k;

    }

}
