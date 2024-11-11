package com.app.appbookapi.Controllers;

import com.app.appbookapi.Models.Autor;
import com.app.appbookapi.Models.Categoria;
import com.app.appbookapi.Models.Libro;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Short>{
    public List<Autor> busquedaPorAutor(String nombre);
    public List<Categoria> busquedaPorCategoria(String categoria);
    public List<Libro> busquedaSegunPrestamo(Short prestamo);

    public Libro buscarPorNombre(String nombre);
    public Libro buscarPorId(short id);
    
}
