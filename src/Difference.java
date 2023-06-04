public class Difference extends Function{
    Function firstFunc , secondFunc;
    public Difference(Function first, Function second){
        this.firstFunc = first;
        this.secondFunc =second;
    }
    /**
     * Calculates value of Differance at the given point
     * @param point double value in which we want to calculate value of difference
     * @return value of function at point
     */
    @Override
    public double valueAt(double point){
        return new Sum(this.firstFunc ,new Negation(this.secondFunc)).valueAt(point);
    }
    /**
     * Calculates derivative of difference function
     * @return Function = this function's derivative
     */
    @Override
    public Function derivative(){
        return new Difference(this.firstFunc.derivative() ,secondFunc.derivative());
    }
    @Override
    public String toString(){
        return ("(" + this.firstFunc.toString() + " - " + this.secondFunc.toString() + ")");
    }
}
