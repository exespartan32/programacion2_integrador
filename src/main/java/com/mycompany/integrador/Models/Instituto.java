/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Models;

import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public class Instituto {

    private Long idInstituto;
    private String razonSocial;
    private ArrayList<Curso> cursos;
    private ArrayList<Alumno> alumnos;
    private ListaMateriales materiales;
    private ArrayList<Profesor> profesores;

    public Instituto(Long idInstituto, String razonSocial, ArrayList<Curso> cursos, ArrayList<Alumno> alumnos, ListaMateriales materiales, ArrayList<Profesor> profesores) {
        this.idInstituto = idInstituto;
        this.razonSocial = razonSocial;
        this.cursos = cursos;
        this.alumnos = alumnos;
        this.materiales = materiales;
        this.profesores = profesores;
    }

    public Long getIdInstituto() {
        return idInstituto;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public ListaMateriales getMateriales() {
        return materiales;
    }

    public void setMateriales(ListaMateriales materiales) {
        this.materiales = materiales;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

}
