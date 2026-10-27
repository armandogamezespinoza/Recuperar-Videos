package com.sd;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class EncontrarArchivos {

    // Archivos corruptos a recuperar
    private ArrayList<String> archivosParaRecuperar = new ArrayList<>();
    private String videoDeEjemplo;

    public EncontrarArchivos(String nombreArchivoReferencia) throws URISyntaxException {
        this.videoDeEjemplo = nombreArchivoReferencia;

        File jarFile = new File(EncontrarArchivos.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI().getPath());

        File targetDir = jarFile.getParentFile();
        File[] files = targetDir.listFiles();

        if (files != null) {
            for (File file : files) {
                // Excluimos el propio JAR, el video de referencia y el ejecutable de untrunc
                if (!file.getName().equals(jarFile.getName())
                        && !file.getName().equals(videoDeEjemplo)
                        && !file.getName().equals("untrunc.exe")) {
                    archivosParaRecuperar.add(file.getName());
                }
            }
        }
    }

    public ArrayList<String> getArchivosParaRecuperar() {
        return archivosParaRecuperar;
    }

    public String getArchivoEjemplo() {
        return videoDeEjemplo;
    }
}