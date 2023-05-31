public class MultiSum extends Function {
    protected Function[] funcToSum;
    public MultiSum(Function first, Function second, Function...a){
        int length = 2 + a.length;
        this.funcToSum = new Function[length];
        this.funcToSum[0] = first;
        this.funcToSum[1] = second;
        for (int i = 2; i < length; i++) {
            this.funcToSum[i] = a[i];
        }
    }
    @Override
    public  double valueAt(double point){
        double value = 0;
        for(int i = 2 ; i < this.funcToSum.length; i++){
            value += this.funcToSum[i].valueAt(point);
        }
        return value + funcToSum[0].valueAt(point) + funcToSum[1].valueAt(point);
    }
    @Override
    public  Function derivative(){
        Function[] derFunc = new Function[this.funcToSum.length - 2];
        Function firstDer  = this.funcToSum[0].derivative(), secondDer = this.funcToSum[1].derivative();
        for(int i = 2; i < this.funcToSum.length; i++){
            derFunc[i] = this.funcToSum[i].derivative();
        }

        return new MultiSum(firstDer, secondDer, derFunc);
    }
}

