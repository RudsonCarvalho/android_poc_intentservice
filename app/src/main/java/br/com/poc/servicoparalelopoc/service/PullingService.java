package br.com.poc.servicoparalelopoc.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import br.com.poc.servicoparalelopoc.MainActivity;
import br.com.poc.servicoparalelopoc.model.service.PocService;
import br.com.poc.servicoparalelopoc.model.service.PocServiceImpl;

/**
 * Created by rudsonkiyoshi on 13/12/16.
 */

public class PullingService extends IntentService {

    public static String PARAM_OUT_MSG;

    public PullingService() {
        super("PullingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        PocService service = new PocServiceImpl();

        int count = 0;

        while(true) {

            String value = service.doSomething();

            //update view with value
            notifyUI(value);

            Log.i("SERVICO_PULLING", "=> excecutando pulling ".concat(String.valueOf(count++)));

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void notifyUI(String value) {

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(MainActivity.UpdateTextFieldReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(PARAM_OUT_MSG, value);
        sendBroadcast(broadcastIntent);
    }
}
