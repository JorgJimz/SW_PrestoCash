package ws.interfaz;

import java.util.List;

import common.MultiBody;
import model.Contrato;
import model.Prestamo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IContratoService {
	@Headers({ "Accept: application/json" })
	@POST("v1/prestamo/{sede}/cargar")
	Call<List<Prestamo>> CargarPrestamos(@Path(value = "sede") int sede, @Query("status") int status);

	@Headers({ "Accept: application/json" })
	@POST("v1/contrato/correlativo")
	Call<String> ObtenerCorrelativo(@Query("flag") String flag);

	@Headers({ "Accept: application/json" })
	@POST("v1/contrato/generar")
	Call<Contrato> GenerarContrato(@Body MultiBody mb);
}
