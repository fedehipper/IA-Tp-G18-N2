package com.tp.ia;

import static com.tp.ia.src.CambioMinimo.calcularCambioMinimo;
import com.tp.ia.src.CambioMinimoFuncionAptitud;

public class TpIA {

    public static void main(String[] args) throws Exception {
        int amount = 353;
        try {
        //amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("El (Monto de dinero) debe ser un numero entero valido");
            System.exit(1);
        }

        if (amount < 1 || amount >= CambioMinimoFuncionAptitud.MAX_MONTO) {
            System.out.println("El monto de dinero debe estar entre 1 y " + (CambioMinimoFuncionAptitud.MAX_MONTO - 1) + ".");
        } else {
            calcularCambioMinimo(amount);
        }
    }

}
