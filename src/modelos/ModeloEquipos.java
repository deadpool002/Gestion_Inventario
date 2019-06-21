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
public class ModeloEquipos {
    ConexionBD Conexion;
    int Total;

    public ModeloEquipos() {
        this.Conexion = new ConexionBD();
        this.Total = 0;
    }
    
    

    public boolean guardar(int id, String carnet, String numero, String nombre,String direccion) {
        if (id == 0) {
            String consulta = "INSERT INTO cliente "
                    + "(carnetIdentidad, nombre, numeroTelefono, fechaRegistro,direccion) VALUES "
                    + "('" + carnet + "', '" + nombre + "', '" + numero + "',NOW(),'"+direccion+"')";
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
    
    public DefaultTableModel getLista(String textoBusqueda,String tipo,String estado) {
        DefaultTableModel modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        try {
            
            String consulta="select N.id,C.carnetIdentidad,marca,modelo,N.fecha_registro,N.fecha_entrega from numeroServicio N "
                        + "inner join soporte_"+tipo+" P on N.id = P.id_servicio "
                        +" inner join cliente C on N.id_cliente = C.id WHERE estado='"+estado+"'";
            
            
            boolean condicion= true;
            if (!textoBusqueda.isEmpty()) {
                consulta += " AND (N.id LIKE '%" + textoBusqueda + "%' OR C.carnetIdentidad LIKE '%" + textoBusqueda + "%'"
                        + " OR marca LIKE '%" + textoBusqueda + "%') ";
                
            }
            ResultSet resultado = Conexion.getDatos(consulta);
            // Se crea el array de columnas
            String fecha="";
            int tipoestado=0;
            if(estado.equals("entregado")){
                fecha="Fecha de Entrega";
                tipoestado=6;
            }else{
                fecha="Fecha Recepcion";
                tipoestado=5;
            }
            String[] columnas = {"Id", "Carnet", "Marca","Modelo",fecha};

            resultado.last();
            Total = resultado.getRow();
            //Se crea una matriz con tantas filas y columnas que necesite
            Object[][] datos = new String[Total][5];

            if (resultado.getRow() > 0) {
                resultado.first();
                int i = 0;
                do {
                    datos[i][0] = resultado.getString(1);
                    datos[i][1] = resultado.getString(2);
                    datos[i][2] = resultado.getString(3);
                    datos[i][3] = resultado.getString(4);
                    datos[i][4] = resultado.getString(tipoestado);
                    i++;
                } while (resultado.next());
            }
            resultado.close();
            modeloTabla.setDataVector(datos, columnas);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return modeloTabla;
    }
    
    public int getTotal() {
        return Total;
    }
    
}
