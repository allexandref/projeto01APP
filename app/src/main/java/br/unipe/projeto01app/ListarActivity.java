package br.unipe.projeto01app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.unipe.projeto01app.adapter.AdapterPessoa;
import br.unipe.projeto01app.model.Pessoa;
import br.unipe.projeto01app.remote.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarActivity extends AppCompatActivity {

    private List<Pessoa> pessoas = new ArrayList<Pessoa>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);

        listView = (ListView) findViewById(R.id.list_pessoas);

        Call<List<Pessoa>> call = new RetrofitConfig().getService().obterPessoas();

        call.enqueue(new Callback<List<Pessoa>>() {
            @Override
            public void onResponse(Call<List<Pessoa>> call, Response<List<Pessoa>> response) {
                if(response.isSuccessful()){
                    pessoas = response.body();
                    listView.setAdapter(new AdapterPessoa(ListarActivity.this, R.layout.listar, pessoas));
                }
            }

            @Override
            public void onFailure(Call<List<Pessoa>> call, Throwable t) {
                Log.e("CrudService   ", "Erro ao obter pessoas:" + t.getMessage());
            }
        });

//        buttonVoltar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ListarActivity.this, MainActivity.class));
//                Intent intent = new Intent(ListarActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
