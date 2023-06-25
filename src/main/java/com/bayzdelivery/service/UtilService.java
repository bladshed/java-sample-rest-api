package com.bayzdelivery.service;

import com.bayzdelivery.exceptions.ApiRequestException;
import com.bayzdelivery.model.Delivery;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilService {

    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    public static void setCommission(Delivery delivery){
        // OrderPrice * 0.05 + Distance * 0.5
        float commission = Float.parseFloat(
            decfor.format(
        (delivery.getPrice() * 0.05f) + (delivery.getDistance() * 0.5f)
            )
        );
        delivery.setCommission(commission);
    }
    public static String incrementDate(String date) {
        try {
            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date outputDate = outputFormatter.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(outputDate);
            cal.add(Calendar.DATE, 1);

            return outputFormatter.format(cal.getTime());
        } catch (Exception e){
            throw new ApiRequestException("Invalid date format.");
        }
    }

    public static String formatLateDate() {
        try {
            SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date outputDate = outputFormatter.parse(outputFormatter.format(new Date()));
            Calendar cal = Calendar.getInstance();
            cal.setTime(outputDate);
            cal.add(Calendar.MINUTE, -45);

            return outputFormatter.format(cal.getTime());
        } catch (Exception e){
            throw new ApiRequestException("Invalid date format.");
        }
    }
}
