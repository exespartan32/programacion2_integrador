/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import java.util.Optional;
import com.mycompany.integrador.Models.HistorialDeCuentas;

/**
 *
 * @author exequiel
 */
public interface HistorialCuentaService {

    public void pagarCurso(HistorialDeCuentas hdc);

    public void modificarPago(Long id, HistorialDeCuentas hdc);

    public void eliminarPago(Long id);

    public ArrayList<HistorialDeCuentas> buscarTodosLosPagos();

    public Optional<HistorialDeCuentas> buscarPago(Long id);
    
    
}
