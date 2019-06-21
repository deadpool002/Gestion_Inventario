/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import ConexionBD.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author pablo
 */
public class ModeloLogin {
    private ConexionBD con;
    
    
    public ModeloLogin(){
        this.con= new ConexionBD();
    }
    public String[] getAcceso(String login,String password){
        String[] usuario= {"","","",""};
        try {
            
            ResultSet resultado= con.getDatos(
                    "Select * FROM usuario WHERE user='"+login+"' AND password=SHA1('"+password+"')");
            
            if(resultado.next()){
                usuario[0]=resultado.getString("user");
                usuario[1]=resultado.getString("nombreUsuario");
                usuario[2]=resultado.getString("cargo");
                usuario[3]=resultado.getString("id");
                String url  = "update usuario set fechaIngreso=(NOW()) where id="+usuario[3];
                con.ejecutarConsulta(url);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ModeloLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
}
