package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ArrendatarioDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    //Funcion que devuelve la consulta de todos los arrendatario
    public List listar(){
        List<Arrendatario> datos = new ArrayList<>();
        String sql = "SELECT * FROM arrendatarios";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Arrendatario a = new Arrendatario();
                a.setCedula(rs.getString(1));
                a.setNombres(rs.getString(2));
                a.setApellidos(rs.getString(3));
                a.setTelefono(rs.getString(4));
                a.setDepartamentoId(rs.getInt(5));
                datos.add(a);
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
    
    public int registrar(Arrendatario a){
        String sql = "INSERT INTO `arrendatarios`(`cedula`, `nombres`, `apellidos`, `telefono`, `departamento_id`) VALUES (?,?,?,?,?)";
        int idGenerado=0;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getCedula());
            ps.setString(2, a.getNombres());
            ps.setString(3, a.getApellidos());
            ps.setString(4, a.getTelefono());
            ps.setInt(5, a.getDepartamentoId());
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
            System.err.println("ArrendatarioDAO: "+e);
            return 0;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificar(Arrendatario a){
        String sql = "UPDATE `arrendatarios` SET `nombres`=?,`apellidos`=?,`telefono`=?,`telefono`=?,departamento_id=? WHERE `cedula`=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);                        
            ps.setString(1, a.getNombres());
            ps.setString(2, a.getApellidos());
            ps.setString(3, a.getTelefono());
            ps.setInt(4, a.getDepartamentoId());
            ps.setString(5, a.getCedula());
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
    
    public boolean eliminar(Arrendatario a){
        String sql = "DELETE FROM arrendatarios WHERE  cedula=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getCedula());
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
    
    public boolean buscar(Arrendatario a){
        String sql = "SELECT *  FROM arrendatarios WHERE  cedula=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getCedula());
            rs = ps.executeQuery();
            if(rs.next()){
                a.setCedula(rs.getString(1));
                a.setNombres(rs.getString(2));
                a.setApellidos(rs.getString(3));
                a.setTelefono(rs.getString(4));
                a.setDepartamentoId(rs.getInt(5));
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
