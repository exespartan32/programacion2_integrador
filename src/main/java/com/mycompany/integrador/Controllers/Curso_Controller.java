/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Service.CursoServiceImplement;
import com.mycompany.integrador.Service.ProfesorServiceImplement;
import com.mycompany.integrador.Models.Curso;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class Curso_Controller {

    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();
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
            //System.out.println("¡Curso " + nombreCurso + " guardado con éxito!");
        } catch (NumberFormatException e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                ERROR!!: La duración debe ser un número.          ");
            System.out.println("------------------------------------------------------------------");
        } catch (InputMismatchException e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                ERROR!!: La duracion debe ser un numero.           ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarTodosLosCursos() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println("///////////////////////////////////////////////////////////////////");
                System.out.println("|                           Elemento " + i + ":                           |");
                System.out.println("------------------------------------------------------------------");
                System.out.println("| curso: " + listaCursos.get(i).getNombreCurso());
                System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                System.out.println("| DNI del profesor a cargo: " + listaCursos.get(i).getDniProfesor());
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay cursos que mostrar                    ");
            System.out.println("                 primero agregue cursos al sistema                ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarCursoPorNombreCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            System.out.print("ingrese el nombre del curso que desa buscar: ");
            String nombreCurso = sc.nextLine();
            Curso curso = cursoServiceImplement.buscarCurso(nombreCurso);
            if (curso.getNombreCurso() != null) {
                System.out.println("///////////////////////////////////////////////////////////////////");
                System.out.println("|                         Datos Encontrados                       |");
                System.out.println("------------------------------------------------------------------");
                System.out.println("| nombre: " + curso.getNombreCurso());
                System.out.println("| duracion : " + curso.getMesesDuracion() + " meses");
                System.out.println("| DNI del profesor a cargo: " + curso.getDniProfesor());
                System.out.println("------------------------------------------------------------------");
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("     no se encontro el curso de " + nombreCurso + " en base de datos   ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay cursos que mostrar                    ");
            System.out.println("                 primero agregue cursos al sistema                ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void modificarCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("                       seleccione el curso que desea modificar            ");
                System.out.println("_________________________________________________________________________");
                for (int i = 0; i < listaCursos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| curso: " + listaCursos.get(i).getNombreCurso());
                    System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                    System.out.println("| DNI del profesor a cargo: " + listaCursos.get(i).getDniProfesor());
                    System.out.println("------------------------------------------------------------------");
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
                //System.out.println("¡Curso modificado con éxito!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("             ERROR!!: no existe el registro seleccionado          ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                   ERROR!!: debe ser un numero.                   ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay cursos que mostrar                    ");
            System.out.println("                 primero agregue cursos al sistema                ");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void eliminarCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            try {
                System.out.println("_________________________________________________________________________");
                System.out.println("                  seleccione el curso que desea eliminar                 ");
                System.out.println("_________________________________________________________________________");
                for (int i = 0; i < listaCursos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| curso: " + listaCursos.get(i).getNombreCurso());
                    System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                    System.out.println("| DNI del profesor a cargo: " + listaCursos.get(i).getDniProfesor());
                    System.out.println("------------------------------------------------------------------");
                }
                System.out.print("elemento Nº ");
                try {
                    int i_curso = sc.nextInt();
                    String nombreCurso = listaCursos.get(i_curso).getNombreCurso();
                    sc.nextLine();
                    System.out.print("¿Está seguro que desea eliminar el curso " + nombreCurso + "? (si/no): ");
                    String confirmacion = sc.nextLine();
                    if (confirmacion.equalsIgnoreCase("si") || confirmacion.equalsIgnoreCase("no")) {
                        if (confirmacion.equalsIgnoreCase("si")) {
                            cursoServiceImplement.eliminarCurso(nombreCurso);
                            //System.out.println("Curso eliminado.");
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                    } else {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("           ERROR!!: opcion incorrecta. debe colocar si o no       ");
                        System.out.println("------------------------------------------------------------------");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("                    ERROR!!: debe ser un numero.                  ");
                    System.out.println("------------------------------------------------------------------");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("               ERROR!!: no existe el registro seleccionado         ");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("                   ERROR!!: debe ser un numero.                   ");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                     no hay cursos que mostrar                    ");
            System.out.println("                 primero agregue cursos al sistema                ");
            System.out.println("------------------------------------------------------------------");
        }
    }

}
