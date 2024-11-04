import java.util.Random;

public class QuickSort implements SortAlgorithm {
    private ComparisonCounter counter;
    private Random random;

    public QuickSort(ComparisonCounter counter) {
        this.counter = counter;
        this.random = new Random();
    }

    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1, 1); // 기본적으로 첫 번째 버전 선택
    }

    public void quickSort(int[] array, int low, int high, int version) {
        if (low < high) {
            int pi;
            switch (version) {
                case 1 -> pi = partitionFirstLastPivot(array, low, high);
                case 2 -> pi = partitionRandomPivot(array, low, high);
                case 3 -> pi = partitionMedianOfThree(array, low, high);
                case 4 -> pi = partitionCustom(array, low, high);
                default -> throw new IllegalArgumentException("Invalid pivot version");
            }

            quickSort(array, low, pi - 1, version);
            quickSort(array, pi + 1, high, version);
        }
    }

    // Version 1: 첫 번째 또는 마지막 요소를 피벗으로 선택
    private int partitionFirstLastPivot(int[] array, int low, int high) {
        int pivot = array[high]; // 마지막 요소를 피벗으로 선택
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (counter.compare(array[j], pivot)) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    // Version 2: 무작위 요소를 피벗으로 선택
    private int partitionRandomPivot(int[] array, int low, int high) {
        int pivotIndex = low + random.nextInt(high - low + 1);
        swap(array, pivotIndex, high); // 무작위 피벗을 끝으로 이동
        return partitionFirstLastPivot(array, low, high);
    }

    // Version 3: 세 개의 요소 중 중앙값을 피벗으로 선택
    private int partitionMedianOfThree(int[] array, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivotIndex = medianOfThree(array, low, mid, high);
        swap(array, pivotIndex, high); // 중앙값을 끝으로 이동
        return partitionFirstLastPivot(array, low, high);
    }

    private int medianOfThree(int[] array, int a, int b, int c) {
        if (counter.compare(array[a], array[b])) {
            if (counter.compare(array[b], array[c])) return b;
            else if (counter.compare(array[a], array[c])) return c;
            else return a;
        } else {
            if (counter.compare(array[a], array[c])) return a;
            else if (counter.compare(array[b], array[c])) return c;
            else return b;
        }
    }

    // Version 4: 커스텀 전략 (중앙 근처의 임의 요소 선택)
    private int partitionCustom(int[] array, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivotIndex = mid + random.nextInt(Math.max(1, (high - mid) / 2));
        swap(array, pivotIndex, high); // 피벗을 끝으로 이동
        return partitionFirstLastPivot(array, low, high);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
