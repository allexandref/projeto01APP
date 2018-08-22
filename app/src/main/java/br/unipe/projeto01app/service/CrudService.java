package br.unipe.projeto01app.service;

import java.util.List;

import br.unipe.projeto01app.model.Pessoa;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudService {

    @POST("persons")
    Call<Pessoa> cadastrar(@Body Pessoa pessoa);

    @GET("persons")
    Call<List<Pessoa>> obterPessoas();

    @PUT("persons/{id}")
    Call<Pessoa> atualizar(@Path("id") Long id, @Body Pessoa pessoa);

    @DELETE("persons/{id}")
    Call<Pessoa> delete(@Path("id") Long id);

    @GET("persons/{id}")
    Call<Pessoa> obterPessoasPorId(@Path("id") Long id);
}
