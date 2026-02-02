package cl.alkewallet.model;

/**
 * Interfaz que define el contrato de operaciones esenciales para cualquier 
 * tipo de cuenta dentro del ecosistema Alke Wallet.
 * Establece los métodos obligatorios que garantizan el comportamiento 
 * de una billetera digital.
 * * @author Felipe C
 */
public interface IWallet {
    
    /**
     * Define la operación de ingreso de dinero a la cuenta.
     * Las clases que implementen este método deben incluir validaciones de 
     * límites de saldo según el tipo de cuenta.
     * * @param monto Cantidad de dinero a depositar.
     */
    void depositar(double monto);

    /**
     * Define la operación de extracción de dinero.
     * @param monto Cantidad de dinero a retirar.
     * @return true si la operación es exitosa (fondos suficientes), 
     * false en caso contrario.
     */
    boolean retirar(double monto);

    /**
     * Define la obtención del saldo actual de la cuenta en moneda local (CLP).
     * @return El saldo disponible como un valor de tipo double.
     */
    double getSaldo();
}