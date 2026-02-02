package cl.alkewallet.model;

/**
 * Representa una Cuenta Corriente dentro del sistema Alke Wallet.
 * Esta clase hereda de Cuenta y establece un límite de saldo significativamente 
 * mayor en comparación con otros productos como la Cuenta RUT.
 * * @author Felipe C
 */
public class CuentaCorriente extends Cuenta {
    
    /** * Límite máximo de saldo permitido para la Cuenta Corriente ($100.000.000).
     * Definido como constante para asegurar la integridad de la regla de negocio.
     */
    public static final double LIMITE_CC = 100000000.0;

    /**
     * Constructor para Cuenta Corriente.
     * Utiliza el constructor de la superclase para inicializar los atributos base.
     * * @param titular Nombre del titular de la cuenta.
     * @param saldoInicial Monto con el que se apertura la cuenta, sujeto a validación.
     */
    public CuentaCorriente(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    /**
     * Registra un depósito tras verificar que no se exceda el límite de 100 millones.
     * Implementa polimorfismo mediante la sobreescritura (Override) del método base.
     * * @param monto Cantidad de dinero a ingresar. Debe ser un valor positivo.
     * @see Cuenta#depositar(double)
     */
    @Override
    public void depositar(double monto) {
        // Valida que el depósito sea positivo y que el saldo total no sobrepase el límite
        if (monto > 0 && (this.saldo + monto) <= LIMITE_CC) {
            super.depositar(monto);
        } else {
            // Manejo de error visual en consola si se intenta superar el límite de la cuenta
            System.out.printf("❌ Error: El saldo total de la Cuenta Corriente no puede superar $%,.0f%n", LIMITE_CC);
        }
    }
}