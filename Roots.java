//-------------------------------------------------------------------------------------------------------------
//
// Roots.java
// This is a program that uses the bisection method(or a binary search) to find the roots of a polynomial.
// It assumes that the monkey at the keyboard enters the correct input when prompted.
//
// Tristan Clark
//
//-------------------------------------------------------------------------------------------------------------

import java.util.Scanner;
import java.lang.Math;

class Roots
{ 

    // poly(double[] C, double x)
    // evaluates the polynomial at x and returns the result
    static double poly(double[] C, double x) {
        double sum = 0.0;
        for(int i = 0; i < C.length; ++i){
            double n = 1.0 * i;
            sum += C[i] * Math.pow(x, n);
        }
        return sum;
    }

    // diff(double[] C)
    // returns the derivative of the polynomial C
    static double[] diff(double[] C) {
        double[] derivative = new double[C.length - 1];
        for(int i = 0; i < derivative.length; ++i){
            derivative[i] = (i+1)*(C[i+1]);
        }
        return derivative;
    }

    // findRoot(double[] C, double a, double b, double tolerance)
    // if poly(C,a) and poly(C,b) have different signs then this will return an approximation
    // of the root of poly(C,x) in the interval [a,b], with an error no more than tolerance.
    // precondition: poly(C,a) and poly(C,b) return different signs
    static double findRoot(double[] C, double a, double b, double tolerance) {
        double m = (a + b)/2;
        double width = b - a;
        while(width>tolerance) {
            if(Math.signum(poly(C,a)) != Math.signum(poly(C,m))){
                return findRoot(C,a,m,tolerance);
            }
            else {
                return findRoot(C,m,b,tolerance);
            }
        }
        return m;
    }

    // isNeg(double number)
    // takes a number and returns true if its negative and false if positive and true otherwise
    static boolean isNeg(double number){
        if(number < 0.0) return true;
        else if(number > 0.0) return false;
        else return true; //this might be bad practice
    }

    public static void main(String args[]) 
    { 
        // tolerance and threshold
        double tolerance = 0.00000000001;
        double threshold = 0.001;

        // get input for the users degree and store it in variable
        Scanner myDeg = new Scanner(System.in);
        System.out.println("Enter the degree: "); 
        int degree = myDeg.nextInt();

        // get input from user to find the poly array
        Scanner myCoef = new Scanner(System.in);
        System.out.println("Enter " + (degree + 1) + " coefficients: ");
        String data = myCoef.nextLine();

        // splits the string input into an array
        String tmpDataArr[] = data.split(" ");

        // converts the string array to a double array
        double[] polynomial = new double[tmpDataArr.length];
        for(int i = 0; i < polynomial.length; ++i){
            polynomial[i] = Double.parseDouble(tmpDataArr[i]);
        }

        // creates a derivative array and sets up the variables used in the continuous loop
        double[] derivative = diff(polynomial);
        Scanner scan = new Scanner(System.in);
        double start;
        double end;
        double root;

        // Will ask the user for endpoints until q is enterd
        while(true){
            System.out.println("Enter endpoints, or q to quit: ");
            if(scan.hasNext("q")){
                break;
            }
            
            start = scan.nextDouble();
            end = scan.nextDouble();

            if(isNeg(poly(polynomial, start)) != isNeg(poly(polynomial, end))) {
                System.out.print("Odd root in [" + start + ", " + end + "] found at: ");
                System.out.printf("%.10f%n",findRoot(polynomial, start, end, tolerance));
            }
            else if (isNeg(poly(derivative, start)) != isNeg(poly(derivative, end))) {
                root = findRoot(derivative, start, end, tolerance);
                if(Math.abs(poly(polynomial,root)) < threshold){
                    System.out.print("Even root in [" + start + ", " + end + "] found at: ");
                    System.out.printf("%.10f%n",root);
                } else {
                    System.out.println("No root found.");
                }
            } else {
                System.out.println("No root found in [" + start + ", " + end + "]");
            }
        }
        System.out.println("bye!");
        myDeg.close();
        myCoef.close();
        scan.close();
    } 
}