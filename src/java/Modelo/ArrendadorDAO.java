package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ArrendadorDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    //Funcion que devuelve la consulta de todos los arrendador
    public List listar(){
        List<Arrendador> datos = new ArrayList<>();
        String sql = "SELECT * FROM arrendador";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Arrendador a = new Arrendador();
                a.setCedula(rs.getString(1));
                a.setNombres(rs.getString(2));
                a.setApellidos(rs.getString(3));
                a.setTelefono(rs.getString(4));
                a.setEmail(rs.getString(5));
                a.setClave(rs.getString(6));
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
    
    public int registrar(Arrendador a){
        String sql = "INSERT INTO `arrendador`(`cedula`, `nombres`, `apellidos`, `telefono`, `email`, `clave`) VALUES (?,?,?,?,?,?)";
        int idGenerado=0;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getCedula());
            ps.setString(2, a.getNombres());
            ps.setString(3, a.getApellidos());
            ps.setString(4, a.getTelefono());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getClave());
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
            System.err.println("ArrendadorDAO: "+e);
            return 0;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificar(Arrendador a){
        String sql = "UPDATE `arrendador` SET `nombres`=?,`apellidos`=?,`telefono`=?,`telefono`=?,email=?,`clave`=? WHERE `cedula`=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);            
            ps.setString(1, a.getNombres());
            ps.setString(2, a.getApellidos());
            ps.setString(3, a.getTelefono());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getClave());
            ps.setString(6, a.getCedula());
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
    
    public boolean eliminar(Arrendador a){
        String sql = "DELETE FROM arrendador WHERE  cedula=?";
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
    
    public boolean buscar(Arrendador a){
        String sql = "SELECT *  FROM arrendador WHERE  cedula=?";
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
                a.setEmail(rs.getString(5));
                a.setClave(rs.getString(6));
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
