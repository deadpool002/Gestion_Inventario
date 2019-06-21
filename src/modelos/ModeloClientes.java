/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import ConexionBD.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class ModeloClientes {
    ConexionBD Conexion;
    int Total;

    public ModeloClientes() {
        this.Conexion = new ConexionBD();
        this.Total = 0;
    }
    
    public String[] getUsuario(int id) {
        String[] usuario = new String[4];
        try {
            ResultSet resultado = Conexion.getDatos("SELECT * FROM usuarios WHERE id = " + id);
            if (resultado.next()) {
                usuario[0] = "" + id;
                usuario[1] = resultado.getString("user");
                usuario[2] = resultado.getString("nombre");
                usuario[3] = resultado.getString("cargo");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return usuario;
    }

    public boolean guardar(int id, String carnet, String numero, String nombre,String direccion) {
        if (id == 0) {
            String consulta = "INSERT INTO cliente"
                    + "(carnetIdentidad, nombre, numeroTelefono, fechaRegistro,direccion) VALUES "
                    + "(" + carnet + ", '" + nombre + "', '" + numero + "',NOW(),'"+direccion+"')";
            if (Conexion.ejecutarConsulta(consulta)) {
                return true;
            } else {
                return false;
            }
        } else {
            String consulta = "UPDATE cliente "
                    + "SET nombre='" + nombre + "', carnetIdentidad=" + carnet + ",numeroTelefono='"+numero+"',direccion='"+direccion+"' "
                    + " WHERE id=" + id;
            if (Conexion.ejecutarConsulta(consulta)) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public boolean borrar(int id) {
        String consulta = "DELETE FROM cliente "
                + " WHERE id = " + id;
        if (Conexion.ejecutarConsulta(consulta)) {
            return true;
        } else {
            return false;
        }
    }
    
    public DefaultTableModel getLista(String textoBusqueda) {
        DefaultTableModel modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        try {
            String consulta = "SELECT * FROM  cliente";
            
            boolean condicion= true;
            if (!textoBusqueda.isEmpty()) {
                consulta += " WHERE nombre LIKE '%" + textoBusqueda + "%' OR carnetIdentidad LIKE '%" + textoBusqueda + "%'"
                        + " OR numeroTelefono LIKE '%" + textoBusqueda + "%'";
                
            }
            ResultSet resultado = Conexion.getDatos(consulta);
            // Se crea el array de columnas
            String[] columnas = {"Id", "Nombre", "Carnet Identidad", "numero","fecha_registro","direccion"};

            resultado.last();
            Total = resultado.getRow();
            //Se crea una matriz con tantas filas y columnas que necesite
            Object[][] datos = new String[Total][6];

            if (resultado.getRow() > 0) {
                resultado.first();
                int i = 0;
                do {
                    datos[i][0] = resultado.getString("id");
                    datos[i][1] = resultado.getString("nombre");
                    datos[i][2] = resultado.getString("carnetIdentidad");
                    datos[i][3] = resultado.getString("numeroTelefono");
                    datos[i][4] = resultado.getString("fechaRegistro");
                    datos[i][5] = resultado.getString("direccion");
                    i++;
                } while (resultado.next());
            }
            resultado.close();
            modeloTabla.setDataVector(datos, columnas);
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        }
        return modeloTabla;
    }
    
    public int getTotal() {
        return Total;
    }
    
}
