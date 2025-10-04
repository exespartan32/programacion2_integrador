/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import java.util.Optional;
import com.mycompany.integrador.Models.Persona;

/**
 *
 * @author exequiel
 */
public interface PersonaService {

    public void guardarPersona(Persona persona);

    public void modificarPersona(Long id, Persona persona);

    public void eliminarPersona(Long id);

    public ArrayList<Persona> buscarTodasLasPersonas();

    public Persona buscarPersona(String dni);
}
