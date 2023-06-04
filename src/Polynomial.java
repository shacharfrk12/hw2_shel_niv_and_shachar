public class Polynomial extends Function {
    protected double[] Coefficients;

    public Polynomial(double... a) {
        this.Coefficients = a;
    }

    @Override

    public double valueAt(double point) {
        double value = 0;
        for (int i = 0; i < Coefficients.length; i++) {
            value += Coefficients[i] * Math.pow(point, i);
        }
        return value;
    }

    @Override

    public Polynomial derivative() {
        if (Coefficients.length == 1) {
            return new Constant(0);
        }
        double[] ad = new double[Coefficients.length - 1];
        for (int i = 0; i < Coefficients.length - 1; i++) {
            ad[i] = Coefficients[i + 1] * (i + 1);
        }
        return new Polynomial(ad);
    }


    public String toString1() {
        String str = "";
        if (Coefficients.length == 0)
            return str;
        if (Coefficients[0] != 0)
            str += Coefficients[0];

        for (int i = 1; i < this.Coefficients.length; i++) {
            if (Coefficients[i] == 0) {
                continue;
            }
            if (Coefficients[i] == 1) {
                str += " + x^" + i;
                continue;
            }
            if (Coefficients[i] == -1) {
                str += " - x^" + i;
                continue;
            }

            if (Coefficients[i] > 0) {
                if (Coefficients[i] % 1 == 0) {
                    str += " + " + (int) Coefficients[i] + "x^" + i;
                } else str += " + " + Coefficients[i] + "x^" + i;
            } else if (Coefficients[i] % 1 == 0) {
                str += " - " + (int) Math.abs(Coefficients[i]) + "x^" + i;
                continue;
            } else str += " - " + Math.abs(Coefficients[i]) + "x^" + i;

        }
        return "(" + str + ")";
    }
    @Override
    public String toString(){
        String str = "";
        int firstI = 0;
        if (Coefficients.length == 0)
            return str;
        if (Coefficients[0] != 0)
            str += coefficientStr(Coefficients[0], true, true);
        else{
            //finding first non-zero coefficient
            while (firstI < Coefficients.length && Coefficients[firstI] == 0) {
                firstI++;
            }
            if (firstI < Coefficients.length) {
                str += coefficientStr(Coefficients[firstI], true, false) + variableStr(Coefficients[firstI], firstI);
            }
        }

        for (int i = firstI+1; i < Coefficients.length; i++) {
            double currA = Coefficients[i];
            str += coefficientSign(currA) + coefficientStr(currA, false, false) + variableStr(currA, i);
        }
        if(str.equals(""))
            return "(0)";
        return "(" + str + ")";
    }

    static private String coefficientSign(double coefficient) {
        if (coefficient == 0)
            return "";
        if (coefficient < 0)
            return " - ";
        return " + ";
    }

    static private String coefficientStr(double coefficient , boolean isFirst, boolean isBasic) {
        if (!isBasic && (coefficient == 0 || coefficient == 1 || coefficient == -1)){
            return "";
        }
        if(isFirst){
            if (coefficient % 1 == 0) {
                return String.valueOf((int) coefficient);
            }
            return String.valueOf(coefficient);
        }
        else if (coefficient % 1 == 0) {
            return String.valueOf(Math.abs((int)coefficient));
        }
        return String.valueOf(Math.abs(coefficient));
    }

    static private String variableStr(double coefficient, int i){
        if (coefficient == 0)
            return "";
        if(i == 0)
            return "";
        if(i==1)
            return "x";
        return "x^" + i;
    }

    @Override
    public Polynomial taylorPolynomial(int n){
        double[] taylor = new double[Math.min(n+1, this.Coefficients.length)];
        for(int i = 0; i<Math.min(n+1, this.Coefficients.length); i++){
            taylor[i] = this.Coefficients[i];
        }
        return new Polynomial(taylor);
    }
}
