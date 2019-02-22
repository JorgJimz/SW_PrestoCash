package ws.implementacion;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.AnnotationExclusionStrategy;
import common.Constantes;
import common.MultiBody;
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
			MultiBody mb = new MultiBody();
			mb.setContrato(c);
			mb.setEgreso(e);
			Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
			Retrofit retrofit = new Retrofit.Builder().baseUrl(Constantes.URI_WS)
					.addConverterFactory(GsonConverterFactory.create(gson)).build();
			IContratoService contratoService = retrofit.create(IContratoService.class);
			Call<Contrato> contrato = contratoService.GenerarContrato(mb);
			return contrato.execute().body();
		} catch (Exception ex) {
			System.out.println(ex.getClass());
			//ex.printStackTrace();
			return Contrato.DEFAULT;
		}
	}

}
