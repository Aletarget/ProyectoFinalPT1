package com.app.appbookapi.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Libros")
public class Libro{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short idLibro;
    private String titulo;
    @Column(name = "anioPublicacion")
    @Temporal(TemporalType.DATE)
    private Date anioPublicacion;
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
    public Date getAnioPublicacion() {
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

    public void setAnioPublicacion(Date anioPublicacion) {
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
}
