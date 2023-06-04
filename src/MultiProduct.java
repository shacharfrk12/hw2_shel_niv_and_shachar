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
    @Override
    public double valueAt(double point){
        double value = 1;
        for (Function function : this.functions) {
            value *= function.valueAt(point);
        }
        return value;
    }
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
    @Override
    public MultiSum derivative(){
        //taking each function and multiplying its derivative with all other functions
        //first and second functions
        Function first = this.kDerivative(0);
        Function second = this.kDerivative(1);
        //other functions
        Function[] funcToSum = new Function[this.functions.length - 2];
        for (int i = 2; i < this.functions.length; i++) {
            funcToSum[i-2] = this.kDerivative(i);
        }
        //sum of all multiplication
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

    /**
     * taking a function and multiplying its derivative with all other functions
     * @param k index of function in functions array
     * @return multiProduct of this function's derivative with all other functions
     */
    private MultiProduct kDerivative(int k){
        int startSame = 0;
        Function second = this.functions[0];
        Function derivativeFunc = this.functions[k].derivative();
        // array of new functions starting from function indexes 2
        Function[] newFunctions = new Function[functions.length-2];
        if(k == 0){
            second = this.functions[1];
        }
        //taking all functions forward until k (overriding function k)
        else if (k>=2){
            for(int i = 0; i <= k - 2;i++){
                newFunctions[i] = this.functions[i+1];
            }
            startSame = k-1;
        }
        //copying all functions after k
        for(int i = startSame; i < newFunctions.length; i++){
            newFunctions[i] = this.functions[i+2];
        }
        return new MultiProduct(derivativeFunc, second, newFunctions);
    }
}
