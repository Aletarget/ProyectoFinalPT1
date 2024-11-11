package com.app.appbookapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.app.appbookapi.Models.Autor;
import com.app.appbookapi.Models.Categoria;
import com.app.appbookapi.Models.Libro;
import com.app.appbookapi.services.ApiResponse;
import com.app.appbookapi.services.OpenLibraryService;
import com.app.appbookapi.Controllers.LibroRepository;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository librorepository;

    @Autowired
    private OpenLibraryService openLibraryService;

    // CRUD PARA LISTAR LIBROS SEGÚN AUTOR, CATEGORÍA Y PRÉSTAMOS
    @GetMapping("/buscarAutor")
    public List<Autor> buscarPorAutor(@RequestParam String autor) {
        return librorepository.busquedaPorAutor(autor);
    }

    @GetMapping("/buscarCate")
    public List<Categoria> buscarPorCategoria(@RequestParam String categoria) {
        return librorepository.busquedaPorCategoria(categoria);
    }

    @GetMapping("/buscarPrest")
    public List<Libro> buscarPorPrestamo(@RequestParam short prestamo) {
        return librorepository.busquedaSegunPrestamo(prestamo);
    }

    // Búsqueda de libro por nombre
    @GetMapping("/buscar")
    public Libro buscar(@RequestParam String nombre) {
        return librorepository.buscarPorNombre(nombre);
    }

    private Libro buscarPorId(short id) {
        return librorepository.buscarPorId(id);
    }

    // Guardar un libro. Si no se encuentra en la base de datos, intenta obtenerlo de OpenLibrary.
    @PostMapping("/guardar")
    public Libro agregar(@RequestBody Libro libro) {
        Libro libroExistente = librorepository.buscarPorNombre(libro.getTitulo());
        if (libroExistente != null) {
            return libroExistente;
        } else {
            // Intentar obtener el libro desde OpenLibrary
            ApiResponse apiResponse = openLibraryService.buscarLibroPorTitulo(libro.getTitulo());
            if (apiResponse != null) {
                libro.setDescripcion(apiResponse.getDescripcion());
                libro.setAnioPublicacion(apiResponse.getAnioPublicacion());
                libro.setDisponibilidad("Disponible"); // Ejemplo de cómo podrías configurar este valor
                librorepository.save(libro);
                return libro;
            } else {
                // Si el libro tampoco está en OpenLibrary, se puede devolver un mensaje de error
                return null;
            }
        }
    }

    // Actualizar un libro existente
    @PutMapping("/actualizar/{id}")
    public void actualizar(@PathVariable short id, @RequestBody Libro libroCurrent) {
        Libro viejo = validarBusqueda(id);
        if (viejo != null) {
            viejo.setAnioPublicacion(libroCurrent.getAnioPublicacion());
            viejo.setAutor(libroCurrent.getAutor());
            viejo.setCategoria(libroCurrent.getCategoria());
            viejo.setDescripcion(libroCurrent.getDescripcion());
            viejo.setTitulo(libroCurrent.getTitulo());
            librorepository.save(viejo);
        } else {
            // Redirigir o manejar el caso donde no se encuentra el libro
        }
    }

    // Eliminar un libro por ID
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable short id) {
        Libro libro = validarBusqueda(id);
        if (libro != null) {
            librorepository.delete(libro);
        } else {
            // Redirigir o manejar el caso donde no se encuentra el libro
        }
    }

    // Validación de búsqueda por nombre
    public Libro validarBusqueda(String nombre) {
        Libro libro = buscar(nombre);
        return libro != null ? libro : null;
    }

    // Validación de búsqueda por ID
    public Libro validarBusqueda(short id) {
        Libro libro = buscarPorId(id);
        return libro != null ? libro : null;
    }
}
