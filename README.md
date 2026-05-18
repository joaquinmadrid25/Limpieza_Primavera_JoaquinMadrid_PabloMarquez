**Alumnos**:Joaquin Madrid y Pablo Márquez

**Nombre actividad**: AEE-Refactorizacion-de-Codigo-Legacy

**Ciclo**: Desarrollo de Aplicaciones Web

**Fecha**: 18/05/2026

# Fase 1: Analisis de la Deuda Técnica

1. **Verificación inicial**: Ejecuta los tests unitarios. Todo debe salir en verde. 
Esto os garantiza que el código original, por muy feo que sea, funciona.

2. **Oler el código (Code Smells)**: El Copiloto anotará en un bloc de notas los tres grandes 
problemas de este código:

- Números mágicos. ¿Qué significa 0.25 o 0.15? Son valores hardcodeados sin contexto. 
Si mañana el IVA o el descuento cambian, ¿dónde los buscamos?

Para el IVA los valores como 0.25 o 0.15 aparecen hardcodeados sin ningún contexto que 
explique su significado. ¿Qué representan exactamente? ¿IVA, comisión o descuento? 
Si mañana cambia una regla de negocio, habría que buscar manualmente todos los lugares donde aparecen esos valores para modificarlos, aumentando el riesgo de errores y dificultando el mantenimiento del código. 

- Variables sin significado. Nombres como cT, m, tC o dV no aportan ninguna semántica. Nos obligan a adivinar.
- cT: calculoTotal
- m: importeBase
- tC: tipoCliente
- dV: esSocioVip

- **Código Spaghetti**. La anidación de múltiples if-else crea una estructura en forma de flecha > 
que hace casi imposible seguir el flujo lógico de ejecución.
El código espagueti es un claro ejemplo de "mal olor en el código," un término que identifica áreas problemáticas. Con la formula **M = C + 1**  se puede saber de manera objetiva si el código es fácil de mantener. En este caso, las estructuras de los if pueden resultar un poco liosas ya que sin comentar nada ves todo junto y de golpe.

# Fase 2: Refactorización Asistida por el IDE (Quirófano abierto)

1. Renombrado Seguro. Utiliza exclusivamente las herramientas automáticas del IDE para cambiar los nombres de las variables en todo el documento a la vez, sin riesgo de errores tipográficos.
- Sugerencia: cT por calcularTotal, m por importeBase, tC por tipoCliente, dV por esSocioVip.

2. Extracción de Constantes. Selecciona los números mágicos (0.25, 0.15, etc.) y usa la herramienta de extracción del IDE para crear constantes private static final en la parte superior de la clase. Usa nombres autoexplicativos como DESCUENTO_VIP o DESCUENTO_ESTANDAR.

3. Cláusulas de Guarda (Guard Clauses). Modifica la estructura de control para "aplanar" el código. Invierte las condiciones lógicas y utiliza retornos tempranos (return) para eliminar todos los bloques “else”.
- Ejemplo conceptual: En lugar de if (importe > 0) { ... } else { return 0; }, cambiadlo a if (importe <= 0) return 0; en la primera línea.

# Fase 3: Verificación, Documentación y Entrega
1. Validación constante. Vuelve a ejecutar los tests unitarios tras CADA pequeño cambio. ¡Deben seguir en verde! Si alguno falla, significa que habéis roto el negocio. Usad el control de versiones (Git) para deshacer los cambios y volver a un estado seguro.

2. Documentación profesional. Genera la documentación JavaDoc escribiendo / y pulsando Enter justo encima del método. Rellena los campos @param explicando qué recibe la función y el @return detallando qué devuelve.

3. Guardado en el repositorio: Realiza un commit semántico en vuestro repositorio que describa exactamente lo que habéis hecho.
- Ejemplo: git commit -m "refactor: reducción de complejidad ciclomática mediante cláusulas de guarda y nombrado semántico"

