package br.unipe.projeto01app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.unipe.projeto01app.model.Pessoa;
import br.unipe.projeto01app.remote.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    private EditText nomePessoa;
    private EditText sobrenomePessoa;
    private Button buttonSalvar;
    private Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);

        nomePessoa      = (EditText) findViewById(R.id.id_nome);
        sobrenomePessoa = (EditText) findViewById(R.id.id_sobrenome);
        buttonSalvar    = (Button) findViewById(R.id.bt_salvar);
        buttonVoltar    = (Button) findViewById(R.id.bt_voltar);

        buttonSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Pessoa p = new Pessoa();
                p.setFirstName(nomePessoa.getText().toString());
                p.setLastName(sobrenomePessoa.getText().toString());
                Call<Pessoa> call = new RetrofitConfig().getService().cadastrar(p);

                call.enqueue(new Callback<Pessoa>() {
                    @Override
                    public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                        if(response.isSuccessful()){
                            Toast t = Toast.makeText(CadastroActivity.this, "Cadastrado realizado com sucesso.", Toast.LENGTH_LONG);
                            t.setGravity(Gravity.CENTER_VERTICAL, 0,0);
                            t.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pessoa> call, Throwable t) {
                        Log.e("CrudService   ", "Erro ao cadastrar pessoa:" + t.getMessage());
                    }
                });
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroActivity.this, MainActivity.class));
                Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
