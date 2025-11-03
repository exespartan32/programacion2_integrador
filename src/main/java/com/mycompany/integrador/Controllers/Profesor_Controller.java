/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Enums.TipoPersona;
import com.mycompany.integrador.Service.ProfesorServiceImplement;
import java.time.LocalDate;
import com.mycompany.integrador.Models.Profesor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Profesor_Controller {

    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();
    private final Scanner sc = new Scanner(System.in);

    public void nuevoProfesor() {
        LocalDate fechaActual = LocalDate.now();
        try {
            System.out.println("--- Creando Nuevo Profesor ---");
            System.out.print("Ingrese el DNI: ");
            String dni = sc.nextLine();
            sc.nextLine();
            System.out.print("Ingrese los Nombres: ");
            String nombres = sc.nextLine();
            sc.nextLine();
            System.out.print("Ingrese el Apellido Paterno: ");
            String apellidoPaterno = sc.nextLine();
            sc.nextLine();
            System.out.print("Ingrese el Apellido Materno: ");
            String apellidoMaterno = sc.nextLine();
            sc.nextLine();
            System.out.print("Ingrese la Edad: ");
            int edad = Integer.parseInt(sc.nextLine());
            sc.nextLine();
            System.out.print("Ingrese el Sueldo: ");
            int sueldo = Integer.parseInt(sc.nextLine());
            sc.nextLine();
            System.out.print("¿Tiene presentismo? (si/no): ");
            boolean presentismo = sc.nextLine().equalsIgnoreCase("si");

            Profesor profesor = new Profesor(sueldo, presentismo, dni, nombres, apellidoPaterno, apellidoMaterno, edad, fechaActual, TipoPersona.PROFESOR);
            profesorServiceImplement.guardarProfesor(profesor);
            System.out.println("¡Profesor " + nombres + " guardado con éxito!");
        } catch (NumberFormatException e) {
            System.out.println("Error: La edad y el sueldo deben ser números.");
        }
    }

    public void buscarTodosProfesores() {
        ArrayList<Profesor> listaProfesores = profesorServiceImplement.buscarProfesor();

        for (int i = 0; i < listaProfesores.size(); i++) {
            System.out.println("Elemento " + i + ": " + listaProfesores.get(i));
        }
    }

    public void buscarProfesor() {
        Profesor profesor = profesorServiceImplement.buscarProfesor("132123132");
        System.out.println(profesor.toString());
    }

    public void modificarDatosProfesor() {
        try {
            ArrayList<Profesor> listaProfesores = profesorServiceImplement.buscarProfesor();
            if (listaProfesores.size() > 0) {
                System.out.println("seleccione el profesor que desea modificar");
                for (int j = 0; j < listaProfesores.size(); j++) {
                    int elemento = j + 1;
                    System.out.println("elemento " + elemento + " => " + listaProfesores.get(j).toString());
                }
                int i_profesor = sc.nextInt();
                String dniProfesor = listaProfesores.get(i_profesor - 1).getDNI();

                System.out.println("--- Ingrese los nuevos datos para " + listaProfesores.get(i_profesor - 1).getNombres() + " ---");
                System.out.print("Nuevo Nombre: ");
                String nombresModificado = sc.nextLine();
                sc.nextLine();
                System.out.print("Nuevo Apellido Paterno: ");
                String apellidoPaternoModificado = sc.nextLine();
                sc.nextLine();
                System.out.print("Nuevo Apellido Materno: ");
                String apellidoMaternoModificado = sc.nextLine();
                sc.nextLine();
                System.out.print("Nueva Edad: ");
                int edadModificado = Integer.parseInt(sc.nextLine());
                sc.nextLine();
                System.out.print("Nuevo Sueldo: ");
                int sueldoModificado = Integer.parseInt(sc.nextLine());
                sc.nextLine();
                System.out.print("¿Tiene presentismo? (si/no): ");
                boolean presentismoModificado = sc.nextLine().equalsIgnoreCase("si");
                LocalDate fechaModificacion = LocalDate.now();

                Profesor profesorModificado = new Profesor(sueldoModificado,
                        presentismoModificado,
                        nombresModificado,
                        apellidoPaternoModificado,
                        apellidoMaternoModificado,
                        edadModificado,
                        fechaModificacion
                );
                profesorServiceImplement.modificarProfesor(dniProfesor, profesorModificado);
                System.out.println("¡Profesor modificado con éxito!");
            } else {
                System.out.println("no hay registros que mostrar");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("no existe el registro seleccionado");
        }
    }

    public void eliminarProfesor() {
        ArrayList<Profesor> listaProfesores = profesorServiceImplement.buscarProfesor();
        if (listaProfesores.size() > 0) {
            try {
                System.out.println("seleccione el profesor que desea eliminar");
                System.out.println("///////////////////////////////////////////////////////////////////");
                for (int j = 0; j < listaProfesores.size(); j++) {
                    int elemento = j + 1;
                    System.out.println("elemento " + elemento + " => " + listaProfesores.get(j).toString());
                }
                int i_profesor = sc.nextInt();
                String dniProfesor = listaProfesores.get(i_profesor - 1).getDNI();
                profesorServiceImplement.eliminarProfesor(dniProfesor);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }

        } else {
            System.out.println("no hay registros que mostrar");
        }
    }
}
