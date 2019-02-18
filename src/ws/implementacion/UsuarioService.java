package ws.implementacion;

import common.Constantes;
import model.Usuario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ws.interfaz.IUsuarioService;

public class UsuarioService {

	public Usuario iniciarSesion(Usuario u) throws Exception {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
				.addConverterFactory(GsonConverterFactory.create()).build();
		IUsuarioService usuarioService = retrofit.create(IUsuarioService.class);
		Call<Usuario> user = usuarioService.iniciarSesion(u);
		return user.execute().body();
	}
}
