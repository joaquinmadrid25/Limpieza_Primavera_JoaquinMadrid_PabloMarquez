**Alumnos**:Joaquin Madrid y Pablo Márquez

**Nombre actividad**: AEE-Refactorizacion-de-Codigo-Legacy

**Ciclo**: Desarrollo de Aplicaciones Web

**Fecha**: 18/05/2026

# Fase 1: Analisis de la Deuda Técnica
Antes de empezar a refactorizar el código, es necesario realizar un análisis previo. En primer lugar, hay que validar que el código que tenemos actualmente funciona, aunque por dentro no esté bien estructurado. Al hacer los test unitarios, hemos verificado que funciona correctamente. 

Si nos adentramos en los valores del código, en concreto 0.25 y 0.15, son literales numéricos que si mañana cambia una regla de negocio, habría que buscar manualmente todos los lugares donde aparecen esos valores para modificarlos, aumentando el riesgo de errores y dificultando el mantenimiento del código. Por ello, hemos decidido declararlos como constantes cuando vayamos a refactorizar el código, para que cuando haya que modificarlo, con cambiar el valor de la constante es suficiente.

En cuanto a las variables, el nombre que tenían anteriormente se podía interpretar de muchísimas formas, por lo que no era una buena práctica. Hemos pensado cambiarlos para que así sea más eficiente y legible.

| ANTIGUA | ACTUAL |
| :---: | :---: |
| cT | calculoTotal |
| m | importeBase |
| tC | tipoCliente |
| dV | esSocioVip |
  

**Código Spaghetti**:
Es un termino peyorativo para programas que tienen una estructura de control de flujo compleja, caso como un plato de fideos entrelazados:
- **Sin principio ni fin claro**: El flujo salta de un lado a otro.

- **Dependencias ocultas**: Tocar una parte del sistema rompe algo en un lugar totalmente inesperado.

- **Falta de jerarquía**: Todo parece estar al mismo nivel de importancia, lo que confunde al lector.

|Aspecto|Codigo Spaghetti|Codigo Limpio|
|:-------|:--------------|:------------|
|Flujo|Como un laberinto|Como una lista de tareas|
|Validacion|"Si todo esto es cierto, hazlo|"Si algo falla, delante"|
|Atencion|Tiene que leer todo para entender una parte|Puedes leer solo la parte que te interesa|

   
# Fase 2: Refactorización Asistida por el IDE (Quirófano abierto)

1. Renombrado Seguro. Utiliza exclusivamente las herramientas automáticas del IDE para cambiar los nombres de las variables en todo el documento a la vez, sin riesgo de errores tipográficos.
- Sugerencia: cT por calcularTotal, m por importeBase, tC por tipoCliente, dV por esSocioVip.

2. Extracción de Constantes. Selecciona los números mágicos (0.25, 0.15, etc.) y usa la herramienta de extracción del IDE para crear constantes private static final en la parte superior de la clase. Usa nombres autoexplicativos como DESCUENTO_VIP o DESCUENTO_ESTANDAR.

3. Cláusulas de Guarda (Guard Clauses). Modifica la estructura de control para "aplanar" el código. Invierte las condiciones lógicas y utiliza retornos tempranos (return) para eliminar todos los bloques “else”.
- Ejemplo conceptual: En lugar de if (importe > 0) { ... } else { return 0; }, cambiadlo a if (importe <= 0) return 0; en la primera línea.
public class FacturacionLegacy {


      // Método a refactorizar
        public double calcularTotal(double importeBase, int tipoCliente, boolean esSocioVip) {

      //Entorno:
         private static final double DESCUENTO_VIP = 0.25;
          private static final double DESCUENTO_ESTANDAR = 0.15;
           private static final double DESCUENTO_REGALO = 0.05;

       //Algoritmo:

       if (importeBase > 0) {
            if (tipoCliente == 1) {
                if (esSocioVip == true)
                    return importeBase - (importeBase * DESCUENTO_VIP);

                else
                    return importeBase - (importeBase * DESCUENTO_ESTANDAR);

           } else {
                if (tipoCliente == 2) {
                    return importeBase - (importeBase * DESCUENTO_REGALO);

      } else {
      return importeBase;

       }//Fin Si

       }

        } else {
            return 0;
        }//Fin Si

      }//Fin Función

      }

# Fase 3: Verificación, Documentación y Entrega
1. Validación constante. Vuelve a ejecutar los tests unitarios tras CADA pequeño cambio. ¡Deben seguir en verde! Si alguno falla, significa que habéis roto el negocio. Usad el control de versiones (Git) para deshacer los cambios y volver a un estado seguro.

Al aplicar las clausulas de guarda, hemos invertido el contol y eliminado el "efecto piramide"(la indentacion profunda). EL flujo logico original se mantiene intacto, pero ahora es lineal.

**Casos criticos a verificar en verde**:
- importeBase negativo o cero $\rightarrow$ Debe retornar 0.0.
- tipoCliente == 1 y esSocioVip == true $\rightarrow$ Debe aplicar 0.25.
- tipoCliente == 1 y esSocioVip == false $\rightarrow$ Debe aplicar 0.15.
- tipoCliente == 2 $\rightarrow$ Debe aplicar 0.05.
- Cualquier otro caso $\rightarrow$ Debe retornar el importeBase sin cambios.

2. Documentación Profesional (JavaDoc)

Colocamos la documentación estructurada justo encima de la clase y del método refactorizado. Nota cómo las constantes quedan limpiamente declaradas a nivel de clase (fuera del método, corrigiendo el error de anidamiento del código legacy).

    /**
    * Clase encargada de gestionar las reglas de negocio para la facturación
    * y cálculo de importes de la aplicación.
    */
    public class FacturacionLegacy {

    private static final double DESCUENTO_VIP = 0.25;
    private static final double DESCUENTO_ESTANDAR = 0.15;
    private static final double DESCUENTO_REGALO = 0.05;

    /**
     * Calcula el importe total facturado aplicando los descuentos correspondientes 
     * según el tipo de cliente y su estado de suscripción VIP.
     * * @param importeBase   El monto bruto de la factura (debe ser mayor que 0).
     * @param tipoCliente   El código identificador del tipo de cliente (1 para habitual, 2 para especial).
     * @param esSocioVip    Indicador de si el cliente cuenta con membresía VIP activa.
     * @return El importe total neto tras aplicar los descuentos, o 0 si el importe base no es válido.
     */
   
           public double calcularTotal(double importeBase, int tipoCliente, boolean esSocioVip) {
        
           // Cláusula de guarda: Validación de entrada

            if (importeBase <= 0) {
              return 0;
           }

           // Cláusula de guarda: Cliente Tipo 1

           if (tipoCliente == 1) {
            if (esSocioVip) {
                return importeBase - (importeBase * DESCUENTO_VIP);
            }
            return importeBase - (importeBase * DESCUENTO_ESTANDAR);

           }

           // Cláusula de guarda: Cliente Tipo 2

           if (tipoCliente == 2) {
            return importeBase - (importeBase * DESCUENTO_REGALO);

           }

           // Caso por defecto (Sin descuentos)
           return importeBase;
           }
           }

3. Guardado en el repositorio: Realiza un commit semántico en vuestro repositorio que describa exactamente lo que habéis hecho.
- Ejemplo: git commit -m "refactor: reducción de complejidad ciclomática mediante cláusulas de guarda y nombrado semántico"

