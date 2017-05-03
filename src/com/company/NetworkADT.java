package com.company;

import java.io.*;
import java.util.ArrayList;
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
    public void shortestPath(String city1, String city2){


//        return -1;
    }
//    public int getUnsettledCount(boolean unsettled[])
//    {
//        int count = 0;
//        for (int index = 0; index < unsettled.length; index++)
//        {
//            if (unsettled[index])
//            {
//                count++;
//            }
//        }
//        return count;
//    }
//    public void primsAlgorithm(int adjacencyMatrix[][])
//    {
//        int evaluationVertex;
//        for (int source = 1; source <= this.size; source++)
//        {
//            for (int destination = 1; destination <= this.size; destination++)
//            {
//                this.network[source][destination] = adjacencyMatrix[source][destination];
//            }
//        }
//
//        for (int index = 1; index <= numberofvertices; index++)
//        {
//            key[index] = INFINITE;
//        }
//        key[1] = 0;
//        unsettled[1] = true;
//        parent[1] = 1;
//
//        while (getUnsettledCount(unsettled) != 0)
//        {
//            evaluationVertex = getMimumKeyVertexFromUnsettled(unsettled);
//            unsettled[evaluationVertex] = false;
//            settled[evaluationVertex] = true;
//            evaluateNeighbours(evaluationVertex);
//        }
//    }
//
//    private int getMimumKeyVertexFromUnsettled(boolean[] unsettled2)
//    {
//        int min = Integer.MAX_VALUE;
//        int node = 0;
//        for (int vertex = 1; vertex <= numberofvertices; vertex++)
//        {
//            if (unsettled[vertex] == true && key[vertex] < min)
//            {
//                node = vertex;
//                min = key[vertex];
//            }
//        }
//        return node;
//    }
//
//    public void evaluateNeighbours(int evaluationVertex)
//    {
//
//        for (int destinationvertex = 1; destinationvertex <= numberofvertices; destinationvertex++)
//        {
//            if (settled[destinationvertex] == false)
//            {
//                if (adjacencyMatrix[evaluationVertex][destinationvertex] != INFINITE)
//                {
//                    if (adjacencyMatrix[evaluationVertex][destinationvertex] < key[destinationvertex])
//                    {
//                        key[destinationvertex] = adjacencyMatrix[evaluationVertex][destinationvertex];
//                        parent[destinationvertex] = evaluationVertex;
//                    }
//                    unsettled[destinationvertex] = true;
//                }
//            }
//        }
//    }
//
//    public void printMST()
//    {
//        System.out.println("SOURCE  : DESTINATION = WEIGHT");
//        for (int vertex = 2; vertex <= numberofvertices; vertex++)
//        {
//            System.out.println(parent[vertex] + "\t:\t" + vertex +"\t=\t"+ adjacencyMatrix[parent[vertex]][vertex]);
//        }
//    }

//     A utility function to find the vertex with minimum key
//     value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[])
    {

        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < size; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }
    /**
     * using Prim’s algorithm, generate the MST starting at
     city and output for each node in the Graph aside from city, which city it connects
     to, as well as the total cost of the MST
     * @param city
     */
    public void generateMST(String city){
        // Array to store constructed MST
//        int size= this.size;//getIndex(city);
        int parents[] = new int[size];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int [size];

        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[size];

        // Initialize all keys as INFINITE
        for (int i = 0; i < size; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0;//getIndex(city);
        // picked as first vertex
        parents[0] = getIndex(city);//-1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < size-1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < size; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (network[u][v] > 0 && mstSet[v] == false &&
                        network[u][v] <  key[v])
                {
                    parents[v]  = u;
                    key[v] = network[u][v];
                }
        }
//        printMST(parents);
        for (int i = 1; i < size; i++) {
            System.out.println("City "+cities[i]+" connects to  "+cities[parents[i]] );
//            System.out.println(parents[i] + " - " + i + "    " +
//                    network[i][parents[i]]);
        }


    }

//    /**
//     * This is just a utility function to
//     * @param parents
//     */
//    void printMST(int parents[])
//    {
//    }

    /**
     * This is our depth first search,
     * @param city
     */
    public void dfs(String city){
        //create an array tracking our visited values
        //set them all to false
        //using the Boolean wrapper class
        Boolean[] visted = new Boolean[size];
        Arrays.fill(visted, false);
        //this is our stack to store our visited city names
        ArrayList<String> stack = new ArrayList<>();
        int i= getIndex(city);
        int j;
        visted[i] =true;
        System.out.println(city);
        //basically we'll iterate while we have unvisted nodes
        do{
            //iterate the edges in reverse
            for( j =size-1; j> -1; j--){
                //do this in reverse, that way we do the depth first
                if(network[i][j] > 0 && visted[j] == false){
                    stack.add(cities[j]); //add to our visited stack
                }
            }
            //update our index with the top of the stack
            i = getIndex(stack.remove(stack.size()-1));//do the stack pop
            //by popping we'll slowly backtrack
            //now we'll do the backtracking piece as need be
            System.out.println(cities[i]);//output visited node
            visted[i]= true; //mark it as visited
        }while(Arrays.asList(visted).contains(false));

    }
    public void rdfs(String city){

    }

    /**
     * This is our bredth first search. Vist all adjacent nodes, backtracking and working across our network
     * @param city the starting vertice
     */
    public void bfs(String city){
        //this is our queue because fuuuuuuuck java and it doesn't have a native queue implementation
        //create an array tracking our visited values
        //set them all to false
        //using the Boolean wrapper class
        Boolean[] visted = new Boolean[size];
        Arrays.fill(visted, false);
        //this is our stack to store our visited city names
        ArrayList<String> queue = new ArrayList<>();
        int i= getIndex(city);
        int j;
        visted[i] =true;
        System.out.println(city);
        //basically we'll iterate while we have unvisted nodes
        do{
            //iterate the edges 
            for( j=0; j <size; j++ ){
                //get all the adjacent nodes
                if(network[i][j] > 0 && visted[j] == false){
                    queue.add(cities[j]); //add to our visited queue (enqueue
                }
            }
            //update our index with the first value in the queue
            i = getIndex(queue.remove(0));//do the dequeue
            //now we'll do the backtracking
            //by dequeuing we'll slowly backtrack until we reach a node we haven't visted yet
            while(visted[i]==true && queue.size() >0){
                i = getIndex(queue.remove(0));//do the dequeue
            }
            if (queue.size() >0) {
                System.out.println(cities[i]);//output visited node
                visted[i]= true; //mark it as visited
            }
        }while(!queue.isEmpty());

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
            int verticies = 0;
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
                }else if(lineCount > verticies){
                    if(sublineCount > verticies-1){
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

