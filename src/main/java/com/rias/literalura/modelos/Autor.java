package com.rias.literalura.modelos;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import com.rias.literalura.modelos.dto.DatosAutor;

@Entity
@Table(name = "autores")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String nombre;
    private Integer annoNacimiento;
    private Integer annoMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.annoNacimiento = datosAutor.fechaDeNacimiento();
        this.annoMuerte = datosAutor.fechaDeMuerte();
    }

    @Override
    public String toString() {
        String autor =  "\n__________________________________"+
                        "\n!!!!!!!!!! Autores !!!!!!!!!!!!!!"+
                        "\nAutor: "+ this.nombre+
                        "\nAño nacimiento: " + this.annoNacimiento+
                        "\nAño fallecimiento: "+this.annoMuerte+
                        "\nLibros"+ libros.stream().map(Libro::getTitulo).collect(Collectors.toList());   
                        
        String footer = "\n==================================";
            return autor+footer;
        }

    public Integer getAnnoNacimiento() {
        return annoNacimiento;
    }

    public void setAnnoNacimiento(Integer annoNacimiento) {
        this.annoNacimiento = annoNacimiento;
    }

    public Integer getAnnoMuerte() {
        return annoMuerte;
    }

    public void setAnnoMuerte(Integer annoMuerte) {
        this.annoMuerte = annoMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l ->l.setAutor(this));
        this.libros = libros;
    }
}

// import java.util.ArrayList;
// import java.util.List;


// import com.rias.literalura.modelos.dto.DatosAutor;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "autores")
// public class Autor {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long Id;
//     @Column(unique =  true)
//     private String nombreAutor;
//     private Integer annoNacimiento;
//     private Integer annoMuerte;
//     @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//     private List<Libro> libros;

//     public Autor() {
//     }

//     public Autor(DatosAutor datosAutor) {
//         this.nombreAutor = datosAutor.nombre();
//         this.annoNacimiento = datosAutor.annoNacimiento();
//         this.annoMuerte = datosAutor.annoMuerte();
//     }

//     
//             // autor = autor.replace("%libros", libros);   
            
            
//     return autor+libros+footer;
//     }

//     public Long getId() {
//         return Id;
//     }

//     public void setId(Long id) {
//         Id = id;
//     }

//     public String getNombreAutor() {
//         return nombreAutor;
//     }

//     public void setNombreAutor(String nombreAutor) {
//         this.nombreAutor = nombreAutor;
//     }

//     public Integer getAnnoNacimiento() {
//         return annoNacimiento;
//     }

//     public void setAnnoNacimiento(Integer annoNacimiento) {
//         this.annoNacimiento = annoNacimiento;
//     }

//     public Integer getAnnoMuerte() {
//         return annoMuerte;
//     }

//     public void setAnnoMuerte(Integer annoMuerte) {
//         this.annoMuerte = annoMuerte;
//     }

//     public List<Libro> getLibros() {
//         return libros;
//     }

//     public void setLibros(List<Libro> libros) {
//         this.libros = libros;
//     }

    
// }
