package es.upm.miw.iwvg_devops.rest;

import es.upm.miw.iwvg_devops.Fraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    private Fraction fractionOne;
    private Fraction fractionError;
    @BeforeEach
    void initializeFractions(){
        fractionOne = new Fraction();
        fractionError = new Fraction(0,0);
    }
    @Test
    void testFractionDefaultConstructor(){
        assertEquals(1,fractionOne.getNumerator());
        assertEquals(1,fractionOne.getDenominator());
    }
    @Test
    void testFractionGetNumerator(){
        assertEquals(1,fractionOne.getNumerator());
        assertEquals(0,fractionError.getNumerator());
    }
    @Test
    void testFractionSetNumerator(){
        int newNumerator = 2;
        fractionOne.setNumerator(newNumerator);
        fractionError.setNumerator(newNumerator);
        assertEquals(newNumerator,fractionOne.getNumerator());
        assertEquals(newNumerator,fractionError.getNumerator());
    }
    @Test
    void testFractionGetDenominator(){
        assertEquals(1,fractionOne.getDenominator());
        assertEquals(0,fractionError.getDenominator());
    }
    @Test
    void testFractionSetDenominator(){
        int newDenominator = 2;
        fractionOne.setDenominator(newDenominator);
        fractionError.setDenominator(newDenominator);
        assertEquals(newDenominator,fractionOne.getDenominator());
        assertEquals(newDenominator,fractionError.getDenominator());
    }
    @Test
    void testFractionDecimal(){
        assertEquals(1,fractionOne.decimal());
        fractionOne.setDenominator(2);
        assertEquals(0.5,fractionOne.decimal());
    }
    @Test
    void testErrorFractionDecimal(){
        assertTrue(Double.isNaN(fractionError.decimal()));
        fractionError.setDenominator(1);
        assertEquals(0,fractionError.decimal());
    }
    @Test
    void testFractionToString(){
        String fractionOneString = "Fraction{" +
                "numerator=" + '1' +
                ", denominator=" + '1' +
                '}';
        String fractionErrorString = "Fraction{" +
                "numerator=" + '0' +
                ", denominator=" + '0' +
                '}';
        assertEquals(fractionOneString,fractionOne.toString());
        assertEquals(fractionErrorString,fractionErrorString.toString());
    }
}
