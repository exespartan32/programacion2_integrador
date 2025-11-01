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

    public void matricularAlumnoEnCurso() {
        if (alumnoServiceImplement.buscarAlumno().size() <= 0) {
            System.out.println("no hay alumnos para asignar");
        } else {
            System.out.println("seleccine el alumno a matricular");
            System.out.println("///////////////////////////////////////////////////////////////////");
            for (int i = 0; i < alumnoServiceImplement.buscarAlumno().size(); i++) {
                System.out.println("Elemento " + i + 1 + ": " + alumnoServiceImplement.buscarAlumno().get(i).toString());
            }
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            matriculaServiceImplement.matricularAlumno(alumnoServiceImplement.buscarAlumno().get(i).getDNI());
        }
    }

    public void buscarMatriculaDeAlumno() {
        System.out.println("seleccione el alumno del que quiete buscar la matricula");
        System.out.println("///////////////////////////////////////////////////////////////////");
        for (int i = 0; i < alumnoServiceImplement.buscarAlumno().size(); i++) {
            int elemento = i + 1;
            System.out.println("Elemento " + elemento + ": " + alumnoServiceImplement.buscarAlumno().get(i).toString());
        }
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        Alumno alumno = alumnoServiceImplement.buscarAlumno().get(i);
        matriculaServiceImplement.buscarMatriculaCurso(alumno);

        ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);

        System.out.println("datos encontrados:");
        for (int j = 0; j < listaMatriculas.size(); j++) {
            System.out.println("=> " + matriculaServiceImplement.buscarMatriculaCurso(alumno).get(j).toString());
        }
    }

    public void buscarMatriculaDeCurso() {
        System.out.println("seleccione el curso del que quiete buscar la matricula");
        System.out.println("///////////////////////////////////////////////////////////////////");
        for (int i = 0; i < cursoServiceImplement.buscarCurso().size(); i++) {
            int elemento = i + 1;
            System.out.println("Elemento " + elemento + ": " + cursoServiceImplement.buscarCurso().get(i).toString());
        }
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        Curso curso = cursoServiceImplement.buscarCurso().get(i);
        ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(curso.getNombreCurso());

        System.out.println("datos encontrados:");
        for (int j = 0; j < listaMatriculas.size(); j++) {
            System.out.println("=> " + matriculaServiceImplement.buscarMatriculaCurso(curso.getNombreCurso()).get(j).toString());
        }
    }

    public void buscarTodasLasMatriculas() {
        ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso();
        for (int j = 0; j < listaMatriculas.size(); j++) {
            System.out.println("=> " + matriculaServiceImplement.buscarMatriculaCurso().get(j).toString());
        }
    }

    public void darDeBajaAlumno() {
        System.out.println("seleccione el alumno del que quiete buscar la matricula");
        System.out.println("///////////////////////////////////////////////////////////////////");
        for (int i = 0; i < alumnoServiceImplement.buscarAlumno().size(); i++) {
            int elemento = i + 1;
            System.out.println("Elemento " + elemento + ": " + alumnoServiceImplement.buscarAlumno().get(i).toString());
        }
        Scanner sc = new Scanner(System.in);
        int i_alumno = sc.nextInt();
        Alumno alumno = alumnoServiceImplement.buscarAlumno().get(i_alumno);
        ArrayList<Matricula> listaMatriculas = matriculaServiceImplement.buscarMatriculaCurso(alumno);

        System.out.println("seleccione el curso del que quiere der de baja el alumno:");
        System.out.println("///////////////////////////////////////////////////////////////////");
        for (int j = 0; j < listaMatriculas.size(); j++) {
            int elemento = j + 1;
            System.out.println("Elemento " + elemento + ": " + matriculaServiceImplement.buscarMatriculaCurso(alumno).get(j).getNombreCurso());
        }
        int i_curso = sc.nextInt();
        Curso curso = cursoServiceImplement.buscarCurso().get(i_curso);

        matriculaServiceImplement.desvincularAlumno(alumno.getDNI(), curso.getNombreCurso());

    }

}
