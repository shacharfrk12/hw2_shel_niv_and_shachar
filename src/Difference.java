public class Difference extends Function{
    Function firstFunc , secondFunc;
    public Difference(Function first, Function second){
        this.firstFunc = first;
        this.secondFunc =second;
    }
    @Override
    public double valueAt(double point){
        return new Sum(firstFunc ,new Negation(secondFunc)).valueAt(point);
    }
    @Override
    public Function derivative(){
        return  new Sum(firstFunc ,new Negation(secondFunc)).derivative();
    }
}
