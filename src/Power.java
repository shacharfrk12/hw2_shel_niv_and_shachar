public class Power extends Function{
    protected Function function;
    protected int power;

    public Power(Function function, int power){
        this.function = function;
        this.power = power;
    }
    /**
     * Calculates value of multiProduct at the given point
     * @param point double value in which we want to calculate value of powe fucntion
     * @return value of function at point
     */
    @Override
    public double valueAt(double point){
        return Math.pow(this.function.valueAt(point), this.power);
    }
    /**
     * Calculates derivative of power function
     * @return Function = this function's derivative
     */
    @Override
    public Function derivative() {
        if (this.power == 1)
            return this.function.derivative();
        Function mainDer = new Power(this.function, power - 1);
        return new MultiProduct(new Constant(this.power), mainDer, this.function.derivative());
    }
    @Override
    public String toString(){
        return ("(" + this.function.toString() + "^" + this.power + ")");
    }
}
