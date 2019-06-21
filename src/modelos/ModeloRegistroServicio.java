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
public class ModeloRegistroServicio {

    ConexionBD Conexion;

    public ModeloRegistroServicio() {
        this.Conexion = new ConexionBD();
    }

    public boolean guardar(int _cliente, String _tipo) {

        String consulta = "INSERT INTO numeroServicio "
                + "(id_cliente,fecha_registro,estado,tipo_registro) VALUES "
                + "(" + _cliente + ",NOW(),'pendiente','" + _tipo + "')";
        if (Conexion.ejecutarConsulta(consulta)) {
            return true;
        } else {
            return false;
        }

    }
    
    public boolean modificarServicio(String _id,String registro,String entrega,String repuesto,String servicio) {
        String consulta = "UPDATE numeroServicio SET fecha_registro='"+registro+"',"
                + "fecha_entrega='"+entrega+"',costo_repuesto="+repuesto+","
                + "costo_servicio="+servicio+" where id="+_id;
        
        if (Conexion.ejecutarConsulta(consulta)) {
            return true;
        } else {
            return false;
        }
    }
    public String[] getUltimoRegistro() {
        int id = 0;
        String[] numeroServicio = {"", "", "", "", "", "",""};
        try {
            String getId = "select max(id) from numeroServicio";
            ResultSet resId = Conexion.getDatos(getId);
            if (resId.next()) {
                id = Integer.parseInt(resId.getString(1));
            }
            resId.close();
            String consulta = "select date(S.fecha_registro),time(S.fecha_registro),C.carnetIdentidad,"
                    + "C.nombre,C.direccion,C.numeroTelefono,S.id"
                    + " from numeroServicio S inner join cliente C on S.id_cliente = C.id where S.id=" + id + "";

            ResultSet resultado = Conexion.getDatos(consulta);

            //Se crea una matriz con tantas filas y columnas que necesite
            if (resultado.next()) {

                numeroServicio[0] = resultado.getString(1);
                numeroServicio[1] = resultado.getString(2);
                numeroServicio[2] = resultado.getString(3);
                numeroServicio[3] = resultado.getString(4);
                numeroServicio[4] = resultado.getString(5);
                numeroServicio[5] = resultado.getString(6);
                numeroServicio[6] = resultado.getString(7);
                

            }
            resultado.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return numeroServicio;
    }

    
    /* REGISTRO DE SOPORTE PORTATILES */
    public boolean guardarPortatil(String _idServicio,
                                    String _marca,
                                    String _modelo,
                                    String _nroSerie,
                                    String _cargador,
                                    String _serieCargador,
                                    String _descripcion) {
        String consulta = "INSERT INTO soporte_portatil "
                + "(id_servicio,marca,modelo,numero_serie,cargador,numero_serie_cargador,descripcion_trabajo)"
                + " VALUES(" + _idServicio + ",'"+_marca +"',"
                + "'"+_modelo+"','" + _nroSerie + "','" + _cargador + "','" + _serieCargador + "','" + _descripcion + "')";
        if (Conexion.ejecutarConsulta(consulta)) {
            return true;
        } else {
            return false;
        }

    }
}
