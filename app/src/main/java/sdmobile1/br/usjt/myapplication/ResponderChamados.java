package sdmobile1.br.usjt.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import sdmobile1.br.usjt.myapplication.Adapter.ADPChamado;
import sdmobile1.br.usjt.myapplication.Base.BaseActivity;
import sdmobile1.br.usjt.myapplication.Model.Chamado;
import sdmobile1.br.usjt.myapplication.Model.Fila;
/**
 * Created by Diego Sgarbi RA:201517012 & Paulo Aragão RA:201522680 - Grupo 4
 */
public class ResponderChamados extends BaseActivity {

    private ListView lvRespChamados;
    private Chamado chamado;
    private ADPChamado adpChamado;
    private ArrayList<Chamado> listaChamado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_chamados);
        Intent intent9 = getIntent();
        lvRespChamados = (ListView) findViewById(R.id.lvRespChamados);
        listaChamado= new ArrayList<Chamado>();
        adpChamado = new ADPChamado(listaChamado, ResponderChamados.this);
        lvRespChamados.setAdapter(adpChamado);
        new AsyncCaller().execute();

        lvRespChamados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        }
    });
}

    private class AsyncCaller extends AsyncTask<Void, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(ResponderChamados.this);

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
            String url = "http://www.devpauloaragao.com.br/sdmobile/rest/solucionador/fila/listar";
            String result = "";
            try{
                result = run(url);
                if(!result.isEmpty()){
                    try {
                        JSONArray jArray = new JSONArray(result);

                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObj = jArray.getJSONObject(i);
                            Chamado chamado = new Chamado();
                            chamado.setNmChamado(jObj.getString("nmChamado"));
                            chamado.setMensagem(jObj.getString("mensagem"));
                            chamado.setResposta(jObj.getString("resposta"));
                            chamado.setStatus(jObj.getString("status"));
                            chamado.setDtInclusao(jObj.getString("dtInclusao"));
                            chamado.setDtAlteracao(jObj.getString("dtAlteracao"));
                            chamado.setDtExclusao(jObj.getString("dtExclusao"));
                            chamado.setDtInclusao(jObj.getString("dtInclusao"));


                            listaChamado.add(chamado);
                        }

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
            adpChamado = new ADPChamado(listaChamado,ResponderChamados.this);
            lvRespChamados.setAdapter(adpChamado);
            adpChamado.notifyDataSetChanged();
            pdLoading.dismiss();
        }

    }
}