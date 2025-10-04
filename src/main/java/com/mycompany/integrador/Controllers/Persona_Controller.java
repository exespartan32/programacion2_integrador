/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Enums.TipoPersona;
import com.mycompany.integrador.Service.PersonaServiceImplement;
import com.mycompany.integrador.Models.Persona;
import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class Persona_Controller {

    PersonaServiceImplement personaService = new PersonaServiceImplement();

    public void crearNuevaPersona() {
        Persona persona = new Persona("43270183", "daniel exequiel", "ortiz", "mayorga", 24, LocalDate.now().now(), TipoPersona.ALUMNO);
        System.out.println("data: " + persona.toString());
        personaService.guardarPersona(persona);
    }

    public void buscarPersona() {
        String dni = "43270183";
        Persona p = personaService.buscarPersona(dni);
        System.out.println("datos persona: " + p.toString());
    }

}
