public class Quotient extends Function{
    protected Function numerator;
    protected Function denominator;
    public Quotient(Function numerator, Function denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }
    @Override
    public  double valueAt(double point){
        return numerator.valueAt(point) - denominator.valueAt(point);
    }
    @Override
    public Function derivative(){
        // new numerator = numerator derivative * denominator - numerator * (denominator derivative)
        // new denominator = denominator power of 2
        Function newNumerator = new Sum(new Product(numerator.derivative(), denominator), new Negation(new Product(numerator, denominator.derivative())));
        Function newDenominator = new Power(denominator, 2);
        return new Quotient(newNumerator, newDenominator);
    }

}
