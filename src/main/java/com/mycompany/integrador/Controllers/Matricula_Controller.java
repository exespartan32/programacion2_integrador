/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Service.MatriculaServiceImplement;
import com.mycompany.integrador.Service.AlumnoServiceImplement;
import com.mycompany.integrador.Service.CursoServiceImplement;
import java.util.Scanner;
import com.mycompany.integrador.Models.Alumno;
import com.mycompany.integrador.Models.Curso;
import com.mycompany.integrador.Models.Matricula;
import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public class Matricula_Controller {

    MatriculaServiceImplement matriculaServiceImplement = new MatriculaServiceImplement();
    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();
    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    Scanner sc = new Scanner(System.in);

    public void matriculaPrueba() {
        matriculaServiceImplement.matricularAlumno("43270183", "maquillaje");
    }

    public void matricularAlumnoEnCurso() {
        if (alumnoServiceImplement.buscarAlumno().size() <= 0) {
            System.out.println("no hay alumnos para asignar");
        } else {
            try {
                System.out.println("seleccine el alumno a matricular");
                System.out.println("///////////////////////////////////////////////////////////////////");
                ArrayList<Alumno> listaAlumno = alumnoServiceImplement.buscarAlumno();
                for (int i = 0; i < listaAlumno.size(); i++) {
                    int elemento = i + 1;
                    System.out.println("Elemento " + elemento + ": " + listaAlumno.get(i).toString());
                }
                int i_alumno = sc.nextInt();
                String dniAlumno = listaAlumno.get(i_alumno - 1).getDNI();

                ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
                System.out.println("\n seleccine el curso al que desea matriclar el alumno con DNI: " + dniAlumno);
                System.out.println("///////////////////////////////////////////////////////////////////");
                if (listaCursos.size() <= 0) {
                    System.out.println("no hay cursos donde matricular el alumno");
                } else {
                    for (int j = 0; j < listaCursos.size(); j++) {
                        int elemento = j + 1;
                        System.out.println("Elemento " + elemento + ": " + listaCursos.get(j).toString());
                    }

                    Scanner sc = new Scanner(System.in);
                    int i_curso = sc.nextInt();
                    String nombreCurso = listaCursos.get(i_curso - 1).getNombreCurso();

                    // si el alumno ya esta matriculado en este curso no se puede volver a matricluar
                    if (matriculaServiceImplement.buscarMatriculaCurso(alumnoServiceImplement.buscarAlumno(dniAlumno), listaCursos.get(i_curso - 1)) == null) {
                        System.out.println("el alumno con DNI " + dniAlumno + " ya esta matriculado en el curso de " + listaCursos.get(i_curso - 1).getNombreCurso());
                    } else {
                        System.out.println("se matricula el alumno con dni " + dniAlumno + " en el curso de " + nombreCurso);
                        matriculaServiceImplement.matricularAlumno(dniAlumno, nombreCurso);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }

        }
    }

    public void buscarMatriculaDeAlumno() {
        if (alumnoServiceImplement.buscarAlumno().size() <= 0) {
            System.out.println("no hay alumnos para mostrar");
        } else {
            try {
                System.out.println("seleccione el alumno del que quiete buscar la matricula");
                System.out.println("///////////////////////////////////////////////////////////////////");
                for (int i = 0; i < alumnoServiceImplement.buscarAlumno().size(); i++) {
                    int elemento = i + 1;
                    System.out.println("Elemento " + elemento + ": " + alumnoServiceImplement.buscarAlumno().get(i).toString());
                }
                int i_alumno = sc.nextInt();
                Alumno alumno = alumnoServiceImplement.buscarAlumno().get(i_alumno - 1);
                matriculaServiceImplement.buscarMatriculaCurso(alumno);
                ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);
                System.out.println("datos encontrados:");
                for (int j = 0; j < listaMatriculas.size(); j++) {
                    System.out.println("=> " + matriculaServiceImplement.buscarMatriculaCurso(alumno).get(j).toString());
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }
        }
    }

    public void buscarMatriculaDeCurso() {
        if (cursoServiceImplement.buscarCurso().size() <= 0) {
            System.out.println("no hay cursos para mostrar");
        } else {
            System.out.println("seleccione el curso del que quiete buscar la matricula");
            System.out.println("///////////////////////////////////////////////////////////////////");
            for (int i = 0; i < cursoServiceImplement.buscarCurso().size(); i++) {
                int elemento = i + 1;
                System.out.println("Elemento " + elemento + ": " + cursoServiceImplement.buscarCurso().get(i).toString());
            }
            int i_alumno = sc.nextInt();
            Curso curso = cursoServiceImplement.buscarCurso().get(i_alumno - 1);
            ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(curso.getNombreCurso());
            System.out.println("datos encontrados:");
            for (int j = 0; j < listaMatriculas.size(); j++) {
                System.out.println("=> " + matriculaServiceImplement.buscarMatriculaCurso(curso.getNombreCurso()).get(j).toString());
            }
        }
    }

    public void buscarTodasLasMatriculas() {
        if (matriculaServiceImplement.buscarMatriculaCurso().size() > 0) {
            ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso();
            for (int j = 0; j < listaMatriculas.size(); j++) {
                System.out.println("=> " + matriculaServiceImplement.buscarMatriculaCurso().get(j).toString());
            }
        } else {
            System.out.println("no hay registros para mostrar");
        }
    }

    public void darDeBajaAlumno() {
        if (alumnoServiceImplement.buscarAlumno().size() <= 0) {
            System.out.println("no hay alumnos para mostrar");
        } else {
            try {
                System.out.println("seleccione el alumno del que quiete buscar la matricula");
                System.out.println("///////////////////////////////////////////////////////////////////");
                for (int i = 0; i < alumnoServiceImplement.buscarAlumno().size(); i++) {
                    int elemento = i + 1;
                    System.out.println("Elemento " + elemento + ": " + alumnoServiceImplement.buscarAlumno().get(i).toString());
                }
                int i_alumno = sc.nextInt();
                Alumno alumno = alumnoServiceImplement.buscarAlumno().get(i_alumno - 1);
                ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);

                System.out.println("seleccione el curso del que quiere der de baja el alumno:");
                System.out.println("///////////////////////////////////////////////////////////////////");
                for (int j = 0; j < listaMatriculas.size(); j++) {
                    int elemento = j + 1;
                    System.out.println("Elemento " + elemento + ": " + matriculaServiceImplement.buscarMatriculaCurso(alumno).get(j).getNombreCurso());
                }
                int i_curso = sc.nextInt();
                Curso curso = cursoServiceImplement.buscarCurso().get(i_curso - 1);

                matriculaServiceImplement.desvincularAlumno(alumno.getDNI(), curso.getNombreCurso());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }
        }
    }
}
