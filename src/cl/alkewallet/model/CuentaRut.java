package cl.alkewallet.model;

/**
 * Representa una Cuenta RUT, una especialización de Cuenta con límites específicos.
 * Aplica reglas de negocio propias de la banca chilena para este producto.
 * * @author Felipe C
 */
public class CuentaRut extends Cuenta {
    /** Límite máximo de saldo permitido para la Cuenta RUT ($5.000.000) */
    public static final double LIMITE_RUT = 5000000.0;

    /**
     * Constructor para Cuenta RUT. Invoca al constructor de la superclase Cuenta.
     * * @param titular Nombre del dueño de la cuenta.
     * @param saldoInicial Monto de apertura, validado previamente en la capa de aplicación.
     */
    public CuentaRut(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    /**
     * Realiza un depósito validando que el saldo resultante no exceda el límite de $5.000.000.
     * Sobrescribe el comportamiento base para aplicar la regla de negocio de Cuenta RUT.
     * * @param monto Cantidad de dinero a ingresar a la cuenta.
     * @see Cuenta#depositar(double)
     */
    @Override
    public void depositar(double monto) {
        // Verifica que el monto sea positivo y que la suma no supere el tope máximo
        if (monto > 0 && (this.saldo + monto) <= LIMITE_RUT) {
            super.depositar(monto);
        } else {
            // Informa al usuario sobre el exceso del límite permitido
            System.out.printf("❌ Error: El saldo total no puede superar $%,.0f%n", LIMITE_RUT);
        }
    }
}