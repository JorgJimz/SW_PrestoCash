package ws.interfaz;

import java.util.List;

import model.Articulo;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IArticuloService {
	@Headers({ "Accept: application/json" })
	@POST("v1/articulo/historial")
	Call<List<Articulo>> ObtenerHistorial(@Query("clienteId") int clienteId);

	@Headers({ "Accept: application/json" })
	@POST("v1/articulo/obtener")
	Call<Articulo> ObtenerArticulo(@Query("id") int id);
}
