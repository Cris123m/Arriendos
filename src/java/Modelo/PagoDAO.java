package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PagoDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    //Funcion que devuelve la consulta de todos los pago
    public List listar(){
        List<Pago> datos = new ArrayList<>();
        String sql = "SELECT * FROM pagos";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Pago p = new Pago();
                p.setIdPago(rs.getInt(1));
                p.setFechaPago(rs.getDate(2));
                p.setAbonado(rs.getDouble(3));
                p.setEstado(rs.getString(4));
                p.setCedula(rs.getString(5));
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
    
    public int registrar(Pago p){
        String sql = "INSERT INTO `pagos`(`fecha_pago`, `abonado`, `estado`, `arrendatarios_cedula`) VALUES (?,?,?,?)";
        int idGenerado=0;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, p.getFechaPago());
            ps.setDouble(2, p.getAbonado());
            ps.setString(3, p.getEstado());
            ps.setString(4, p.getCedula());
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
            System.err.println("PagoDAO: "+e);
            return 0;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificar(Pago p){
        String sql = "UPDATE `pagos` SET `fecha_pago`=?,`abonado`=?,`estado`=?,`arrendatarios_cedula`=? WHERE `id_pago`=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);                        
            ps.setDate(1, p.getFechaPago());
            ps.setDouble(2, p.getAbonado());
            ps.setString(3, p.getEstado());
            ps.setString(4, p.getCedula());
            ps.setInt(5, p.getIdPago());
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
    
    public boolean eliminar(Pago p){
        String sql = "DELETE FROM pagos WHERE  id_pago=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getIdPago());
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
    
    public boolean buscar(Pago p){
        String sql = "SELECT *  FROM pagos WHERE  id_pago=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getIdPago());
            rs = ps.executeQuery();
            if(rs.next()){
                p.setIdPago(rs.getInt(1));
                p.setFechaPago(rs.getDate(2));
                p.setAbonado(rs.getDouble(3));
                p.setEstado(rs.getString(4));
                p.setCedula(rs.getString(5));
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
