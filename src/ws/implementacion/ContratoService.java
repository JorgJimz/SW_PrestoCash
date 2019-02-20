package ws.implementacion;

import java.util.List;

import common.Constantes;
import common.NullOnEmptyConverterFactory;
import model.Contrato;
import model.Egreso;
import model.Prestamo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ws.interfaz.IContratoService;

public class ContratoService {

	public List<Prestamo> CargarPrestamos(int sede, int status) {
		try {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
					.addConverterFactory(new NullOnEmptyConverterFactory())
					.addConverterFactory(GsonConverterFactory.create()).build();
			IContratoService contratoService = retrofit.create(IContratoService.class);
			Call<List<Prestamo>> lPrestamo = contratoService.CargarPrestamos(sede, status);
			return lPrestamo.execute().body();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String ObtenerCorrelativo(String flag) {
		try {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
					.addConverterFactory(new NullOnEmptyConverterFactory())
					.addConverterFactory(GsonConverterFactory.create()).build();
			IContratoService contratoService = retrofit.create(IContratoService.class);
			Call<String> correlativo = contratoService.ObtenerCorrelativo(flag);
			return correlativo.execute().body();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Contrato GenerarContrato(Contrato c, Egreso e) {
		try {
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
					.addConverterFactory(GsonConverterFactory.create()).build();
			IContratoService contratoService = retrofit.create(IContratoService.class);
			Call<Contrato> contrato = contratoService.GenerarContrato(c, e);
			return contrato.execute().body();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Contrato.DEFAULT;
		}
	}

}
