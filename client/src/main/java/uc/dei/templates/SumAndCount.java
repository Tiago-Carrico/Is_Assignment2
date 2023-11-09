package uc.dei.templates;

public class SumAndCount {
    private float sum;
    private int count;

    public SumAndCount(float sum, int count) {
        this.sum = sum;
        this.count = count;
    }

    public SumAndCount accumulate(float value) {
        return new SumAndCount(sum + value, count + 1);
    }

    public float getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }
}
