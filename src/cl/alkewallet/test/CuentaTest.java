package cl.alkewallet.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import cl.alkewallet.model.CuentaCorriente;
import cl.alkewallet.model.CuentaRut;

class CuentaTest {

    // Prueba 1: Verificar que el depósito normal en Cuenta Corriente funciona
    @Test
    void testDepositoCuentaCorriente() {
        CuentaCorriente cc = new CuentaCorriente("Test", 1000);
        cc.depositar(500);
        assertEquals(1500, cc.getSaldo(), "El saldo debería ser 1500 después del depósito");
    }

    // Prueba 2: Verificar el límite máximo de la Cuenta RUT ($5.000.000)
    @Test
    void testLimiteCuentaRut() {
        CuentaRut rut = new CuentaRut("Test", 4900000);
        rut.depositar(200000); // Esto debería fallar por superar los 5M
        assertEquals(4900000, rut.getSaldo(), "El saldo no debería cambiar si supera el límite");
    }

    // Prueba 3: Verificar que no se puede retirar más de lo que hay (Sin Sobregiro)
    @Test
    void testRetiroSinSobregiro() {
        CuentaCorriente cc = new CuentaCorriente("Test", 5000);
        boolean resultado = cc.retirar(6000);
        assertFalse(resultado, "El retiro debería fallar por falta de fondos");
        assertEquals(5000, cc.getSaldo(), "El saldo debe permanecer igual tras retiro fallido");
    }

    // Prueba 4: Verificar que el retiro exitoso descuenta el monto correcto
    @Test
    void testRetiroExitoso() {
        CuentaRut rut = new CuentaRut("Test", 10000);
        boolean resultado = rut.retirar(3000);
        assertTrue(resultado);
        assertEquals(7000, rut.getSaldo());
    }

    // Prueba 5: Verificar que las tasas de cambio se actualizan correctamente
    @Test
    void testCambioTasa() {
        CuentaCorriente cc = new CuentaCorriente("Test", 0);
        cc.setTasaUSD(900.0);
        assertEquals(900.0, cc.getTasaUSD(), "La tasa USD debería haberse actualizado a 900");
    }
}