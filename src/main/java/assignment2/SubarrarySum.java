package assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main
{
    private static<K, V> void insert(Map<K, List<V>> hashMap, K key, V value)
    {
        hashMap.putIfAbsent(key, new ArrayList<>());
        hashMap.get(key).add(value);
    }
    public static void printAllSubarrays(int[] A)
    {
        Map<Integer, List<Integer>> hashMap = new HashMap<>();

        insert(hashMap, 0, -1);

        int sum = 0;

        for (int i = 0; i < A.length; i++)
        {
            sum += A[i];

            if (hashMap.containsKey(sum))
            {
                List<Integer> list = hashMap.get(sum);

                // find all subarrays with the same sum
                for (Integer value: list)
                {
                    System.out.println("Subarray [" + (value + 1) + "…" +
                            i + "]");
                }
            }

            // insert (sum so far, current index) pair into the hashmap
            insert(hashMap, sum, i);
        }
    }

    public static void main (String[] args)
    {
        int[] A = {4, 2, 7, 6, -3, -1, -2, 42, 0, -42, 9, -4, 5, -5, -6, -7, -8, -99, 42, 11, 20, 1, 2, 3};

        //printAllSubarrays(A);
        //b2v1(A);
        c2v1(A);
    }

    public static void b2v1 (int [] B) {
        int sum = 0;
        StringBuilder SubString = new StringBuilder();

        for (int i = 0; i < B.length; i++) //Iterating first number
        {
            for (int j = i + 1; j < B.length; j++) {    //iterating the second number

                if(B[i] + B[j] == sum) {            // if the numbers in the array == 0, return 0.
                    SubString.append(B[i]+ "+" +B[j]+ " = "+ sum + "\n");
                }

            }
        }
        System.out.println("The elements in the array equal sum zero: "+ SubString);
    }


    public static void c2v1 (int [] numbers) {
        int smallest = numbers[0];
        int biggest = numbers[0];
        int smallestPos = 0;
        int biggestPos = 0;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > biggest) {
                biggest = numbers[i];
            biggestPos = i;
        }
            else if (numbers[i] < smallest) {
                smallest = numbers[i];
                smallestPos = i; }
        }


    System.out.println("The smallest number is: " + smallest +" and location in array is: " +smallestPos);
    System.out.println("The biggest number is: " + biggest +" and location in array is: " +biggestPos);
    }
}