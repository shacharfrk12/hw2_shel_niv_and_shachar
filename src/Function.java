public abstract class Function {
    public abstract double valueAt(double point);
    public abstract Function derivative();
    public abstract double bisectionMethod(int a, int b, int epsilon);
    public abstract double newtonRaphsonMethod(int a, int b, int epsilon);
    public abstract double newtonRaphsonMethod(int a);
    public abstract Function taylorPolynomial(int n);
    @Override
    public String toString(){

    }



}
