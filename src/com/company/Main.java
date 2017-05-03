package com.company;

public class Main {

    public static void main(String[] args) {
	    NetworkADT networkADT = new NetworkADT("network.txt");
        networkADT.generateMST("Tampa");
        System.out.println("Test");

    }
}
