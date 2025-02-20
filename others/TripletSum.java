package others;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

public class TripletSum {
    public static void main(String[] args) {
        final int[] input = {1, 4, 45, 6, 10, 8};
        final int target = 13;

        System.out.print("Input: ");
        Util.printArr(input);

        final TripletSum app = new TripletSum();
        // System.out.println(app.hasTripletSum(input, target));
        // System.out.println(app.hasTripletSumWithHashSet(input, target));
        System.out.println(app.hasTripletSum2Pts(input, target));
    }

    // Naive approach
    private boolean hasTripletSum(final int[] arr, final int target) {
        final int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == target) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // with Hashset
    private boolean hasTripletSumWithHashSet(int[] arr, int target) {
        final int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            
            final Set<Integer> set = new HashSet<>();

            for (int j = i + 1; j < n; j++) {
                int second = target - arr[i] - arr[j];

                if (set.contains(second)) {
                    return true;
                }

                set.add(arr[j]);
            }
        }

        return false;
    }

    // Using sorting and two pointer approach
    private boolean hasTripletSum2Pts(int[] arr, int target) {
        final int n = arr.length;

        Arrays.sort(arr);

        for (int i = 0; i < n - 2; i++) {
            
            int l = i + 1, r = n - 1;

            int requiredSum = target - arr[i];
            while (l < r) {
                if (arr[l] + arr[r] == requiredSum) {
                    return true;
                } 
                if (arr[l] + arr[r] < requiredSum) {
                    l++;
                } else if (arr[l] + arr[r] > requiredSum) {
                    r--;
                }
            }
        }

        return false;
    }
}
