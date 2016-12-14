package br.com.poc.servicoparalelopoc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import br.com.poc.servicoparalelopoc.service.PullingService;

import static br.com.poc.servicoparalelopoc.service.PullingService.PARAM_OUT_MSG;

public class MainActivity extends AppCompatActivity {

    private TextView txtView;
    private Button btnProcessar;
    private ListView lstContent;
    private ArrayAdapter<String> lstAdapter;

    private UpdateTextFieldReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializa componentes da tela
        initComponents();

        //inicializa eventos dos controles
        initEvents();
    }


    /***
     * inicializa componentes da tela
     */
    private void initComponents() {

        txtView = (TextView) findViewById(R.id.txtCount);
        btnProcessar = (Button) findViewById(R.id.btnProcessar);
        lstContent = (ListView) findViewById(R.id.lstContent);

        lstAdapter = new ArrayAdapter<String>(this, R.layout.simple_row, R.id.list_item);
        lstContent.setAdapter(lstAdapter);

        //register receiver broadcast
        IntentFilter filter = new IntentFilter(UpdateTextFieldReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new UpdateTextFieldReceiver();
        registerReceiver(receiver, filter);
    }

    /**
     * inicializa eventos da tela
     */
    private void initEvents() {

        /**
         * botao processar click
         */
        btnProcessar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, PullingService.class);
                startService(i);

                txtView.setText("Pulling Iniciado");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        txtView.setText("Começar Pulling");
    }


    /**
     * Classe que recebe a notificacao para atualizar o texto
     */
    public class UpdateTextFieldReceiver extends BroadcastReceiver {

        public static final String ACTION_RESP =
                "intent.action.MESSAGE_PROCESSED";

        @Override
        public void onReceive(Context context, Intent intent) {

            String text = intent.getStringExtra(PARAM_OUT_MSG);

            //atualiza o textView
            //TextView result = (TextView) findViewById(R.id.txtCount);
            // result.setText(text);

            //atualiza a listview
            lstAdapter.add(text);
            ListView lstContentResult = (ListView) findViewById(R.id.lstContent);
            lstContentResult.setAdapter(lstAdapter);
        }
    }
}
