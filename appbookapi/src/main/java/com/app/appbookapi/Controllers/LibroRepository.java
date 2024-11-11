package com.app.appbookapi.Controllers;

import com.app.appbookapi.Models.Libro;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Short>{
    public List<Libro> busquedaPorAutor(String nombre);
    public List<Libro> busquedaPorCategoria(String categoria);
    public List<Libro> busquedaSegunPrestamo(Short prestamo);

    public Libro buscarPorNombre(String nombre);
    public void eliminarLibro(String nombre);
    public void guardarLibro(Libro libro);
    public void actualizarLibro(String nombre);
}
