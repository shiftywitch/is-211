package assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Assignment2 {
    public static void main(String[] args) {
        int[] numbers = {4,2,7,6,-3,-1,-2,42,0,-42,9,-4,5,-5,-6,-7,-8,-99,42,11,20,1,2,3};

        System.out.println(a2(numbers));
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
                int next = remaining.next();
                sum += next;
                consecutiveSubString.append(next).append(" ");
                if (sum == 0) {
                    validConsecutiveSubStrings.add(consecutiveSubString.toString());
                }
            }
        }

        return validConsecutiveSubStrings;
    }
}
