/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;
import com.mycompany.integrador.Models.Material;
import java.util.ArrayList;
import java.util.Optional;
/**
 *
 * @author exequiel
 */
public interface MaterialService {
    
    public void guardarMaterial(Material material);

    public void modificarMaterial(Long id, Material material);

    public void borrarMaterial(Long id);

    public ArrayList<Material> buscarTodosLosMateriales();

    public Optional<Material> buscarMaterial(Long id);
}
