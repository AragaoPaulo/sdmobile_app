package sdmobile1.br.usjt.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import sdmobile1.br.usjt.myapplication.Base.BaseActivity;
import sdmobile1.br.usjt.myapplication.Model.Chamado;

/**
 * Created by Diego Sgarbi RA:201517012 & Paulo Aragão RA:201522680 - Grupo 4
 */
public class MeuChamado extends BaseActivity {
    private Integer idChamado;
    private TextView lbData;
    private TextView lbStatus;
    private TextView lbTitulo;
    private TextView lbMensagem;
    private TextView lbDtResposta;
    private TextView lbResposta;
    private Button btVoltar;
    private Chamado chamado;
    private Button btCancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_chamado);
        chamado = (Chamado) getIntent().getExtras().get("chamado");
        lbData = (TextView)findViewById(R.id.lbData);
        lbStatus = (TextView)findViewById(R.id.lbStatus);
        lbTitulo = (TextView)findViewById(R.id.lbTitulo);
        lbMensagem = (TextView)findViewById(R.id.lbMensagem);
        lbDtResposta = (TextView)findViewById(R.id.lbDtResposta);
        lbResposta = (TextView)findViewById(R.id.lbResposta);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        btCancelar = (Button)findViewById(R.id.btCancelar);
        lbData.setText(chamado.getDtInclusao());
        lbStatus.setText(chamado.getStatus());
        lbTitulo.setText(chamado.getNmChamado());
        lbMensagem.setText(chamado.getMensagem());
        if (chamado.getDtExclusao()!= null) {
            lbDtResposta.setText(chamado.getDtExclusao());
        }

        if (chamado.getResposta()!= null) {
            lbResposta.setText(chamado.getResposta());
        }
            //new AsyncCaller().execute();


    }

    public void voltarLista(View view){
        Intent intent = new Intent(MeuChamado.this, VisualizarChamados.class);
        startActivity(intent);
    }
    public void cancelarChamado(View view){
    Intent intent = new Intent(MeuChamado.this, VisualizarChamados.class);
        startActivity(intent);
        if ()
    }
}
    private class AsyncCaller extends AsyncTask<Void, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(MeuChamado.this);

        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
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
            String url = "http://www.devpauloaragao.com.br/sdmobile/rest/usuario/chamado/cancelar/";
            String result = "";
            try {
                JSONObject jOb = new JSONObject();
                jOb.put("idChamado", chamado.getIdChamado());
                result = post(url, jOb.toString());
                if (!result.isEmpty()) {
                    try {
                        JSONArray jArray = new JSONArray(result);
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObj = jArray.getJSONObject(i);
                            chamado.setNmChamado(jObj.getString("nmChamado"));
                            chamado.setMensagem(jObj.getString("mensagem"));
                            chamado.setResposta(jObj.getString("resposta"));
                            chamado.setStatus(jObj.getString("status"));
                            chamado.setDtInclusao(jObj.getString("dtInclusao"));
                            chamado.setDtAlteracao(jObj.getString("dtAlteracao"));
                            chamado.setDtExclusao(jObj.getString("dtExclusao"));
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
            pdLoading.dismiss();
        }
    }



