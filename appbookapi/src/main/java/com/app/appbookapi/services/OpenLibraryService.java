package com.app.appbookapi.Services;

import com.app.appbookapi.Models.Libro;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenLibraryService {

    private final String BASE_URL = "https://openlibrary.org/search.json?q=";

    public List<Libro> buscarLibros(String query) {
        String url = BASE_URL + query.replace(" ", "+");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(url, ApiResponse.class);

        List<Libro> libros = new ArrayList<>();
        if (response.getBody() != null && response.getBody().getDocs() != null) {
            for (ApiResponse.Doc doc : response.getBody().getDocs()) {
                Libro libro = new Libro();
                libro.setTitulo(doc.getTitle());
                libro.setAnioPublicacion(doc.getFirstPublishYear());
                libro.setDisponibilidad(doc.getAvailability());
                libro.setDescripcion("Descripción del libro"); // Puedes ajustarla según tu modelo
                libros.add(libro);
            }
        }
        return libros;
    }
}
