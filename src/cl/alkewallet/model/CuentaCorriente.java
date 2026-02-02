package cl.alkewallet.model;

public class CuentaCorriente extends Cuenta {
    public static final double LIMITE_CC = 100000000.0;

    public CuentaCorriente(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public void depositar(double monto) {
        if (monto > 0 && (this.saldo + monto) <= LIMITE_CC) {
            super.depositar(monto);
        } else {
            System.out.printf("❌ Error: El saldo total no puede superar $%,.0f%n", LIMITE_CC);
        }
    }
}