public abstract class Function {
    protected static final double epsilon = Math.pow(10, -5);
    /**
     * Calculates value of function at the given point
     * @param point double value in which we want to calculate value of function
     * @return value of function at point
     */
    public abstract double valueAt(double point);
    /**
     * Calculates derivative of function
     * @return Function - this function's derivative
     */
    public abstract Function derivative();

    /**
     * Calculates functions root between a and b with a max error value of epsilon
     * @param a left end of the interval
     * @param b right end of the interval
     * @param epsilon error range
     * @return double value - a root of the function in range [a, b]
     */
    public double bisectionMethod(double a, double b, double epsilon){
        double left = a;
        double right = b;
        while(right - left > epsilon){
            double mid = (left + right) / 2;
            if((this.valueAt(left) * this.valueAt(mid)) > 0){
                left = mid;
            }
            else right = mid;
        }
        return (left + right) / 2 ;
    }
    /**
     * Calculates functions root between a and b with a max error value of default epsilon defines as
     * static class attribute
     * @param a left end of the interval
     * @param b right end of the interval
     * @return double value - an estimated root of the function in range [a, b]
     */
    public double bisectionMethod(double a, double b){
        return bisectionMethod(a, b, epsilon);
    }

    /**
     * Calculates functions root around point a with a max error value of epsilon
     * @param a point around which to search for root
     * @param epsilon error range
     * @return double value - an estimated root of the function around a
     */
    public double newtonRaphsonMethod(double a, double epsilon){
        double element = a;
        double elementValue = this.valueAt(a);
        Function derivative = this.derivative();
        while(Math.abs(elementValue) >=  epsilon){
            element = element - (elementValue / derivative.valueAt(element));
            elementValue = this.valueAt(element);
        }
        return element;
    }
    /**
     * Calculates functions root around point a with a max error value of default epsilon defines as
     *      * static class attribute
     * @param a point around which to search for root
     * @return double value - an estimated root of the function around a
     */
    public double newtonRaphsonMethod(double a){
        return newtonRaphsonMethod(a, epsilon);
    }

    /**
     * taylor polynomial of function around the point zero and of degree n
     * @param n degree of taylor polynomial
     * @return taylor polynomial of n degree around zero
     */
    public Polynomial taylorPolynomial(int n){
        double[] taylor = new double[n + 1] ;
        double currValue = this.valueAt(0) ;
        Function derivFunc = this;
        for(int i = 0 ; i <= n; i++){
            taylor[i] = (currValue/factorial(i));
            currValue = derivFunc.derivative().valueAt(0);
            derivFunc = derivFunc.derivative();

        }
        return new Polynomial(taylor);
    }

    /**
     * An implementation of factorial arithmetic function
     * @param n number to calculate it's factorial
     * @return n factorial
     */
    public double factorial(int n){
        if(n == 0)
            return 1;
        double res = 1;
        for(int i = 1; i <= n; i++){
            res *= i;
        }
        return res;
    }
}
