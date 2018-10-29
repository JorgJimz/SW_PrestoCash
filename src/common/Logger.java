package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Logger {

	public static void RegistrarIncidencia(Exception x) {
		try {
			File f = new File(System.getProperty("user.dir").concat("\\logs\\") + LocalDate.now() + ".txt");
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("FECHA-HORA: " + LocalDateTime.now());
			bw.write("VISTA/CONTROLADOR/METODO: " + x.getCause().getMessage());
			bw.write("HEADER: " + x.getLocalizedMessage());
			bw.write("CONTENT: " + x.getMessage());
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}