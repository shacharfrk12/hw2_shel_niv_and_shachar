public class MultiSum extends Function {
    protected Function[] funcToSum;
    public MultiSum(Function first, Function second, Function...a){
        int length = 2 + a.length;
        this.funcToSum = new Function[length];
        this.funcToSum[0] = first;
        this.funcToSum[1] = second;
        for (int i = 2; i < length; i++) {
            this.funcToSum[i] = a[i - 2];
        }
    }
    @Override
    public  double valueAt(double point){
        double value = 0;
        for(int i = 2 ; i < this.funcToSum.length; i++){
            value += this.funcToSum[i].valueAt(point);
        }
        return funcToSum[0].valueAt(point) + funcToSum[1].valueAt(point) + value;
    }
    @Override
    public  Function derivative(){
        Function firstDer  = this.funcToSum[0].derivative(), secondDer = this.funcToSum[1].derivative();
        if(this.funcToSum.length == 2)
            return new Sum(firstDer, secondDer);

        Function[] derFunc = new Function[this.funcToSum.length - 2];
        for(int i = 2; i < this.funcToSum.length; i++){
            derFunc[i - 2] = this.funcToSum[i].derivative();
        }

        return new MultiSum(firstDer, secondDer, derFunc);
    }
    @Override
    public String toString(){
        String str = "(" + funcToSum[0].toString();
        for (int i = 1; i < funcToSum.length; i++) {
            str += " + " + funcToSum[i].toString() ;
        }
        return str + ")";
    }
}

