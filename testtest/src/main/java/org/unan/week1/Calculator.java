package org.unan.week1;


import java.util.Objects;

// 계산기 유형이 DIGITAL, ENGINEERING
public abstract class Calculator {
    private static final int MAX_VALUE = 100000;
    private CalculatorType type;

    public Calculator(CalculatorType type) {
        this.type = type;
    }

    public int add(Integer x, Integer y) {
        if (Objects.isNull(x) || Objects.isNull(y)) {
            throw new IllegalArgumentException("NULL");
        }

        if (x+y > MAX_VALUE) {
            throw new IllegalArgumentException("LIMIT");
        }

        if (x+y < 0) {
            throw new IllegalArgumentException("NEGATIVE");
        }
        return x+y;
    }

    public int subtract(Integer x, Integer y) {
        if (Objects.isNull(x) || Objects.isNull(y)) {
            throw new IllegalArgumentException("NULL");
        }

        if (x-y > MAX_VALUE) {
            throw new IllegalArgumentException("LIMIT");
        }

        if (x-y < 0) {
            throw new IllegalArgumentException("NEGATIVE");
        }
        return x-y;
    }

    public int multiply(Integer x, Integer y) {
        if (Objects.isNull(x) || Objects.isNull(y)) {
            throw new IllegalArgumentException("NULL");
        }

        if (x * y > MAX_VALUE) {
            throw new IllegalArgumentException("LIMIT");
        }

        if (x * y < 0) {
            throw new IllegalArgumentException("NEGATIVE");
        }

        return x*y;
    }

    public int divide(Integer x, Integer y) {
        if (Objects.isNull(x) || Objects.isNull(y)) {
            throw new IllegalArgumentException("NULL");
        }

        if (y == 0) {
            throw new IllegalArgumentException("ZERO");
        }

        if (x / y < 0) {
            throw new IllegalArgumentException("NEGATIVE");
        }

        return x / y;
    }
}
