package com.example.danceculture.municipiosnar.DatosApi;

import com.example.danceculture.municipiosnar.models.Municipio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by DanceCulture on 12/10/17.
 */

public interface SitiosService {

    @GET("pfet-63uw.json")

    Call<List<Municipio>> obtenerListaDeSitios();

}
