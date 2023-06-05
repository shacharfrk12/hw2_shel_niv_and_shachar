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
        // if the free coefficient isn't zero, we print it
        if (Coefficients[0] != 0)
            str += coefficientStr(Coefficients[0], true, true);
        else{
            //finding first non-zero coefficient
            while (firstI < Coefficients.length && Coefficients[firstI] == 0) {
                firstI++;
            }
            //and printing it
            if (firstI < Coefficients.length) {
                str += coefficientStr(Coefficients[firstI], true, false) + variableStr(Coefficients[firstI], firstI);
            }
        }
        //printing every other coefficient with a sign before it
        for (int i = firstI+1; i < Coefficients.length; i++) {
            double currA = Coefficients[i];
            str += coefficientSign(currA) + coefficientStr(currA, false, false) + variableStr(currA, i);
        }
        //if all coefficients are zero we print zero
        if(str.equals(""))
            return "(0)";
        //putting function in brackets
        return "(" + str + ")";
    }

    /**
     * Generating specific coefficient sign (plus or minus)
     * @param coefficient value of a specific coefficient
     * @return + if the coefficient is positive, - if negative, empty string otherwise
     */
    static private String coefficientSign(double coefficient) {
        if (coefficient == 0)
            return "";
        if (coefficient < 0)
            return " - ";
        return " + ";
    }

    /**
     * Generating coefficient value string
     * @param coefficient value of a specific coefficient
     * @param isFirst boolean - if it's the first non-zero coefficient
     * @param isBasic boolean - if it's the basic (free) coefficient
     * @return string that represents coefficient value
     */
    static private String coefficientStr(double coefficient , boolean isFirst, boolean isBasic) {
        // 0, 1 and -1 coefficients aren't presented unless it's the free coefficient
        if (!isBasic && (coefficient == 0 || coefficient == 1 || coefficient == -1)){
            return "";
        }
        // first non-zero coefficient is presented with its sign and isn't seperated from it
        if(isFirst){
            // if this is a whole number we'll present it as int and not double
            if (coefficient % 1 == 0) {
                return String.valueOf((int) coefficient);
            }
            return String.valueOf(coefficient);
        }
        //others are presented as absolute value, sigh function will handle their sign presentation
        else if (coefficient % 1 == 0) {
            // if this is a whole number we'll present it as int and not double
            return String.valueOf(Math.abs((int)coefficient));
        }
        return String.valueOf(Math.abs(coefficient));
    }

    /**
     * Generating coefficient's variable string
     * @param coefficient value of a specific coefficient
     * @param i index of coefficient in the coefficients array
     * @return string representation of coefficient's variable
     */
    static private String variableStr(double coefficient, int i){
        //if the coefficient is zero the variable will not be shown
        if (coefficient == 0)
            return "";
        // if the coefficient is the free coefficient it has no variable
        if(i == 0)
            return "";
        //for the first coefficient - its variable it x in the power of 1, which is simply x
        if(i==1)
            return "x";
        //for all other coefficients the variable will be x in the power of i
        return "x^" + i;
    }
    @Override
    public Polynomial taylorPolynomial(int n){
        // for polynomials, taylor polynomial of each degree will be identical to the
        // polynomial discarding all degrees larger than n
        double[] taylor = new double[Math.min(n+1, this.Coefficients.length)];
        for(int i = 0; i<Math.min(n+1, this.Coefficients.length); i++){
            taylor[i] = this.Coefficients[i];
        }
        return new Polynomial(taylor);
    }

}
