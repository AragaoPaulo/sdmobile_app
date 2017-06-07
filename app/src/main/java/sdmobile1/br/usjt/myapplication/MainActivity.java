package sdmobile1.br.usjt.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import sdmobile1.br.usjt.myapplication.Model.Usuario;
/**
 * Created by Diego Sgarbi RA:201517012 & Paulo Aragão RA:201522680 - Grupo 4
 */
public class MainActivity extends BaseActivity {
    private EditText lbUsuario;
    private EditText lbSenha;
    private Button btEntrar;
    private Usuario usuario;
    private String email;
    private String senha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent3 = getIntent();
        Intent intent6 = getIntent();
        lbUsuario = (EditText)findViewById(R.id.lbUsuario);
        lbSenha = (EditText)findViewById(R.id.lbSenha);
        btEntrar = (Button)findViewById(R.id.btEntrar);
        usuario = new Usuario();


        OkHttpClient client = new OkHttpClient();

    }

    public void logarUsuario(View view){


        TextView lbUsuario = (TextView) findViewById(R.id.lbUsuario);
        TextView lbSenha = (TextView) findViewById(R.id.lbSenha);
        email = lbUsuario.getText().toString();
        senha = lbSenha.getText().toString();
        if(email.equalsIgnoreCase("")){
            showMsg("Preencha o email por favor.");
        }
        else if(senha.equalsIgnoreCase("")){
            showMsg("Preencha a senha por favor.");
        }
        else{
            if(isConnected(MainActivity.this)){
                new AsyncCaller(MainActivity.this).execute();
            }
            else{
                showMsg("Você não está conectado a internet.");
            }
        }
    }


    private class AsyncCaller extends AsyncTask<Void, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        Context context;
        private AsyncCaller(Context context) {
            this.context = context.getApplicationContext();
        }

        String run(String url) throws IOException{
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
            String url = "http://www.devpauloaragao.com.br/sdmobile/rest/usuario/" + email + "/" + senha;
            String result = "";
            try{
                result = run(url);
                if(!result.isEmpty()){
                    try {
                        JSONObject jObj = new JSONObject(result);
                            usuario.setIdUsuario(Integer.parseInt(jObj.getString("idUsuario")));
                            usuario.setNmUsuario(jObj.getString("nmUsuario"));
                            usuario.setCargo(jObj.getString("cargo"));
                            usuario.setEmail(jObj.getString("email"));
                            usuario.setSenha(jObj.getString("senha"));
                            usuario.setTelefone(jObj.getString("telefone"));
                            usuario.setStatus(jObj.getString("status").charAt(0));
                            usuario.setDtInclusao(jObj.getString("dtInclusao"));
                            usuario.setDsPerfil(jObj.getJSONObject("perfil").getString("dsPerfil"));
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
            if(usuario.getIdUsuario() != null){
                if(usuario.getDsPerfil().equalsIgnoreCase("SOLICITANTE")){
                    Intent intent = new Intent(MainActivity.this, TelaSolicitante.class);
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);
                }
                if(usuario.getDsPerfil().equalsIgnoreCase("SOLUCIONADOR")) {
                    Intent intent = new Intent(MainActivity.this, TelaSolucionador.class);
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);
                }
            }
            else{
                showMsg("Email ou senhas invalidos.");
            }
            pdLoading.dismiss();
        }

    }
}
