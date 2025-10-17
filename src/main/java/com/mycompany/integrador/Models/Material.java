/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Models;

import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class Material {

    private Long idMaterial;
    private String nombre;
    private String color;
    private String marca;
    private int precio;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliinacion;

    public Material(String nombre, String color, String marca, int precio, String descripcion, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliinacion) {
        this.nombre = nombre;
        this.color = color;
        this.marca = marca;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliinacion = fechaEliinacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public LocalDate getFechaEliinacion() {
        return fechaEliinacion;
    }

    public void setFechaEliinacion(LocalDate fechaEliinacion) {
        this.fechaEliinacion = fechaEliinacion;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

}
