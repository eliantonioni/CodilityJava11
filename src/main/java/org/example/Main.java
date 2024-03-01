package org.example;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        int[] arr1 = new int[]{6,3,3,1};
        int[] arr2 = new int[]{1,3,6,4,1,2};
        int[] arr3 = new int[]{1,2,3};
        int[] arr4 = new int[]{-1,-2,-3,1,3};
        int[] arr5 = new int[]{-2,-3,0};
        System.out.println(m.solution(arr1));
        System.out.println(m.solution(arr2));
        System.out.println(m.solution(arr3));
        System.out.println(m.solution(arr4));
        System.out.println(m.solution(arr5));
    }

    public int solution(int[] A) {

        HashSet<Integer> set = new HashSet<>();

        int result = 1;
        List<Integer> sorted = Arrays.stream(A)
                .boxed()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        if (sorted.get(0) <= 1) {
            int counter = 0;
            for (int i = 0; i < sorted.size() - 1; i++) {
                int a1 = sorted.get(i);
                int a2 = sorted.get(i + 1);
                if (a1 > 0 && a1 < a2 - 1) {
                    result = a1 + 1;
                    break;
                }
                counter++;
            }
            if (counter == sorted.size()-1) {
                result = sorted.get(counter) + 1;
                result = result > 0 ? result : 1;
            }
        }
        return result;
    }

}