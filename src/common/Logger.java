package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Logger {

	public static void RegistrarIncidencia(Exception e) {
		try {
			String nombreDirectorio = System.getProperty("user.dir").concat("\\logs\\");
			File directorio = new File(nombreDirectorio);
			if (!directorio.exists()) {
				directorio.mkdir();
			}
			File f = new File(nombreDirectorio + LocalDate.now() + ".txt");
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("FECHA-HORA: " + LocalDateTime.now());
			bw.write("LOCALIZACIÓN: " + e.getStackTrace()[2].getMethodName());
			bw.write("EXCEPCIÓN: " + e.getClass().getName());
			bw.write("DETALLE: " + e.getMessage());
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}