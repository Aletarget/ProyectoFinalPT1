package com.app.appbookapi.services;
import com.app.appbookapi.Models.Libro;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                
                // Convertir el año de publicación (String) a Date
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy"); // "yyyy" porque solo tenemos el año
                    Date anioPublicacion = format.parse(doc.getFirstPublishYear()); // Convertir a Date
                    libro.setAnioPublicacion(anioPublicacion); // Establecer el campo anioPublicacion
                } catch (Exception e) {
                    // Si la conversión falla, puedes establecer un valor nulo o manejar el error
                    libro.setAnioPublicacion(null);
                }

                libro.setDisponibilidad(doc.getAvailability());
                libro.setDescripcion("Descripción del libro"); // Puedes ajustarla según tu modelo
                libros.add(libro);
            }
        }
        return libros;
    }
}
