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
import java.util.ArrayList;
import java.util.InputMismatchException;
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
                System.out.println("///////////////////////////////////////////////////////////////////");
                System.out.println("|                           Elemento " + i + ":                           |");
                System.out.println("------------------------------------------------------------------");
                System.out.println("| curso: " + listaCursos.get(i).getNombreCurso());
                System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                System.out.println("| DNI del profesor a cargo: " + listaCursos.get(i).getDniProfesor());
                System.out.println("-----------------------------------------------------------------");
            }
            int i_curso = sc.nextInt();
            Curso curso = listaCursos.get(i_curso - 1);
            System.out.println("ingrese el precio del curso");
            int precio = sc.nextInt();
            try {
                ValorCurso valorCurso = new ValorCurso(
                        listaCursos.get(i_curso).getNombreCurso(),
                        precio,
                        LocalDate.now(),
                        null,
                        null);
                valorCursoServiceImplement.asignarPrecio(valorCurso);
            } catch (NumberFormatException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("Error: el precio debe ser un número.");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("no hay cursos que mostrar");
            System.out.println("primero agregue cursos al sistema");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarPrecioPorId() {
        ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
        if (listaCursos.size() > 0) {
            System.out.println("ingrese el id que desea buscar");
            int id = sc.nextInt();
            if (valorCursoServiceImplement.buscarValorCursos().size() > 0) {
                ValorCurso listaPrecios = valorCursoServiceImplement.buscarValorCursos(id);
                System.out.println("////////////////////////////////////////////////////////////");
                System.out.println("|                      Datos encontrados:                  |");
                System.out.println("-----------------------------------------------------------");
                System.out.println("| nombre del curso: " + listaPrecios.getNombreCurso());
                System.out.println("| precio del curso: " + listaPrecios.getPrecioCurso());
                System.out.println("-----------------------------------------------------------");
            } else {
                System.out.println("------------------------------------------------------------------");
                System.out.println("No se encontro ningun curso con id " + id);
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("no hay precios de cursos que mostrar");
            System.out.println("primero agregue los precios al sistema");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void verTodosLosPrecios() {
        ArrayList<ValorCurso> listaPrecios = valorCursoServiceImplement.buscarValorCursos();
        if (listaPrecios.size() > 0) {
            for (int i = 0; i < listaPrecios.size(); i++) {
                System.out.println("///////////////////////////////////////////////////////////////////");
                System.out.println("|                           Elemento " + i + ":                           |");
                System.out.println("------------------------------------------------------------------");
                System.out.println("| nombre del curso: " + listaPrecios.get(i).getNombreCurso());
                System.out.println("| precio del curso: " + listaPrecios.get(i).getPrecioCurso());
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("no hay precios de cursos que mostrar");
            System.out.println("primero agregue los precios al sistema");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarPrecioDeCurso() {
        System.out.println("seleccione el curso del que quiere ver el precio:");
        System.out.println("///////////////////////////////////////////////////////////////////");
        if (valorCursoServiceImplement.buscarValorCursos().size() > 0) {
            try {
                ArrayList<Curso> listaCursos = cursoServiceImplement.buscarCurso();
                for (int i = 0; i < listaCursos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| curso: " + listaCursos.get(i).getNombreCurso());
                    System.out.println("| meses de duracion: " + listaCursos.get(i).getMesesDuracion());
                    System.out.println("| DNI del profesor a cargo: " + listaCursos.get(i).getDniProfesor());
                    System.out.println("------------------------------------------------------------------");
                }
                System.out.println("elemento Nº ");
                int i_curso = sc.nextInt();
                ValorCurso valorCurso = valorCursoServiceImplement.buscarValorCursos(listaCursos.get(i_curso).getNombreCurso());
                System.out.println(valorCurso.toString());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("ERROR!!: no existe el registro seleccionado");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("no hay precios de cursos que mostrar");
            System.out.println("primero agregue los precios al sistema");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void modificarPrecio() {
        if (valorCursoServiceImplement.buscarValorCursos().size() > 0) {
            try {
                System.out.println("seleccione el curso del que quiere modificar el precio:");
                System.out.println("///////////////////////////////////////////////////////////////////");
                ArrayList<ValorCurso> listaPreciosCursos = valorCursoServiceImplement.buscarValorCursos();
                for (int i = 0; i < listaPreciosCursos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| nombre del curso: " + listaPreciosCursos.get(i).getNombreCurso());
                    System.out.println("| precio del curso: " + listaPreciosCursos.get(i).getPrecioCurso());
                    System.out.println("-------------------------------------------------------------------");
                }
                System.out.println("elemento Nº ");
                int i_curso = sc.nextInt();
                ValorCurso valorCurso = listaPreciosCursos.get(i_curso);
                System.out.println("indique el nuevo precio del curso");
                int precioNuevo = sc.nextInt();
                valorCurso.setPrecioCurso(precioNuevo);
                valorCurso.setFechaModificacion(LocalDate.now());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("ERROR!!: no existe el registro seleccionado");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("ERROR!!: debe ser un numero.");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("no hay precios de cursos que mostrar");
            System.out.println("primero agregue los precios al sistema");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void buscarPrecioPorCurso() {
        System.out.println("ingrese el nombre del curso que quiere buscar");
        String nombreCurso = sc.nextLine();
        if (valorCursoServiceImplement.buscarValorCursos().size() > 0) {
            System.out.println("//////////////////////////////////////////////////////////////");
            System.out.println("|                       Datos encontrados:                   |");
            System.out.println("--------------------------------------------------------------");
            System.out.println("| nombre del curso: " + valorCursoServiceImplement.buscarValorCursos(nombreCurso).getNombreCurso());
            System.out.println("| precio del curso: " + valorCursoServiceImplement.buscarValorCursos(nombreCurso).getPrecioCurso());
            System.out.println("--------------------------------------------------------------");
        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("no hay precios de cursos que mostrar");
            System.out.println("primero agregue los precios al sistema");
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void eliminarPrecioDeUnCurso() {
        if (valorCursoServiceImplement.buscarValorCursos().size() > 0) {
            try {
                System.out.println("seleccione el curso del que quiere eliminar el precio:");
                System.out.println("///////////////////////////////////////////////////////////////////");
                ArrayList<ValorCurso> listaPreciosCursos = valorCursoServiceImplement.buscarValorCursos();
                for (int i = 0; i < listaPreciosCursos.size(); i++) {
                    System.out.println("///////////////////////////////////////////////////////////////////");
                    System.out.println("|                           Elemento " + i + ":                           |");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("| nombre del curso: " + listaPreciosCursos.get(i).getNombreCurso());
                    System.out.println("| precio del curso: " + listaPreciosCursos.get(i).getPrecioCurso());
                    System.out.println("-------------------------------------------------------------------");
                }
                System.out.println("elemento Nº ");
                int i_curso = sc.nextInt();

                System.out.print("¿Está seguro que desea eliminar el precio del curso de " + listaPreciosCursos.get(i_curso).getNombreCurso() + "? (si/no): ");
                String confirmacion = sc.nextLine();
                if (confirmacion.equalsIgnoreCase("si") || confirmacion.equalsIgnoreCase("no")) {
                    if (confirmacion.equalsIgnoreCase("si")) {
                        valorCursoServiceImplement.eliminarPrecio(i_curso);
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                } else {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("ERROR!!: opcion incorrecta. debe colocar si o no");
                    System.out.println("-------------------------------------------------------------------");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("ERROR!!: no existe el registro seleccionado");
                System.out.println("------------------------------------------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("ERROR!!: debe ser un numero.");
                System.out.println("------------------------------------------------------------------");
            }
        } else {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("no hay precios de cursos que mostrar");
            System.out.println("primero agregue los precios al sistema");
            System.out.println("-------------------------------------------------------------------");
        }
    }

}
