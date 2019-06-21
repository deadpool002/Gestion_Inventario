/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import interfaces.InterfazPrincipal;
import interfaces.RegistroCliente;
import interfaces.RegistroImpresora;
import interfaces.RegistroPortatil;
import interfaces.RegistroUsuario;
import interfaces.impresion.InterfazImpresion;
import interfaces.impresion.escritorio;
import interfaces.impresion.impresora;
import interfaces.impresion.otros;
import interfaces.impresion.portatil;
import interfaces.login;
import interfaces.paneles.PanelClientes;
import interfaces.paneles.PanelEquipos;
import interfaces.paneles.PanelUsuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelos.ModeloClientes;
import modelos.ModeloEquipos;
import modelos.ModeloLogin;
import modelos.ModeloRegistroServicio;
import modelos.ModeloUsuarios;

/**
 *
 * @author pablo
 */
public class ControladorPrincipal implements ActionListener, KeyListener {

    login interfazLogin;
    ModeloLogin modeloLogin;

    /*Creando la interfaz Principal*/
    InterfazPrincipal interfazPrincipal;


    /*PANEL USUARIOS*/
    PanelUsuarios panelUsuarios;
    ModeloUsuarios modeloUsuarios;
    RegistroUsuario registroUsuarios;
    /*PANEL CLIENTES*/
    PanelClientes panelClientes;
    ModeloClientes modeloClientes;
    RegistroCliente registroClientes;
    /*Panel EQUIPOS*/
    PanelEquipos panelEquipos;
    ModeloEquipos modeloEquipos;

    /*PANEL IMPRESIONES*/
    impresora panelImpresora;
    escritorio panelEscritorio;
    otros panelOtros;

    portatil panelPortatil;
    InterfazImpresion interfazImpresion;

    /*REGISTRO DE SERVICIOS*/
    RegistroPortatil registroPortatil;
    RegistroImpresora registroImpresora;
    ModeloRegistroServicio modeloRegistroServicio;
    
    DateFormat df ;


