package es.upm.miw.iwvg_devops;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction() {
        this(1, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public double decimal() {
        return (double) numerator / denominator;
    }

    public boolean isProper(){
        return this.numerator < this.denominator;
    }

    public boolean isImproper(){
        return this.numerator >= this.denominator;
    }
    public boolean isEquivalent(Fraction other){
        double decimal = this.decimal();
        double otherDecimal = other.decimal();
        return decimal == otherDecimal;
    }
    public void add(Fraction other){
        int leastCommonMultiplyDenominator = this.getLeastCommonMultiplyDenominatorToOther(other);
        int multiply = leastCommonMultiplyDenominator/this.denominator;
        int otherMultiply = leastCommonMultiplyDenominator/other.getDenominator();
        this.denominator = leastCommonMultiplyDenominator;
        this.numerator = (this.numerator*multiply)+(other.getNumerator()*otherMultiply);
        this.simplifyFraction();
    }
    public void multiply(Fraction other){
        this.numerator *= other.getNumerator();
        this.denominator *= other.getDenominator();
        this.simplifyFraction();
    }
    public void divide(Fraction other){
        this.numerator *= other.getDenominator();
        this.denominator *= other.getNumerator();
        this.simplifyFraction();
    }
    private void simplifyFraction(){
        int gcdBetweenNumeratorDenominator = Fraction.getGreatestCommonDivisorBetweenTwoNumbers(this.numerator,this.denominator);
        this.numerator /= gcdBetweenNumeratorDenominator;
        this.denominator /= gcdBetweenNumeratorDenominator;
    }
    private int getLeastCommonMultiplyDenominatorToOther(Fraction other){
        int greatestCommonDivisor = this.getGreatestCommonDivisorDenominatorToOther(other);
        return (this.denominator*other.getDenominator())/greatestCommonDivisor;
    }
    private int getGreatestCommonDivisorDenominatorToOther(Fraction other){
        return Fraction.getGreatestCommonDivisorBetweenTwoNumbers(this.denominator,other.getDenominator());
    }
    private static int getGreatestCommonDivisorBetweenTwoNumbers(int numberA, int numberB){
        List<Integer> divisorsA = Fraction.getAllDivisorsForNumber(numberA);
        List<Integer> divisorsB = Fraction.getAllDivisorsForNumber(numberB);
        return divisorsA.stream()
                .filter(divisorsB::contains)
                .mapToInt(divisor->divisor)
                .max().orElse(1);
    }
    private static List<Integer> getAllDivisorsForNumber(int number){
        List<Integer> divisors = new ArrayList<>();
        int half = number/2;
        for(int divisor = 1; divisor <= half; divisor++){
            if(number % divisor == 0){
                divisors.add(divisor);
            }
        }
        divisors.add(number);
        return divisors;
    }
    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
