package com.sd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RepararArchivos {

    private ArrayList<String> listadoArchivos;
    private String nombreCorrecto;

    public RepararArchivos(ArrayList<String> archivos, String nombreVideoBueno) {
        this.listadoArchivos = archivos;
        this.nombreCorrecto = nombreVideoBueno;
    }

    public void reparar() throws IOException, InterruptedException {
        for (String nombreArchivo : listadoArchivos) {
            ProcessBuilder pb = new ProcessBuilder(
                    ".\\untrunc.exe", "-s", nombreCorrecto, nombreArchivo
            );

            pb.redirectErrorStream(true);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Se reparó " + nombreArchivo);
            } else {
                System.out.println("Ocurrió un error con el video " + nombreArchivo + " código: " + exitCode);
            }
        }
    }
}
