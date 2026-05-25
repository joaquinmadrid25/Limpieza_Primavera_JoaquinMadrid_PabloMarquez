# "Limpieza de Primavera" (Refactorización de Código Legado)  

**Alumnos**: Joaquin Madrid y Pablo Márquez

**Nombre actividad**: AEE-Refactorizacion-de-Codigo-Legacy

**Ciclo**: Desarrollo de Aplicaciones Web

**Fecha**: 18/05/2026

## Fase 1: Analisis de la Deuda Técnica
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

   
## Fase 2: Refactorización Asistida por el IDE (Quirófano abierto)
Ahora si, pasamos a refactorizar el código. Al modificarlo, hemos tenido en cuenta los siguientes aspectos:
- **Renombrado seguro**: hemos cambiado el nombre de las variables, y al cambiarlas, con la ayuda del IDE (Visual Studio Code) hemos cambiado el nombre en el otro archivo automáticamente, sin tocar manualmente dicho código.
- **Extracción de constantes**: hemos creado constantes para cada tipo de descuento (IVA) para que sea más legible y más facil a la hora de tener que modificarlo en un futuro.
- **Claúsulas de Guarda**: hemos modificado la las diferentes estructuras de cada instrucción, para ver un código limpio y ordenado.  

## Fase 3: Verificación y Documentación
Una vez **refactorizado** el código, hay que volver a verificar que todo funciona correctamente, ya que sino incumplimos con el objetivo de la empresa. Al realizar los **test unitarios** podeos verificar que nuestro código funciona, y ahora con un código mucho más legible y estrcuturado.

Para hacer que el código sea lo más simple y legible posible, hemos añadido documentación con **JavaDoc**. Para ello, colocamos la documentación estructurada justo del **método refactorizado**, para que así se vea cómo las **constantes** quedan limpiamente declaradas a nivel de clase (fuera del método, corrigiendo el error de anidamiento del código legacy). En la documentación JavDoc también añadimos una breve explicación de cada **parametro** utilizado y del **return** final.

   
    public class FacturacionLegacy {

    private static final double DESCUENTO_VIP = 0.25;
    private static final double DESCUENTO_ESTANDAR = 0.15;
    private static final double DESCUENTO_REGALO = 0.05;

    /**
     * Calcula el importe total facturado aplicando los descuentos correspondientes 
     * según el tipo de cliente y su estado de suscripción VIP.
     * @param importeBase   El monto bruto de la factura (debe ser mayor que 0).
     * @param tipoCliente   El código identificador del tipo de cliente (1 para habitual, 2 para especial).
     * @param esSocioVip    Indicador de si el cliente cuenta con membresía VIP activa.
     * @return El importe total neto tras aplicar los descuentos, o 0 si el importe base no es válido.
     */
   

Finalmente, hemos ejecutado este commit para dar como finalizada la actividad, haciendo una breve descripción de lo realizado.
**git commit -m** "refactor: reducción de complejidad ciclomática mediante cláusulas de guarda y nombrado semántico"
