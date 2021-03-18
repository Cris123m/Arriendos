package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PrecioDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    //Funcion que devuelve la consulta de todos los precio
    public List listar(){
        List<Precio> datos = new ArrayList<>();
        String sql = "SELECT * FROM precios";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Precio p = new Precio();
                p.setIdPrecio(rs.getInt(1));
                p.setValorArriendo(rs.getDouble(2));
                p.setValorGarantia(rs.getDouble(3));
                datos.add(p);
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
    
    public int registrar(Precio p){
        String sql = "INSERT INTO `precios`(`valor_arriendo`, `valor_garantia`) VALUES (?,?)";
        int idGenerado=0;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, p.getValorArriendo());
            ps.setDouble(2, p.getValorGarantia());
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
            System.err.println("PrecioDAO: "+e);
            return 0;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificar(Precio p){
        String sql = "UPDATE `precios` SET `valor_arriendo`=?,`valor_garantia`=? WHERE `id_precio`=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);                        
            ps.setDouble(1, p.getValorArriendo());
            ps.setDouble(2, p.getValorGarantia());
            ps.setInt(3, p.getIdPrecio());
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
    
    public boolean eliminar(Precio p){
        String sql = "DELETE FROM precios WHERE  id_precio=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getIdPrecio());
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
    
    public boolean buscar(Precio p){
        String sql = "SELECT *  FROM precios WHERE  id_precio=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getIdPrecio());
            rs = ps.executeQuery();
            if(rs.next()){
                p.setIdPrecio(rs.getInt(1));
                p.setValorArriendo(rs.getDouble(2));
                p.setValorGarantia(rs.getDouble(3));
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
