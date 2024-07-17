package com.rias.literalura.modelos.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Double numeroDeDescargas,
        @JsonAlias("authors")List<DatosAutor> autores
) {
}

// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonAlias;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// @JsonIgnoreProperties(ignoreUnknown = true)
// public record DatosLibro(
// @JsonAlias("title") String nombre,
// @JsonAlias("authors") List<DatosAutor> autores,
// @JsonAlias("languages") List<String> idiomas,
// @JsonAlias("download_count") Integer descargas
// ) {
   
// }
