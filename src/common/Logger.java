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
			bw.newLine();
			bw.write("FECHA-HORA: " + LocalDateTime.now());
			bw.newLine();
			bw.write("EXCEPCIÓN: " + e.getClass().getName() + " - " + e.getMessage());
			bw.newLine();
			bw.write("DETALLE: ");
			bw.newLine();
			for (int i=0; i < 5 ; i++) {
				bw.write("ARCHIVO: " + e.getStackTrace()[i].getFileName());
				bw.newLine();
				bw.write("\t LÍNEA: " + e.getStackTrace()[i].getLineNumber());
				bw.newLine();
				bw.write("\t \t CLASE: " + e.getStackTrace()[i].getClassName());
				bw.newLine();
				bw.write("\t \t \t MÉTODO: " + e.getStackTrace()[i].getMethodName());
				bw.newLine();
			}
			bw.write("***********************************************************************");
			bw.flush();
			bw.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}