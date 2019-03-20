package ws.interfaz;

import common.MultiBody;
import model.Contrato;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ICompraService {
	
	@Headers({ "Accept: application/json" })
	@POST("v1/compra/generar")
	Call<Contrato> GenerarContrato(@Body MultiBody mb);
}
