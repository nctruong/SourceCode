package hackerrank.com;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void sort(int a[]){
        int n = a.length;
        int numberOfSwaps = 0;
        for (int i = 0; i < n; i++) {
            

            for (int j = 0; j < n - 1; j++) {
                if (a[j] > a[j + 1]) {
                    //swap(a[j], a[j + 1]);
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    numberOfSwaps++;
                }
            }

            if (numberOfSwaps == 0) {
                break;
            }
            
        }
       
        System.out.println("Array is sorted in "+numberOfSwaps+" swaps.");
        System.out.println("First Element: "+a[0]);
        System.out.println("Last Element: "+a[n-1]);
    }
    

    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        /*for (int i = 0; i < n; i++){
        String arr = in.next();
        
        String a[] = arr.split(" ");
        }*/
        //if (a.length != n){
          //  throw new Exception("Number of element is invalid");
       // }
        
        int b[] = new int[n];
        for (int i = 0; i < n; i++){
            b[i] = in.nextInt();
        }
       
        sort(b);
    }
}