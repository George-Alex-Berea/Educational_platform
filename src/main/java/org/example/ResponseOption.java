package org.example;

public enum ResponseOption {
    A,
    B,
    C,
    D;

    public static ResponseOption fromString(String value) {
        switch (value) {
            case "A" -> {
                return A;
            }
            case "B" -> {
                return B;
            }
            case "C" -> {
                return C;
            }
            case "D" -> {
                return D;
            }
            default -> throw new IllegalArgumentException("Invalid option value: " + value);
        }
    }
}
