package techniques;

/**
 * A Two Sum problem with 2-pointers approach.
 */
public class TwoPointers {
    public static void main(String[] args) {
        final int[] input = {2, 7, 11, 15};

        final int target = 9;
        final int[] result = twoSum(input, target);

        for (int i : result) {
            System.out.print(input[i] + " ");
        }
        System.out.println();
    }

    private static int[] twoSum(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            final int currSum = arr[left] + arr[right];

            if (currSum == target) {
                return new int[]{left + 1, right + 1};
            } else if (currSum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[2];
    }
}
