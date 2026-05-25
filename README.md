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

2. Documentación profesional. Genera la documentación JavaDoc escribiendo / y pulsando Enter justo encima del método. Rellena los campos @param explicando qué recibe la función y el @return detallando qué devuelve.

3. Guardado en el repositorio: Realiza un commit semántico en vuestro repositorio que describa exactamente lo que habéis hecho.
- Ejemplo: git commit -m "refactor: reducción de complejidad ciclomática mediante cláusulas de guarda y nombrado semántico"

