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
import retrofit2.Retrofit;

public class ConsultarActivity extends AppCompatActivity {

    private EditText id;
    private Button buttonConsultar;
    private Button buttonVoltar;
    private EditText nome;
    private EditText sobrenome;
    private Pessoa pessoa;
    private Button buttonAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar);

        id              = (EditText)findViewById(R.id.id);
        buttonConsultar = (Button)  findViewById(R.id.bt_consultar);
        buttonVoltar    = (Button)  findViewById(R.id.bt_voltar);
        nome            = (EditText)findViewById(R.id.id_nome);
        sobrenome       = (EditText)findViewById(R.id.id_sobrenome);
        buttonAlterar   = (Button)  findViewById(R.id.bt_alterar);

        buttonConsultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Call<Pessoa> call = new RetrofitConfig().getService().obterPessoasPorId(Long.valueOf(id.getText().toString()));
                call.enqueue(new Callback<Pessoa>() {
                    @Override
                    public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                        if(response.isSuccessful()){
                           pessoa = response.body();
                           nome.setText(pessoa.getFirstName());
                           sobrenome.setText(pessoa.getLastName());
                        }
                    }

                    @Override
                    public void onFailure(Call<Pessoa> call, Throwable t) {
                        Log.e("CrudService   ", "Erro ao consultar pessoa:" + t.getMessage());
                    }
                });
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonAlterar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                pessoa.setFirstName(nome.getText().toString());
                pessoa.setLastName(sobrenome.getText().toString());
                Call<Pessoa> call = new RetrofitConfig().getService().atualizar(pessoa.getId(), pessoa);

                call.enqueue(new Callback<Pessoa>() {
                    @Override
                    public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                        Toast t = Toast.makeText(ConsultarActivity.this, "Alterado com sucesso.", Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER_VERTICAL, 0,0);
                        t.show();
                    }

                    @Override
                    public void onFailure(Call<Pessoa> call, Throwable t) {
                        Log.e("CrudService   ", "Erro ao alterar pessoa:" + t.getMessage());
                    }
                });
            }
        });
    }
}
