public class FacturacionLegacy {
    //Atributos:
        private static final double DESCUENTO_VIP = 0.25;
        private static final double DESCUENTO_ESTANDAR = 0.15;
        private static final double DESCUENTO_REGALO = 0.05;


    /**
     * Calcula el total a pagar por un cliente según su tipo.
     * @param importeBase El importe base de la factura.
     * @param tipoCliente El tipo de cliente (1 = VIP, 2 = Estándar, otro = Sin categoría).
     * @param esSocioVip El indicador de si el cliente es socio VIP (aplica solo para tipoCliente 1).
     * @return El total a pagar después de aplicar los descuentos correspondientes.
     */

    // Método a refactorizar:
    public double calculoTotal(double importeBase, int tipoCliente, boolean esSocioVip) {
    //Entorno:
        //No se necesita ninguna variable para realizar el método
    //Algoritmo:
        if (importeBase <= 0) {
            return 0;
        } else {
            switch (tipoCliente) {
                case 1:
                    if (esSocioVip) {
                        return importeBase - (importeBase * DESCUENTO_VIP);
                    } else {
                        return importeBase - (importeBase * DESCUENTO_ESTANDAR);
                    }//Fin Si
                case 2:
                    return importeBase - (importeBase * DESCUENTO_REGALO);
                default:
                    return importeBase;
            }//Fin Según Sea
        }//Fin Si
    }//Fin Función
}


/**
 * Clase Legacy del sistema de facturación.
 * ADVERTENCIA: Código con alta deuda técnica. No modificar la firma del método.
 */
/*public class FacturacionLegacy {

    // Método a refactorizar
    public double cT(double m, int tC, boolean dV) {
        if (m > 0) {
            if (tC == 1) {
                if (dV == true)
                    return m - (m * 0.25);
                else
                    return m - (m * 0.15);
            } else {
                if (tC == 2) {
                    return m - (m * 0.05);
                } else {
                    return m;
                }
            }
        } else {
            return 0;
        }
    }
}*/