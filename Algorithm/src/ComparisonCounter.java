public class ComparisonCounter {
    // A. Count the number of key comparisons
    private int compCount = 0;

    //  a function COMPARE(X ,Y )
    public boolean compare(int x, int y) {
        compCount++;
        return x < y;
    }

    public int getCount() {
        return compCount;
    }

    public void reset() {
        compCount = 0;
    }
}
