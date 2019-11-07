package ru.unn.agile.polynomialcalculator.model;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class Polynomial {

    private int degree;
    private double[] coefs;

    public Polynomial(final double coef, final int degree) {
        if (degree < 0) {
            throw new IllegalArgumentException("Can not create polynomial with negative degree!");
        }
        this.coefs = new double[degree + 1];
        this.coefs[degree] = coef;
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public double getCoef(final int degree) {
        return coefs[degree];
    }

    private void reduce() {
        this.degree = 0;
        for (int i = this.coefs.length - 1; i >= 0; i--) {
            if (coefs[i] != 0) {
                this.degree = i;
                return;
            }
        }
    }

    public Polynomial plus(final Polynomial that) {
        Polynomial sum = new Polynomial(0, Math.max(this.degree, that.degree));
        for (int i = 0; i <= this.degree; i++) {
            sum.coefs[i] += this.coefs[i];
        }
        for (int i = 0; i <= that.degree; i++) {
            sum.coefs[i] += that.coefs[i];
        }
        sum.reduce();
        return sum;
    }

    public Polynomial minus(final Polynomial that) {
        Polynomial minus = new Polynomial(0, Math.max(this.degree, that.degree));
        for (int i = 0; i <= this.degree; i++) {
            minus.coefs[i] += this.coefs[i];
        }
        for (int i = 0; i <= that.degree; i++) {
            minus.coefs[i] -= that.coefs[i];
        }
        minus.reduce();
        return minus;
    }

    public Polynomial multiply(final Polynomial that) {
        Polynomial product = new Polynomial(0, this.degree + that.degree);
        for (int i = 0; i <= this.degree; i++) {
            for (int j = 0; j <= that.degree; j++) {
                product.coefs[i + j] += this.coefs[i] * that.coefs[j];
            }
        }
        product.reduce();
        return product;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int degree = this.degree; degree >= 0; degree--) {
            double coef = this.coefs[degree];
            if (coef != 0.) {
                if (coef > 0 && !result.toString().equals("")) {
                    result.append(" + ");
                }
                if (coef < 0) {
                    result.append(" - ");
                }
                result.append(String.format(Locale.US, "%.1f", Math.abs(coef)));
                if (degree > 0) {
                    result.append("x");
                }
                if (degree > 1) {
                    result.append("^").append(degree);
                }
            } else {
                if (degree == 0 && result.toString().equals("")) {
                    result.append("0");
                }
            }

        }
        return result.toString().strip();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Polynomial that = (Polynomial) other;
        if (this.degree != that.degree) {
            return false;
        }
        for (int i = this.degree; i >= 0; i--) {
            if (this.coefs[i] != that.coefs[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int magicNumber = 31; // TravisCI don't like default IntelliJ implementation of hashCode
        int result = Objects.hash(degree);
        result = magicNumber * result + Arrays.hashCode(coefs);
        return result;
    }
}
