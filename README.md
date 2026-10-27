# corrupted-sd

Herramienta de automatización para recuperar archivos de video corruptos usando [untrunc](https://github.com/ponchio/untrunc).

## Contexto

Cuando una tarjeta SD falla a mitad de grabación, los archivos de video quedan con el header dañado y no se pueden reproducir ni descargar correctamente. `untrunc` resuelve esto tomando un archivo de referencia grabado con la misma cámara y configuración para reconstruir el header de los archivos corruptos.

Este proyecto automatiza ese proceso: en vez de ejecutar `untrunc` manualmente archivo por archivo, el programa los detecta, los repara en lote y limpia los originales corruptos al finalizar.

## Requisitos

- Java 17+
- `untrunc.exe` (Windows) en la misma carpeta que el JAR
- Un archivo de video en buen estado grabado con la misma cámara (`video_bueno.mp4`), también en la misma carpeta

## Estructura esperada de la carpeta de ejecución

```
📁 carpeta/
├── corrupted-sd.jar
├── untrunc.exe
├── video_bueno.mp4      ← archivo de referencia
├── video_001.mp4        ← archivos corruptos a recuperar
├── video_002.mp4
└── ...
```

## Uso

1. Compilar el proyecto con Maven:
   ```bash
   mvn clean package
   ```

2. Copiar el JAR generado (`target/corrupted-sd-1.0-SNAPSHOT.jar`) a la carpeta junto con `untrunc.exe`, `video_bueno.mp4` y los archivos corruptos.

3. Ejecutar:
   ```bash
   java -jar corrupted-sd-1.0-SNAPSHOT.jar
   ```

El programa listará todos los archivos en la carpeta, llamará a `untrunc` por cada uno de ellos y borrará los archivos originales corruptos al terminar.

## ¿Cómo funciona?

| Clase | Responsabilidad |
|---|---|
| `EncontrarArchivos` | Detecta los archivos a recuperar en la carpeta de ejecución, excluyendo el JAR, el video de referencia y el ejecutable de untrunc |
| `RepararArchivos` | Lanza un proceso de `untrunc` por cada archivo corrupto y muestra el resultado en consola |
| `EliminarArchivosCorruptos` | Elimina los archivos corruptos originales una vez que `untrunc` generó las versiones recuperadas |

## Notas

- Los archivos recuperados por `untrunc` se guardan con el sufijo `_fixed` en el nombre, por lo que los originales son seguros de eliminar al finalizar.
- Este proyecto fue desarrollado y probado en Windows (de ahí el uso de `untrunc.exe`).
