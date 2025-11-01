/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Models;

import com.mycompany.integrador.Enums.TipoPersona;
import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class Usuario {

    private int idUsuario;
    private String email;
    private String nombreUsuario;
    private String contrasenia;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;

    public Usuario(String email, String nombreUsuario, String contrasenia, LocalDate fechaCreacion) {
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario(int idUsuario, String email, String nombreUsuario, String contrasenia, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
    }

    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getIdUsuario() {
        return idUsuario;
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

    public LocalDate getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(LocalDate fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", email=" + email + ", nombreUsuario=" + nombreUsuario + ", contrasenia=" + contrasenia + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", fechaEliminacion=" + fechaEliminacion + '}';
    }

}
