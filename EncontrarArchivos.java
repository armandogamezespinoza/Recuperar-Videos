package com.sd;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class EncontrarArchivos {

    // Aquí se van a almacenar los nombres de los archivos con los que trabajaremos
    // Formato de ArrayList<String> para guardar los nombres de los archivos
    private ArrayList<String> archivosParaRecuperar = new ArrayList<>();
    private String videoDeEjemplo;

    public EncontrarArchivos() throws URISyntaxException {
        File jarFile = new File(EncontrarArchivos.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI().getPath());

        File targetDir = jarFile.getParentFile();
        File[] files = targetDir.listFiles();

        this.videoDeEjemplo = "video_bueno.mp4";

        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals(jarFile.getName())) { // excluir el propio JAR
                    if (!file.getName().equals(videoDeEjemplo)){ // excluir el video de ejemplo
                        archivosParaRecuperar.add(file.getName());
                    }
                }
            }
        }

        System.out.println("Se estará trabajando con los archivos de la carpeta " + targetDir.getPath());
        System.out.println("Los archivos son: " + archivosParaRecuperar);
    }

    // Implementación de un getter para poder usar la lista de archivos
    public ArrayList<String> getArchivosParaRecuperar() {
        return archivosParaRecuperar;
    }
}