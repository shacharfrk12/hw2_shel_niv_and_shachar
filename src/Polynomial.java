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
        double[] ad = new double[Coefficients.length - 1];
        for (int i = 0; i < Coefficients.length - 1; i++) {
            ad[i] = Coefficients[i + 1] * (i + 1);
        }
        return new Polynomial(ad);
    }
}
