package br.com.poc.servicoparalelopoc.model.service;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by rudsonkiyoshi on 13/12/16.
 */

public class PocServiceImpl implements PocService {


    public PocServiceImpl() {
        Log.i("POC_SERVICE", "new instance ");
    }

    @Override
    public String doSomething() {

        String value = Calendar.getInstance().getTime().toString();
        try {

            Log.i("POC_SERVICE", "CALLED: " + value);

            Thread.sleep(1000L);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return value;
    }
}
