package sdmobile1.br.usjt.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import sdmobile1.br.usjt.myapplication.Base.BaseActivity;
import sdmobile1.br.usjt.myapplication.Model.Chamado;
import sdmobile1.br.usjt.myapplication.Model.Usuario;
/**
 * Created by Diego Sgarbi RA:201517012 & Paulo Aragão RA:201522680 - Grupo 4
 */
public class TelaSolicitante extends BaseActivity {

    private Button btNovoChamado;
    private Button btVisChamados;
    private Button btSair;
    private Chamado chamado;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_solicitante);
        Intent intent = getIntent();
        Intent intent2 = getIntent();
        usuario = (Usuario) getIntent().getExtras().get("usuario");
        btNovoChamado = (Button)findViewById(R.id.btNovoChamado);
        btVisChamados = (Button)findViewById(R.id.btVisChamados);
        btSair = (Button)findViewById(R.id.btSair);
        chamado = new Chamado();
    }
// botão Novo chamado
    public void novoChamado(View view){
        Intent intent1 = new Intent(this,NovoChamado.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);
    }
//botão sair
    public void retornarActivityLogin(View view){
        Intent intent3 = new Intent(this,MainActivity.class);
        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent3);
        alert("Logout realizado com sucesso.");
    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
// botão verificar chamados

    public void visualizarChamados(View view){
        Intent intent4 = new Intent(this,VisualizarChamados.class);
        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent4.putExtra("usuario", usuario);
        startActivity(intent4);
    }

  }
