package br.unipe.projeto01app.remote;

import br.unipe.projeto01app.service.CrudService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://10.60.0.47:8080/api/v1.0/")
                .client(client.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public CrudService getService() {
        return this.retrofit.create(CrudService.class);
    }
}
