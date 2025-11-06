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
            System.out.print("Ingrese los Nombres: ");
            String nombres = sc.nextLine();
            System.out.print("Ingrese el Apellido Paterno: ");
            String apellidoPaterno = sc.nextLine();
            System.out.print("Ingrese el Apellido Materno: ");
            String apellidoMaterno = sc.nextLine();
            System.out.print("Ingrese la Edad: ");
            int edad = Integer.parseInt(sc.nextLine());
            System.out.print("Ingrese el Sueldo: ");
            int sueldo = Integer.parseInt(sc.nextLine());
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
            System.out.println("///////////////////////////////////////////////////////////////////");
            System.out.println("|                    Elemento " + i + ":                            |");
            System.out.println("------------------------------ ------------------------------------");
            System.out.println("| nombre: " + listaProfesores.get(i).getNombres());
            System.out.println("| apellidos: " + listaProfesores.get(i).getApellidoPaterno() + " " + listaProfesores.get(i).getApellidoMaterno());
            System.out.println("| DNI: " + listaProfesores.get(i).getDNI());
            System.out.println("-------------------------------------------------------------------");
        }
    }

    public void buscarProfesor() {
        System.out.print("Ingrese el DNI del profesor a buscar: ");
        String dni = sc.nextLine();
        Profesor profesor = profesorServiceImplement.buscarProfesor(dni);
        if (profesor != null && profesor.getDNI() != null) {
            System.out.println("| Datos: ");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("| nombre: " + profesor.getNombres());
            System.out.println("| apellidos: " + profesor.getApellidoPaterno() + " " + profesor.getApellidoMaterno());
            System.out.println("| DNI: " + profesor.getDNI());
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("No se encontró ningún profesor con el DNI: " + dni);
        }
    }

    public void modificarDatosProfesor() {
        try {
            ArrayList<Profesor> listaProfesores = profesorServiceImplement.buscarProfesor();
            if (listaProfesores.size() > 0) {
                System.out.println("seleccione el profesor que desea modificar");
                for (int j = 0; j < listaProfesores.size(); j++) {
                    System.out.println("////////////////////////////////////////////////////////////////////");
                    System.out.println("|                      Elemento " + j + ":                               |");
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("| nombre: " + listaProfesores.get(j).getNombres());
                    System.out.println("| apellidos: " + listaProfesores.get(j).getApellidoPaterno() + " " + listaProfesores.get(j).getApellidoMaterno());
                    System.out.println("| DNI: " + listaProfesores.get(j).getDNI());
                    System.out.println("--------------------------------------------------------------------");
                }
                System.out.println("elemento Nº ");
                int i_profesor = sc.nextInt();
                String dniProfesor = listaProfesores.get(i_profesor).getDNI();

                sc.nextLine();
                System.out.println("--- Ingrese los nuevos datos para " + listaProfesores.get(i_profesor).getNombres() + " ---");
                System.out.print("Nuevo Nombre: ");
                String nombresModificado = sc.nextLine();
                System.out.print("Nuevo Apellido Paterno: ");
                String apellidoPaternoModificado = sc.nextLine();
                System.out.print("Nuevo Apellido Materno: ");
                String apellidoMaternoModificado = sc.nextLine();
                System.out.print("Nueva Edad: ");
                int edadModificado = Integer.parseInt(sc.nextLine());
                System.out.print("Nuevo Sueldo: ");
                int sueldoModificado = Integer.parseInt(sc.nextLine());
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
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                    Elemento " + j + ":                            |");
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("| nombre: " + listaProfesores.get(j).getNombres());
                    System.out.println("| apellidos: " + listaProfesores.get(j).getApellidoPaterno() + " " + listaProfesores.get(j).getApellidoMaterno());
                    System.out.println("| DNI: " + listaProfesores.get(j).getDNI());
                    System.out.println("---------------------------------------------------------- ---------");
                }
                System.out.print("elemento Nº ");
                int i_profesor = sc.nextInt();
                String dniProfesor = listaProfesores.get(i_profesor - 1).getDNI();
                System.out.print("¿Está seguro que desea eliminar al prefesor con DNI " + dniProfesor + "? (si/no): ");
                String confirmacion = sc.nextLine().toLowerCase();
                if (confirmacion == "si" || confirmacion == "no") {
                    profesorServiceImplement.eliminarProfesor(dniProfesor);
                } else {
                    System.out.println("opcion incorrecta. debe colocar si o no");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }
        } else {
            System.out.println("no hay registros que mostrar");
        }
    }
}
