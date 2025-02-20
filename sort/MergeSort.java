package sort;

public class MergeSort {

    public static void main(String[] args) {
        final int[] input = new int[] {12, 3, 5, 2, 7, 8, 19, 45, 14, 40, 11, 4, 39, 17, 35, 15};

        final MergeSort app = new MergeSort();
        System.out.print("Initial: ");
        printArr(input);
        app.sort(input, 0, input.length - 1);
        System.out.print("Sorted: ");
        printArr(input);
    }

    // Merges two subarrays of arr[]
    // First subarray is arr[l..m]
    // Second subarry is arr[m + 1..r]
    private void merge(int[] arr, int l, int m, int r) {
        
        // Find the length of the two subarrays
        final int n1 = m - l + 1;
        final int n2 = r - m;

        // Create temp arrays
        final int[] leftArr = new int[n1];
        final int[] rightArr = new int[n2];

        // Populate the left and right subarrays
        for (int i = 0; i < n1; i++) 
            leftArr[i] = arr[l + i];
        for (int i = 0; i < n2; i++) 
            rightArr[i] = arr[m + 1 + i];

        // Merging two temp arrays
        
        // Initial indices of firt and second subarrays
        int i = 0, j = 0;

        // Initial index of the merged array
        int k = l;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of left subarray
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy the remainig elements of right subarray
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Recursive function to sort and merge
    private void sort(int[] arr, int l, int r) {

        if (l < r) {
            // Find the mid point
            int m = l + (r - l) / 2;

            // Sort firt and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

}
