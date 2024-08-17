package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Cadena_txt {

    private File archivo;

    public Cadena_txt() {
        archivo = crearArchivoAutoincrementable();
    }

    // Método para crear un archivo con un nombre autoincrementable
    private File crearArchivoAutoincrementable() {
        int contador = 1;
        File archivo;
        do {
            archivo = new File("archivo_" + contador + ".txt");
            contador++;
        } while (archivo.exists());  // Incrementar hasta encontrar un número disponible
        return archivo;
    }

    // Método para guardar contenido en el archivo y sobrescribir si ya existe
    public void guardarArchivo(String contenido) {
        try (FileWriter escritor = new FileWriter(archivo, false)) { // 'false' para sobrescribir
            escritor.write(contenido);
            System.out.println("Archivo guardado con éxito: " + archivo.getName());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    // Método para guardar el archivo con un nombre diferente
    public void guardarComo(String nuevoNombre, String contenido) {
        archivo = new File(nuevoNombre);
        guardarArchivo(contenido);
    }

    // Método para abrir y leer el contenido del archivo
    public void abrirArchivo() {
        try {
            String rutaArchivo = "C:\\Users\\robin\\OneDrive\\Desktop\\editor_texto1";
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            System.out.println("Contenido del archivo:\n" + contenido);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    // Método para eliminar el archivo
    public void eliminar() {
        if (archivo.delete()) {
            System.out.println("Archivo eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el archivo.");
        }
    }

    public static void main(String[] args) {
        Cadena_txt archivo = new Cadena_txt();

        // Guardar contenido en el archivo y sobrescribir si ya existe
        archivo.guardarArchivo("Este es el contenido inicial del archivo.");

        // Cambiar el nombre del archivo y guardar nuevo contenido
        archivo.guardarComo("nuevoNombre.txt", "Este es el contenido después de cambiar el nombre.");

        // Abrir y mostrar el contenido del archivo
        archivo.abrirArchivo();

        // Eliminar el archivo
        archivo.eliminar();
    }
}