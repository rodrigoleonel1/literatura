package com.rias.literalura.modelos;

import com.rias.literalura.modelos.dto.DatosLibros;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Double descargas;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {
    }

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idioma = datosLibros.idioma().get(0);
        this.descargas = datosLibros.numeroDeDescargas();
    }

    @Override
    public String toString() {
         String libro = "__________________________________" +
                        "\n========== Libros ===============" +
                        "\nTitulo: " + this.titulo +
                        "\nAutor: " + this.autor.getNombre() +
                        "\nIdioma: " + this.idioma +
                        "\nDescargas: " + this.descargas;

        String footer = "\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°";
        return libro + footer;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.ForeignKey;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "libros")
// public class Libro {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long Id;
// @Column(unique = true)
// private String nombreLibro;
// private String idiomas;
// private Integer descargas;
// // ! Linked Id

// // @Column(unique = true)
// @ManyToOne
// @JoinColumn(name = "autor_id")
// private Autor autor;

// public Libro() {

// }

// public Libro(DatosLibro datosLibro) {
// this.nombreLibro = datosLibro.nombre();
// this.idiomas = datosLibro.idiomas().get(0);
// this.descargas = datosLibro.descargas();
// }

// @Override
// public String toString() {
// String libro = """
// ============ Libro ============
// Titulo: %titulo
// Autor: %autor
// Idioma: %idioma
// Descargas totales: %descargas
// ===============================
// """;
// libro = libro.replace("%titulo", this.getNombreLibro());
// libro = libro.replace("%autor", this.getAutor().getNombreAutor());
// switch (this.idiomas) {
// case "es":
// libro = libro.replace("%idioma", "Español");
// break;
// case "en":
// libro = libro.replace("%idioma", "Ingles");
// break;
// case "fr":
// libro = libro.replace("%idioma", "Frances");
// break;
// default:
// libro = libro.replace("%idioma", "Idioma desconocido");
// break;
// }

// libro = libro.replace("%descargas", String.valueOf(this.getDescargas()));
// return libro;
// }
// public String StringfromList(){
// return this.getNombreLibro();
// }

// public Long getId() {
// return Id;
// }

// public void setId(Long id) {
// this.Id = id;
// }

// public String getIdiomas() {
// return idiomas;
// }

// public void setIdiomas(String idiomas) {
// this.idiomas = idiomas;
// }

// public Integer getDescargas() {
// return descargas;
// }

// public void setDescargas(Integer descargas) {
// this.descargas = descargas;
// }

// public Autor getAutor() {
// return autor;
// }

// public void setAutor(Autor autor) {
// this.autor = autor;
// }

// public String getNombreLibro() {
// return nombreLibro;
// }

// public void setNombreLibro(String nombreLibro) {
// this.nombreLibro = nombreLibro;
// }

// }
