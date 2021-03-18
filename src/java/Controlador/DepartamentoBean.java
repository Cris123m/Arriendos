/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Departamento;
import Modelo.DepartamentoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "departamentoBean")
@ViewScoped
public class DepartamentoBean implements Serializable{

    DepartamentoDAO dDAO= new DepartamentoDAO();
    private List<Departamento> listaDep= new ArrayList<Departamento>();
    private Departamento d = new Departamento();

    public Departamento getD() {
        return d;
    }

    public void setD(Departamento d) {
        this.d = d;
    }

    public List<Departamento> getListaDep() {
        return listaDep;
    }

    public void setListaDep(List<Departamento> listaDep) {
        this.listaDep = listaDep;
    }
    
    
    public DepartamentoBean() {
        
        listaDep=dDAO.listar();
    }
    
    public void getEliminar(Integer id){
        try {
            Departamento d = new Departamento();
            d.setIdDepartamento(id);
            dDAO.eliminar(d);
        } catch (Exception e) {
        }
    }
    
}
