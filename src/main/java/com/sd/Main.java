package com.sd;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        // Nombre del video de referencia; se puede pasar como argumento o usa el valor por defecto
        String nombreArchivoCorrecto = args.length > 0 ? args[0] : "video_bueno.mp4";

        // Detectamos los archivos corruptos en la carpeta de ejecución
        EncontrarArchivos encontrarArchivos = new EncontrarArchivos(nombreArchivoCorrecto);
        ArrayList<String> archivos = encontrarArchivos.getArchivosParaRecuperar();

        // Lanzamos untrunc sobre cada archivo corrupto
        RepararArchivos repararArchivos = new RepararArchivos(archivos, nombreArchivoCorrecto);
        repararArchivos.reparar();

        // Limpiamos los archivos originales corruptos
        EliminarArchivosCorruptos eliminar = new EliminarArchivosCorruptos(archivos);
        eliminar.eliminar();
    }
}
