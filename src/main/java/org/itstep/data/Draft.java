package org.itstep.data;

public enum Draft {
    YES(1, "Yes"),
    NO(2, "No");
    public final int num;
    public final String mean;

    Draft (int num, String mean) {	// Конструктор
        this.num = num;
        this.mean = mean;
    }

    public int num() {
        return num;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", num, mean);
    }
}
