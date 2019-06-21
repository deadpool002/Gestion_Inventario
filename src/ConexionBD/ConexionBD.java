/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pablo
 */
public class ConexionBD {
    private Connection con = null;
    public ConexionBD(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String baseDatos = "jdbc:mysql://localhost:3307/ServicioTecnico";
            String user ="root";
            String password ="";
            con =  (Connection) DriverManager.getConnection(baseDatos,user,password);
            //System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error conexion Base de Datos");
        }
    }
    public Connection getConexion(){
        return con;
    }
    public void Desconectar(){
        con = null;
    }
    public boolean ejecutarConsulta(String consulta){
        try {
            PreparedStatement prepararConsulta = (PreparedStatement) this.getConexion().prepareStatement(consulta);
            prepararConsulta.execute();
            prepararConsulta.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public ResultSet getDatos(String consulta){
        
        try {
            PreparedStatement prepararConsulta;
            prepararConsulta = (PreparedStatement) this.getConexion().prepareStatement(consulta);
            return prepararConsulta.executeQuery();
           
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
