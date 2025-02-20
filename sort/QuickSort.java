package sort;

public class QuickSort {

    public static void main(String[] args) {
        final int[] input = new int[] {12, 3, 5, 2, 7, 8, 19, 45, 14, 40, 11, 4, 39, 17, 35, 15};

        final QuickSort app = new QuickSort();
        System.out.print("Initial: ");
        printArr(input);
        app.sort(input, 0, input.length - 1);
        System.out.print("Sorted: ");
        printArr(input);
    }

    // The QuickSort function impl.
    private void sort(int[] arr, int low, int high) {
        if (low < high) {

            // The partition index of the pivot
            int pi = partition(arr, low, high);

            // Recursive calls for smaller elements
            // and greater or equals elements
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    // The partition function
    // Always selectes the last element as pivot
    private int partition(int[] arr, int low, int high) {

        // Choose the pivot
        int pivot = arr[high];

        // Index of smaller element and indicates the
        // right position of pivot found so far
        int i = low - 1;

        // Traverse arr[low..high] and move all smaller 
        // elements to the left side. Elements from low
        // i are smaller after every iteration
        for (int j = low; j <= high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        // Move pivot after smaller elements and
        // return its position
        swap(arr, i + 1, high);
        return i + 1;   
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
