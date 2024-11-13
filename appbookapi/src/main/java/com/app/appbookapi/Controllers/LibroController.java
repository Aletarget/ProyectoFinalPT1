package com.app.appbookapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.appbookapi.Models.Autor;
import com.app.appbookapi.Models.Categoria;
import com.app.appbookapi.Models.Libro;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class LibroController {
    @Autowired
    private LibroRepository librorepository;


    //CRUD PARA LISTAR LIBROS SEGUN AUTOR, CATEGORIA Y PRESTAMOS
    @GetMapping("/buscarAutor")
    public List<Autor> buscarPorAutor(@RequestParam String autor) {
        return librorepository.busquedaPorAutor(autor); //Con el uso de JPA no es necesario implementar una logica interna en el metodo que se implemento en la interfaz
    }
    @GetMapping("/buscarCate")
    public List<Categoria> buscarPorCategoria(@RequestParam String categoria) {
        return librorepository.busquedaPorCategoria(categoria); 
    }    
           
    
    //Busqueda
    @GetMapping("/buscar")
    public Libro buscar(@RequestParam String nombre) {
        return librorepository.buscarPorNombre(nombre);
    }
    private Libro buscarPorId(short id) {
        return librorepository.buscarPorId(id);
    }

    //Guardar
    @PostMapping("/guardar")
    public void agregar(@RequestBody Libro libro) {
        //Codigo para añadir un nuevo libro
    }
    
    // Actualizar
    @PutMapping("/actualizar/{id}")
    public void actualizar(@PathVariable short id, @RequestBody Libro libroCurrent) {
        
        if (validarBusqueda(id) != null) {
            Libro viejo = validarBusqueda(id);
            viejo.setAnioPublicacion(libroCurrent.getAnioPublicacion());
            viejo.setAutor(libroCurrent.getAutor());
            viejo.setCategoria(libroCurrent.getCategoria());
            viejo.setDescripcion(libroCurrent.getDescripcion());
            viejo.setTitulo(libroCurrent.getTitulo());
        }else{
            //Codigo para redirigir a una direccion donde muestre que la busqueda no pudo encontrar nada
        }
    }
    // Eliminar
    @DeleteMapping
    

    // Validacion de busqueda
    public Libro validarBusqueda(String nombre){
        Libro libro = buscar(nombre);
        if (libro != null) {
            return libro;
        }else{
            return null;
        }
    }
    public Libro validarBusqueda(short id){
        Libro libro = buscarPorId(id);
                if (libro != null) {
                    return libro;
                }else{
                    return null;
                }
            }

    
}
