public class MultiProduct extends Function{
    protected Function[] functions;
    public MultiProduct(Function first, Function second, Function...functions){
        // to make sure product is of at least two functions,
        // we require there to be two function elements separate from all
        this.functions = new Function[2+functions.length];
        this.functions[0] = first;
        this.functions[1] = second;
        //inserting functions indexes 2 and above to the array attribute
        for (int i = 2; i < functions.length; i++) {
            this.functions[i] = functions[i];
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
        double value = 0;
        for (int i = 0; i < this.functions.length; i++) {
            value *= this.functions[i].valueAt(point);
        }
        return value;
    }

    /**
     * Calculates derivative of multiProduct function
     * @return Function = this function's derivative
     */
    @Override
    public Function derivative(){
        // derivative taking first and second functions as derivatives
        Function[] firstSecond = new Function[2];
        for (int i = 0; i < 2; i++) {
            MultiProduct currentProduct = new MultiProduct(this);
            currentProduct.functions[i] = currentProduct.functions[i].derivative();
        }
        //derivatives with all other functions
        Function[] sumFunctions = new Function[this.functions.length-2];
        for (int i = 0; i < this.functions.length; i++) {
            MultiProduct currentProduct = new MultiProduct(this);
            currentProduct.functions[i] = currentProduct.functions[i].derivative();
        }
        return new MultiSum(firstSecond[0], firstSecond[1], sumFunctions);
    }
    @Override
    public String toString(){
        String productStr = "";
        for (int i = 0; i < this.functions.length; i++) {
            productStr += "("+this.functions[i].toString()+")";
            if(i<this.functions.length-1)
                productStr += " * ";
        }
        return productStr;
    }
}
