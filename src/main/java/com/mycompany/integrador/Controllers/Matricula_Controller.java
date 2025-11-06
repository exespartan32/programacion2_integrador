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
                ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("/////////////////////////////////////////////////////////////////////////");
                    System.out.println("|                               Elemento " + i + ":                       |");
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("| nombre: " + listaAlumnos.get(i).getNombres());
                    System.out.println("| apellidos: " + listaAlumnos.get(i).getApellidoPaterno() + " " + listaAlumnos.get(i).getApellidoMaterno());
                    System.out.println("| DNI: " + listaAlumnos.get(i).getDNI());
                    System.out.println("-------------------------------------------------------------------------");
                }
                System.out.println("elemento Nº ");
                int i_alumno = sc.nextInt();
                String dniAlumno = listaAlumnos.get(i_alumno).getDNI();

                ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
                System.out.println("\n seleccine el curso al que desea matriclar el alumno con DNI: " + dniAlumno);
                System.out.println("///////////////////////////////////////////////////////////////////");
                if (listaCursos.size() <= 0) {
                    System.out.println("no hay cursos donde matricular el alumno");
                } else {
                    for (int j = 0; j < listaCursos.size(); j++) {
                        System.out.println("////////////////////////////////////////////////////////////////////////");
                        System.out.println("|                          Elemento " + j + ":                            |");
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("| nombre del curso: " + listaCursos.get(j).getNombreCurso());
                        System.out.println("| meses de duracion: " + listaCursos.get(j).getMesesDuracion());
                        System.out.println("| DNI del profesor a cargo: " + listaCursos.get(j).getDniProfesor());
                        System.out.println("-------------------------------------------------------------------------");
                    }
                    System.out.println("elemento Nº ");
                    int i_curso = sc.nextInt();
                    String nombreCurso = listaCursos.get(i_curso).getNombreCurso();

                    // si el alumno ya esta matriculado en este curso no se puede volver a matricluar
                    if (matriculaServiceImplement.buscarMatriculaCurso(alumnoServiceImplement.buscarAlumno(dniAlumno), listaCursos.get(i_curso)) == null) {
                        System.out.println("el alumno con DNI " + dniAlumno + " ya esta matriculado en el curso de " + listaCursos.get(i_curso).getNombreCurso());
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
                ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("/////////////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                            |");
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("| nombre: " + listaAlumnos.get(i).getNombres());
                    System.out.println("| apellidos: " + listaAlumnos.get(i).getApellidoPaterno() + " " + listaAlumnos.get(i).getApellidoMaterno());
                    System.out.println("| DNI: " + listaAlumnos.get(i).getDNI());
                    System.out.println("------------------------------------------------------------------------");
                }
                System.out.println("elemento Nº ");
                int i_alumno = sc.nextInt();
                Alumno alumno = alumnoServiceImplement.buscarAlumno().get(i_alumno);
                matriculaServiceImplement.buscarMatriculaCurso(alumno);
                ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);
                System.out.println("datos encontrados:");

                for (int j = 0; j < listaMatriculas.size(); j++) {
                    System.out.println("//////////////////////////////////////////////////////////////////////////");
                    System.out.println("|                         Elemento " + j + ":                              |");
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("| nombre: " + listaAlumnos.get(i_alumno).getNombres());
                    System.out.println("| apellidos: " + listaAlumnos.get(i_alumno).getApellidoPaterno() + " " + listaAlumnos.get(j).getApellidoMaterno());
                    System.out.println("| DNI: " + listaAlumnos.get(i_alumno).getDNI());
                    System.out.println("| esta matriculado en: " + listaMatriculas.get(j).getNombreCurso());
                    System.out.println("------------------------------------------------------------------------");
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
            ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println("//////////////////////////////////////////////////////////////////////////////");
                System.out.println("|                              Elemento " + i + ":                               |");
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("| curso: " + listaCursos.get(i).getNombreCurso());
                System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                System.out.println("| DNI del profesor a cargo: " + listaCursos.get(i).getDniProfesor());
                System.out.println("------------------------------------------------------------------------------");
            }
            System.out.println("elemento Nº ");
            int i_alumno = sc.nextInt();
            Curso curso = cursoServiceImplement.buscarCurso().get(i_alumno);
            ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(curso.getNombreCurso());
            System.out.println("datos encontrados:");
            for (int j = 0; j < listaMatriculas.size(); j++) {
                System.out.println("/////////////////////////////////////////////////////////////////////////");
                System.out.println("|                          Elemento " + j + ":                              |");
                System.out.println("------------------------------------------------------------------------");
                System.out.println("| DNI del alumno: " + listaMatriculas.get(j).getDNIAlumno());
                System.out.println("| esta matriculado en: " + listaMatriculas.get(j).getNombreCurso());
                System.out.println("------------------------------------------------------------------------");
            }
        }
    }

    public void buscarTodasLasMatriculas() {
        if (matriculaServiceImplement.buscarMatriculaCurso().size() > 0) {
            ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso();
            for (int j = 0; j < listaMatriculas.size(); j++) {
                System.out.println("| Elemento " + j + ": ");
                System.out.println("------------------------------------------------------------------------");
                System.out.println("| DNI del alumno: " + listaMatriculas.get(j).getDNIAlumno());
                System.out.println("| esta matriculado en: " + listaMatriculas.get(j).getNombreCurso());
                System.out.println("------------------------------------------------------------------------");
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
                ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();

                for (int i = 0; i < listaAlumnos.size(); i++) {
                    System.out.println("| Elemento " + i + ": ");
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("| nombre: " + listaAlumnos.get(i).getNombres());
                    System.out.println("| apellidos: " + listaAlumnos.get(i).getApellidoPaterno() + " " + listaAlumnos.get(i).getApellidoMaterno());
                    System.out.println("| DNI: " + listaAlumnos.get(i).getDNI());
                    System.out.println("------------------------------------------------------------------------");
                }
                System.out.println("elemento Nº ");
                int i_alumno = sc.nextInt();
                Alumno alumno = alumnoServiceImplement.buscarAlumno().get(i_alumno);
                ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);

                System.out.println("seleccione el curso del que quiere der de baja el alumno:");
                System.out.println("///////////////////////////////////////////////////////////////////");
                for (int j = 0; j < listaMatriculas.size(); j++) {
                    System.out.println("| Elemento " + j + ": ");
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("| DNI del alumno: " + listaMatriculas.get(j).getDNIAlumno());
                    System.out.println("| esta matriculado en: " + listaMatriculas.get(j).getNombreCurso());
                    System.out.println("------------------------------------------------------------------------");
                }
                int i_curso = sc.nextInt();
                Curso curso = cursoServiceImplement.buscarCurso().get(i_curso);

                System.out.print("¿Está seguro que desea dar de baja el alumno "
                        + alumno.getNombres() + " "
                        + alumno.getApellidoPaterno() + " "
                        + alumno.getApellidoMaterno() + " con dni "
                        + alumno.getDNI() + " del curso de "
                        + curso.getNombreCurso() + "? (si/no): ");

                String confirmacion = sc.nextLine().toLowerCase();
                if (confirmacion == "si" || confirmacion == "no") {
                    matriculaServiceImplement.desvincularAlumno(alumno.getDNI(), curso.getNombreCurso());
                } else {
                    System.out.println("opcion incorrecta debe colocar si o no");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }
        }
    }
}
