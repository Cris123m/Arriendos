package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DepartamentoDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    //Funcion que devuelve la consulta de todos los departamento
    public List listar(){
        List<Departamento> datos = new ArrayList<>();
        String sql = "SELECT * FROM departamentos";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setIdDepartamento(rs.getInt(1));
                d.setPiso(rs.getInt(2));
                d.setLetra(rs.getString(3).charAt(0));
                d.setCapacidad(rs.getInt(4));
                d.setPrecioId(rs.getInt(5));
                datos.add(d);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return datos;
    }
    
    public int registrar(Departamento d){
        String sql = "INSERT INTO `departamentos`(`piso`, `letra`, `capacidad`, `precio_id`) VALUES (?,?,?,?)";
        int idGenerado=0;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, d.getPiso());
            ps.setString(2, d.getLetra()+"");
            ps.setInt(3, d.getCapacidad());
            ps.setInt(4, d.getPrecioId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo guardar");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                     idGenerado = generatedKeys.getInt(1);
            }
            return idGenerado;
        } catch (SQLException e) {
            System.err.println("DepartamentoDAO: "+e);
            return 0;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificar(Departamento d){
        String sql = "UPDATE `departamentos` SET `piso`=?,`letra`=?,`capacidad`=?,`precio_id`=? WHERE `id_departamento`=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);                        
            ps.setInt(1, d.getPiso());
            ps.setString(2, d.getLetra()+"");
            ps.setInt(3, d.getCapacidad());
            ps.setInt(4, d.getPrecioId());
            ps.setInt(5, d.getIdDepartamento());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean eliminar(Departamento d){
        String sql = "DELETE FROM departamentos WHERE  id_departamento=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, d.getIdDepartamento());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscar(Departamento d){
        String sql = "SELECT *  FROM departamentos WHERE  id_departamento=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, d.getIdDepartamento());
            rs = ps.executeQuery();
            if(rs.next()){
                d.setIdDepartamento(rs.getInt(1));
                d.setPiso(rs.getInt(2));
                d.setLetra(rs.getString(3).charAt(0));
                d.setCapacidad(rs.getInt(4));
                d.setPrecioId(rs.getInt(5));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
