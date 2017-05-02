package com.company;

/**
 * Created by andrewsmiley on 5/2/17.
 */
public class NetworkADT {

    String[] cities;
    int[][] network;
    int size;

    public NetworkADT() {
        cities = new String[8];
        network = new int[8][8];
        size=0;
    }

    public NetworkADT(String filename) {

    }


}

