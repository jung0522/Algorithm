import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        int[] smallRandomArray = generateRandomArray(32);
        int[] smallSortedArray = smallRandomArray.clone();

        Arrays.sort(smallSortedArray); // 작은 정렬된 배열 생성

        int[] largeRandomArray = generateRandomArray(1024);

        // 비교 횟수를 위한 ComparisonCounter 생성
        ComparisonCounter counter = new ComparisonCounter();

        // 각 정렬 알고리즘을 테스트할 객체 생성
        SortAlgorithm insertionSort = new InsertionSort(counter);
        SortAlgorithm heapSort = new HeapSort(counter);
        SortAlgorithm mergeSort = new MergeSort(counter);
        QuickSort quickSort = new QuickSort(counter);

        // 테이블 헤더 출력
        System.out.printf("%-35s%-20s%-20s%n", "Algorithm & Array Type", "Comparisons", "Time (ns)");
        System.out.println("=".repeat(75));

        // 작은 배열 (n=32) 테스트 - 정렬된 배열과 무작위 배열
        System.out.println("Small Array (n=32) Tests (Sorted):");
        testSortAlgorithm("Insertion Sort (Sorted)", insertionSort, smallSortedArray.clone(), counter);
        testSortAlgorithm("Heap Sort (Sorted)", heapSort, smallSortedArray.clone(), counter);
        testSortAlgorithm("Merge Sort (Sorted)", mergeSort, smallSortedArray.clone(), counter);
        for (int version = 1; version <= 4; version++) {
            testQuickSort("Quick Sort - Version " + version + " (Sorted)", quickSort, smallSortedArray.clone(), version, counter);
        }

        System.out.printf("\n\n\n%-35s%-20s%-20s%n", "Algorithm & Array Type", "Comparisons", "Time (ns)");
        System.out.println("=".repeat(75));

        System.out.println("Small Array (n=32) Tests (Random):");
        testSortAlgorithm("Insertion Sort (Random)", insertionSort, smallRandomArray.clone(), counter);
        testSortAlgorithm("Heap Sort (Random)", heapSort, smallRandomArray.clone(), counter);
        testSortAlgorithm("Merge Sort (Random)", mergeSort, smallRandomArray.clone(), counter);
        for (int version = 1; version <= 4; version++) {
            testQuickSort("Quick Sort - Version " + version + " (Random)", quickSort, smallRandomArray.clone(), version, counter);
        }

        System.out.printf("\n\n\n%-35s%-20s%-20s%n", "Algorithm & Array Type", "Comparisons", "Time (ns)");
        System.out.println("=".repeat(75));

        // 큰 배열 (n=1024) 테스트 - 무작위 배열
        System.out.println("Large Array (n=1024) Tests (Random):");
        testSortAlgorithm("Insertion Sort (Random)", insertionSort, largeRandomArray.clone(), counter);
        testSortAlgorithm("Heap Sort (Random)", heapSort, largeRandomArray.clone(), counter);
        testSortAlgorithm("Merge Sort (Random)", mergeSort, largeRandomArray.clone(), counter);
        for (int version = 1; version <= 4; version++) {
            testQuickSort("Quick Sort - Version " + version + " (Random)", quickSort, largeRandomArray.clone(), version, counter);
        }
    }

    private static void testSortAlgorithm(String name, SortAlgorithm algorithm, int[] array, ComparisonCounter counter) {
        // B. Use the actual measured clock time
        counter.reset();
        long startTime = System.nanoTime();
        algorithm.sort(array);
        long endTime = System.nanoTime();
        System.out.printf("%-35s%-20d%-20d%n", name, counter.getCount(), (endTime - startTime));
    }

    private static void testQuickSort(String name, QuickSort quickSort, int[] array, int version, ComparisonCounter counter) {
        counter.reset();
        long startTime = System.nanoTime();
        quickSort.quickSort(array, 0, array.length - 1, version);
        long endTime = System.nanoTime();
        System.out.printf("%-35s%-20d%-20d%n", name, counter.getCount(), (endTime - startTime));
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }
}
