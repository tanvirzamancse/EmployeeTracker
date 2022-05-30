package com.employeetracker.VewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MapActivityViewModel {


    private static String Time;
    public String getTime() {
        DateFormat df = new SimpleDateFormat("KK:mm:ss a, dd/MM/yyyy", Locale.getDefault());
        String Time = df.format(new Date());
      return Time;
    }



}
