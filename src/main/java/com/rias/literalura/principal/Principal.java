package com.rias.literalura.principal;

import com.rias.literalura.modelos.Libro;
import com.rias.literalura.modelos.dto.DatosAutor;
import com.rias.literalura.modelos.dto.DatosLibros;
import com.rias.literalura.modelos.dto.DatosResponse;
import com.rias.literalura.repository.AutorRepository;
import com.rias.literalura.repository.LibroRepository;

import java.util.List;
import java.util.Scanner;

// import com.rias.literalura.repository.LibroRepository;
import com.rias.literalura.modelos.Autor;
import com.rias.literalura.service.ConvierteDatos;
import com.rias.literalura.service.ServiceConsultaApi;

public class Principal
{
    private static ServiceConsultaApi consultaApi = new ServiceConsultaApi();
    private Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String SEACH = "?search=";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private final String commandLinner = "> ";
    private String menu = """
            ----------------------------------------------
            Literalura realizado por Roberto Aguirre
            
            1 - Buscar libro por titulo
            2 - Lista libros registrados
            3 - Listar autores registrados
            4 - Listar autores vivos en un determinado año
            5 - Listar libros por idiomas


            0 - Salir
            ----------------------------------------------
            """;
    private String idiomas = """
            Elige un idioma para filtrar los libros
            es - spañol
            en - Ingles
            fr - Frances
            pt - Portugues
            it - Italiano
            """;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {

    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println(menu);
            System.out.print(commandLinner);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    verLibrosBuscados();
                    break;
                case 3:
                    verAutoresRegistrados();
                    break;
                case 4:
                    verAutoresEnAnnoN();
                    break;
                case 5:
                    verLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Valor incorrecto, seleccione algo del menu.");
                    break;
            }
        }
    }

    private void buscarLibroPorTitulo() {

        DatosResponse datos = convierteDatos.obtenerDatos(consultaLibroTitulo(), DatosResponse.class);
        try {
            DatosLibros datosLibro = datos.datosLibros().get(0);
            DatosAutor datosAutor = datos.datosLibros().get(0).autores().get(0);
            Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                    .orElseGet(() -> autorRepository.save(new Autor(datosAutor)));
            if (libroRepository.findByTitulo(datosLibro.titulo()).isEmpty()) {
                Libro libro = new Libro(datosLibro);
                libro.setAutor(autor);
                libroRepository.save(libro);
                System.out.println("Datos libro:\n" + libro);
                System.out.println("Libro guardado en base de datos");

            } 
            else {
                System.out.println("==================================\n" + "El libro ya se encuntra registrado"+ "\n==================================");
            }
        } catch (Exception e) {

            System.out.printf("");
            System.out.println("===================\n" + "Libro no encontrado" + "\n===================");
        }

    }

    private void verLibrosBuscados() {
        List<Libro> librosBuscados = libroRepository.findAll();
        librosBuscados.stream()
                .forEach(e -> System.out.println(e.toString()));
    }

    private void verAutoresRegistrados() {
        autorRepository.findAll().stream()
                .forEach(a -> System.out.println(a));
    }

    private void verAutoresEnAnnoN() {
        System.out.println("Ingresa el año de nacimiento");
        System.out.print(commandLinner);
        int inicio = teclado.nextInt();
        System.out.println("Ingresa el año de nacimiento");
        System.out.print(commandLinner);
        int fin = teclado.nextInt();
        var autores = autorRepository.encontrarAutoresEntreFechas(inicio, fin);
        if (autores.isEmpty()) {
            System.out.println("Ningun autor encontrado");
        } else {
            autores.stream().forEach(a -> System.out.println(a));
        }
    }

    private void verLibrosPorIdioma() {
        System.out.println(idiomas);
        System.out.println("Ingresa las siglas para buscar un libro ");
        System.out.print(commandLinner);
        String idioma = teclado.nextLine();
        var libros = libroRepository.encontrarPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("Ningun libro encontrado");
        } else {
            libros.stream().forEach(a -> System.out.println(a));
        }
    }

    public String consultaLibroTitulo() {
        System.out.println("Ingresa el titulo a buscar: ");
        System.out.print(commandLinner);
        var libro = teclado.nextLine().replace(" ", "%20");
        var json = consultaApi.obtenerDatos(URL_BASE + SEACH + libro);
        // DatosResponse respuesta = convierteDatos.obtenerDatos(json,
        // DatosResponse.class);
        // System.out.println(json);
        // List<DatosLibro> datosLibros = respuesta.datosLibros();

        return json;
        // return null;
    }
}
