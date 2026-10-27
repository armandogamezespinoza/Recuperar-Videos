package com.sd;

import java.io.File;
import java.util.ArrayList;

public class EliminarArchivosCorruptos {

    private ArrayList<String> listadoArchivos;

    public EliminarArchivosCorruptos(ArrayList<String> listado) {
        this.listadoArchivos = listado;
    }

    public void eliminar() {
        for (String video : listadoArchivos) {
            File f = new File(video);

            if (f.exists() && f.delete()) {
                System.out.println("Se eliminó correctamente el archivo " + video);
            } else {
                System.out.println("No se pudo eliminar el archivo " + video);
            }
        }
    }
}
