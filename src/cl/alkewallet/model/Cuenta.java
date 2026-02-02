package cl.alkewallet.model;

import java.util.Locale;

/**
 * Clase base que representa una cuenta bancaria genérica en Alke Wallet.
 * Implementa el contrato IWallet y sirve como base para cuentas especializadas.
 * * <pre>
 * DIAGRAMA DE ESTRUCTURA:
 * «interface»
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
    // Atributos protegidos (protected) para que las clases hijas puedan acceder a ellos
    protected String titular;
    protected double saldo;
    // Tasas de cambio por defecto para la conversión de divisas
    protected double tasaUSD = 877.19;
    protected double tasaEUR = 1032.66;

    /**
     * Constructor para inicializar una cuenta con un titular y saldo base.
     * @param titular Nombre del dueño de la cuenta.
     * @param saldoInicial Dinero con el que se abre la cuenta.
     */
    public Cuenta(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    /**
     * Incrementa el saldo de la cuenta.
     * Este método es sobreescrito en las clases hijas para validar límites de depósito.
     * @param monto Cantidad a depositar.
     */
    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("\n✅ Depósito exitoso.");
            mostrarSaldoSimple();
        }
    }

    /**
     * Resta el monto del saldo actual si existen fondos suficientes.
     * @param monto Cantidad a retirar.
     * @return true si el retiro fue exitoso, false si no hay saldo suficiente.
     */
    public boolean retirar(double monto) {
        if (monto > 0 && monto <= this.saldo) {
            this.saldo -= monto;
            return true;
        }
        return false;
    }

    /**
     * Muestra por consola el saldo actual formateado.
     * Utiliza Locale.GERMANY para mostrar puntos en los miles y comas en decimales.
     */
    public void mostrarSaldoSimple() {
        System.out.println("----------------------------------------");
        // %s detecta automáticamente el nombre de la clase (CuentaRut o CuentaCorriente)
        System.out.printf(Locale.GERMANY, "SALDO ACTUAL (%s): $%,.0f CLP%n", 
                          this.getClass().getSimpleName(), this.saldo);
        System.out.println("----------------------------------------");
    }

    /**
     * Muestra una tabla con el valor actual de las divisas configuradas en el sistema.
     * Incluye 2 decimales para precisión en las tasas de cambio.
     */
    public void mostrarEquivalencias() {
        System.out.println("\n=================================================================================");
        System.out.println("                                  VALOR MONEDAS                                       ");
        // Formato con alineación para que los valores de USD y EURO queden perfectamente alineados
        System.out.printf(java.util.Locale.GERMANY, "%36s = $%,8.2f CLP%n", "1  USD", this.tasaUSD);
        System.out.printf(java.util.Locale.GERMANY, "%36s = $%,8.2f CLP%n", "1 EURO", this.tasaEUR);
    }

    // --- Métodos de acceso (Getters y Setters) ---
    // Permiten obtener o modificar los valores de forma controlada

    public double getSaldo() { return saldo; }
    public double getTasaUSD() { return tasaUSD; }
    public double getTasaEUR() { return tasaEUR; }
    
    // Estos setters permiten actualizar los valores del dólar/euro desde el menú principal
    public void setTasaUSD(double t) { this.tasaUSD = t; }
    public void setTasaEUR(double t) { this.tasaEUR = t; }
}