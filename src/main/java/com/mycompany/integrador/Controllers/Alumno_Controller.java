/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Enums.TipoPersona;
import com.mycompany.integrador.Models.Alumno;
import com.mycompany.integrador.Service.AlumnoServiceImplement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Alumno_Controller {

    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();
    Scanner sc = new Scanner(System.in);

    public void nuevoAlumno() {
        LocalDate fechaActual = LocalDate.now();
        System.out.println("--- Creando Nuevo Alumno ---");
        try {
            System.out.print("Ingrese el DNI: ");
            String dni = sc.nextLine();
            System.out.print("Ingrese los Nombres: ");
            String nombres = sc.nextLine();
            System.out.print("Ingrese el Apellido Paterno: ");
            String apellidoPaterno = sc.nextLine();
            System.out.print("Ingrese el Apellido Materno: ");
            String apellidoMaterno = sc.nextLine();
            System.out.print("Ingrese el Año de ingreso (ej: 2025): ");
            String anioIngreso = sc.nextLine();
            System.out.print("Ingrese el Mes de ingreso (ej: octubre): ");
            String mesIngreso = sc.nextLine();
            System.out.print("Ingrese la Edad: ");
            int edad = Integer.parseInt(sc.nextLine());

            Alumno alumno = new Alumno(anioIngreso, mesIngreso, dni, nombres, apellidoPaterno, apellidoMaterno, edad, fechaActual, TipoPersona.ALUMNO);
            alumnoServiceImplement.guardarAlumno(alumno);
            System.out.println("¡Alumno " + nombres + " guardado con éxito!");
        } catch (NumberFormatException e) {
            System.out.println("Error: La edad debe ser un número.");
        }
    }

    public void buscarTodosAlumnos() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
        for (int i = 0; i < listaAlumnos.size(); i++) {
            System.out.println("Elemento " + i + ": " + listaAlumnos.get(i));
        }
    }

    public void buscarAlumno() {
        Alumno alumno = alumnoServiceImplement.buscarAlumno("43270183");
        System.out.println("datos: " + alumno.toString());
    }

    public void modificarAlumno() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
        if (listaAlumnos.size() > 0) {
            try {
                System.out.println("seleccione el alumno que desea modificar");
                for (int j = 0; j < listaAlumnos.size(); j++) {
                    int elemento = j + 1;
                    System.out.println("elemento " + elemento + " => " + listaAlumnos.get(j).toString());
                }
                int i_alumno = sc.nextInt();
                String dniAlumno = listaAlumnos.get(i_alumno - 1).getDNI();

                System.out.println("--- Ingrese los nuevos datos para " + listaAlumnos.get(i_alumno - 1).getNombres() + " ---");
                System.out.print("Nuevo Nombre: ");
                String nombresModificado = sc.nextLine();
                System.out.print("Nuevo Apellido Paterno: ");
                String apellidoPaternoModificado = sc.nextLine();
                System.out.print("Nuevo Apellido Materno: ");
                String apellidoMaternoModificado = sc.nextLine();
                System.out.print("Nuevo Año de Ingreso: ");
                String anioIngresoModificado = sc.nextLine();
                System.out.print("Nuevo Mes de Ingreso: ");
                String mesIngresoModificado = sc.nextLine();
                System.out.print("Nueva Edad: ");
                int edadModificado = Integer.parseInt(sc.nextLine());
                LocalDate fechaCreacionModificado = LocalDate.now();

                Alumno alumnoModificado = new Alumno(anioIngresoModificado, mesIngresoModificado, nombresModificado, apellidoPaternoModificado, apellidoMaternoModificado, edadModificado, fechaCreacionModificado);
                alumnoServiceImplement.modificarALumno(dniAlumno, alumnoModificado);
                System.out.println("¡Alumno modificado con éxito!");
            } catch (NumberFormatException e) {
                System.out.println("Error: La edad y el sueldo deben ser números.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }
        } else {
            System.out.println("no existen registros");
        }
    }

    public void eliminarAlumno() {

        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
        if (listaAlumnos.size() > 0) {
            try {
                System.out.println("seleccione el alumno que desea modificar");
                for (int j = 0; j < listaAlumnos.size(); j++) {
                    int elemento = j + 1;
                    System.out.println("elemento " + elemento + " => " + listaAlumnos.get(j).toString());
                }
                int i_alumno = sc.nextInt();
                String dniAlumno = listaAlumnos.get(i_alumno - 1).getDNI();

                System.out.print("¿Está seguro que desea eliminar al alumno con DNI " + dniAlumno + "? (si/no): ");
                String confirmacion = sc.nextLine();
                if (confirmacion.equalsIgnoreCase("si")) {
                    alumnoServiceImplement.borrarAlumno(dniAlumno);
                    System.out.println("Alumno eliminado.");
                } else {
                    System.out.println("Operación cancelada.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: La edad y el sueldo deben ser números.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }
        } else {
            System.out.println("no existen registros");
        }
    }

}
