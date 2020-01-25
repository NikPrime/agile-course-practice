package ru.unn.agile.distancebetweenvector.model;

import java.util.Vector;

public class VectorMetric {
    private float sumOfModulesL1 = 0f;
    private float sumOfModulesL2 = 0f;
    private float sumOfModulesL3 = 0f;
    public float calculateL1(final Vector<Float> x) {
        x.forEach((elem) -> sumOfModulesL1 += Math.abs((float) elem));
        return sumOfModulesL1;
    }

    public float calculateL2(final Vector<Float> x) {
        x.forEach((elem) -> sumOfModulesL2 += Math.pow((float) elem, 2.0));
        return (float) Math.sqrt(sumOfModulesL2);
    }

    public float calculateL3(final Vector<Float> x) {
        final float pow = 3.0f;
        x.forEach((elem) -> sumOfModulesL3 += Math.abs(Math.pow((float) elem, pow)));
        return (float) Math.pow((float) sumOfModulesL3, 1.0 / pow);
    }
}




