public class Constant extends Polynomial{
    protected final double value;
    public Constant(double value){
        super(value);
        this.value = this.Coefficients[0];
    }
    @Override
    public double valueAt(double point){
        return this.value;
    }
    @Override
    public Constant derivative(){
        return new Constant(0);
    }
    @Override
    public Constant taylorPolynomial(int n){
        return this;
    }
    @Override
    public String toString(){
        String str = "(" + value + ")";
        return str;
    }
}
