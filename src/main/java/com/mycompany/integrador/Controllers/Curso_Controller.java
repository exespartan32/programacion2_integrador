/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Service.CursoServiceImplement;
import com.mycompany.integrador.Models.Curso;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Curso_Controller {

    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    Scanner sc = new Scanner(System.in);

    public void nuevoCurso() {
        try {
            LocalDate fechaActual = LocalDate.now();
            System.out.println("--- Creando Nuevo Curso ---");
            System.out.print("Ingrese el Nombre del Curso: ");
            String nombreCurso = sc.nextLine();
            System.out.print("Ingrese la Duración en Meses: ");
            int duracionMeses = Integer.parseInt(sc.nextLine());
            Curso curso = new Curso(nombreCurso, duracionMeses, fechaActual, null, null);
            cursoServiceImplement.guardarCurso(curso);
            System.out.println("¡Curso " + nombreCurso + " guardado con éxito!");
        } catch (NumberFormatException e) {
            System.out.println("Error: La duración debe ser un número.");
        }
    }

    public void buscarTodosLosCursos() {
        ArrayList<Curso> litaCursos = cursoServiceImplement.buscarCurso();
        for (int i = 0; i < litaCursos.size(); i++) {
            System.out.println("///////////////////////////////////////////////////////////////////");
            System.out.println("|                    Elemento " + i + ":                            |");
            System.out.println("-------------------------------------------------------------------");
            System.out.println("| curso: " + litaCursos.get(i).getNombreCurso());
            System.out.println("| meses de duracion: " + litaCursos.get(i).getMesesDuracion());
            System.out.println("| DNI del profesor a cargo: " + litaCursos.get(i).getDniProfesor());
            System.out.println("-------------------------------------------------------------------");
        }
    }

    public void buscarCurso() {
        Curso curso = cursoServiceImplement.buscarCurso("peluqueria");
        System.out.println("datos: " + curso.toString());
    }

    public void modificarCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            try {
                System.out.println("seleccione el curso que desea modificar");
                System.out.println("////////////////////////////////////////////");
                for (int i = 0; i < listaCursos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                    Elemento " + i + ":                            |");
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("| curso: " + listaCursos.get(i).getNombreCurso());
                    System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                    System.out.println("| DNI del profesor a cargo: " + listaCursos.get(i).getDniProfesor());
                    System.out.println("-------------------------------------------------------------------");
                }
                System.out.print("elemento Nº ");
                int i_curso = sc.nextInt();
                String nombreCurso = listaCursos.get(i_curso).getNombreCurso();
                System.out.println("--- Ingrese los nuevos datos ---");
                sc.nextLine();
                System.out.print("Nuevo Nombre del Curso: ");
                String nuevoNombre = sc.nextLine();
                System.out.print("Nueva Duración en Meses: ");
                int nuevaDuracion = Integer.parseInt(sc.nextLine());
                Curso cursoModificado = new Curso(nuevoNombre, nuevaDuracion, LocalDate.now());
                cursoServiceImplement.modificarCurso(nombreCurso, cursoModificado);
                System.out.println("¡Curso modificado con éxito!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }
        } else {
            System.out.println("no hay registros que mostrar");
        }
    }

    public void eliminarCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            try {
                System.out.println("seleccione el curso que desea eliminar");
                System.out.println("////////////////////////////////////////////");
                for (int i = 0; i < listaCursos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                    Elemento " + i + ":                            |");
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("| curso: " + listaCursos.get(i).getNombreCurso());
                    System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                    System.out.println("| DNI del profesor a cargo: " + listaCursos.get(i).getDniProfesor());
                    System.out.println("-------------------------------------------------------------------");
                }
                System.out.print("elemento Nº ");
                int i_curso = sc.nextInt();
                String nombreCurso = listaCursos.get(i_curso).getNombreCurso();
                sc.nextLine();
                System.out.print("¿Está seguro que desea eliminar el curso " + nombreCurso + "? (si/no): ");
                String confirmacion = sc.nextLine().toLowerCase();
                if (confirmacion == "si" || confirmacion == "no") {
                    if (confirmacion.equalsIgnoreCase("si")) {
                        cursoServiceImplement.eliminarCurso(nombreCurso);
                        System.out.println("Curso eliminado.");
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                } else {
                    System.out.println("opcion incorrecta debe colocar si o no");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }
        } else {
            System.out.println("no hay registros que mostrar");
        }
    }

}
