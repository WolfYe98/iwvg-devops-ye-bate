package es.upm.miw.iwvg_devops.rest;

import es.upm.miw.iwvg_devops.Fraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    private Fraction fractionOne;
    private Fraction fractionError;
    private Fraction fractionHalf;
    private Fraction twoThird;

    @BeforeEach
    void initializeFractions() {
        fractionOne = new Fraction();
        fractionError = new Fraction(0, 0);
        fractionHalf = new Fraction(1, 2);
        twoThird = new Fraction(2, 3);
    }

    @Test
    void testFractionDefaultConstructor() {
        assertEquals(1, fractionOne.getNumerator());
        assertEquals(1, fractionOne.getDenominator());
    }

    @Test
    void testFractionGetNumerator() {
        assertEquals(1, fractionOne.getNumerator());
        assertEquals(0, fractionError.getNumerator());
    }

    @Test
    void testFractionSetNumerator() {
        int newNumerator = 2;
        fractionOne.setNumerator(newNumerator);
        fractionError.setNumerator(newNumerator);
        assertEquals(newNumerator, fractionOne.getNumerator());
        assertEquals(newNumerator, fractionError.getNumerator());
    }

    @Test
    void testFractionGetDenominator() {
        assertEquals(1, fractionOne.getDenominator());
        assertEquals(0, fractionError.getDenominator());
    }

    @Test
    void testFractionSetDenominator() {
        int newDenominator = 2;
        fractionOne.setDenominator(newDenominator);
        fractionError.setDenominator(newDenominator);
        assertEquals(newDenominator, fractionOne.getDenominator());
        assertEquals(newDenominator, fractionError.getDenominator());
    }

    @Test
    void testFractionDecimal() {
        assertEquals(1, fractionOne.decimal());
        fractionOne.setDenominator(2);
        assertEquals(0.5, fractionOne.decimal());
    }

    @Test
    void testErrorFractionDecimal() {
        assertTrue(Double.isNaN(fractionError.decimal()));
        fractionError.setDenominator(1);
        assertEquals(0, fractionError.decimal());
    }

    @Test
    void testFractionToString() {
        String fractionOneString = "Fraction{" +
                "numerator=" + '1' +
                ", denominator=" + '1' +
                '}';
        String fractionErrorString = "Fraction{" +
                "numerator=" + '0' +
                ", denominator=" + '0' +
                '}';
        assertEquals(fractionOneString, fractionOne.toString());
        assertEquals(fractionErrorString, fractionErrorString.toString());
    }

    @Test
    void testIsProper() {
        assertFalse(fractionOne.isProper());
        assertFalse(fractionError.isProper());
        assertTrue(fractionHalf.isProper());
    }

    @Test
    void testIsImproper() {
        assertTrue(fractionOne.isImproper());
        assertTrue(fractionError.isImproper());
        assertFalse(fractionHalf.isImproper());
    }

    @Test
    void testIsEquivalent() {
        Fraction otherFraction = new Fraction(2, 2);
        Fraction otherFractionOne = new Fraction();
        assertFalse(fractionOne.isEquivalent(fractionHalf));
        assertTrue(fractionOne.isEquivalent(otherFractionOne));
        assertTrue(fractionOne.isEquivalent(otherFraction));
    }

    @Test
    void testAddFractions() {
        Fraction otherFractionTwoThird = new Fraction(2, 3);
        otherFractionTwoThird.add(fractionHalf);
        fractionHalf.add(twoThird);
        fractionOne.add(twoThird);
        assertEquals(7, fractionHalf.getNumerator());
        assertEquals(6, fractionHalf.getDenominator());
        assertEquals(7, otherFractionTwoThird.getNumerator());
        assertEquals(6, otherFractionTwoThird.getDenominator());
        assertEquals(5, fractionOne.getNumerator());
        assertEquals(3, fractionOne.getDenominator());
    }

    @Test
    void testMultiplyFractions() {
        Fraction otherFraction = new Fraction(1, 2);
        otherFraction.multiply(fractionHalf);
        fractionHalf.multiply(twoThird);
        assertEquals(1, fractionHalf.getNumerator());
        assertEquals(3, fractionHalf.getDenominator());
        assertEquals(1, otherFraction.getNumerator());
        assertEquals(4, otherFraction.getDenominator());
    }

    @Test
    void testDivideFractions() {
        Fraction otherFraction = new Fraction(1, 4);
        otherFraction.divide(fractionHalf);
        fractionHalf.divide(twoThird);
        assertEquals(1, otherFraction.getNumerator());
        assertEquals(2, otherFraction.getDenominator());
        assertEquals(3, fractionHalf.getNumerator());
        assertEquals(4, fractionHalf.getDenominator());
    }
}
