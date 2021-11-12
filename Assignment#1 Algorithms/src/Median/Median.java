package Median;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;


public class Median {
	
	
	
	
	private static void swap(int[]arr, int i,int j)
	{
		int temp = arr[i] ;
		arr[i] = arr[j] ;
		arr[j] = temp ;
	}
	private static int partition(int arr[], int p, int r) {
		int pivot = arr[p];
		int pIndex = p;
		for (int i = p+1; i <= r; i++) {
			if (arr[i] <= pivot) {
				swap(arr,i, ++pIndex);
			}
		}
		swap(arr, pIndex, p);
		return pIndex;
	}
	private int Rand_partition(int[] arr,int p , int r)
	{
		int i = (int) ((Math.random() * (r - p)) + p) ;
		
		swap(arr,i,p) ;
		
		return partition(arr,p,r) ;
	}
	
	public int Rand_select(int[] arr , int p, int r , int i)
	{
		if(p==r)
			return arr[p] ;
		
		int q = Rand_partition(arr,p,r) ;
		int k = q - p + 1 ;
		
		if(i == k)
			return arr[q] ;
		else if(i<k)
			return Rand_select(arr,p,q-1,i);
		else 
			return Rand_select(arr,q+1,r,i-k) ;
	}
	public int Median_of_Medians(int[] arr , int p, int r , int i)
	{
		if(p==r)
			return arr[p] ;
		
		int q = Median_partition(arr,p,r) ;
		int k = q - p + 1 ;
		
		if(i == k)
			return arr[q] ;
		else if(i<k)
			return Median_of_Medians(arr,p,q-1,i);
		else 
			return Median_of_Medians(arr,q+1,r,i-k) ;
	}
	
	private int Median_partition(int[] arr, int p, int r) {
		int pivot = getPivotValue(arr,p,r);
		int index = 0 ;
		for(int i=p ;i<=r ;i++)
			if(arr[i] == pivot )
			{
				index = i ;
				break ;
			}
		
        swap(arr,index,p) ;
		
		return partition(arr,p,r) ;
		
	}
	private int getPivotValue(int[] arr, int p, int r) {
		if(arr.length <= 9)
		{
			Arrays.sort(arr);
			return arr[r-p / 2] ;
		}
		int temp[] = null ;
		int[] medians = new int[(int) Math.ceil((double) (r-p+1) / 2 )] ;
		int medianIndex = 0 ;
		while(r>=p)
		{
			temp = new int[Math.min(5, r-p+1)] ;
			for(int i=0 ;i<temp.length && p<=r ;i++)
				temp[i] = arr[p++] ;
			Arrays.sort(temp) ;
			medians[medianIndex++] = temp[temp.length / 2] ;
		}
		return getPivotValue(medians,0,medians.length-1) ;
	}
	public int naive(int[] arr)
	{
		Arrays.sort(arr) ;
		int m = arr.length / 2 ;
		
		return arr[m] ;
	}
	
	public static void sampleRun(int n)
	{
		int[] arr = new int[n];
		Median s = new Median() ;
		for(int i=0 ;i<n ;i++)
		{
			int r = n , p = 0 ;
			int x = (int) ((Math.random() * (r - p)) + p) ;
			arr[i] = x ;
		}
		
		
		System.out.println("Size : " + n);
		System.out.println("Randomize : ");
		long time1 = System.currentTimeMillis();		
		System.out.println("Answer : " + s.Rand_select(arr,0,arr.length-1,arr.length/2 + 1));
		System.out.println("Time : " + (System.currentTimeMillis() - time1)) ;
		System.out.println("********************************************") ;
		System.out.println("Median of Medians : ");
		 time1 = System.currentTimeMillis();		
		System.out.println("Answer : " +s.Median_of_Medians(arr,0,arr.length-1,arr.length/2 + 1));
		System.out.println("Time : " + (System.currentTimeMillis() - time1)  ) ;
		System.out.println("********************************************") ;
		System.out.println("Naiive : ");
		 time1 = System.currentTimeMillis();		
		System.out.println("Answer : " +s.naive(arr));
		System.out.println("Time : " + (System.currentTimeMillis() - time1) ) ;
	}


	public static void main(String[] args) {
		
		sampleRun(10000000);
		
		
		
		

	}

}
