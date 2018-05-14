/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;

/**
 *
 * @author daniel
 */
public class conexion {
    static Connection contacto = null;
    public static Connection getConexion(){
        
    
    String url ="jdbc:sqlserver://CaboFroward2018.mssql.somee.com;databaseName=CaboFroward2018";
   // String url ="jdbc:sqlserver://192.168.129.12;databaseName=Control_Accesos_Froward";
    
   try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         
        
    }catch (ClassNotFoundException e){
    
    JOptionPane.showMessageDialog(null, "Error driver","error",JOptionPane.ERROR_MESSAGE);
    }
    try{
       // contacto = DriverManager.getConnection(url,"ctrlaccfrw","SwaDr4phAX3c");
       contacto = DriverManager.getConnection(url,"danielbustos86_SQLLogin_2","ow4ifjce9v");
    }catch (SQLException e){
         JOptionPane.showMessageDialog(null, "Error driver","error",JOptionPane.ERROR_MESSAGE);
   
    }
        return contacto;
    }
    
    public static ResultSet Consulta(String consulta){
        Connection con = getConexion();
        Statement declara;
        try{
            declara = con.createStatement();
            ResultSet respuesta= declara.executeQuery(consulta);
            return respuesta;
        }catch (SQLException e){
         JOptionPane.showMessageDialog(null, "Error Resulset","error",JOptionPane.ERROR_MESSAGE);
   
        }
        return null;
    
    
    
    
    }
    
    public static boolean query(String consulta){
        Connection con = getConexion();
        Statement declara;
        boolean resul;
        
        try{
            declara = con.createStatement();
            resul=declara.execute(consulta);
            
        }catch (SQLException e){
         JOptionPane.showMessageDialog(null, "Error Resulset","error",JOptionPane.ERROR_MESSAGE);
              resul=false;  
        }   
        return resul;
    
    
    
    
    }

     public static String Procedimiento_1(int tipomov,String ipequ,int ID_PERSONA_APROBADA){
        Connection con = getConexion();
      
       // CallableStatement cstmt;
        String resul;
       // Date fecha= new Date("01-01-2017");
        try{
             CallableStatement cStmt = con.prepareCall("{call REGISTRA_ACCESO(?,?,?,?)}");  
                cStmt.setString(1, null);
                cStmt.setInt(2, tipomov);
                cStmt.setString(3, ipequ);
                cStmt.setInt(4, ID_PERSONA_APROBADA);
                
                
       
          //    cStmt.setDate(3,'01-01-2017');

                

                cStmt.executeUpdate();
                
                
                resul="ok";
                
                
        }catch (SQLException e){
       // JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
            resul=e.getMessage();  
        
        
        }   
        return resul;
    
    
  }
       public static boolean ModificaLibro(int codigoLibro,String nombreLibro){
        Connection con = getConexion();
      
       // CallableStatement cstmt;
        boolean resul;
        
        try{
             CallableStatement cStmt = con.prepareCall("{call ModificaLibro(?,?)}");  
                cStmt.setInt(1, codigoLibro);
                cStmt.setString(2, nombreLibro);
                cStmt.executeUpdate();
                
                
                resul=true;
                
                
        }catch (SQLException e){
         JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
              resul=false;  
        
        
        }   
        return resul;
    
    
  }
    
       public static ResultSet DatosMov(int rut,String pasaporte){
        Connection con = getConexion();
        
        
        try{
            CallableStatement cStmt = con.prepareCall("{call DATOS_MOVIMIENTO(?,?)}");  
         
             cStmt.setInt(1, rut);
                cStmt.setString(2, pasaporte);
            ResultSet result = cStmt.executeQuery();
          
           return result;
        }catch (SQLException e){
         JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
   
        }
        return null;
    
    
    
    
    }
     

}
