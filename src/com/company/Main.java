package com.company;

public class Main {

    public static void main(String[] args) {
	    NetworkADT networkADT = new NetworkADT("network.txt");
        networkADT.generateMST("Boston");
        System.out.println("Test");

    }
}
