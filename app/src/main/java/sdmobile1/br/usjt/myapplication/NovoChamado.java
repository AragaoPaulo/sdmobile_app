package sdmobile1.br.usjt.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import sdmobile1.br.usjt.myapplication.Base.BaseActivity;
/**
 * Created by Diego Sgarbi RA:201517012 & Paulo Aragão RA:201522680 - Grupo 4
 */
public class NovoChamado extends BaseActivity {

    private EditText lbTitulo;
    private  EditText lbMensagem;
    private Button btEnviar;
    private String titulo;
    private String mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_chamado);
        lbTitulo = (EditText) findViewById(R.id.lbTitulo);
        lbMensagem = (EditText)findViewById(R.id.lbMensagem);
        btEnviar = (Button) findViewById(R.id.btEnviar);
        Intent intent1 = getIntent();

    }


    public void enviarChamado(View view){
        Intent intent2 = new Intent(this,TelaSolicitante.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);


        titulo = lbTitulo.getText().toString();
        mensagem = lbMensagem.getText().toString();
        if(titulo.equalsIgnoreCase("") ){
            showMsg("Preencha o título.");
        }
        else if(mensagem.equalsIgnoreCase("")) {
            showMsg("Preencha a mensagem");

        }
        else{

            startActivity(intent2);


        }
    }



    private class AsyncCaller extends AsyncTask<Void, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(NovoChamado.this);

        String run(String url) throws IOException {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("Carregando...");
            pdLoading.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "https://fipeapi.appspot.com/api/1/carros/marcas.json";
            String result = "";
            try{
                result = run(url);
                if(!result.isEmpty()){
                    try {
                        JSONArray jArray = new JSONArray(result);
                        /*for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObj = jArray.getJSONObject(i);
                            nomeMarca = jObj.getString("name");
                        }
                    */
                    }catch(JSONException je) {
                        je.printStackTrace();
                    }
                }
            }catch(Exception e){

            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            /*
            adpCarros = new ADPCarros(listaCarros,CarrosActivity.this);
            lvListCarros.setAdapter(adpCarros);
            */
            pdLoading.dismiss();
        }

    }

}
