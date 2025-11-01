/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import com.mycompany.integrador.Models.HistorialDeCuentas;

/**
 *
 * @author exequiel
 */
public interface HistorialCuentaService {

    public void pagarCurso(HistorialDeCuentas hdc);

    //public void modificarPago(int id, HistorialDeCuentas hdc);
    //public void eliminarPago(int id);
    public ArrayList<HistorialDeCuentas> buscarPago();

    public HistorialDeCuentas buscarPago(int id);

    public HistorialDeCuentas buscarPago(String DNIAlumno);
    //public HistorialDeCuentas buscarPago();

    public int verSaldo(String DNIAlumno);

    public HistorialDeCuentas buscarPago(String nombreCurso, boolean pago);

}
