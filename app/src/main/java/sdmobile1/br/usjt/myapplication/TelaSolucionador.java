package sdmobile1.br.usjt.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sdmobile1.br.usjt.myapplication.Base.BaseActivity;
/**
 * Created by Diego Sgarbi RA:201517012 & Paulo Aragão RA:201522680 - Grupo 4
 */
public class TelaSolucionador extends BaseActivity {
    private Button btVerChamados;
    private Button btGerFila;
    private Button btSair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_solucionador);
        Intent intent5 = getIntent();
        Intent intent8 = getIntent();
        btVerChamados = (Button) findViewById(R.id.btVerChamados);
        btGerFila = (Button)findViewById(R.id.btGerFila);
        btSair = (Button)findViewById(R.id.btSair);
    }

    public void gerenciarFila(View view){
        Intent intent7 = new Intent(this,GerenciarFila.class);
        intent7.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent7);

    }

    public void responderChamado(View view){
        Intent intent9 = new Intent(this,ResponderChamados.class);
        intent9.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent9);

    }


    public void retornarActivityLogin(View view) {
        Intent intent6 = new Intent(this, MainActivity.class);
        intent6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent6);
        alert("Logout realizado com sucesso.");
    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }



}
