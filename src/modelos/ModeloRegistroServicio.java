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

    public boolean modificarServicio(String _id, String registro, String entrega, String repuesto, String servicio) {
        String consulta = "UPDATE numeroServicio SET fecha_registro='" + registro + "',"
                + "fecha_entrega='" + entrega + "',costo_repuesto=" + repuesto + ","
                + "costo_servicio=" + servicio + " where id=" + _id;

        if (Conexion.ejecutarConsulta(consulta)) {
            return true;
        } else {
            return false;
        }
    }

    public String[] getUltimoRegistro() {
        int id = 0;
        String[] numeroServicio = {"", "", "", "", "", "", ""};
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
                + " VALUES(" + _idServicio + ",'" + _marca + "',"
                + "'" + _modelo + "','" + _nroSerie + "','" + _cargador + "','" + _serieCargador + "','" + _descripcion + "')";
        if (Conexion.ejecutarConsulta(consulta)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean savePrinter(String id, String marca, String modelo,
            String serie, String cartucho, String color, String negro, String descripcion) {
        String consulta = "INSERT INTO soporte_impresora "
                + " (id_servicio,marca,modelo,numero_serie,cartuchos,color,negro,descripcion_trabajo) "
                + " VALUES(" + id + ",'" + marca + "','" + modelo + "','" + serie + "','"
                + cartucho + "','" + color + "','" + negro + "','" + descripcion + "')";

        if (Conexion.ejecutarConsulta(consulta)) {
            return true;
        } else {
            return false;
        }
    }

    /*EXTRAER DATOS DE PORTATIL*/
    public String[] getPortatil(String _id_servicio) {
        try {
            String[] portatil = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
            String consulta = "select date(S.fecha_registro),time(S.fecha_registro),date(S.fecha_entrega),time(S.fecha_entrega),"
                    + " S.costo_repuesto,S.costo_servicio,C.carnetIdentidad,C.nombre,C.numeroTelefono,C.direccion,"
                    + " P.marca,P.modelo,P.numero_serie,P.cargador,P.numero_serie_cargador,P.descripcion_trabajo"
                    + " from soporte_portatil P inner join numeroServicio S on P.id_servicio = S.id "
                    + " inner join cliente C on C.id = S.id_cliente where P.id_servicio=" + _id_servicio;
            ResultSet resultado = Conexion.getDatos(consulta);

            if (resultado.next()) {
                portatil[0] = resultado.getString(1);//fecha registro
                portatil[1] = resultado.getString(2);//hora registro
                portatil[2] = resultado.getString(3);//fecha entrega
                portatil[3] = resultado.getString(4);//hora entrega
                portatil[4] = resultado.getString(5);//costo repuesto
                portatil[5] = resultado.getString(6);//costo servicio
                portatil[6] = resultado.getString(7);//carnet cliente
                portatil[7] = resultado.getString(8);//nombre
                portatil[8] = resultado.getString(9);//numero celular
                portatil[9] = resultado.getString(10);//direccion
                portatil[10] = resultado.getString(11);//marca 
                portatil[11] = resultado.getString(12);//modelo
                portatil[12] = resultado.getString(13);//numero serie
                portatil[13] = resultado.getString(14);//cargador
                portatil[14] = resultado.getString(15);//numero serie cargador
                portatil[15] = resultado.getString(16);//descripciom

            }
            return portatil;
        } catch (SQLException ex) {
            System.err.println("Error al traer datos de portatil");
        }
        return null;
    }

    public String[] getPrinter(String id) {
        try {
            String[] printer = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
            String consulta = "select date(S.fecha_registro),time(S.fecha_registro),date(S.fecha_entrega),time(S.fecha_entrega),"
                    + " S.costo_repuesto,S.costo_servicio,C.carnetIdentidad,C.nombre,C.numeroTelefono,C.direccion,"
                    + " I.marca,I.modelo,I.numero_serie,I.cartuchos,I.color,I.negro,I.descripcion_trabajo"
                    + " from soporte_impresora I inner join numeroServicio S on I.id_servicio = S.id"
                    + " inner join cliente C on C.id = S.id_cliente where I.id_servicio=" + id;
            ResultSet resultado = Conexion.getDatos(consulta);
            
            if (resultado.next()) {
                printer[0] = resultado.getString(1);//fecha registro
                printer[1] = resultado.getString(2);//hora registro
                printer[2] = resultado.getString(3);//fecha entrega
                printer[3] = resultado.getString(4);//hora entrega
                printer[4] = resultado.getString(5);//costo repuesto
                printer[5] = resultado.getString(6);//costo servicio
                printer[6] = resultado.getString(7);//carnet cliente
                printer[7] = resultado.getString(8);//nombre
                printer[8] = resultado.getString(9);//numero celular
                printer[9] = resultado.getString(10);//direccion
                printer[10] = resultado.getString(11);//marca
                printer[11] = resultado.getString(12);//modelo
                printer[12] = resultado.getString(13);//numero serie
                printer[13] = resultado.getString(14);//cartucho
                printer[14] = resultado.getString(15);//color
                printer[15] = resultado.getString(16);//negro
                printer[16] = resultado.getString(17);//descripcion
                
                
            }
            return printer;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloRegistroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateLaptop(String _id_servicio, String marca,
            String modelo, String serie, String cargador, String serie_cargador, String trabajo) {
        String consulta = "UPDATE soporte_portatil SET marca='" + marca + "',modelo='" + modelo + "',"
                + "numero_serie='" + serie + "',cargador='" + cargador + "',"
                + "numero_serie_cargador='" + serie_cargador + "',"
                + "descripcion_trabajo ='" + trabajo + "' where id_servicio=" + _id_servicio;
        if (Conexion.ejecutarConsulta(consulta)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePrinter(String id, String marc, String mod, String ser, String cart, String col, String neg, String desc) {
        String consulta = "Update soporte_impresora SET "
                + " marca='" + marc + "',modelo='" + mod + "',numero_serie='" + ser + "', "
                + " cartuchos='" + cart + "',color='" + col + "',negro='" + neg + "',descripcion_trabajo='" + desc + "' "
                + " WHERE id_servicio =" + id;
        if (Conexion.ejecutarConsulta(consulta)) {
            return true;
        } else {
            return false;
        }
    }
}
