package assignment2;

import java.util.*;

public class Assignment2 {
    public static void main(String[] args) {
        int[] numbers = {4,2,7,6,-3,-1,-2,42,0,-42,9,-4,5,-5,-6,-7,-8,-99,42,11,20,1,2,3};

        //Task 2A version 1
        System.out.println(a2(numbers));

        //Task 2A version 2
        a2v2(numbers);


    }

    //Consecutive subStrings that adds up to zero
    public static ArrayList<String> a2(int[] numbers) {
        ArrayList<String> validConsecutiveSubStrings = new ArrayList<>();
        Iterator<Integer> numberList = Arrays.stream(numbers).iterator();
        int i = 0;
        while (numberList.hasNext()) {
            int start = numberList.next();
            int sum = start;
            StringBuilder consecutiveSubString = new StringBuilder(start + " ");
            Iterator<Integer> remaining = Arrays.stream(numbers).iterator();
            i++;
            for (int j = 1; j <= i; j++) {
                remaining.next();
            }

            while (remaining.hasNext()) {
                if (sum == 0) {
                    validConsecutiveSubStrings.add(consecutiveSubString.toString());
                }
                int next = remaining.next();
                sum += next;
                consecutiveSubString.append(next).append(" ");
            }
        }

        return validConsecutiveSubStrings;
    }

    public static void a2v2(int[] A)
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

    private static<K, V> void insert(Map<K, List<V>> hashMap, K key, V value)
    {
        hashMap.putIfAbsent(key, new ArrayList<>());
        hashMap.get(key).add(value);
    }
}
