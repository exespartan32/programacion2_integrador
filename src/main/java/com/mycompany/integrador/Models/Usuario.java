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

    private Long idUsuario;
    private String email;
    private String nombreUsuario;
    private String contrasenia;

    public Usuario(String email, String nombreUsuario, String contrasenia) {
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
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

    public Long getIdUsuario() {
        return idUsuario;
    }
}
