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
public class Profesor extends Persona {

    private int sueldo;
    private boolean presentismo;

    public Profesor(int sueldo, boolean presentismo, String DNI, String nombres, String apellidoMaterno, String apellidoPaterno, int edad, LocalDate fechaCreacion, TipoPersona tipoPersona) {
        super(DNI, nombres, apellidoMaterno, apellidoPaterno, edad, fechaCreacion, tipoPersona);
        this.sueldo = sueldo;
        this.presentismo = presentismo;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public boolean isPresentismo() {
        return presentismo;
    }

    public void setPresentismo(boolean presentismo) {
        this.presentismo = presentismo;
    }

}
