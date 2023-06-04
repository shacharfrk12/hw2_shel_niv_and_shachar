public class Negation extends Function{
    protected Function function;

    public Negation(Function function){
        this.function = function;
    }

    @Override
    public double valueAt(double point){
        return (-1) * this.function.valueAt(point);
    }

    /**
     * Calculates derivative of negation function
     * @return Function = this function's derivative
     */
    @Override
    public Function derivative(){
        return new Negation(this.function.derivative());
    }

    @Override
    public String toString(){
        return "(-" + this.function.toString() + ")";
    }
}
