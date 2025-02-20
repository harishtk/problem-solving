package search;

import java.util.*;

public class BinarySearch {

    public static void main(String[] args) {
        // final int[] input = new int[] {5, 6, 7, 11, 12, 13, 17, 22, 34, 54, 56, 58, 60};
        final int[] input = new int[] {1, 2};

        for (int n : input) System.out.print(n + " ");
        System.out.println();
        
        try (final Scanner scn = new Scanner(System.in)) {
            int n = -1;
            do {
                System.out.print("Enter number to search (-1 to exit): ");
                // n = Integer.parseInt(scn.nextLine());
                n = scn.nextInt();
                
                if (search(input, n)) {
                    System.out.println(String.format("%d is found.", n));
                } else {
                    System.out.println(String.format("%d is not found.", n));
                }
            } while (n != -1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean search(int[] arr, int target) {
        if (arr.length == 0) return false;

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            final int mid = (low + high) / 2;
            // System.out.println(String.format("low=%d high=%d mid=%d value=%d", low, high, mid, arr[mid]));

            if (arr[mid] == target) {
                return true;
            }

            if (target < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1; 
            }
        }

        return false;
    }
}