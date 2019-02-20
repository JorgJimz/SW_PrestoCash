package ws.implementacion;

import java.util.List;

import common.Constantes;
import common.NullOnEmptyConverterFactory;
import model.Articulo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ws.interfaz.IArticuloService;

public class ArticuloService {

	public List<Articulo> ObtenerHistorial(int clienteId) {
		try {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
					.addConverterFactory(new NullOnEmptyConverterFactory())
					.addConverterFactory(GsonConverterFactory.create()).build();
			IArticuloService articuloService = retrofit.create(IArticuloService.class);
			Call<List<Articulo>> lHistorialArticulo = articuloService.ObtenerHistorial(clienteId);
			return lHistorialArticulo.execute().body();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Articulo ObtenerArticulo(int id) {
		try {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
					.addConverterFactory(new NullOnEmptyConverterFactory())
					.addConverterFactory(GsonConverterFactory.create()).build();
			IArticuloService articuloService = retrofit.create(IArticuloService.class);
			Call<Articulo> articulo = articuloService.ObtenerArticulo(id);
			return articulo.execute().body();
		} catch (Exception e) {
			e.printStackTrace();
			return Articulo.DEFAULT;
		}
	}

}
