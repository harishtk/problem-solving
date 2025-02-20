package sort;

public class BubbleSort {
    
    public static void main(String[] args) {
        final int[] input = new int[] {12, 3, 5, 2, 7, 8, 19, 45, 14, 40, 11, 4, 39, 17, 35, 15};

        System.out.print("Input:  ");
        printArr(input);
        sort(input);
        System.out.print("Sorted: ");
        printArr(input);
    }

    private static void sort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
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
