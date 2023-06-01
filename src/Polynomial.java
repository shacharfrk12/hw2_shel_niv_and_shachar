public class Polynomial extends Function {
    protected double[] Coefficients;

    public Polynomial(double... a) {
        this.Coefficients = a;
    }

    @Override

    public double valueAt(double point) {
        int value = 0;
        for (int i = 0; i < Coefficients.length; i++) {
            value += Coefficients[i] * Math.pow(point, i);
        }
        return value;
    }

    @Override

    public Polynomial derivative() {
        if(Coefficients.length == 1){
            return new Constant(0);
        }
        double[] ad = new double[Coefficients.length - 1];
        for (int i = 0; i < Coefficients.length - 1; i++) {
            ad[i] = Coefficients[i + 1] * (i + 1);
        }
        return new Polynomial(ad);
    }

    @Override
    public String toString() {
        String str = "";
        if(Coefficients.length == 0)
            return str;
        if (Coefficients[0] != 0) {
            str += Coefficients[0];
        }
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
                    str += " + " + (int) Coefficients[i] + "x^" + i ;
                    continue;
                }
                else str += " + " + Coefficients[i] + "x^" + i ;
            }
            else if (Coefficients[i] % 1 == 0) {
                str += " - " + (int) Math.abs(Coefficients[i]) + "x^" + i ;
                continue;
            }
            else str += " - " + Math.abs(Coefficients[i]) + "x^" + i ;

        }
        return str;
    }
}
