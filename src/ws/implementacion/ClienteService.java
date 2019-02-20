package ws.implementacion;

import common.Constantes;
import common.NullOnEmptyConverterFactory;
import model.Cliente;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ws.interfaz.IClienteService;

public class ClienteService {

	public Cliente BuscarCliente(String documento) {
		try {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
					.addConverterFactory(new NullOnEmptyConverterFactory())
					.addConverterFactory(GsonConverterFactory.create()).build();
			IClienteService clienteService = retrofit.create(IClienteService.class);
			Call<Cliente> client = clienteService.BuscarCliente(documento);
			return client.execute().body();
		} catch (Exception e) {
			e.printStackTrace();
			return Cliente.DEFAULT;
		}
	}

}
