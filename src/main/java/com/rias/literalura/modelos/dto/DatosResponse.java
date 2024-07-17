package com.rias.literalura.modelos.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResponse(
    @JsonAlias("count") Integer encontrados,
    @JsonAlias("results")List<DatosLibros> datosLibros 

) {

}
