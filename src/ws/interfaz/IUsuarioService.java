package ws.interfaz;

import model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IUsuarioService {
	@Headers({ "Accept: application/json" })
	@POST("v1/login")
	Call<Usuario> iniciarSesion(@Body Usuario u);
}
