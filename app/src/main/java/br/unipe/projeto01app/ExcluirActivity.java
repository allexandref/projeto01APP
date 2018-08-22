package br.unipe.projeto01app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.unipe.projeto01app.model.Pessoa;
import br.unipe.projeto01app.remote.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExcluirActivity extends AppCompatActivity {

    private EditText id;
    private Button buttonExcluir;
    private Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletar);

        id = (EditText) findViewById(R.id.id);
        buttonExcluir = (Button) findViewById(R.id.bt_deletar);
        buttonVoltar = (Button) findViewById(R.id.bt_voltar);

        buttonExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Call<Pessoa> call = new RetrofitConfig().getService().delete(Long.valueOf(id.getText().toString()));
                call.enqueue(new Callback<Pessoa>() {
                    @Override
                    public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ExcluirActivity.this, "Excluído com sucesso.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pessoa> call, Throwable t) {
                        Log.e("CrudService   ", "Erro ao excluir pessoa:" + t.getMessage());
                    }
                });
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExcluirActivity.this, MainActivity.class));
                Intent intent = new Intent(ExcluirActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
