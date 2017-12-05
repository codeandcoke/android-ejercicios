package com.sfaci.incidencias.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.UTMRef;

/**
 * Created by dam on 5/12/17.
 */

public class Util {

    public static LatLng DeUMTSaLatLng(double latitud, double longitud) {

        UTMRef utm = new UTMRef(latitud, longitud, 'N', 30);

        return utm.toLatLng();
    }

    public static Date parseFecha(String fecha) throws ParseException {

        fecha = fecha.replace("T", " ");
        fecha = fecha.replace("Z", "");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
        return sdf.parse(fecha);
    }
}
