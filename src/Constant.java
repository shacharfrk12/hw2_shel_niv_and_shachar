import javax.management.StringValueExp;

public class Constant extends Function{
    protected final double value;
    public Constant(double value){
        this.value = value;
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
    public String toString(){
        return String.valueOf(value);
    }
}
