package com.app.appbookapi.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Libro{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short idLibro;
    private String titulo;
    private String anioPublicacion;
    private String disponibilidad;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idAutorFK")
    private Autor autor;
    
    @ManyToOne
    @JoinColumn(name = "idCategoriaFK")
    private Categoria categoria;

    /*
    Setters y Getters
    */
    public String getAnioPublicacion() {
        return anioPublicacion;
    }
    public Autor getAutor() {
        return autor;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getDisponibilidad() {
        return disponibilidad;
    }
    public short getIdLibro() {
        return idLibro;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public void setIdLibro(short idLibro) {
        this.idLibro = idLibro;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Libro getLibro(){
        Libro libro = new Libro();
        libro.setAnioPublicacion(getAnioPublicacion());
        libro.setAutor(getAutor());
        libro.setCategoria(getCategoria());
        libro.setDescripcion(getDescripcion());
        libro.setIdLibro(getIdLibro());
        libro.setTitulo(getTitulo());
        libro.setDisponibilidad(getDisponibilidad());
        return libro;
    }
}
