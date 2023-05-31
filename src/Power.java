public class Power extends Function{
    protected Function function;
    protected double power;

    public Power(Function function, double power){
        this.function = function;
        this.power = power;
    }

    @Override
    public double valueAt(double point){
        return Math.pow(function.valueAt(point), power);
    }

    @Override
    public Function derivative() {
        if (power == 1)
            return function.derivative();
        return new MultiProduct(new Constant(power), new Power(function, power - 1), function.derivative());
    }
}
