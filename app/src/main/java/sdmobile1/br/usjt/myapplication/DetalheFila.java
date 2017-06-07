package sdmobile1.br.usjt.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sdmobile1.br.usjt.myapplication.Base.BaseActivity;
import sdmobile1.br.usjt.myapplication.Model.Chamado;
import sdmobile1.br.usjt.myapplication.Model.Usuario;
/**
 * Created by Diego Sgarbi RA:201517012 & Paulo Aragão RA:201522680 - Grupo 4
 */
public class DetalheFila extends BaseActivity {

    private Integer idChamado;
    private TextView lbData;
    private TextView lbPrioridade;
    private TextView lbStatus;
    private TextView lbNmSolicitante;
    private TextView lbLocal;
    private TextView lbCargo;
    private TextView lbTitulo;
    private TextView lbMensagem;
    private Spinner spPrioridade;

    private Chamado chamado;
    private Usuario usuario;
    private List<String> prioridade = new ArrayList<String>();
    private String prioridade1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_fila);

        lbData = (TextView) findViewById(R.id.lbData);
        lbPrioridade = (TextView) findViewById(R.id.lbPrioridade);
        lbStatus = (TextView) findViewById(R.id.lbStatus);
        lbNmSolicitante = (TextView) findViewById(R.id.lbNmSolicitante);
        lbLocal = (TextView) findViewById(R.id.lbLocal);
        lbCargo = (TextView) findViewById(R.id.lbCargo);
        lbTitulo = (TextView) findViewById(R.id.lbTitulo);
        lbMensagem = (TextView) findViewById(R.id.lbMensagem);
        spPrioridade = (Spinner) findViewById(R.id.spPrioridade);

        chamado = new Chamado();
        usuario = new Usuario();
        idChamado = getIntent().getExtras().getInt("idChamado");

        prioridade.add("na fila");
        prioridade.add("cancelado");
        prioridade.add("respondido");
        prioridade.add("atualizando");
        prioridade.add("jklfdja");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, prioridade);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spPrioridade.setAdapter(spinnerArrayAdapter);

        spPrioridade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                prioridade1 = parent.getItemAtPosition(posicao).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void salvarPrioridade(View view) {

        Intent intent = new Intent(DetalheFila.this, GerenciarFila.class);

        if(prioridade1.equalsIgnoreCase("")) {
            showMsg("Selecione o nível de prioridade");

        }
        else{

            startActivity(intent);
        }

    }

    private class AsyncCaller extends AsyncTask<Void, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(DetalheFila.this);

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
            String url = "http://www.devpauloaragao.com.br/sdmobile/rest/solucionador/chamado/visualizar/" + idChamado;
            String result = "";
            try {
                result = run(url);
                if (!result.isEmpty()) {
                    try {
                        JSONArray jArray = new JSONArray(result);
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObj = jArray.getJSONObject(i);
                            chamado.setNmChamado(jObj.getString("nmChamado"));
                            chamado.setMensagem(jObj.getString("mensagem"));

                            chamado.setStatus(jObj.getString("status"));
                            chamado.setDtInclusao(jObj.getString("dtInclusao"));
                            chamado.setDtAlteracao(jObj.getString("dtAlteracao"));
                            chamado.setDtExclusao(jObj.getString("dtExclusao"));
                            chamado.setDtInclusao(jObj.getString("dtInclusao"));
                            JSONObject jsonFila = jObj.getJSONObject("fila");
                            chamado.getFila().setPrioridade(Integer.parseInt(jsonFila.getString("prioridade")));
                        }

                    } catch (JSONException je) {
                        je.printStackTrace();
                    }
                }
            } catch (Exception e) {

            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            lbData.setText(chamado.getDtInclusao());
            lbStatus.setText(chamado.getStatus());
            lbNmSolicitante.setText(usuario.getNmUsuario());
            lbCargo.setText(usuario.getCargo());
            lbTitulo.setText(chamado.getNmChamado());
            lbMensagem.setText(chamado.getMensagem());
            lbPrioridade.setText(chamado.getFila().getPrioridade());
            lbLocal.setText(usuario.getLocal());


            pdLoading.dismiss();
        }

    }


    private class AsyncCaller2 extends AsyncTask<Void, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(DetalheFila.this);

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
            String url = "http://www.devpauloaragao.com.br/sdmobile/rest/solucionador/chamado/visualizar/" + idChamado;
            String result = "";
            try {
                result = run(url);
                if (!result.isEmpty()) {
                    try {
                        JSONArray jArray = new JSONArray(result);
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObj = jArray.getJSONObject(i);
                            chamado.setNmChamado(jObj.getString("nmChamado"));
                            chamado.setMensagem(jObj.getString("mensagem"));

                            chamado.setStatus(jObj.getString("status"));
                            chamado.setDtInclusao(jObj.getString("dtInclusao"));
                            chamado.setDtAlteracao(jObj.getString("dtAlteracao"));
                            chamado.setDtExclusao(jObj.getString("dtExclusao"));
                            chamado.setDtInclusao(jObj.getString("dtInclusao"));
                            JSONObject jsonFila = jObj.getJSONObject("fila");
                            chamado.getFila().setPrioridade(Integer.parseInt(jsonFila.getString("prioridade")));
                        }

                    } catch (JSONException je) {
                        je.printStackTrace();
                    }
                }
            } catch (Exception e) {

            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            lbData.setText(chamado.getDtInclusao());
            lbStatus.setText(chamado.getStatus());
            lbNmSolicitante.setText(usuario.getNmUsuario());
            lbCargo.setText(usuario.getCargo());
            lbTitulo.setText(chamado.getNmChamado());
            lbMensagem.setText(chamado.getMensagem());
            lbPrioridade.setText(chamado.getFila().getPrioridade());
            lbLocal.setText(usuario.getLocal());


            pdLoading.dismiss();
        }

    }


}
