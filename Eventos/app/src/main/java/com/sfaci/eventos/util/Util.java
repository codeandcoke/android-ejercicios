package com.sfaci.eventos.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String formatearFecha(Date fecha) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(fecha);
    }

    public static Date parsearFecha(String fecha) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.parse(fecha);
    }

    public static String formatearMoneda(float cantidad) {

        DecimalFormat df = new DecimalFormat("#.00 €");
        return df.format(cantidad);
    }
}
