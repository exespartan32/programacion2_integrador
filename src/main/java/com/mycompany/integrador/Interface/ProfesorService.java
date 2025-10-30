/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import java.util.Optional;
import com.mycompany.integrador.Models.Profesor;

/**
 *
 * @author exequiel
 */
public interface ProfesorService {

    public void guardarProfesor(Profesor profesor);

    public void modificarProfesor(String dni, Profesor profesor);

    public void eliminarProfesor(String dni);

    public ArrayList<Profesor> buscarProfesor();

    public Profesor buscarProfesor(String dni);
}
