package com.rias.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rias.literalura.modelos.Autor;
import com.rias.literalura.modelos.Libro;

import java.util.List;
import java.util.Optional;





public interface LibroRepository extends JpaRepository<Libro,Long>{
    Optional<Libro> findByTitulo(String nombre);
    Optional<Libro> findByAutor(Autor autor);
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> encontrarPorIdioma(String idioma);
    
    // @Query
    // List<Autor> encontrarEntreAños();
    // @Query("SELECT a FROM Libro l WHERE a.id = :id")
    // List<Libro> obtenerLibroDeAutor(int id);
    
    
    // @Query(value="SELECT * FROM Libros WHERE totaltemporadas <= 2 AND evaluacion >= 7",nativeQuery = true)
    // @Query("SELECT s FROM Libro s WHERE s.totaltemporadas <= :temporadas AND s.evaluacion >= :evaluacion")
    // List<Libro> LibrosPorTemporadasYEvaluacion();

}
