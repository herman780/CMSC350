/*
 * Author Name: Herman Mann
 * Project: # 2 - Involves the writing of a program which will have the ability to
 * examine a file of polynomials and will be able to determine whether the
 * polynomials in that specific file are in ascending order using two different
 * methods of comparison. 
 * Filename: Polynomial.java
 * Date: 02/06/2022
 * Description: This is a Java class a Polynomial class define an individual polynomial. 
 * Polynomial objects should be represented internally by a singly linked list. 
 * Each node of that linked list should contain one term of the polynomial consisting
 * of its coefficient and exponent. It is not permitted to use the predefined Java
 * LinkedList class, but instead must create the nodes of the linked list as instances 
 * of a static nested class inside the Polynomial class. The Polynomial class must 
 * implement both the Iterable and Comparable interfaces.
 */

//Package Name
package cmsc350_hermanmann_project2;

//Import files
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;


/**
 * Class define an individual polynomial. Polynomial objects should be
 * represented internally by a singly linked list. Each node of that linked list
 * should contain one term of the polynomial consisting of its coefficient and
 * exponent. It is not permitted to use the predefined Java LinkedList class,
 * but instead must create the nodes of the linked list as instances of a static
 * nested class inside the Polynomial class. The Polynomial class must implement
 * both the Iterable and Comparable interfaces.
 */
public class Polynomial implements Iterable<Polynomial.PolynomialTerm>, Comparable<Polynomial> {

    //Polynomial term head variable
    private PolynomialTerm polynomialTermHead;
    //Polynomial comparator variable
    private Comparator<Polynomial> polynomialComparator;

    /**
     * A constructor that accepts a string that defines one polynomial in the
     * same format as provided in the input file
     *
     * @param inputLine - a input line string from input file
     */
    public Polynomial(String inputLine) {
        //set polynomial term head to null
        polynomialTermHead = null;
        try {
            //tokenize the string containing the input line of polynomial expression
            StringTokenizer stringTokenizer = Utility.CreateStringTokenizer(inputLine);

            //while there are more tokens
            while (stringTokenizer.hasMoreTokens()) {
                //get the next token and set the polynomical coefficient token
                String polynomialTermCoefficientToken = getNextValidToken(stringTokenizer);
                //get the next token and set the polynomical exponent token
                String polynomialTermExponentToken = getNextValidToken(stringTokenizer);
                //insert the polynomial term in the polynomial list
                insertPolynomialTerm(polynomialTermCoefficientToken, polynomialTermExponentToken);
            }
        } catch (NumberFormatException ex) {
            //throw an exception
            throw new InvalidPolynomialSyntax(Utility.MSG_INVALID_DATA_TYPE);
        }
        catch (Exception ex) {
            //throw an exception
            throw new InvalidPolynomialSyntax(ex.getLocalizedMessage());
        }
    }

    /**
     * Constructor to initialize the polynomial comparator using lambda
     * expression for polynomials
     */
    public Polynomial() {
        polynomialComparator = (Polynomial firstPolynomial, Polynomial secondPolynomial)
                -> firstPolynomial.compareExponents(secondPolynomial);
    }

    /**
     * Constructor to initialize the polynomial comparator from polynomial
     * comparator
     *
     * @param polynomialComparator
     */
    public Polynomial(Comparator<Polynomial> polynomialComparator) {
        this.polynomialComparator = polynomialComparator;
    }

    /**
     * To insert the polynomial term to a polynomial
     *
     * @param polynomialTermCoefficientToken - polynomial term coefficient token
     * @param polynomialTermExponentToken - polynomial term exponent token
     */
    private void insertPolynomialTerm(String polynomialTermCoefficientToken, String polynomialTermExponentToken) {
        //coeffient of polynomial term
        double coefficient = Double.parseDouble(polynomialTermCoefficientToken);
        //exponent of polynomial term
        int exponent = Integer.parseInt(polynomialTermExponentToken);
        //check for negative exponent
        if (exponent < 0) {
            throw new InvalidPolynomialSyntax(Utility.MSG_INVALID_EXPONENT_NEGATIVE);
        }
        //sert current polynomial term
        PolynomialTerm currentPolynomialTerm = polynomialTermHead;
        //check for first polynomial term
        if (currentPolynomialTerm == null) {
            //set polynomial term head as new polynomial term
            polynomialTermHead = new PolynomialTerm(coefficient, exponent);
            //set next polynomial term as null
            polynomialTermHead.nextPolynomialTerm = null;
        } else {
            //loop through until next polynomial term is not null
            while (currentPolynomialTerm.nextPolynomialTerm != null) {
                //set current polynomial term
                currentPolynomialTerm = currentPolynomialTerm.nextPolynomialTerm;
            }

            if (currentPolynomialTerm.exponent < exponent) {
                throw new InvalidPolynomialSyntax(Utility.MSG_INVALID_EXPONENT_ORDER);
            }
            //set next polynomial term from new polynomial term
            currentPolynomialTerm.nextPolynomialTerm = new PolynomialTerm(coefficient, exponent);
        }
    }

