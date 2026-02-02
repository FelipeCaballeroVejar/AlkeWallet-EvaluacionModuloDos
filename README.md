# Alke Wallet - Sistema de Billetera Digital 💰

Este proyecto es una aplicación de consola robusta desarrollada en **Java** para la Evaluación del Módulo Dos. El sistema permite gestionar de manera profesional una **Cuenta Corriente** y una **Cuenta RUT** de forma simultánea, aplicando reglas de negocio bancarias del mundo real.

## 🚀 Características Principales

- **Arquitectura Basada en Contratos**: Uso de interfaces para definir operaciones financieras esenciales.
- **Gestión Multi-cuenta**: Manejo paralelo de productos financieros con saldos independientes.
- **Validación de Límites Estricta**:
   **Cuenta Corriente**: Límite máximo de $100.000.000 CLP.
   **Cuenta RUT**: Límite máximo de $5.000.000 CLP.
- **Módulo de Divisas Dinámico**: Conversión automática a USD y EUR con capacidad de actualizar tasas de cambio en tiempo real.
- **Interfaz de Usuario Alineada**: Reportes formateados con `printf` y `Locale.GERMANY` para una visualización contable profesional.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java 21 (JavaSE-21).
- **IDE**: Eclipse IDE.
- **Testing**: JUnit 5 para asegurar la integridad de los depósitos y retiros.
- **Conceptos POO**: Implementación de Herencia, Interfaces, Polimorfismo y Encapsulamiento.

## 📂 Estructura del Proyecto

Basado en la arquitectura de paquetes implementada:

- `cl.alkewallet.app`: 
   `Main.java`: Orquestador de menús, validación de entradas y lógica de flujo.
- `cl.alkewallet.model`: 
   `IWallet.java`: Interfaz que actúa como contrato para las operaciones.
   `Cuenta.java`: Clase base con lógica de divisas y atributos protegidos.
   `CuentaCorriente.java` & `CuentaRut.java`: Clases hijas con reglas de límites específicas.
- `cl.alkewallet.test`: 
   `CuentaTest.java`: Suite de 5 pruebas unitarias automatizadas.

## 🧪 Pruebas Unitarias

El proyecto incluye tests para validar:
1. Depósitos correctos en Cuenta Corriente.
2. Restricción de límite de $5.000.000 en Cuenta RUT.
3. Bloqueo de sobregiros (retiros mayores al saldo).
4. Integridad del saldo tras retiros exitosos.
5. Actualización de tasas de cambio de divisas.

## 🧠 Conclusiones Técnicas

El desarrollo de Alke Wallet se centró en la aplicación de **Clean Code** y patrones de diseño robustos. La implementación de una **interfaz (`IWallet`)** permite desacoplar la definición de las operaciones de su implementación, facilitando la escalabilidad del sistema. Al utilizar **Herencia**, se logró una reutilización de código eficiente en la gestión de divisas, mientras que el **Polimorfismo** permitió centralizar la lógica de operaciones financieras en métodos genéricos. Finalmente, la integración de **Pruebas Unitarias** con JUnit 5 asegura que las reglas de negocio críticas se cumplan bajo cualquier escenario de ejecución.
