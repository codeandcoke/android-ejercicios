package com.sfaci.mihorario;

public class DateUtils {

    /**
     * Devuelve un dia de la semana como número.
     * El primer dia (lunes) devuelve un 0
     * @param dia El dia de la semana como cadena de texto
     * @return El dia de la semana como número entero
     */
    public static int diaOrdinal(String dia) {

        switch (dia) {
            case "Lunes":
                return 0;
            case "Martes":
                return 1;
            case "Miércoles":
                return 2;
            case "Jueves":
                return 3;
            case "Viernes":
                return 4;
            default:
                return -1;
        }
    }
}
