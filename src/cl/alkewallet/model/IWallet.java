package cl.alkewallet.model;

public interface IWallet {
    // Las interfaces solo declaran los métodos, no llevan código (cuerpo)
    void depositar(double monto);
    boolean retirar(double monto);
    double getSaldo();
}