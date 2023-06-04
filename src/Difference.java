public class Difference extends Function{
    Function firstFunc , secondFunc;
    public Difference(Function first, Function second){
        this.firstFunc = first;
        this.secondFunc =second;
    }
    @Override
    public double valueAt(double point){
        return new Sum(this.firstFunc ,new Negation(this.secondFunc)).valueAt(point);
    }
    @Override
    public Function derivative(){
        return new Difference(this.firstFunc.derivative() ,secondFunc.derivative());
    }
    @Override
    public String toString(){
        return ("(" + this.firstFunc.toString() + " - " + this.secondFunc.toString() + ")");
    }
}
