package org.unan.week1;


import java.util.Objects;

public class EngineeringCalculator extends Calculator{
    public EngineeringCalculator() {
        super(CalculatorType.ENGINEERING);
    }

    public int areaOfVerticalTriangle(final Integer base, final Integer height) {
        if (Objects.isNull(base) || Objects.isNull(height)) {
            throw new IllegalArgumentException("NULL");
        }

        if (base % 2 != 0 || height % 2 != 0) {
            throw new IllegalArgumentException("NOTINT");
        }

        return (int) base * height / 2;
    }

}
