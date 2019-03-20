package ws.interfaz;

import model.Cliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IClienteService {
	@Headers({ "Accept: application/json" })
	@POST("v1/cliente/buscar")
	Call<Cliente> BuscarCliente(@Query("documento") String documento);
	
	@Headers({ "Accept: application/json" })
	@POST("v1/cliente/registrar")
	Call<Cliente> RegistrarCliente(@Body Cliente c);

}
