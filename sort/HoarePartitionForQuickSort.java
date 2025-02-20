package sort;

public class HoarePartitionForQuickSort {
    public static void main(String[] args) {
        final int[] input = new int[] {12, 3, 5, 2, 7, 8, 19, 45, 14, 40, 11, 4, 39, 17, 35, 15};

        final HoarePartitionForQuickSort app = new HoarePartitionForQuickSort();
        System.out.print("Initial: ");
        printArr(input);
        app.sort(input, 0, input.length - 1);
        System.out.print("Sorted: ");
        printArr(input);
    }

    private void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            sort(arr, low, pi);
            sort(arr, pi + 1, high);
        }
    }

    // Hoare's partition    
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low - 1, j = high + 1;

        while (true) {
            
            // find next element larget than pivot
            // from the left
            do {
                i++;
            } while (arr[i] < pivot);

            // find next element smaller than pivot
            // from the right
            do {
                j--;
            } while (arr[j] > pivot);

            // if left and right crosses each other
            // no swapping required
            if (i >= j) {
                return j;
            };

            // swap larger and smaller elements
            swap(arr, i, j);
        }
    }

    private void swap(int[] arr, int i, int j) {
        final int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
