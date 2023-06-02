public class Quotient extends Function{
    protected Function numerator;
    protected Function denominator;
    public Quotient(Function numerator, Function denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }
    /**
     * Calculating value of multiProduct at the given point
     * @param point double value in which we want to calculate value of quotient function
     * @return value of function at point
     */
    @Override
    public  double valueAt(double point){
        return (this.numerator.valueAt(point) / this.denominator.valueAt(point));
    }
    /**
     * Calculates derivative of quotient function
     * @return Function = this function's derivative
     */
    @Override
    public Function derivative(){
        // new numerator = numerator derivative * denominator - numerator * (denominator derivative)
        // new denominator = denominator power of 2
        Function newNumerator = new Sum(new Product(this.numerator.derivative(), this.denominator),
                new Negation(new Product(this.numerator, this.denominator.derivative())));
        Function newDenominator = new Power(this.denominator, 2);
        return new Quotient(newNumerator, newDenominator);
    }
    @Override
    public String toString(){
        return ("(" + this.numerator.toString() + " / " + this.denominator.toString() + ")");
    }

}