    /**
     * To get next valid token from the string tokenizer by eliminating the
     * space token
     *
     * @param stringTokenizer - string tokenizer
     * @return - a valid string token
     */
    private String getNextValidToken(StringTokenizer stringTokenizer) {
        String token = Utility.STR_EMPTY;
        //loop until the has more tokens
        while (stringTokenizer.hasMoreTokens()) {
            //get next token
            token = stringTokenizer.nextToken();
            //check for space and break the loop once get a valid token
            if (!token.equals(Utility.STR_SPACE)) {
                break;
            }
        }
        return token;
    }

    /**
     * To get the polynomial term head
     *
     * @return - polynomial term head
     */
    private PolynomialTerm getPolynomialTermHead() {
        return polynomialTermHead;
    }

    /**
     * An override compareTo method that compares two polynomials. If the two
     * polynomials have different highest order exponents, the one with the
     * highest exponent is the greatest. If their highest exponents are the
     * same, their coefficients are compared. If two polynomials have the same
     * highest order exponent with the same coefficients the next highest
     * exponent is examined, and so on
     *
     * @param polynomialToCompare - polynomial to be compared
     * @return - return integer based on the comparison
     */
    @Override
    public int compareTo(Polynomial polynomialToCompare) {
        //current polynomial term
        PolynomialTerm currentPolynomialTerm = this.polynomialTermHead;
        //polynomial term to compare
        PolynomialTerm polynomialTermToCompare = polynomialToCompare.polynomialTermHead;
        //loop through polynomial terms
        while (currentPolynomialTerm != null && polynomialTermToCompare != null) {
            //check for the exponent part of the polynomial terms
            if (currentPolynomialTerm.getExponent() != polynomialTermToCompare.getExponent()) {
                return currentPolynomialTerm.getExponent() - polynomialTermToCompare.getExponent();
            } else {
                if (currentPolynomialTerm.getCoefficient() != polynomialTermToCompare.getCoefficient()) {
                    if (polynomialTermToCompare.getCoefficient() > currentPolynomialTerm.getCoefficient()) {
                        return Utility.INT_NEGATIVE_RESULT;
                    } else if (polynomialTermToCompare.getCoefficient() < currentPolynomialTerm.getCoefficient()) {
                        return Utility.INT_POSITIVE_RESULT;
                    }
                }
            }
            //new value for the current polynomial term
            currentPolynomialTerm = currentPolynomialTerm.getNextPolynomialTerm();
            //new value for the polynomial term to compare
            polynomialTermToCompare = polynomialTermToCompare.getNextPolynomialTerm();
        }
        //check if both are the null values
        if (currentPolynomialTerm == null && polynomialTermToCompare == null) {
            return Utility.INT_EQUAL_RESULT;
        }
        //check fot the current polynomial term as null
        if (currentPolynomialTerm == null) {
            return Utility.INT_NEGATIVE_RESULT;
        } else {
            return Utility.INT_POSITIVE_RESULT;
        }
    }

