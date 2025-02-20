package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourSum {
    public static void main(String[] args) {
        int[] arr = {10, 2, 3, 4, 5, 7, 8};
        int target = 23;

        List<List<Integer>> result = fourSumWithTwoPointer(arr, target);
        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }

    static List<List<Integer>> fourSum(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (arr[i] + arr[j] + arr[k] + arr[l] == target) {
                            List<Integer> curr = new ArrayList<>(
                                Arrays.asList(arr[i], arr[j], arr[k], arr[l])
                            );
                            Collections.sort(curr);

                            if (!result.contains(curr)) {
                                result.add(curr);
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    static List<List<Integer>> fourSumHash(int[] arr, int target) {
        Set<List<Integer>> result = new HashSet<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                Set<Integer> s = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    int sum = arr[i] + arr[j] + arr[k];
                    int last = target - sum;

                    if (s.contains(last)) {
                        List<Integer> curr = Arrays.asList(
                            arr[i], arr[j], arr[k], last
                        );

                        Collections.sort(curr);

                        result.add(curr);
                    }
                    s.add(arr[k]);
                }
            }
        }
        
        return new ArrayList<>(result);
    }

    static List<List<Integer>> fourSumWithTwoPointer(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            
            // Skip duplicates for i
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            for (int j = i + 1; j < n; j++) {
                
                // Skip duplicates for j
                if (j > i + 1 && arr[j] == arr[j - 1]) continue;

                int k = j + 1;
                int l = n - 1;

                while (k < l) {
                    int sum = arr[i] + arr[j] + arr[k] + arr[l];
                    if (sum == target) {
                        result.add(Arrays.asList(arr[i], arr[j], arr[k], arr[l]));
                        k++;
                        l--;

                        while (k < l && arr[k] == arr[k - 1]) k++; // Skip duplicates for k
                        while (k < l && arr[l] == arr[l + 1]) l--; // Skip duplicates for l
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }

        return result;
    }
}
