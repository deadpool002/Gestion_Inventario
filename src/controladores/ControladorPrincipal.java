/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import interfaces.InterfazPrincipal;
import interfaces.RegistroCliente;
import interfaces.RegistroImpresora;
import interfaces.RegistroPc;
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
    RegistroPc registroPc;
    RegistroImpresora registroImpresora;
    ModeloRegistroServicio modeloRegistroServicio;

    DateFormat df;

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
        registroPc = new RegistroPc();
        modeloRegistroServicio = new ModeloRegistroServicio();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.interfazLogin.btnIngresar())) {
            validacionDatos();
        }
        if (ae.getSource().equals(this.interfazPrincipal.getMenuEscritorio())) {
            if (panelClientes.TablaClientes().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un Cliente");
            } else {
                registroNumeroOrden(Integer.parseInt(panelClientes.TablaClientes().getValueAt(
                        panelClientes.TablaClientes().getSelectedRow(),
                        0).toString()), "pc");
            }
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
            if (panelClientes.TablaClientes().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un Cliente");
            } else {
                registroNumeroOrden(Integer.parseInt(panelClientes.TablaClientes().getValueAt(
                        panelClientes.TablaClientes().getSelectedRow(),
                        0).toString()), "otros");
            }
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
                Pcs();
            }
            if (_tipo.equals("otros")) {
            }
        } else {
            System.out.println("Error al registrar");
        }
    }

    private void Portatiles() {
        String datosServicio[] = modeloRegistroServicio.getUltimoRegistro();

        registroPortatil.getFechaRecepcion().setDate(parseDate(datosServicio[0]));
        registroPortatil.getHoraRecepcion().setText(datosServicio[1]);
        registroPortatil.getTxtCarnet().setText(datosServicio[2]);
        registroPortatil.getTxtNombre().setText(datosServicio[3]);
        registroPortatil.getTxtDireccion().setText(datosServicio[4]);
        registroPortatil.getTxtCelular().setText(datosServicio[5]);
        registroPortatil.getNumeroOrden().setText(datosServicio[6]);
        registroPortatil.setVisible(true);
        registroPortatil.getBtnGuardar().setVisible(true);
        /*LIMPIAR INTERFAZ DE REGISTRO PORTATIL*/
        registroPortatil.getFechaEntrega().setDate(null);
        registroPortatil.getHoraEntrega().setText("00:00");
        registroPortatil.getTxtRepuesto().setText("0");
        registroPortatil.getTxtServicio().setText("0");
        registroPortatil.getTxtMarca().setText("");
        registroPortatil.getTxtModelo().setText("");
        registroPortatil.getTxtSeriePc().setText("");
        registroPortatil.getCargadorNO().isSelected();

        registroPortatil.getSerieCargador().setText("");
        registroPortatil.getTxtTrabajo().setText("\n\nOBSERVACIONES:");

        registroPortatil.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String id = registroPortatil.getNumeroOrden().getText();

                String fecha_registro = df.format(registroPortatil.getFechaRecepcion().getDate()) + " "
                        + registroPortatil.getHoraRecepcion().getText();
                String fecha_entrega = df.format(registroPortatil.getFechaEntrega().getDate()) + " "
                        + registroPortatil.getHoraEntrega().getText();
                String costo_repuesto = registroPortatil.getTxtRepuesto().getText();
                String costo_servicio = registroPortatil.getTxtServicio().getText();

                String marca = registroPortatil.getTxtMarca().getText();
                String modelo = registroPortatil.getTxtModelo().getText();
                String numero_serie = registroPortatil.getTxtSeriePc().getText();
                String cargador = "";
                if (registroPortatil.getCargadorSI().isSelected()) {
                    cargador = "SI";
                }
                if (registroPortatil.getCargadorNO().isSelected()) {
                    cargador = "NO";
                }
                String serie_cargador = "";
                if (cargador.equals("SI")) {
                    serie_cargador = registroPortatil.getSerieCargador().getText();
                }
                String descripcion = registroPortatil.getTxtTrabajo().getText();

                if (modeloRegistroServicio.modificarServicio(id, fecha_registro, fecha_entrega, costo_repuesto, costo_servicio)
                        && modeloRegistroServicio.guardarPortatil(id, marca, modelo, numero_serie, cargador, serie_cargador, descripcion)) {
                    System.out.println("Registrado Correctamente");
                    cargarDatosPortatil(id);
                } else {
                    System.out.println("Error al Registrar el equipo");
                }
            }

        });
        registroPortatil.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (modeloRegistroServicio.updateLaptop(registroPortatil.getNumeroOrden().getText(),
                        registroPortatil.getTxtMarca().getText(),
                        registroPortatil.getTxtModelo().getText(),
                        registroPortatil.getTxtSeriePc().getText(),
                        registroPortatil.getCargadorSI().isSelected() ? "SI" : "NO",
                        registroPortatil.getCargadorSI().isSelected() ? registroPortatil.getSerieCargador().getText() : "",
                        registroPortatil.getTxtTrabajo().getText())
                        && modeloRegistroServicio.modificarServicio(
                                registroPortatil.getNumeroOrden().getText(),
                                df.format(registroPortatil.getFechaRecepcion().getDate()) + " "
                                + registroPortatil.getHoraRecepcion().getText(),
                                df.format(registroPortatil.getFechaEntrega().getDate()) + " "
                                + registroPortatil.getHoraEntrega().getText(),
                                registroPortatil.getTxtRepuesto().getText(),
                                registroPortatil.getTxtServicio().getText())) {
                    cargarDatosPortatil(registroPortatil.getNumeroOrden().getText());
                } else {
                    System.out.println("Error al modificar Datos");
                }
            }
        });
        registroPortatil.getBtnImprimir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] portatil = modeloRegistroServicio.getPortatil(registroPortatil.getNumeroOrden().getText());
                panelPortatil.fillForm(registroPortatil.getNumeroOrden().getText(),
                        portatil[0] + " " + portatil[1],
                        portatil[2] + " " + portatil[3],
                        portatil[4], portatil[5],
                        portatil[6], portatil[7],
                        portatil[8], portatil[9],
                        portatil[10], portatil[11],
                        portatil[12], portatil[13],
                        portatil[14], portatil[15]);
                interfazImpresion.setImpresion(panelPortatil);
                interfazImpresion.setVisible(true);
            }
        });
    }

    private void cargarDatosPortatil(String id) {
        String[] portatil = modeloRegistroServicio.getPortatil(id);
        registroPortatil.getBtnGuardar().setVisible(false);

        registroPortatil.getFechaRecepcion().setDate(parseDate(portatil[0]));
        registroPortatil.getHoraRecepcion().setText(portatil[1]);
        registroPortatil.getFechaEntrega().setDate(parseDate(portatil[2]));
        registroPortatil.getHoraEntrega().setText(portatil[3]);
        registroPortatil.getTxtRepuesto().setText(portatil[4]);
        registroPortatil.getTxtServicio().setText(portatil[5]);
        registroPortatil.getTxtCarnet().setText(portatil[6]);
        registroPortatil.getTxtNombre().setText(portatil[7]);
        registroPortatil.getTxtCelular().setText(portatil[8]);
        registroPortatil.getTxtDireccion().setText(portatil[9]);
        registroPortatil.getTxtMarca().setText(portatil[10]);
        registroPortatil.getTxtModelo().setText(portatil[11]);
        registroPortatil.getTxtSeriePc().setText(portatil[12]);
        if (portatil[13].equals("SI")) {
            registroPortatil.getCargadorSI().isSelected();
        } else {
            registroPortatil.getCargadorNO().isSelected();
        }
        registroPortatil.getSerieCargador().setText(portatil[14]);
        registroPortatil.getTxtTrabajo().setText(portatil[15]);
        registroPortatil.getNumeroOrden().setText(id);
    }

    private void Impresoras() {
        String datosServicio[] = modeloRegistroServicio.getUltimoRegistro();

        registroImpresora.getFechaRecepcion().setDate(parseDate(datosServicio[0]));
        registroImpresora.getHoraRecepcion().setText(datosServicio[1]);
        registroImpresora.getTxtCarnet().setText(datosServicio[2]);
        registroImpresora.getTxtNombre().setText(datosServicio[3]);
        registroImpresora.getTxtDireccion().setText(datosServicio[4]);
        registroImpresora.getTxtCelular().setText(datosServicio[5]);
        registroImpresora.getTxtNumeroOrden().setText(datosServicio[6]);
        registroImpresora.getBtnGuardar().setVisible(true);
        registroImpresora.setVisible(true);
        registroImpresora.getBtnGuardar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (modeloRegistroServicio.modificarServicio(registroImpresora.getTxtNumeroOrden().getText(),
                        df.format(registroImpresora.getFechaRecepcion().getDate()) + " "
                        + registroImpresora.getHoraRecepcion().getText(),
                        df.format(registroImpresora.getFechaEntrega().getDate()) + " "
                        + registroImpresora.getHoraEntrega().getText(),
                        registroImpresora.getTxtCostoRepuesto().getText(),
                        registroImpresora.getTxtCostoServicio().getText())
                        && modeloRegistroServicio.savePrinter(
                                registroImpresora.getTxtNumeroOrden().getText(),
                                registroImpresora.getTxtMarca().getText(),
                                registroImpresora.getTxtModelo().getText(),
                                registroImpresora.getTxtNroSerie().getText(),
                                registroImpresora.getCartuchoSI().isSelected() ? "SI" : "NO",
                                registroImpresora.getCartuchoSI().isSelected() ? registroImpresora.getTxtColor().getText() : "",
                                registroImpresora.getCartuchoSI().isSelected() ? registroImpresora.getTxtNegro().getText() : "",
                                registroImpresora.getTxtTrabajo().getText())) {
                    System.out.println("Registro Correcto");
                    loadPrinterData(registroImpresora.getTxtNumeroOrden().getText());
                } else {
                    System.out.println("Error al registrar");
                }
            }
        });
        registroImpresora.getBtnModificar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (modeloRegistroServicio.modificarServicio(registroImpresora.getTxtNumeroOrden().getText(),
                        df.format(registroImpresora.getFechaRecepcion().getDate()) + " "
                        + registroImpresora.getHoraRecepcion().getText(),
                        df.format(registroImpresora.getFechaEntrega().getDate()) + " "
                        + registroImpresora.getHoraEntrega().getText(),
                        registroImpresora.getTxtCostoRepuesto().getText(),
                        registroImpresora.getTxtCostoServicio().getText())
                        && modeloRegistroServicio.updatePrinter(
                                registroImpresora.getTxtNumeroOrden().getText(),
                                registroImpresora.getTxtMarca().getText(),
                                registroImpresora.getTxtModelo().getText(),
                                registroImpresora.getTxtNroSerie().getText(),
                                registroImpresora.getCartuchoSI().isSelected() ? "SI" : "NO",
                                registroImpresora.getCartuchoSI().isSelected() ? registroImpresora.getTxtColor().getText() : "",
                                registroImpresora.getCartuchoSI().isSelected() ? registroImpresora.getTxtNegro().getText() : "",
                                registroImpresora.getTxtTrabajo().getText())) {
                    loadPrinterData(registroImpresora.getTxtNumeroOrden().getText());
                } else {
                    System.out.println("Error al modificar");
                }
            }
        });
        registroImpresora.getBtnImprimir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] printer = modeloRegistroServicio.getPrinter(registroImpresora.getTxtNumeroOrden().getText());
                panelImpresora.fillForm(registroImpresora.getTxtNumeroOrden().getText(),
                        printer[0] + " " + printer[1],
                        printer[2] + " " + printer[3],
                        printer[4], printer[5],
                        printer[6], printer[7],
                        printer[8], printer[9],
                        printer[10], printer[11],
                        printer[12], printer[13],
                        printer[14], printer[15], printer[16]);
                interfazImpresion.setImpresion(panelImpresora);
                interfazImpresion.setVisible(true);
            }
        });
    }

    private void loadPrinterData(String id) {
        String[] printer = modeloRegistroServicio.getPrinter(id);
        registroImpresora.getBtnGuardar().setVisible(false);
        registroImpresora.getFechaRecepcion().setDate(parseDate(printer[0]));
        registroImpresora.getHoraRecepcion().setText(printer[1]);
        registroImpresora.getFechaEntrega().setDate(parseDate(printer[2]));
        registroImpresora.getHoraEntrega().setText(printer[3]);
        registroImpresora.getTxtCostoRepuesto().setText(printer[4]);
        registroImpresora.getTxtCostoServicio().setText(printer[5]);
        registroImpresora.getTxtCarnet().setText(printer[6]);
        registroImpresora.getTxtNombre().setText(printer[7]);
        registroImpresora.getTxtCelular().setText(printer[8]);
        registroImpresora.getTxtDireccion().setText(printer[9]);
        registroImpresora.getTxtMarca().setText(printer[10]);
        registroImpresora.getTxtModelo().setText(printer[11]);
        registroImpresora.getTxtNroSerie().setText(printer[12]);
        if (printer[13].equals("SI")) {
            registroImpresora.getCartuchoSI().setSelected(true);
        } else {
            registroImpresora.getCartuchoNO().setSelected(true);
        }
        registroImpresora.getTxtColor().setText(printer[14]);
        registroImpresora.getTxtNegro().setText(printer[15]);
        registroImpresora.getTxtTrabajo().setText(printer[16]);
        registroImpresora.getTxtNumeroOrden().setText(id);

    }

    private void Pcs() {
        String datosServicio[] = modeloRegistroServicio.getUltimoRegistro();

        registroPc.getFechaRecepcion().setDate(parseDate(datosServicio[0]));
        registroPc.getTxtHoraRecepcion().setText(datosServicio[1]);
        registroPc.getTxtCarnet().setText(datosServicio[2]);
        registroPc.getTxtNombre().setText(datosServicio[3]);
        registroPc.getTxtDireccion().setText(datosServicio[4]);
        registroPc.getTxtCelular().setText(datosServicio[5]);
        registroPc.getTxtNumeroOrden().setText(datosServicio[6]);
        registroPc.getBtnGuardar().setVisible(true);
        registroPc.setVisible(true);
        registroPc.getBtnGuardar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (modeloRegistroServicio.modificarServicio(
                        registroPc.getTxtNumeroOrden().getText(),
                        df.format(registroPc.getFechaRecepcion().getDate()) + " "
                        + registroPc.getTxtHoraRecepcion().getText(),
                        df.format(registroPc.getFechaEntrega().getDate()) + " "
                        + registroPc.getTxtHoraEntrega().getText(),
                        registroPc.getTxtCostoRepuesto().getText(),
                        registroPc.getTxtCostoServicio().getText())
                        && modeloRegistroServicio.saveDesktop(
                                registroPc.getTxtNumeroOrden().getText(),
                                registroPc.getTxtTargetaMadre().getText(),
                                registroPc.getTxtProcesador().getText(),
                                registroPc.getTxtMemoriaRam().getText(),
                                registroPc.getTxtDiscoDuro().getText(),
                                registroPc.getTxtTarjetaVideo().getText(),
                                registroPc.getTxtTarjetaSonido().getText(),
                                registroPc.getTxtTarjetaWifi().getText(),
                                registroPc.getTxtTarjetaRed().getText(),
                                registroPc.getTxtQuemador().getText(),
                                registroPc.getMonitorSI().isSelected() ? "SI" : "NO",
                                registroPc.getMonitorSI().isSelected() ? registroPc.getTxtSerieMonitor().getText() : "",
                                registroPc.getTxtCables().getText(),
                                registroPc.getTxtSerieCables().getText(),
                                registroPc.getTxtTrabajo().getText())) {
                    System.out.println("Registro Correcto");
                    loadDesktopData(registroImpresora.getTxtNumeroOrden().getText());
                } else {
                    System.out.println("Error al registrar");
                }
            }
        });
        registroPc.getBtnModificar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (modeloRegistroServicio.modificarServicio(
                        registroPc.getTxtNumeroOrden().getText(),
                        df.format(registroPc.getFechaRecepcion().getDate()) + " "
                        + registroPc.getTxtHoraRecepcion().getText(),
                        df.format(registroPc.getFechaEntrega().getDate()) + " "
                        + registroPc.getTxtHoraEntrega().getText(),
                        registroPc.getTxtCostoRepuesto().getText(),
                        registroPc.getTxtCostoServicio().getText())
                        && modeloRegistroServicio.saveDesktop(
                                registroPc.getTxtNumeroOrden().getText(),
                                registroPc.getTxtTargetaMadre().getText(),
                                registroPc.getTxtProcesador().getText(),
                                registroPc.getTxtMemoriaRam().getText(),
                                registroPc.getTxtDiscoDuro().getText(),
                                registroPc.getTxtTarjetaVideo().getText(),
                                registroPc.getTxtTarjetaSonido().getText(),
                                registroPc.getTxtTarjetaWifi().getText(),
                                registroPc.getTxtTarjetaRed().getText(),
                                registroPc.getTxtQuemador().getText(),
                                registroPc.getMonitorSI().isSelected() ? "SI" : "NO",
                                registroPc.getMonitorSI().isSelected() ? registroPc.getTxtSerieMonitor().getText() : "",
                                registroPc.getTxtCables().getText(),
                                registroPc.getTxtSerieCables().getText(),
                                registroPc.getTxtTrabajo().getText())) {
                    
                    loadDesktopData(registroImpresora.getTxtNumeroOrden().getText());
                } else {
                    System.out.println("Error al registrar");
                }
            }
        });
    }

    private void loadDesktopData(String id) {
        String desktop[]= modeloRegistroServicio.getDesktop(id);
        registroPc.getBtnGuardar().setVisible(false);
        registroPc.getFechaRecepcion().setDate(parseDate(desktop[0]));
        registroPc.getTxtHoraRecepcion().setText(desktop[1]);
        registroPc.getFechaEntrega().setDate(parseDate(desktop[2]));
        registroPc.getTxtHoraEntrega().setText(desktop[3]);
        registroPc.getTxtCostoRepuesto().setText(desktop[4]);
        registroPc.getTxtCostoServicio().setText(desktop[5]);
        registroPc.getTxtCarnet().setText(desktop[6]);
        registroPc.getTxtNombre().setText(desktop[7]);
        registroPc.getTxtCelular().setText(desktop[8]);
        registroPc.getTxtDireccion().setText(desktop[9]);
        registroPc.getTxtTargetaMadre().setText(desktop[10]);
        registroPc.getTxtProcesador().setText(desktop[11]);
        registroPc.getTxtMemoriaRam().setText(desktop[12]);
        registroPc.getTxtDiscoDuro().setText(desktop[13]);
        registroPc.getTxtTarjetaVideo().setText(desktop[14]);
        registroPc.getTxtTarjetaSonido().setText(desktop[15]);
        registroPc.getTxtTarjetaWifi().setText(desktop[16]);
        registroPc.getTxtTarjetaRed().setText(desktop[17]);
        registroPc.getTxtQuemador().setText(desktop[18]);
        if(desktop[19].equals("SI")){
            registroPc.getMonitorSI().setSelected(true);  
        }else{
            registroPc.getMonitorNO().setSelected(true);
        }
        registroPc.getTxtSerieMonitor().setText(desktop[20]);
        registroPc.getTxtCables().setText(desktop[21]);
        registroPc.getTxtSerieCables().setText(desktop[22]);
        registroPc.getTxtTrabajo().setText(desktop[23]);
        
        
    }

    private Date parseDate(String fecha) {
        Date fecha_parseada = null;
        try {
            fecha_parseada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (ParseException ex) {
            System.out.println("Error a parsear Fecha");
        }
        return fecha_parseada;
    }
}
