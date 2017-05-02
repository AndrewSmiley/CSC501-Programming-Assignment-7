/* the purpose of this program is to demonstrate menu-driven code and to input and output to/from disk files */
package com.company;
import java.io.*;
import java.util.Scanner;

public class MenuDrivenProgram
{
    public static void main(String[] args)
    {
        int[] array=null;									// this is a simple program that inputs a list of numbers from a disk file, lets the user do various calculations and insertions/deletions, and save the file
        int choice;											// user's input choice from the menu, 1-9 are legal choices (9 means quit)
        Scanner keyboard=new Scanner(System.in);			// used to get any form of user input (menu choice, or data)
        choice=getUserChoice(keyboard);						// getUserChoice is a method that displays a menu of choices, gets the user's choice, verifies its legal, and returns that value
        while(choice!=9)									// repeat while the user does not want to quit
        {
            switch(choice) {								// for each choice, call the appropriate method, output results if needed
                case 1:array=load(array); break;
                case 2:array=insert(array,keyboard); break;
                case 3:System.out.println("Sum = " + getSum(array)); break;
                case 4:System.out.printf("Average = %6.2f\n", getAverage(array)); break;
                case 5:System.out.printf("Std Dev = %6.2f\n", getStd(array)); break;
                case 6:array=delete(array,keyboard); break;
                case 7:getCount(array,keyboard); break;
                case 8:save(array); break;
            }
            choice=getUserChoice(keyboard);					// get the next choice
        }
    }

    // method to display the menu and obtain the user's choice, verifying that its legal (menu not described here in comments but it should be)
    public static int getUserChoice(Scanner in)
    {
        int temp;
        System.out.println("User choices\n1.  Input values from disk\n2.  Add new value to list\n3.  Sum the values\n4.  Compute the average\n5.  Compute the standard deviation\n6.  Delete a value\n7.  Count occurrences\n8.  Save values to disk\n9.  Quit");
        do {
            System.out.print("Enter your choice:  ");
            temp=in.nextInt();
        }while(temp<1||temp>9);
        return temp;
    }

    public static int[] load(int[] array)					// input from disk file numbers.txt the values and insert them into an array of 10000, don't allow more than 10000, then copy the array into an array of the exact size, returning that smaller array
    {
        int[] temp=new int[10000];
        int i=0;											// the index of the array location/value we have input
        try													// need to handle IOExceptions
        {
            Scanner file=new Scanner(new File("numbers.txt"));		// open input file
            while(file.hasNext()&&i<10000)					// while more input and size in array
                temp[i++]=file.nextInt();					// get next value from file, increment array index to indicate another successful input
            file.close();									// close the file when done
            if(i>10000) System.out.println("Array full, cannot input more numbers");	// error message if disk file contains more than 10,000 elements
            array=new int[i];								// reduce the array size (note:  to be more efficient, we should do this if i<10000, not needed if i==10000)
            for(int j=0;j<i;j++)
                array[j]=temp[j];							// copy larger array into the smaller array
        }
        catch(IOException e) {								// exception handling - just output the exception message
            System.out.println(e);
        }
        return array;										// return the reduced array
    }

    public static void save(int[] array)					// method to output the array to the same disk file
    {
        try {												// need to handle IOException
            PrintWriter out=new PrintWriter(new File("numbers.txt"));	// open output file
            for(int i=0;i<array.length;i++)					// output each element of the array to the output file, one entry per line
                out.println(array[i]);
            out.close();									// close the file
        }
        catch(IOException e) {								// exception handling - just output the exception message
            System.out.println(e);
        }
    }

    public static int[] insert(int[] array, Scanner keyboard)	// insert a new datum from the user into the array, increase array size by 1 (very inefficient!)
    {
        int[] temp=new int[array.length+1];					// create a new array, 1 greater than current array
        for(int i=0;i<array.length;i++) temp[i]=array[i];	// copy contents of old array into new
        System.out.print("Enter the new value:  ");			// prompt user for new value
        temp[array.length]=keyboard.nextInt();				// insert new value into new array at the end (no shifting needed)
        return temp;										// return the new array which is 1 greater in size (again, very inefficient handling the array this way)
    }

    public static int getCount(int[] array, Scanner keyboard)	// given a value from the user, count the number of times it appears in the array
    {
        System.out.print("Enter the value to search for:  ");	// prompt user for number
        int value=keyboard.nextInt();
        int count=0;										// our counter variable
        for(int i=0;i<array.length;i++)						// go through the array looking for value and add 1 to count for each occurrence
            if(array[i]==value) count++;
        return count;										// return number of occurrences as counted
    }

    public static int[] delete(int[] array, Scanner keyboard)	// obtain a number from the user and delete the first occurrence from the array, take last value and copy it into vacated location, reduce the array
    {
        System.out.print("Enter value to delete:  ");		// prompt user for the datum
        int value=keyboard.nextInt();
        for(int i=0;i<array.length;i++)						// search while we have yet to reach the end of the array and have not found the number yet (the return statement causes us to leave the method)
            if(array[i]==value)
            {
                array[i]=array[array.length-1];				// copy last array element in place of the found number
                int[] temp=new int[array.length-1];			// create new array 1 item smaller and copy old array into new
                for(int j=0;j<array.length-1;j++)
                    temp[j]=array[j];
                return temp;								// return new array which is 1 item smaller (the deleted item)
            }
//        if(!done) System.out.println("Error, " + value + " not found!");	// output error message since item not found
        return array;										// return the original array
    }

    public static int getSum(int[] array)					// compute and return the sum of the elements in the array
    {
        int temp=0;											// temp is our sum
        for(int i=0;i<array.length;i++)						// iterate through the array, adding each element to temp
            temp+=array[i];
        return temp;										// return the computed sum
    }

    public static double getAverage(int[] array)			// compute and return the average (as a double) of the numbers in the array
    {
        int sum=getSum(array);								// get the sum from getSum
        return (double)sum/array.length;					// return the average as a double
    }

    public static double getStd(int[] array)				// compute and return the standard deviation of the numbers in the array
    {
        double avg=getAverage(array);						// get the average
        double sum=0.0;
        for(int i=0;i<array.length;i++)						// for each value in the array, compute (array[i]-avg)^2 and add this to the sum
        {
            sum+=Math.pow((array[i]-avg),2);
        }
        return Math.sqrt(sum);								// std is the sqrt of the sum, return this value
    }
}