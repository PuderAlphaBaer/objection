package pgdp.pengunums;
import java.util.*;
import java.util.Arrays;

public class PenguNums {

	/**
	 * Gets the n-th penguin number for positive n
	 * 
	 * @param n the index of the penguin number, positive including zero
	 * @return n-th penguin number
	 */
	public static long penguNumPositive(int n) {
		if (n==0) {
			return 0;
		}
		if (n==1) {
			return 1;
		}
		if (n==2) {
			return 1;
		}
		if (n>=3) {
			long[] array = new long[n+1];
			array[0] = 0;
			array[1] = 1;
			array[2] = 1;
			int i =3;
			while (i<=n) {
				array[i]=array[i-1] + array[i-2] + array[i-3];
				++i;
			}
			return array[n];
		}
		return 0;
	}

	/**
	 * Gets the n-th penguin number for negative n
	 * 
	 * @param n the index of the penguin number, strictly negative
	 * @return n-th penguin number
	 */
	public static long penguNumNegative(int n) {
	    if (n%2==-1) {
			return penguNumPositive(-n);
		} if (n%2==0) {
			return -penguNumPositive(-n);
		}
		return 0;
	}

	/**
	 * Gets the n-th penguin number
	 * 
	 * @param n the index of the penguin number
	 * @return n-th penguin number
	 */
	public static long penguNum(int n) {
	    if (n<0){
			return penguNumNegative(n);
		} else {
			return penguNumPositive(n);
		}
	}



	//basiert auf https://www.geeksforgeeks.org/find-the-index-of-an-array-element-in-java/
	public static int findIndex(long arr[], long t) 
    {
        // if array is Null 
        if (arr == null) { 
            return -1; 
        } 
  
        // find length of array 
        long len = arr.length; 
        int i = 0; 
  
        // traverse in the array 
        while (i < len) { 
  
            // if the i-th element is t 
            // then return the index 
            if (arr[i] == t) { 
                return i; 
            } 
            else { 
                i = i + 1; 
            } 
        } 
        return -1; 
    } 

	/**
	 * Gets the index of the given penguin number
	 * 
	 * @param n the penguin number
	 * @return the index of the penguin number
	 */
	public static int penguNumIndex(long n) {
		long longus[] = new long[2147483647];
		for (int i = 0; i<2147483647; i++) {
			longus[i] = penguNumPositive(i);
		}
	    if (findIndex(longus, n)!=-1) {
			return findIndex(longus, n);
		}
		if (findIndex(longus, -n)!=-1) {
			return findIndex(longus, n);
		}
		return 0;
	}

	/**
	 * Gets if n is a penguin number for positive n
	 * 
	 * @param n the number that should be checked, positive including zero
	 * @return true if n is penguin number, false otherwise
	 */
	public static boolean isPenguNumPositive(long n) {
		// TODO
		return false;
	}

	/**
	 * Gets if n is a penguin number
	 * 
	 * @param n the number that should be checked
	 * @return true if n is penguin number, false otherwise
	 */
	public static boolean isPenguNum(long n) {
		// TODO
		return false;
	}

	public static void main(String[] args) {
		// System.out.println(penguNumPositive(0));
		// System.out.println(penguNumPositive(1));
		// System.out.println(penguNumPositive(2));
		// System.out.println(penguNumPositive(3));
		// System.out.println(penguNumPositive(4));
		// System.out.println(penguNumPositive(7));
		// System.out.println(penguNumNegative(-7));
		// System.out.println(penguNumNegative(-6));
		// System.out.println(penguNumNegative(-5));
		// System.out.println(penguNumNegative(-4));
		// System.out.println(penguNumNegative(-3));
		// System.out.println(penguNumNegative(-2));
		// System.out.println(penguNumNegative(-1));
		// System.out.println(penguNum(0));
		// System.out.println(penguNum(1));
		// System.out.println(penguNum(2));
		// System.out.println(penguNum(3));
		// System.out.println(penguNum(4));
		// System.out.println(penguNum(7));
		// System.out.println(penguNum(-7));
		// System.out.println(penguNum(-6));
		// System.out.println(penguNum(-5));
		// System.out.println(penguNum(-4));
		// System.out.println(penguNum(-3));
		// System.out.println(penguNum(-2));
		// System.out.println(penguNum(-1));
		// System.out.println(penguNumIndex(penguNum(Integer.MAX_VALUE)));
		System.out.println(penguNumIndex(penguNum(234)));
	}

}