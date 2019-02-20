package ws.interfaz;

import model.Asistencia;
import model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IUsuarioService {
	@Headers({ "Accept: application/json" })
	@POST("v1/usuario/login")
	Call<Usuario> IniciarSesion(@Body Usuario u);
	
	@Headers({ "Accept: application/json" })
	@POST("v1/usuario/asistencia")
	Call<Asistencia> MarcarAsistencia(@Body Asistencia u);
}
