package ws.implementacion;

import common.Constantes;
import model.Asistencia;
import model.Usuario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ws.interfaz.IUsuarioService;

public class UsuarioService {

	public Usuario iniciarSesion(Usuario u) {		
		try {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
					.addConverterFactory(GsonConverterFactory.create()).build();
			IUsuarioService usuarioService = retrofit.create(IUsuarioService.class);
			Call<Usuario> user = usuarioService.IniciarSesion(u);
			return user.execute().body();			
		} catch (Exception e) {			
			e.printStackTrace();
			return Usuario.DEFAULT;
		}
	}

	public Asistencia MarcarAsistencia(Asistencia a) {
		try {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
					.addConverterFactory(GsonConverterFactory.create()).build();
			IUsuarioService usuarioService = retrofit.create(IUsuarioService.class);
			Call<Asistencia> asistencia = usuarioService.MarcarAsistencia(a);
			return asistencia.execute().body();
		} catch (Exception e) {
			e.printStackTrace();
			return Asistencia.DEFAULT;
		}
	}
}