    /**
     * To compare the exponent parts of the polynomials to check the weak order
     *
     * @param polynomialToCompare - polynomial exponent to be compared
     * @return - return integer based on the comparison
     */
    public int compareExponents(Polynomial polynomialToCompare) {
        //current polynomial term
        PolynomialTerm currentPolynomialTerm = this.polynomialTermHead;
        //polynomial term to compare
        PolynomialTerm polynomialTermToCompare = polynomialToCompare.polynomialTermHead;
        //loop through polynomial terms
        while (currentPolynomialTerm != null && polynomialTermToCompare != null) {
            //check for the exponent part of the polynomial terms
            if (currentPolynomialTerm.getExponent() != polynomialTermToCompare.getExponent()) {
                return currentPolynomialTerm.getExponent() - polynomialTermToCompare.getExponent();
            } else {
                //new value for the current polynomial term
                currentPolynomialTerm = currentPolynomialTerm.getNextPolynomialTerm();
                //new value for the polynomial term to compare
                polynomialTermToCompare = polynomialTermToCompare.getNextPolynomialTerm();
            }
        }
        //check if both are the null values
        if (currentPolynomialTerm == null && polynomialTermToCompare == null) {
            return Utility.INT_EQUAL_RESULT;
        }
        //check fot the current polynomial term as null
        if (polynomialTermToCompare == null) {
            return Utility.INT_POSITIVE_RESULT;
        } else {
            return Utility.INT_NEGATIVE_RESULT;
        }
    }

    /**
     * An iterator method that override and produces an iterator the iterates
     * across the terms of the polynomial from highest exponent to lowest and
     * returns and an object that contains only the coefficient and exponent of
     * the next term
     *
     * @return iterator polynomial term
     */
    @Override
    public Iterator<PolynomialTerm> iterator() {
        //return iterator
        return new Iterator() 
        
        {
            //current polynomial term 
            private PolynomialTerm currentPolynomialTerm = getPolynomialTermHead();

            //Check whether polynomial has next term or not
            @Override
            public boolean hasNext() {
                return currentPolynomialTerm != null && currentPolynomialTerm.getNextPolynomialTerm() != null;
            }

            //To get the next polynomial term
            @Override
            public PolynomialTerm next() {
                PolynomialTerm polynomialTerm = currentPolynomialTerm;
                currentPolynomialTerm = currentPolynomialTerm.nextPolynomialTerm;
                return polynomialTerm;
            }
        };
    }

    /**
     * To convert the polynomial to the string and add the sign based on the
     * coefficient terms
     *
     * @return - formatted polynomial string
     */
    @Override
    public String toString() {
        StringBuilder expressionBuilder = new StringBuilder();
        expressionBuilder.append(polynomialTermHead.toString());
        for (PolynomialTerm polynomialTerm = polynomialTermHead.nextPolynomialTerm; polynomialTerm != null; polynomialTerm = polynomialTerm.nextPolynomialTerm) {

            if (polynomialTerm.coefficient > 0) {
                expressionBuilder.append(Utility.STR_PLUS_SIGN).append(polynomialTerm.toString());
            } else {
                expressionBuilder.append(Utility.STR_SPACE).append(polynomialTerm.toString());
            }
        }
        return expressionBuilder.toString();
    }

    /**
     * Nested polynomial term class that implements the coefficient and exponent
     * part of the polynomial term
     */
    static class PolynomialTerm {

        //Coefficient part of the polynomial term
        private double coefficient;
        //Exponent part of the polynomial term
        private int exponent;
        //Next polynomial term
        private PolynomialTerm nextPolynomialTerm;

        /**
         * Constructor to initialize the polynomial term and its part
         *
         * @param coefficient - Coefficient part of the polynomial term
         * @param exponent - Exponent part of the polynomial term
         */
        private PolynomialTerm(double coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
            this.nextPolynomialTerm = null;
        }

        /**
         * To get an exponent part of the polynomial term
         */
        private int getExponent() {
            return this.exponent;
        }

        /**
         * To get a coefficient part of the polynomial term
         */
        private double getCoefficient() {
            return this.coefficient;
        }

        /**
         * To get next polynomial term
         */
        private PolynomialTerm getNextPolynomialTerm() {
            return nextPolynomialTerm;
        }

        /**
         * To converts a polynomial to a string. Terms with 0 coefficients
         * should be omitted entirely. The exponent of the term with an exponent
         * of 1 should omit the exponent and the term with exponent 0 should
         * omit the variable x as well.
         *
         * @return - formatted polynomial string
         */
        @Override
        public String toString() {
            //formatted polynomial string
            String polynomialTermString = Utility.DECIMAL_FORMAT.format(coefficient);
            if (exponent == Utility.INT_NO_EXPONENT_NO_VARIABLE) {
                return polynomialTermString;
            } else if (exponent == Utility.INT_NO_EXPONENT_ONLY_VARIABLE) {
                return polynomialTermString + Utility.STR_VARIABLE;
            } else {
                return polynomialTermString + Utility.STR_VARIABLE_WITH_POWER + exponent;
            }
        }
    }
}


