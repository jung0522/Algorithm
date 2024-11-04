public class MergeSort implements SortAlgorithm {
    private ComparisonCounter counter;

    public MergeSort(ComparisonCounter counter) {
        this.counter = counter;
    }

    @Override
    public void sort(int[] array) {
        mergeSort(array, new int[array.length], 0, array.length - 1);
    }

    private void mergeSort(int[] array, int[] temp, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(array, temp, left, mid);
        mergeSort(array, temp, mid + 1, right);
        merge(array, temp, left, mid, right);
    }

    private void merge(int[] array, int[] temp, int left, int mid, int right) {
        System.arraycopy(array, left, temp, left, right - left + 1);

        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (counter.compare(temp[i], temp[j])) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            array[k++] = temp[i++];
        }
    }
}
