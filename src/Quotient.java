public class Quotient extends Function{
    protected Function numerator;
    protected Function denominator;
    public Quotient(Function numerator, Function denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }
    @Override
    public  double valueAt(double point){
        return (this.numerator.valueAt(point) / this.denominator.valueAt(point));
    }
    @Override
    public Function derivative(){
        // new numerator = numerator derivative * denominator - numerator * (denominator derivative)
        // new denominator = denominator power of 2
        Function newNumerator = new Difference(new Product(this.numerator.derivative(), this.denominator),
                new Product(this.denominator.derivative(), this.numerator));
        Function newDenominator = new Power(this.denominator, 2);
        return new Quotient(newNumerator, newDenominator);
    }
    @Override
    public String toString(){
        return ("(" + this.numerator.toString() + " / " + this.denominator.toString() + ")");
    }

}
