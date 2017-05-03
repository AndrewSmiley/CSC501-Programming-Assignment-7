package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by andrewsmiley on 5/2/17.
 */
public class NetworkADT {

    String[] cities; // this is our array containing all of or verticies
    int[][] network; //this is our 2D array containing all of our edges, network[i][j] contains the path from cities[i] to cities[j]
    int size; //this stores the number of nodes in currently in our network
    int capacity; //this one stores our current capacity. Only using this for array doubling

    /**
     * Default no arg constructor default capacity to 8 and size to 0
     */
    public NetworkADT() {
        capacity = 8;
        cities = new String[capacity];
        network = new int[capacity][capacity];
        size=0;
    }

    /**
     * This constructor takes a filename argument and will fill our edges and verticies with values from a text file
     * based upon the data in the given filename
     * @param filename the filename containing our data
     */
    public NetworkADT(String filename) {
        load(filename);
    }

    /**
     * This function lets us add a verticy to our network, array doubling where needed
     * @param city the verticy we want to add
     */
    public void addCity(String city){
        //if the size is equal to the capacity we need to transfer everything to an array of double the capacity
        //these are our temporary arrays that we will become our member variables
        String[] tmpCities = new String[capacity*2];
        int[][] tmpNetwork = new int[capacity*2][capacity*2];
        //fill the 2d array with -1
        for(int[] array: tmpNetwork){
            Arrays.fill(array, -1);
        }
        if(size == capacity){
            ///iterate over our current network
            for(int i =0; i < size; i++){
                //transfer our current city
                tmpCities[i] = cities[i];
                //now iterate over our verticies and copy them over
                for(int j=0; j < size; j++){
                    tmpNetwork[i][j] = network[i][j];
                }

            }
            //update our members
            this.network = tmpNetwork;
            this.cities = tmpCities;
            this.capacity = capacity*2;
        }

        //now that all that crap is done, we can actually add our city
        //just find the index we need and then bubble up
        for(int i=0; i < size; i++){
            //if we don't need to move to the next index
            if(cities[i].compareTo(city) > 0){

                //this is dirty
                //store temporarily
//                String tmp = cities[i+1];
                //shift cities[i]
//                cities[i+1] = cities[i];
//                cities[i] = city;// store new city in its appropriate location
                String tmp = cities[i]; //starting city to shift
                String newTmp = null; //new city to shift
                for(int j =i;  j < size; j++){
                    newTmp=cities[j+1];
                    //shift cities[i]
                    cities[j+1] = tmp;
                    tmp = newTmp;
                }
                cities[i] = city; //do the reassignment
                break;
            }
        }
        size++;
    }

    /**
     * This funciton lets us add an edge to our network by getting adding cost to network[i][j] where i is the index
     * of city 1 and j is the index of city 2
     * @param city1 the starting verticie
     * @param city2 the ending verticie
     * @param cost the cost
     */
    public void addEdge(String city1, String city2, int cost){
        this.network[getIndex(city1)][getIndex(city2)]=cost;
    }

    /**
     * Delete an edge between two cities by settting network[i][j] to -1 where i is the index
     * of city 1 and j is the index of city 2
     * @param city1 the starting verticie
     * @param city2 the ending verticie
     */
    public void deleteEdge(String city1, String city2){
        this.network[getIndex(city1)][getIndex(city2)] =-1;
    }

    /**
     * This method will print our network in a table of sorts
     */
    public void print(){}

    public int getIndex(String city){
        //this is just an iterative search
        for(int i =0; i  < size; i++){
            if(cities[i].equalsIgnoreCase(city)){
                return i;
            }
        }

        return -1; //default return
    }

    /**
     * using Dijkstra’s algorithm, this method will determine the shortest path from city1 to city2 and output this path
     * @param city1 the starting verticie
     * @param city2 the ending verticie
     */
    public int shortestPath(String city1, String city2){
        return -1;
    }

    public void generateMST(String city){

    }

    public void dfs(String city){

    }
    public void rdfs(String city){

    }
    public void bfs(String city){
        //this is our queue because fuuuuuuuck java and it doesn't have a native queue implementation
        LinkedList<String> visited = new LinkedList<>();
        boolean[] visted = new boolean[size];



    }

    /**
     * This function will load data from a file and build our network around it
     * @param filename the filename containing our data
     */
    public void load(String filename){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            //reference for our line
            String line;
            int lineCount = 0;
            int verticies;
            int sublineCount = 0; //this is our subline count, just so we can track if we need to increment i (i keeps track of the 2D array index)
            int i=0; //keep track of our 2d index
            int j = 0;//keep track of our cities index
            while ((line = br.readLine()) != null) {

                if(lineCount == 0 ){
                    verticies = Integer.parseInt(line);
                    this.size = verticies; //update the size value
                    this.capacity = verticies;
                    //here we'll create our arrays
                    this.network = new int[verticies][verticies];
                    this.cities = new String[verticies];
                    lineCount++;
                }else if(lineCount > 11){
                    if(sublineCount > 11){
                        i++; //move to the next index of the network
                        sublineCount = 0; //reset the subline count
                        network[i][sublineCount] = Integer.parseInt(line); //add to our network
                        sublineCount++; //increment subline account
                        lineCount++;//increment the line count
                    }else{
                        network[i][sublineCount] = Integer.parseInt(line); //add to our network
                        sublineCount++; //increment our subline count
                        lineCount++; //increment our line count
                    }
                }else{
                    cities[j] = line; //assign the value
                    j++; //increment our cities index
                    lineCount++; //increment our total line count
                }
            }

            br.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void save(String filename ){

    }

}