    public ControladorPrincipal() {
        df = new SimpleDateFormat("yyyy-MM-dd");
        /* Iniciando Con la Ventana Login*/
        this.interfazLogin = new login();
        this.interfazLogin = new login();
        this.interfazLogin.setLocationRelativeTo(null);
        this.interfazLogin.setVisible(true);
        this.interfazLogin.setResizable(false);
        /* Escuchar eventos en campos de texto y boton de ingreso */
        this.interfazLogin.txtUsuario().addKeyListener(this);
        this.interfazLogin.txtPassword().addKeyListener(this);
        this.interfazLogin.btnIngresar().addActionListener(this);


        /*Consultas a la base de datos*/
        modeloLogin = new ModeloLogin();

        /*intancianciando interfaz principal*/
        interfazPrincipal = new InterfazPrincipal();
        interfazPrincipal.getMenuEscritorio().addActionListener(this);
        interfazPrincipal.getMenuImpresora().addActionListener(this);
        interfazPrincipal.getMenuOtros().addActionListener(this);
        interfazPrincipal.getMenuPortatil().addActionListener(this);

        panelUsuarios = new PanelUsuarios();
        panelClientes = new PanelClientes();
        panelEquipos = new PanelEquipos();

        modeloUsuarios = new ModeloUsuarios();
        registroUsuario();

        modeloClientes = new ModeloClientes();
        registroCliente();

        modeloEquipos = new ModeloEquipos();
        /*Funciones Controladores*/
        controladorUsuarios();
        controladorClientes();
        controladorEquipos();

        /*IMPRESIONES*/
        panelImpresora = new impresora();
        panelEscritorio = new escritorio();
        panelOtros = new otros();

        panelPortatil = new portatil();
        interfazImpresion = new InterfazImpresion();

        /* SERVICIOS*/
        registroPortatil = new RegistroPortatil();
        registroImpresora = new RegistroImpresora();
        modeloRegistroServicio = new ModeloRegistroServicio();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.interfazLogin.btnIngresar())) {
            validacionDatos();
        }
        if (ae.getSource().equals(this.interfazPrincipal.getMenuEscritorio())) {
            System.out.println("Escritorio");
        }
        if (ae.getSource().equals(this.interfazPrincipal.getMenuImpresora())) {
            if (panelClientes.TablaClientes().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un Cliente");
            } else {
                registroNumeroOrden(Integer.parseInt(panelClientes.TablaClientes().getValueAt(
                        panelClientes.TablaClientes().getSelectedRow(),
                        0).toString()), "impresora");
            }
        }
        if (ae.getSource().equals(this.interfazPrincipal.getMenuOtros())) {
            System.out.println("Otros");
        }
        if (ae.getSource().equals(this.interfazPrincipal.getMenuPortatil())) {
            if (panelClientes.TablaClientes().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un Cliente");
            } else {
                registroNumeroOrden(Integer.parseInt(panelClientes.TablaClientes().getValueAt(
                        panelClientes.TablaClientes().getSelectedRow(),
                        0).toString()), "portatil");
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            interfazLogin.btnIngresar().doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    /*Validar los datos de loggin */

    public void validacionDatos() {
        if (!this.interfazLogin.txtUsuario().getText().isEmpty()) {
            if (this.interfazLogin.txtPassword().getText().length() > 7) {

                String[] usuario = modeloLogin.getAcceso(
                        this.interfazLogin.txtUsuario().getText(),
                        this.interfazLogin.txtPassword().getText());

                if (usuario[0].isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Usuario o Contraseña Invalidos");
                } else {
                    controlInterfazPrincipal();

                    if (usuario[2].equals("Administrador")) {
                        interfazPrincipal.btnUsuario().setVisible(true);
                        pintarPanelPrincipal(panelUsuarios);

                    } else {
                        interfazPrincipal.btnUsuario().setVisible(false);
                        pintarPanelPrincipal(panelClientes);
                    }
                }
                this.interfazLogin.txtPassword().setText("");
            } else {
                JOptionPane.showMessageDialog(null, "El campo de password debe tener como minino 8 caracteres");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El campo de usurario no puede ir vacio");
        }
    }

    /*PRINCIPIO CONTROL DE LA INTERFAZ PRINCIPAL*/
    public void controlInterfazPrincipal() {
        this.interfazPrincipal.setLocationRelativeTo(null);
        this.interfazPrincipal.setResizable(false);
        this.interfazPrincipal.setVisible(true);
        interfazPrincipal.btnClientes().addActionListener((ActionEvent ae) -> {
            pintarPanelPrincipal(panelClientes);
        });
        interfazPrincipal.btnUsuario().addActionListener((ActionEvent ae) -> {
            pintarPanelPrincipal(panelUsuarios);
        });
        interfazPrincipal.btnEquipos().addActionListener((ActionEvent ae) -> {
            pintarPanelPrincipal(panelEquipos);
        });

    }

    public void pintarPanelPrincipal(JPanel nuevoPanel) {
        nuevoPanel.setSize(1010, 511);
        interfazPrincipal.panelPrincipal().removeAll();
        interfazPrincipal.panelPrincipal().add(nuevoPanel);
        interfazPrincipal.panelPrincipal().revalidate();
        interfazPrincipal.panelPrincipal().repaint();
    }
    /* FIN INTERFAZ PRINCIPAL*/

    /* CONTROL DE USUARIOS*/
    private void controladorUsuarios() {
        this.panelUsuarios.BtnActualizar().addActionListener((ActionEvent ae) -> {
            panelUsuarios.TxtBusqueda().setText("");
            cargarDatosUsuarios("");
        });
        this.panelUsuarios.BtnBuscar().addActionListener((ActionEvent ae) -> {
            cargarDatosUsuarios(panelUsuarios.TxtBusqueda().getText());
        });
        this.panelUsuarios.BtnEditar().addActionListener((ActionEvent ae) -> {
            if (panelUsuarios.TablaUsuarios().getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
            } else {
                registroUsuarios.setLocationRelativeTo(null);
                registroUsuarios.setVisible(true);

                registroUsuarios.BtnAgregar().setVisible(false);
                registroUsuarios.BtnModificar().setVisible(true);
                registroUsuarios.TxtNombre().setText(
                        panelUsuarios.TablaUsuarios().getValueAt(panelUsuarios.TablaUsuarios().getSelectedRow(), 2).toString());
                registroUsuarios.TxtUser().setText(
                        panelUsuarios.TablaUsuarios().getValueAt(panelUsuarios.TablaUsuarios().getSelectedRow(), 1).toString());
                registroUsuarios.ComboCargo().setSelectedItem(
                        panelUsuarios.TablaUsuarios().getValueAt(panelUsuarios.TablaUsuarios().getSelectedRow(), 3).toString());
                registroUsuarios.setTitle(
                        panelUsuarios.TablaUsuarios().getValueAt(panelUsuarios.TablaUsuarios().getSelectedRow(), 0).toString());
                registroUsuarios.TxtContrasenia().setText("");
            }
        });
        this.panelUsuarios.BtnNuevo().addActionListener((ActionEvent ae) -> {
            registroUsuarios.setVisible(true);
            registroUsuarios.setLocationRelativeTo(null);
            registroUsuarios.BtnAgregar().setVisible(true);
            registroUsuarios.BtnModificar().setVisible(false);
            registroUsuarios.setTitle("Nuevo Registro");
            registroUsuarios.TxtContrasenia().setText("");
            registroUsuarios.TxtNombre().setText("");
            registroUsuarios.TxtUser().setText("");
        });
        this.panelUsuarios.TxtBusqueda().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                cargarDatosUsuarios(panelUsuarios.TxtBusqueda().getText());
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        cargarDatosUsuarios("");
    }

    private void cargarDatosUsuarios(String txtBusqueda) {
        this.panelUsuarios.setDatos(this.modeloUsuarios.getLista(txtBusqueda),
                this.modeloUsuarios.getTotal());
    }

    private void registroUsuario() {
        registroUsuarios = new RegistroUsuario();
        this.registroUsuarios.BtnAgregar().addActionListener((ActionEvent ae) -> {
            if (registroUsuarios.TxtContrasenia().getText().length() > 7) {
                if (this.modeloUsuarios.guardar(0,
                        registroUsuarios.TxtUser().getText(),
                        registroUsuarios.TxtContrasenia().getText(),
                        registroUsuarios.TxtNombre().getText(),
                        registroUsuarios.ComboCargo().getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(null, "Agregado Correctamente");
                    registroUsuarios.setVisible(false);
                    cargarDatosUsuarios("");
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe");

                }
            } else {
                JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos 8 caracteres");

            }
        });
        this.registroUsuarios.BtnModificar().addActionListener((ActionEvent ae) -> {
            if (registroUsuarios.TxtContrasenia().getText().length() > 7 || registroUsuarios.TxtContrasenia().getText().length() == 0) {
                if (this.modeloUsuarios.guardar(Integer.parseInt(registroUsuarios.getTitle()),
                        registroUsuarios.TxtUser().getText(),
                        registroUsuarios.TxtContrasenia().getText(),
                        registroUsuarios.TxtNombre().getText(),
                        registroUsuarios.ComboCargo().getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(null, "Modificado Correctamente");
                    registroUsuarios.setVisible(false);
                    cargarDatosUsuarios("");
                } else {
                    JOptionPane.showMessageDialog(null, "No se Pudo modificar usuario");

                }

            } else {
                JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos 8 caracteres");
            }
        });
    }
    /* FIN CONTROL DE USUARIOS*/

    /* CONTROL CLIENTES */
    private void controladorClientes() {

        this.panelClientes.TablaClientes().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {

                System.out.println(panelClientes.TablaClientes().getValueAt(
                        panelClientes.TablaClientes().getSelectedRow(),
                        0));

            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        this.panelClientes.BtnActualizar().addActionListener((ActionEvent ae) -> {
            panelClientes.TxtBusqueda().setText("");
            cargarDatosClientes("");
        });

        this.panelClientes.BtnBuscar().addActionListener((ActionEvent ae) -> {
            cargarDatosClientes(panelClientes.TxtBusqueda().getText());
        });
        this.panelClientes.BtnEditar().addActionListener((ActionEvent ae) -> {
            if (panelClientes.TablaClientes().getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
            } else {
                registroClientes.setVisible(true);
                registroClientes.setLocationRelativeTo(null);
                registroClientes.BtnAgregar().setVisible(false);
                registroClientes.BtnModificar().setVisible(true);

                registroClientes.TxtNombre().setText(
                        panelClientes.TablaClientes().getValueAt(panelClientes.TablaClientes().getSelectedRow(), 1).toString());
                registroClientes.txtCarnet().setText(
                        panelClientes.TablaClientes().getValueAt(panelClientes.TablaClientes().getSelectedRow(), 2).toString());
                registroClientes.txtTelefono().setText(
                        panelClientes.TablaClientes().getValueAt(panelClientes.TablaClientes().getSelectedRow(), 3).toString());
                registroClientes.setTitle(
                        panelClientes.TablaClientes().getValueAt(panelClientes.TablaClientes().getSelectedRow(), 0).toString());
                registroClientes.txtDireccion().setText(
                        panelClientes.TablaClientes().getValueAt(panelClientes.TablaClientes().getSelectedRow(), 5).toString());

            }
        });
        this.panelClientes.BtnNuevo().addActionListener((ActionEvent ae) -> {
            registroClientes.setVisible(true);
            registroClientes.setLocationRelativeTo(null);
            registroClientes.BtnAgregar().setVisible(true);
            registroClientes.BtnModificar().setVisible(false);
            registroClientes.setTitle("Nuevo Registro");
            registroClientes.txtCarnet().setText("");
            registroClientes.TxtNombre().setText("");
            registroClientes.txtTelefono().setText("");
            registroClientes.txtDireccion().setText("");
        });
        this.panelClientes.TxtBusqueda().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                cargarDatosClientes(panelClientes.TxtBusqueda().getText());
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        cargarDatosClientes("");
    }

    private void cargarDatosClientes(String txtBusqueda) {
        this.panelClientes.setDatos(this.modeloClientes.getLista(txtBusqueda),
                this.modeloClientes.getTotal());
    }

    private void registroCliente() {
        registroClientes = new RegistroCliente();
        registroClientes.BtnAgregar().addActionListener((ActionEvent ae) -> {
            if (this.modeloClientes.guardar(0,
                    registroClientes.txtCarnet().getText(),
                    registroClientes.txtTelefono().getText(),
                    registroClientes.TxtNombre().getText(),
                    registroClientes.txtDireccion().getText()
            )) {
                JOptionPane.showMessageDialog(null, "Agregado Correctamente");
                registroClientes.setVisible(false);
                cargarDatosClientes(panelClientes.TxtBusqueda().getText());
            } else {
                JOptionPane.showMessageDialog(null, "El Numero de carnet ya fue registrado");

            }
        });
        registroClientes.BtnModificar().addActionListener((ActionEvent ae) -> {
            if (this.modeloClientes.guardar(
                    Integer.parseInt(registroClientes.getTitle()),
                    registroClientes.txtCarnet().getText(),
                    registroClientes.txtTelefono().getText(),
                    registroClientes.TxtNombre().getText(),
                    registroClientes.txtDireccion().getText()
            )) {
                JOptionPane.showMessageDialog(null, "Modificado Correctamente");
                registroClientes.setVisible(false);
                cargarDatosClientes(panelClientes.TxtBusqueda().getText());
            } else {
                JOptionPane.showMessageDialog(null, "No se Pudo modificar cliente");

            }
        });
    }
    /*  FIN CONTROL CLIENTES*/

    /*  CONTROL EQUIPOS */
    private void controladorEquipos() {

        this.panelEquipos.BtnImprimir().addActionListener((ActionEvent ae) -> {
            interfazImpresion.setImpresion(panelOtros);

            interfazImpresion.setVisible(true);
        });
        this.panelEquipos.getComboEstado().addActionListener((ActionEvent ae) -> {
            cargarDatosEquipos(panelEquipos.TxtBusqueda().getText());
        });
        this.panelEquipos.BtnBuscar().addActionListener((ActionEvent ae) -> {
            cargarDatosEquipos(panelEquipos.TxtBusqueda().getText());
        });
        this.panelEquipos.TxtBusqueda().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                cargarDatosEquipos(panelEquipos.TxtBusqueda().getText());
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        //cargarDatosEquipos(panelEquipos.TxtBusqueda().getText());

    }

    private JPanel cargarPanel(JPanel panel) {
        panel.setSize(766, 434);
        return panel;
    }

    private String getTipo() {
        String tipo = "";
        if (panelEquipos.getImpresora().isSelected()) {
            tipo = "impresora";
        }
        if (panelEquipos.getComputadora().isSelected()) {
            tipo = "pc";
        }
        return tipo;
    }

    private String getEstado() {
        String estado = "";
        estado = panelEquipos.getComboEstado().getSelectedItem().toString();
        return estado;
    }

    private void cargarDatosEquipos(String txtBusqueda) {
        String tipo, estado;
        tipo = getTipo();
        estado = getEstado();
        this.panelEquipos.setDatos(this.modeloEquipos.getLista(txtBusqueda, tipo, estado),
                this.modeloEquipos.getTotal());
    }
    /* FIN CONTROL EQUIPOS*/

    /* NUEVO REGISTRO EN NUMERO DE SERVICIO*/
    private void registroNumeroOrden(int _carnet, String _tipo) {
        if (modeloRegistroServicio.guardar(_carnet, _tipo)) {
            if (_tipo.equals("portatil")) {
                Portatiles();
            }
            if (_tipo.equals("impresora")) {
                Impresoras();
            }
            if (_tipo.equals("pc")) {
            }
            if (_tipo.equals("otros")) {
            }
        } else {
            System.out.println("Error al registrar");
        }
    }

    private void Portatiles() {
        String datosServicio[] = modeloRegistroServicio.getUltimoRegistro();
        Date fechaParseada = null;
        try {
            fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(datosServicio[0]);
        } catch (ParseException ex) {
            System.out.println("Error a parsear Fecha");
        }
        registroPortatil.getFechaRecepcion().setDate(fechaParseada);
        registroPortatil.getHoraRecepcion().setText(datosServicio[1]);
        registroPortatil.getTxtCarnet().setText(datosServicio[2]);
        registroPortatil.getTxtNombre().setText(datosServicio[3]);
        registroPortatil.getTxtDireccion().setText(datosServicio[4]);
        registroPortatil.getTxtCelular().setText(datosServicio[5]);
        registroPortatil.getNumeroOrden().setText(datosServicio[6]);
        registroPortatil.setVisible(true);
        registroPortatil.getBtnGuardar().addActionListener((ActionEvent ae) -> {
            String id = registroPortatil.getNumeroOrden().getText();
            
            String fecha_registro = df.format(registroPortatil.getFechaRecepcion().getDate())+" "
                    +registroPortatil.getHoraRecepcion().getText();
            String fecha_entrega = df.format(registroPortatil.getFechaEntrega().getDate())+" "
                    +registroPortatil.getHoraEntrega().getText();
            String costo_repuesto= registroPortatil.getTxtRepuesto().getText();
            String costo_servicio= registroPortatil.getTxtServicio().getText();
            
            String marca = registroPortatil.getTxtMarca().getText();
            String modelo = registroPortatil.getTxtModelo().getText();
            String numero_serie = registroPortatil.getTxtSeriePc().getText();
            String cargador="";
            if(registroPortatil.getCargadorSI().isSelected()){
                cargador ="SI";
            }
            if(registroPortatil.getCargadorNO().isSelected()){
                cargador ="NO";
            }
            String serie_cargador="";
            if(cargador.equals("SI")){
                serie_cargador = registroPortatil.getSerieCargador().getText();
            }
            String descripcion= registroPortatil.getTxtTrabajo().getText();
            
            
            if(modeloRegistroServicio.modificarServicio(id,fecha_registro, fecha_entrega, costo_repuesto, costo_servicio)
                    &&
                    modeloRegistroServicio.guardarPortatil(id, marca, modelo, numero_serie, cargador, serie_cargador, descripcion))
            {
                System.out.println("Registrado Correctamente");
            }else{
                System.out.println("Error al Registrar el equipo");
            }
        });
    }
    private void Impresoras(){
        String datosServicio[] = modeloRegistroServicio.getUltimoRegistro();
        Date fechaParseada = null;
        try {
            fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(datosServicio[0]);
        } catch (ParseException ex) {
            System.out.println("Error a parsear Fecha");
        }
        registroImpresora.getFechaRecepcion().setDate(fechaParseada);
        registroImpresora.getHoraRecepcion().setText(datosServicio[1]);
        registroImpresora.getTxtCarnet().setText(datosServicio[2]);
        registroImpresora.getTxtNombre().setText(datosServicio[3]);
        registroImpresora.getTxtDireccion().setText(datosServicio[4]);
        registroImpresora.getTxtCelular().setText(datosServicio[5]);
        registroImpresora.getTxtNumeroOrden().setText(datosServicio[6]);
        registroImpresora.setVisible(true);
    }

}
