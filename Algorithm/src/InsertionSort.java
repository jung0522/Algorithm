public class InsertionSort implements SortAlgorithm {
    private ComparisonCounter counter;

    public InsertionSort(ComparisonCounter counter) {
        this.counter = counter;
    }

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && counter.compare(key, array[j])) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
