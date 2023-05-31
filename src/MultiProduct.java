public class MultiProduct extends Function{
    protected Function[] functions;
    public MultiProduct(Function first, Function second, Function...functions){
        this.functions = new Function[2+functions.length];
        this.functions[0] = first;
        this.functions[1] = second;
        for (int i = 2; i < functions.length; i++) {
            this.functions[i] = functions[i];
        }
    }
    public MultiProduct(MultiProduct other){
        this.functions = new Function[other.functions.length];
        for (int i = 0; i < this.functions.length; i++) {
            this.functions[i] = other.functions[i];
        }
    }
    @Override
    public double valueAt(double point){
        double value = 0;
        for (int i = 0; i < this.functions.length; i++) {
            value *= this.functions[i].valueAt(point);
        }
        return value;
    }
    @Override
    public Function derivative(){
        Function[] firstSecond = new Function[2];
        for (int i = 0; i < 2; i++) {
            MultiProduct currentProduct = new MultiProduct(this);
            currentProduct.functions[i] = currentProduct.functions[i].derivative();
        }

        Function[] sumFunctions = new Function[this.functions.length-2];
        for (int i = 0; i < this.functions.length; i++) {
            MultiProduct currentProduct = new MultiProduct(this);
            currentProduct.functions[i] = currentProduct.functions[i].derivative();
        }

        return new MultiSum(firstSecond[0], firstSecond[1], sumFunctions);
    }
}
