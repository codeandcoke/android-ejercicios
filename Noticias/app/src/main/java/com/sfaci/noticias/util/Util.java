package com.sfaci.noticias.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static Date parseFecha(String fecha) throws ParseException {

        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        return df.parse(fecha);
    }

    public static String formatFecha(Date fecha) {

        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        return df.format(fecha);
    }
}
