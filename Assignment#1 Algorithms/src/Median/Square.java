package Median;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;



public class Square {
	
	private static int sortX(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p[0]));
        int mx = 0;
        for (int i = 1; i < points.length; ++i) {
            mx = Math.max(mx, points[i][0] - points[i - 1][0]);
        }
        return mx;
    }
	
	private static int sortY(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p[1]));
        int mx = 0;
        for (int i = 1; i < points.length; ++i) {
            mx = Math.max(mx, points[i][1] - points[i - 1][1]);
        }
        return mx;
    }
	
	
	
	
	
	private static  int bruteForce(int[][] P , int n)
	{
		int min =  2147483647 ;
		if(n==1)
			return min ;
	    for (int i = 0; i < n; ++i)
	        for (int j = i+1; j < n; ++j)
	            if (dist(P[i], P[j]) < min)
	                min = dist(P[i], P[j]);
	    return min;
	}
	
	private static int closest(int[][] points, int n)
	{
		if(n <= 3)
			return bruteForce(points,n);
		int mid = n/2 ;
		int[] point = points[mid] ;
		
		
		int[][] p1 = java.util.Arrays.copyOfRange(points, 0, mid);
		int[][] p2 = java.util.Arrays.copyOfRange(points, mid+1, points.length);
		
		int dl = closest(p1,mid) ;
		int dr = closest(p2 ,n-mid-1);
		
		int d = Math.min(dl,dr) ;
		
		int[][] stripes = new int[n][2];
		int j=0 ;
	    for (int i = 0; i < n; i++)
	        if (point[0]-points[i][0] <= d)
	        	stripes[j++] = points[i] ;
	    
	 
	    return Math.min(d, closestStripes(stripes,j,d));
		
	}
	private static int closestStripes(int[][] P, int n,int d) {
		for (int i = 0; i < n; ++i)
	        for (int j = i+1; j < n; ++j)
	            if (dist(P[i], P[j]) < d)
	                d = dist(P[i], P[j]);
		
		return d ;
	}

	private static int dist(int[] p1, int[] p2)
	{
		return Math.max(Math.abs(p1[0]-p2[0]),Math.abs(p1[1]-p2[1]));
	}
	
	public static int closestPair(int[][] points,int n)
	{
		sortX(points) ;
		return closest(points,n) ;
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);  
		System.out.print("Enter a path: ");  
		String path= sc.nextLine();            
		String pathname = path + "_18011105_output.txt" ;
		File file = new File(pathname);
		 FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int[][] points = null ;
		try {
			 File myObj = new File(path);
			 Scanner myReader = new Scanner(myObj);
			 while (myReader.hasNextLine()) {
			 String data = myReader.nextLine();
			 String arr[] = data.split(" ");
			 int k=0 ;
			 if (arr.length == 1) {
			 int cnt = Integer.valueOf(arr[0]);
			 points = new int[cnt][2] ;
			 while (cnt > 0) {
			 String line = myReader.nextLine();
			 String arr2[] = line.split(" ");
			 points[k][0] = Integer.valueOf(arr2[0]) ;
			 points[k][1] = Integer.valueOf(arr2[1]) ;
			 k++ ;
			 cnt--; 
			 }
			 }
			 try {
	             int x = closestPair(points,points.length) ;
	             System.out.println(x);
	             fileWriter.write(x + "\n");
	            	             System.out.println("Successfully wrote to the file.");
	           } catch (IOException e) {
	             System.out.println("An error occurred.");
	             e.printStackTrace();
	           }
			 }
			 myReader.close();
			 } catch (FileNotFoundException e) {
			 System.out.println("An error occurred.");
			 e.printStackTrace();
			 }
		try {
			fileWriter.close() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
