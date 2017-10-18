package com.example.danceculture.municipiosnar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.danceculture.municipiosnar.DatosApi.SitiosService;
import com.example.danceculture.municipiosnar.models.Municipio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    public static final String TAG = "datosColombia";

    ArrayList<String> listDatos;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView)findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();
    }

    public void obtenerDatos(){
        SitiosService service = retrofit.create(SitiosService.class);
        Call<List<Municipio>> sitioRespuestaCall = service.obtenerListaDeSitios();
        sitioRespuestaCall.enqueue(new Callback<List<Municipio>>() {
            @Override
            public void onResponse(Call<List<Municipio>> call, Response<List<Municipio>> response) {

                if (response.isSuccessful()){
                    List lista = response.body();
                    for (int i =0 ; i <lista.size();i++) {
                        Municipio m = (Municipio) lista.get(i);
                        Log.i(TAG, " | Nombre : " + m.getNombreMunicipio() + " | Alcalde: " + m.getNombreAlcalde() + "| Correo :  " + m.getCorreocontactenos());
                    }

                }else{
                    Log.e(TAG,"OnResponse : "+response.errorBody());
                }


            }

            @Override
            public void onFailure(Call<List<Municipio>> call, Throwable t) {
                Log.e(TAG,"OnFailure : "+t.getMessage());

                //HACER TAREA CON RECYCLE VIEW
                //HACER TAREA CARD VIEW

            }
        });



    }
}
