import javax.print.attribute.standard.Finishings;

public class MultiProduct extends Function{
    protected Function[] functions;
    public MultiProduct(Function first, Function second, Function...functions){
        // to make sure product is of at least two functions,
        // we require there to be two function elements separate from all
        this.functions = new Function[2+functions.length];
        this.functions[0] = first;
        this.functions[1] = second;
        //inserting functions indexes 2 and above to the array attribute
        for (int i = 2; i < functions.length + 2; i++) {
            this.functions[i] = functions[i-2];
        }
    }

    /**
     * Copy Constructor
     * @param other other multiProduct object to clone
     */
    public MultiProduct(MultiProduct other){
        this.functions = new Function[other.functions.length];
        for (int i = 0; i < this.functions.length; i++) {
            this.functions[i] = other.functions[i];
        }
    }

    /**
     * Calculates value of multiProduct at the given point
     * @param point double value in which we want to calculate value of multiProduct
     * @return value of function at point
     */
    @Override
    public double valueAt(double point){
        double value = 1;
        for (int i = 0; i < this.functions.length; i++) {
            value *= this.functions[i].valueAt(point);
        }
        return value;
    }

    /**
     * Calculates derivative of multiProduct function
     * @return Function = this function's derivative
     */
    public MultiSum derivative2(){
        // derivative taking first and second functions as derivatives
        MultiProduct[] firstSecond = new MultiProduct[2];
        for (int i = 0; i < 2; i++) {
            MultiProduct currentProduct = new MultiProduct(this);
            currentProduct.functions[i] = this.functions[i].derivative();
            firstSecond[i] = currentProduct;
        }
        if(this.functions.length == 2)
            return new Sum(firstSecond[0], firstSecond[1]);

        //derivatives with all other functions
        MultiProduct[] sumFunctions = new MultiProduct[this.functions.length-2];
        for (int i = 2; i < this.functions.length; i++) {
            MultiProduct currentProduct = new MultiProduct(this);
            currentProduct.functions[i] = this.functions[i].derivative();
            sumFunctions[i-2] = currentProduct;
        }
        return new MultiSum(firstSecond[0], firstSecond[1], sumFunctions);
    }
    //@Override
    public MultiSum derivative(){
        Function first = this.kDerivative(0);
        Function second = this.kDerivative(1);
        Function[] funcToSum = new Function[this.functions.length - 2];
        for (int i = 2; i < this.functions.length; i++) {
            funcToSum[i-2] = this.kDerivative(i);
        }
        return new MultiSum(first, second, funcToSum);
    }
    @Override
    public String toString(){
        String productStr = "";
        for (int i = 0; i < this.functions.length; i++) {
            productStr += this.functions[i].toString();
            if(i<this.functions.length-1)
                productStr += " * ";
        }
        return "(" + productStr + ")";
    }

    private MultiProduct kDerivative(int k){
        int startSame = 0;
        Function second = this.functions[0];
        Function derivativeFunc = this.functions[k].derivative();
        Function[] newFunctions = new Function[functions.length-2];
        if(k == 0){
            second = this.functions[1];
        }
        else if (k>=2){
            for(int i = 0; i <= k - 2;i++){
                newFunctions[i] = this.functions[i+1];
            }
            startSame = k-1;
        }
        for(int i = startSame; i < newFunctions.length; i++){
            newFunctions[i] = this.functions[i+2];
        }
        return new MultiProduct(derivativeFunc, second, newFunctions);
    }
}
