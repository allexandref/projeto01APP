package br.unipe.projeto01app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * @objetivo Carregar o menu na Activity
     *
     * @param menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_cadastrar: cadastrar(); break;
            case R.id.menu_listar: listar(); break;
            case R.id.menu_excluir: excluir(); break;
            case R.id.menu_consultar: consultar(); break;
                default: return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void cadastrar(){
        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    public void listar(){
        Intent intent = new Intent(MainActivity.this, ListarActivity.class);
        startActivity(intent);
    }

    public void excluir(){
        Intent intent = new Intent(MainActivity.this, ExcluirActivity.class);
        startActivity(intent);
    }

    public void consultar(){
        Intent intent = new Intent(MainActivity.this, ConsultarActivity.class);
        startActivity(intent);
    }
}
