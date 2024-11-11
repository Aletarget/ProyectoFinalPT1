package com.app.appbookapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.appbookapi.Models.Libro;



@Controller
public class LibroController {
    @Autowired
    private LibroRepository librorepository;

    @GetMapping("/buscarAutor")
    public List<Libro> buscarPorAutor(@RequestParam String autor) {
        return librorepository.busquedaPorAutor(autor);
    }
    
}
