package cl.alkewallet.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import cl.alkewallet.model.CuentaCorriente;
import cl.alkewallet.model.CuentaRut;

/**
 * Clase de pruebas unitarias para validar la lógica de negocio de Alke Wallet.
 * Utiliza el framework JUnit 5 para asegurar que los depósitos, retiros y 
 * límites de cuenta operen correctamente.
 * * @author Felipe C
 */
class CuentaTest {

    /**
     * Prueba 1: Verifica que el método depositar incremente el saldo correctamente
     * en una Cuenta Corriente bajo condiciones normales.
     */
    @Test
    void testDepositoCuentaCorriente() {
        CuentaCorriente cc = new CuentaCorriente("Test", 1000);
        cc.depositar(500);
        // assertEquals(esperado, actual, mensaje)
        assertEquals(1500, cc.getSaldo(), "El saldo debería ser 1500 después del depósito");
    }

    /**
     * Prueba 2: Valida la regla de negocio de la Cuenta RUT.
     * Verifica que el sistema bloquee depósitos que excedan el límite de $5.000.000.
     */
    @Test
    void testLimiteCuentaRut() {
        CuentaRut rut = new CuentaRut("Test", 4900000);
        rut.depositar(200000); // Intento de depósito que superaría los 5M
        assertEquals(4900000, rut.getSaldo(), "El saldo no debería cambiar si supera el límite de Cuenta RUT");
    }

    /**
     * Prueba 3: Verifica la seguridad financiera del sistema.
     * Asegura que no se realicen retiros si el monto solicitado es mayor al saldo disponible.
     */
    @Test
    void testRetiroSinSobregiro() {
        CuentaCorriente cc = new CuentaCorriente("Test", 5000);
        boolean resultado = cc.retirar(6000);
        assertFalse(resultado, "El retiro debería fallar (false) por falta de fondos");
        assertEquals(5000, cc.getSaldo(), "El saldo debe permanecer intacto tras un retiro fallido");
    }

    /**
     * Prueba 4: Confirma que un retiro válido descuenta exactamente la cantidad
     * solicitada del saldo de la cuenta.
     */
    @Test
    void testRetiroExitoso() {
        CuentaRut rut = new CuentaRut("Test", 10000);
        boolean resultado = rut.retirar(3000);
        assertTrue(resultado, "El método debería retornar true para un retiro válido");
        assertEquals(7000, rut.getSaldo(), "El saldo restante debería ser exactamente 7000");
    }

    /**
     * Prueba 5: Valida la integridad de la gestión de divisas.
     * Verifica que la actualización de la tasa de cambio (USD) se persista correctamente en el objeto.
     */
    @Test
    void testCambioTasa() {
        CuentaCorriente cc = new CuentaCorriente("Test", 0);
        cc.setTasaUSD(900.0);
        assertEquals(900.0, cc.getTasaUSD(), "La tasa USD debería haberse actualizado a 900");
    }
}