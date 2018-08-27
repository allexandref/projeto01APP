package br.unipe.projeto01app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.unipe.projeto01app.model.Pessoa;
import br.unipe.projeto01app.remote.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button buttonCadastrar;
    private Button buttonListar;
    private Button buttonExcluir;
    private Button buttonConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCadastrar = (Button) findViewById(R.id.bt_menu_cadastrar);
        buttonListar = (Button) findViewById(R.id.bt_menu_listar);
        buttonExcluir = (Button) findViewById(R.id.bt_menu_excluir);
        buttonConsultar = (Button) findViewById(R.id.bt_menu_consultar);


        View.OnClickListener listenerCadastrar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        };

        buttonCadastrar.setOnClickListener(listenerCadastrar);

        buttonListar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                startActivity(intent);
            }
        });

        buttonExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExcluirActivity.class);
                startActivity(intent);
            }
        });

        buttonConsultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConsultarActivity.class);
                startActivity(intent);
            }
        });
    }
}
