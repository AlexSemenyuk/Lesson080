package org.itstep.data;

public enum Role {
    USER(1, "User"),
    ADMIN(2, "Admin");
    private final int num;
    private final String mean;

    Role (int num, String mean) {	// Конструктор
        this.num = num;
        this.mean = mean;
    }

    public int num() {
        return num;
    }

    public String mean() {
        return mean;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", num, mean);
    }

}
