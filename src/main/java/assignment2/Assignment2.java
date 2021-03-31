package assignment2;

import java.util.*;


public class Assignment2 {
    static PriorityQueue<Element> Scores = new PriorityQueue<>();
    static int[] numbers = {4,2,7,6,-3,-1,-2,42,0,-42,9,-4,5,-5,-6,-7,-8,-99,42,11,20,1,2,3};

    public static void main(String[] args) {

        //Task 2A version 1
        System.out.println("------Task 2A------");
        System.out.println(a2(numbers, 0));
        System.out.println(" ");

        //Task 2A version 2
        System.out.println("------Task 2A v2---");
        a2v2(numbers);
        System.out.println(" ");

        //Task 2B
        System.out.println("------Task 2B------");
        b2v1(numbers);
        System.out.println(" ");

        //Task 2C
        System.out.println("------Task 2C------");
        c2v1(numbers);
        System.out.println(" ");


        //Task 4.
        System.out.println("------Task 4-------");
        PriorityQueue<Element> Scores = new PriorityQueue<>();
        add_Element(new Element("Terje", 5));
        add_Element(new Element("Kari", 7));
        add_Element(new Element("Nils", 4));
        add_Element(new Element("Otto", 8));
        add_Element(new Element("Syvert", 7));
        add_Element(new Element("Lise", 11));
        add_Element(new Element("Notto", 0));
        add_Element(new Element("Odd", 1));
        add_Element(new Element("Even", 2));

        Element highest = get_highest_priority_element();
        System.out.println("Highest priority in queue: " + highest.getName() + " " + highest.getPriority() + "\n");

        print_all_elements_in_order();
    }

    /**
     * Task 2A version 1. Consecutive subStrings that adds up to the target sum.
     * @param numbers
     * @param targetSum
     * @return
     */
    public static ArrayList<String> a2(int[] numbers, int targetSum) {
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
                if (sum == targetSum) {
                    validConsecutiveSubStrings.add(consecutiveSubString.toString());
                }
                int next = remaining.next();
                sum += next;
                consecutiveSubString.append(next).append(" ");
            }
        }

        return validConsecutiveSubStrings;
    }


    /**
     * Task 2A version 1. Consecutive subStrings that adds up to the target sum.
     * @param A
     */
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

    /**
     * Task 2B
     * @param B
     */
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

    /**
     * Task 2C
     * @param numbers
     */
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


    /**
     *
     * Task 4
     */

    static Boolean add_Element(Element e) {
        return Scores.add(e);
    }

    static Element get_highest_priority_element() {
        return Scores.peek();
    }

    static Boolean print_all_elements_in_order() {
        while (!Scores.isEmpty()) {
            Element temp = Scores.poll();
            System.out.println(temp.getName() + " " + temp.getPriority());
        }
        return true;
    }


    static class Element implements Comparable<Element>{
        String name;
        int priority;

        Element(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public int getPriority() {
            return priority;
        }

        public boolean equals(Element other) {
            return this.getPriority() == other.getPriority();
        }


        @Override
        public int compareTo(Element o) {
            if (this.equals(o))
                return 0;
            else if (getPriority() < o.getPriority())
                return 1;
            else
                return -1;
        }
    }
}