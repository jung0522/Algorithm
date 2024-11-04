public class HeapSort implements SortAlgorithm {
    private ComparisonCounter counter;

    public HeapSort(ComparisonCounter counter) {
        this.counter = counter;
    }

    @Override
    public void sort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && counter.compare(array[largest], array[left])) {
            largest = left;
        }

        if (right < n && counter.compare(array[largest], array[right])) {
            largest = right;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapify(array, n, largest);
        }
    }
}
