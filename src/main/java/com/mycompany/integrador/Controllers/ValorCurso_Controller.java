/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Models.Curso;
import com.mycompany.integrador.Models.ValorCurso;
import com.mycompany.integrador.Service.ValorCursoServiceImplement;
import com.mycompany.integrador.Service.CursoServiceImplement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author exequiel
 */
public class ValorCurso_Controller {

    ValorCursoServiceImplement valorCursoServiceImplement = new ValorCursoServiceImplement();
    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();

    Scanner sc = new Scanner(System.in);

    public void nuevoValorCurso() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            System.out.println("seleccione el curso del que quiere asignar el precio:");
            System.out.println("///////////////////////////////////////////////////////////////////");
            for (int i = 0; i < listaCursos.size(); i++) {
                int elemento = i + 1;
                System.out.println("Elemento " + elemento + ": " + listaCursos.get(i).getNombreCurso());
            }
            int i_curso = sc.nextInt();
            Curso curso = listaCursos.get(i_curso - 1);
            System.out.println("ingrese el precio del curso");
            int precio = sc.nextInt();
            ValorCurso valorCurso = new ValorCurso(listaCursos.get(i_curso - 1).getNombreCurso(), precio, LocalDate.now(), null, null);
            valorCursoServiceImplement.asignarPrecio(valorCurso);
        } else {
            System.out.println("no hay cursos que mostrar");
        }

    }

    public void buscarPrecioPorId() {
        if (valorCursoServiceImplement.buscarValorCursos().size() > 0) {
            System.out.println(valorCursoServiceImplement.buscarValorCursos(1).toString());
        } else {
            System.out.println("no hay registros que buscar");
        }
    }

    public void buscarPrecioPorCurso() {
        if (valorCursoServiceImplement.buscarValorCursos().size() > 0) {
            System.out.println(valorCursoServiceImplement.buscarValorCursos("peluqueria").toString());
        } else {
            System.out.println("no hay registros que buscar");
        }
    }

    public void buscarPrecioDeCurso() {
        if (valorCursoServiceImplement.buscarValorCursos().size() > 0) {
            try{
                ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
                System.out.println("seleccione el curso del que quiere ver el precio:");
                System.out.println("///////////////////////////////////////////////////////////////////");
                for (int i = 0; i < listaCursos.size(); i++) {
                    int elemento = i + 1;
                    System.out.println("Elemento " + elemento + ": " + listaCursos.get(i).getNombreCurso());
                }
                int i_curso = sc.nextInt();
                ValorCurso valorCurso = valorCursoServiceImplement.buscarValorCursos(listaCursos.get(i_curso - 1).getNombreCurso());
                System.out.println(valorCurso.toString());
            }catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }

        } else {
            System.out.println("no hay cursos que mostrar");
        }

    }

    public void modificarPrecio() {
        if (valorCursoServiceImplement.buscarValorCursos().size() > 0) {
            try{
                System.out.println("seleccione el curso del que quiere modificar el precio:");
                System.out.println("///////////////////////////////////////////////////////////////////");
                ArrayList<ValorCurso> listaPreciosCursos = valorCursoServiceImplement.buscarValorCursos();

                for (int i = 0; i < listaPreciosCursos.size(); i++) {
                    int elemento = i + 1;
                    System.out.println("Elemento " + elemento + ": " + listaPreciosCursos.get(i));
                }
                int i_curso = sc.nextInt();
                if (i_curso > listaPreciosCursos.size()) {
                    System.out.println("no existe el registro seleccionado");
                } else {
                    ValorCurso valorCurso = listaPreciosCursos.get(i_curso - 1);
                    System.out.println("indique el nuevo precio del curso");
                    int precioNuevo = sc.nextInt();
                    valorCurso.setPrecioCurso(precioNuevo);
                    valorCurso.setFechaModificacion(LocalDate.now());
                    valorCursoServiceImplement.modificarPrecio(listaPreciosCursos.get(i_curso - 1).getIdValorCurso(), valorCurso);
                }
            }catch (IndexOutOfBoundsException e) {
                System.out.println("no existe el registro seleccionado");
            }
        } else {
            System.out.println("no hay registros que buscar");
        }
    }
}
