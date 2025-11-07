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
            //System.out.println("¡Alumno " + nombres + " guardado con éxito!");
        } catch (NumberFormatException e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                Error: La edad debe ser un número.                ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarTodosAlumnos() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
        if (listaAlumnos.size() > 0) {
            for (int i = 0; i < listaAlumnos.size(); i++) {
                System.out.println("///////////////////////////////////////////////////////////////////");
                System.out.println("|                           Elemento " + i + ":                           |");
                System.out.println("------------------------------------------------------------------");
                System.out.println("| nombre: " + listaAlumnos.get(i).getNombres());
                System.out.println("| apellidos: " + listaAlumnos.get(i).getApellidoPaterno() + " " + listaAlumnos.get(i).getApellidoMaterno());
                System.out.println("| DNI: " + listaAlumnos.get(i).getDNI());
                System.out.println("| año de ingreso: " + listaAlumnos.get(i).getAnioIngreso());
                System.out.println("| mes de ingreso " + listaAlumnos.get(i).getMesIngreso());
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }

    }

    public void buscarAlumno() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
        if (listaAlumnos.size() > 0) {
            System.out.print("Ingrese el DNI del alumno a buscar: ");
            String dni = sc.nextLine();
            Alumno alumno = alumnoServiceImplement.buscarAlumno(dni);
            if (alumno != null && alumno.getDNI() != null) {
                System.out.println("///////////////////////////////////////////////////////////////////");
                System.out.println("|                         Datos Encontrados                       |");
                System.out.println("------------------------------------------------------------------");
                System.out.println("| nombre: " + alumno.getNombres());
                System.out.println("| apellidos: " + alumno.getApellidoPaterno() + " " + alumno.getApellidoMaterno());
                System.out.println("| DNI: " + alumno.getDNI());
                System.out.println("| año de ingreso: " + alumno.getAnioIngreso());
                System.out.println("| mes de ingreso " + alumno.getMesIngreso());
                System.out.println("------------------------------------------------------------------");
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                No se encontro ningún alumno con el DNI: " + dni + "       ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }

    }

    public void modificarAlumno() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
        if (listaAlumnos.size() > 0) {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("                  seleccione el alumno que desea modificar               ");
                System.out.println("_________________________________________________________________________");
                for (int j = 0; j < listaAlumnos.size(); j++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + j + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| nombre: " + listaAlumnos.get(j).getNombres());
                    System.out.println("| apellidos: " + listaAlumnos.get(j).getApellidoPaterno() + " " + listaAlumnos.get(j).getApellidoMaterno());
                    System.out.println("| DNI: " + listaAlumnos.get(j).getDNI());
                    System.out.println("| año de ingreso: " + listaAlumnos.get(j).getAnioIngreso());
                    System.out.println("| mes de ingreso " + listaAlumnos.get(j).getMesIngreso());
                    System.out.println("------------------------------------------------------------------");
                }
                System.out.print("elemento Nº ");
                int i_alumno = sc.nextInt();
                String dniAlumno = listaAlumnos.get(i_alumno).getDNI();
                System.out.println("--- Ingrese los nuevos datos para " + listaAlumnos.get(i_alumno).getNombres() + " ---");
                sc.nextLine();
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

                Alumno alumnoModificado = new Alumno(
                        anioIngresoModificado,
                        mesIngresoModificado,
                        nombresModificado,
                        apellidoPaternoModificado,
                        apellidoMaternoModificado,
                        edadModificado,
                        fechaCreacionModificado);
                alumnoServiceImplement.modificarALumno(dniAlumno, alumnoModificado);
                //System.out.println("¡Alumno modificado con éxito!");
            } catch (NumberFormatException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                  ERROR!!: La edad debe ser números.              ");
                System.out.println("------------------------------------------------------------------");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("              ERROR!!: no existe el registro seleccionado         ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void eliminarAlumno() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
        if (listaAlumnos.size() > 0) {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("                seleccione el alumno que desea modificar                 ");
                System.out.println("_________________________________________________________________________");
                for (int j = 0; j < listaAlumnos.size(); j++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + j + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| nombre: " + listaAlumnos.get(j).getNombres());
                    System.out.println("| apellidos: " + listaAlumnos.get(j).getApellidoPaterno() + " " + listaAlumnos.get(j).getApellidoMaterno());
                    System.out.println("| DNI: " + listaAlumnos.get(j).getDNI());
                    System.out.println("| año de ingreso: " + listaAlumnos.get(j).getAnioIngreso());
                    System.out.println("| mes de ingreso " + listaAlumnos.get(j).getMesIngreso());
                    System.out.println("------------------------------------------------------------------");
                }
                try {
                    System.out.print("Seleccionar el Elemento Nº ");
                    int i_alumno = sc.nextInt();
                    String dniAlumno = listaAlumnos.get(i_alumno).getDNI();
                    sc.nextLine();
                    System.out.print("¿Está seguro que desea eliminar al alumno con DNI " + dniAlumno + "? (si/no): ");
                    String confirmacion = sc.nextLine();
                    if (confirmacion.equalsIgnoreCase("si") || confirmacion.equalsIgnoreCase("no")) {
                        if (confirmacion.equalsIgnoreCase("si")) {
                            alumnoServiceImplement.borrarAlumno(dniAlumno);
                            //System.out.println("Alumno eliminado.");
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                    } else {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("         ERROR!!: opcion incorrecta. debe colocar si o no         ");
                        System.out.println("------------------------------------------------------------------");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("                  ERROR!!: debe ser un numero.                     ");
                    System.out.println("------------------------------------------------------------------");
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("            ERROR!!: no existe el registro seleccionado           ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay alumnos que mostrar                   ");
            System.out.println("               primero agregue alumnos en el sistema              ");
            System.out.println("------------------------------------------------------------------");
        }
    }

}
