package cl.alkewallet.model;

import java.util.Locale;

/**
 * Clase base que representa una cuenta bancaria genérica en Alke Wallet.
 * Implementa el contrato IWallet y sirve como base para cuentas especializadas.
 * * <pre>
 * DIAGRAMA DE ESTRUCTURA:
 * * «interface»
 * IWallet
 * ^
 * | (implements)
 * |
 * Cuenta  <----------- (Base)
 * ^
 * |_________________
 * |                 |
 * CuentaCorriente     CuentaRut
 * (Límite $100M)     (Límite $5M)
 * </pre>
 * * @author Felipe C
 * @version 1.0
 */


public class Cuenta implements IWallet {
    protected String titular;
    protected double saldo;
    protected double tasaUSD = 877.19;
    protected double tasaEUR = 1032.66;

    public Cuenta(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("\n✅ Depósito exitoso.");
            mostrarSaldoSimple();
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && monto <= this.saldo) {
            this.saldo -= monto;
            return true;
        }
        return false;
    }

    public void mostrarSaldoSimple() {
        System.out.println("----------------------------------------");
        System.out.printf(Locale.GERMANY, "SALDO ACTUAL (%s): $%,.0f CLP%n", 
                          this.getClass().getSimpleName(), this.saldo);
        System.out.println("----------------------------------------");
    }

    public void mostrarEquivalencias() {
        System.out.println("\n=================================================================================");
        System.out.println("                                  VALOR MONEDAS                                       ");
        // %36s centra la etiqueta y %10.0f alinea el valor numérico
        System.out.printf(java.util.Locale.GERMANY, "%36s = $%,8.2f CLP%n", "1  USD", this.tasaUSD);
        System.out.printf(java.util.Locale.GERMANY, "%36s = $%,8.2f CLP%n", "1 EURO", this.tasaEUR);
    }

    // Getters y Setters
    public double getSaldo() { return saldo; }
    public double getTasaUSD() { return tasaUSD; }
    public double getTasaEUR() { return tasaEUR; }
    public void setTasaUSD(double t) { this.tasaUSD = t; }
    public void setTasaEUR(double t) { this.tasaEUR = t; }
}