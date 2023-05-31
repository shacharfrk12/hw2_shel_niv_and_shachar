public class Negation extends Function{
    protected Function function;

    public Negation(Function function){
        this.function = function;
    }
    @Override
    public double valueAt(double point){
        return (-1)* function.valueAt(point);
    }

    @Override
    public Function derivative(){
        return new Negation(function.derivative());
    }
}
