package Dictionary;

import java.util.ArrayList;
import java.util.Arrays;

class Quicksort {

    // method for swapping elements x and y
    static void swap(ArrayList<Word> arr, int x, int y) {
        Word temp = arr.get(x);
        arr.set(x,arr.get(y));
        arr.set(y,temp);
    }

    // partition method
    static int partition(ArrayList<Word> array, int low, int high) {

        //pivot
        Word pivot = array.get(high);

        int x = (low - 1);



        // loop for comparing all elements with pivot element
        for (int y = low; y < high; y++) {
            if (array.get(y).distance < pivot.distance) {
                x++;
                swap(array, x, y);
            }
            else if (array.get(y).distance == pivot.distance) {
                if (array.get(y).frequency > pivot.frequency) {
                    x++;
                    swap(array, x, y);
                }

            }

        }

        Word temp = array.get(x + 1);
        array.set(x + 1, array.get(high));
        array.set(high, temp);


        return (x + 1);
    }

    static void quickSort(ArrayList<Word> array, int low, int high) {
        if (low < high) {

            int array_partition = partition(array, low, high);

            // quick sort elements on the left recursively
            quickSort(array, low, array_partition - 1);

            // quick sort elements on the right recursively
            quickSort(array, array_partition + 1, high);
        }
    }
}