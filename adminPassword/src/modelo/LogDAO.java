/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import  java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author pauEscarcia
 */
public class LogDAO {
    Conexion conexion;
    
    public LogDAO(){
        conexion= new Conexion();
    }
    public String insertLog(String key){
        String rptaRegistro= null;
        try{
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs= accesoDB.prepareCall("{call sp_log(?)}");
            cs.setString(1, key);
            
            int numFAfectas = cs.executeUpdate();
            if (numFAfectas>0){
                rptaRegistro= "Registro Exitoso";
            }
           
        }catch(Exception e){
        
        }
        return rptaRegistro;
    }
    
    public ArrayList<Log> listLogMasterKey(String masterKey){
        ArrayList listaLog = new ArrayList();
        Log log ;
        try{
            Connection acceBD= conexion.getConexion();
            
            PreparedStatement ps= acceBD.prepareStatement("SELECT idKey FROM log WHERE keyss = ?  ");
            ps.setString(1, masterKey);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                log = new Log();
                log.setIdKey(rs.getInt(1));
                listaLog.add(log);
                
            }
        }catch(Exception e){
        
        }
        return listaLog;
    }
    public ArrayList<Log> listLog(){
        ArrayList listaLog = new ArrayList();
        Log log ;
        try{
            Connection acceBD= conexion.getConexion();
            PreparedStatement ps= acceBD.prepareStatement("select * from log");
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                log = new Log();
                log.setIdKey(rs.getInt(1));
                log.setKey(rs.getString(2));
                listaLog.add(log);
                
            }
        }catch(Exception e){
        
        }
        return listaLog;
    }
}
