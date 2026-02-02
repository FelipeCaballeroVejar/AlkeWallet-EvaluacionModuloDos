package cl.alkewallet.app;

import java.util.Scanner;
import java.util.Locale;
import cl.alkewallet.model.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n=== BIENVENIDO A ALKE WALLET ===\n");
        System.out.print("Ingrese nombre del titular: ");
        String nombre = sc.nextLine().toUpperCase();

        // Configuración inicial con validación de límites
        double sCC = solicitarMonto(sc, "Saldo Inicial Cuenta Corriente", CuentaCorriente.LIMITE_CC);
        CuentaCorriente cc = new CuentaCorriente(nombre, sCC);

        double sRut = solicitarMonto(sc, "Saldo Inicial Cuenta RUT", CuentaRut.LIMITE_RUT);
        CuentaRut rut = new CuentaRut(nombre, sRut);

        int op = 0;
        while (op != 5) {
            System.out.println("\n********************************* MENÚ PRINCIPAL *********************************\n");
            System.out.println("    1. Ver Saldos | 2. Depositar | 3. Retirar | 4. Convertir Moneda | 5. Salir ");
            /* System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Convertir Moneda (Divisas)");
            System.out.println("5. Salir");*/
            System.out.print("\n    Seleccione una opción (1-5): ");          


            try {
                op = Integer.parseInt(sc.nextLine());
                switch (op) {
                    case 1 -> mostrarResumenGeneral(nombre, cc, rut, false);
                    case 2 -> seleccionarCuentaYOperar(sc, cc, rut, "DEPOSITAR");
                    case 3 -> seleccionarCuentaYOperar(sc, cc, rut, "RETIRAR");
                    case 4 -> menuDivisas(sc, nombre, cc, rut);
                    case 5 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("❌ Opción inválida.");
                }
            } catch (Exception e) { System.out.println("❌ Error: Ingrese un número."); }
        }
        sc.close();
    }

    private static void mostrarResumenGeneral(String titular, CuentaCorriente cc, CuentaRut rut, boolean incluirDivisas) {
        System.out.println("=================================================================================\n");
        System.out.println("Titular                 : " + titular);
        System.out.println("\nSaldos Disponibles");
        imprimirLineaSaldo("Cuenta Corriente ", cc, incluirDivisas);
        imprimirLineaSaldo("Cuenta RUT       ", rut, incluirDivisas);
        System.out.println("\n=================================================================================");
    }

    private static void imprimirLineaSaldo(String etiqueta, Cuenta c, boolean incluirDivisas) {
        double saldo = c.getSaldo();
        // Reservamos 18 espacios para la etiqueta y 12 para el monto CLP
        System.out.printf(Locale.GERMANY, "- %-18s CLP: $%,12.0f", etiqueta, saldo);
        
        if (incluirDivisas) {
            double usd = saldo / c.getTasaUSD();
            double eur = saldo / c.getTasaEUR();
            
            // EXPLICACIÓN DEL FORMATO:
            // %-7s  -> "USD: " alineado a la izquierda en 7 espacios
            // %10.2f -> El número con 2 decimales en un bloque de 10 espacios
            // |      -> El separador fijo
            System.out.printf(Locale.GERMANY, " | USD: $%10.2f | EUR: €%10.2f |", usd, eur);
        }
        System.out.println();
    }

    private static void seleccionarCuentaYOperar(Scanner sc, CuentaCorriente cc, CuentaRut rut, String tipo) {
        System.out.println("\n¿A qué cuenta desea " + tipo + "?");
        System.out.println("1. Cuenta Corriente | 2. Cuenta RUT | 3. Volver");
        String sel = sc.nextLine();
        
        Cuenta cuentaSel = (sel.equals("1")) ? cc : (sel.equals("2") ? rut : null);
        
        if (cuentaSel != null) {
            System.out.print("Monto a " + tipo.toLowerCase() + ": ");
            try {
                double monto = Double.parseDouble(sc.nextLine());
                if (tipo.equals("DEPOSITAR")) cuentaSel.depositar(monto);
                else if (!cuentaSel.retirar(monto)) System.out.println("❌ Saldo insuficiente.");
                else System.out.println("✅ Retiro exitoso.");
            } catch (Exception e) { System.out.println("❌ Monto inválido."); }
        }
    }

    private static void menuDivisas(Scanner sc, String titular, CuentaCorriente cc, CuentaRut rut) {
        cc.mostrarEquivalencias(); // Muestra el tablero de 1 USD = ...
        mostrarResumenGeneral(titular, cc, rut, true); // Muestra saldos convertidos
        
        System.out.println("--- GESTIÓN DE TASAS ---");
        System.out.println("A. Editar valor USD | B. Editar valor EURO | C. Volver");
        System.out.print("Seleccione: ");
        String sub = sc.nextLine().toUpperCase();
        
        if (sub.equals("A") || sub.equals("B")) {
            System.out.print("Nuevo valor: ");
            try {
                double nuevo = Double.parseDouble(sc.nextLine());
                if (sub.equals("A")) { cc.setTasaUSD(nuevo); rut.setTasaUSD(nuevo); }
                else { cc.setTasaEUR(nuevo); rut.setTasaEUR(nuevo); }
                System.out.println("✅ Tasa actualizada.");
            } catch (Exception e) { System.out.println("❌ Error de valor."); }
        }
    }

    private static double solicitarMonto(Scanner sc, String msg, double max) {
        while (true) {
            System.out.printf("%s (Máx $%,.0f): ", msg, max);
            try {
                double m = Double.parseDouble(sc.nextLine());
                if (m >= 0 && m <= max) return m;
                System.out.printf("❌ El monto debe estar entre 0 y $%,.0f%n", max);
            } catch (Exception e) { System.out.println("❌ Ingrese solo números."); }
        }
    }
}