package cl.alkewallet.model;

public class CuentaRut extends Cuenta {
    public static final double LIMITE_RUT = 5000000.0;

    public CuentaRut(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public void depositar(double monto) {
        if (monto > 0 && (this.saldo + monto) <= LIMITE_RUT) {
            super.depositar(monto);
        } else {
            System.out.printf("❌ Error: El saldo total no puede superar $%,.0f%n", LIMITE_RUT);
        }
    }
}