public abstract class Function {
    protected static final double epsilon = Math.pow(10, -5);
    public abstract double valueAt(double point);
    public abstract Function derivative();
    public double bisectionMethod(double a, double b, double epsilon){
        double left = a;
        double right = b;
        while(right -left > epsilon){
            double mid = (right + left) / 2;
            if((this.valueAt(a) * this.valueAt(b)) < 0){
                left = mid;
            }
            else right = mid;
        }
        return (right + left) / 2 ;
    }

    public double newtonRaphsonMethod(double a, double epsilon){
        double element = a;
        double elementValue = this.valueAt(a);
        while(Math.abs(elementValue) >  epsilon){
            element = element - (elementValue / this.derivative().valueAt(element));
            elementValue = this.valueAt(element);
        }
        return element;
    }
    public double newtonRaphsonMethod(double a){
        return newtonRaphsonMethod(a, epsilon);
    }
    public Polynomial taylorPolynomial(int n){
        double[] taylor = new double[n] ;
        double currValue = this.valueAt(0) ;
        Function derivFunc = this;
        for(int i = 0 ; i < n; i++){
            taylor[i] = (currValue/factorial(i));
            currValue = derivFunc.derivative().valueAt(0);
            derivFunc = derivFunc.derivative();
        }
        return new Polynomial(taylor);
    }
    public int factorial(int n){
        if(n == 0)
            return 1;
        int res = 1;
        for(int i = 1; i <= n; i ++ ){
            res *= i;
        }
        return res;
    }
    @Override
    public String toString(){

    }

}
