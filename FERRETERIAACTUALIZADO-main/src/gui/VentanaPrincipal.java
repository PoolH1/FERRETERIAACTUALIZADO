package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import Arreglo.ArregloVentas;
import Arreglo.ArrayProducto;
import Clase.Venta;
import Clase.Producto;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtId, txtNombre, txtCant;
    private JComboBox<String> cboProducto;
    private JTextField txtPrecio;
    private JTextArea txtS;
    private JButton btnAdicionar, btnBuscar, btnEliminar, btnModificar, btnReportar;
    private JButton btnProductos, btnConsultas;
    private JCheckBox chkUsarBD;
    private ArregloVentas av;
    private ArrayProducto ap;

    public VentanaPrincipal() {
        super("Ferretería - Sistema de Ventas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 550);
        setLayout(null);
        setLocationRelativeTo(null);

        // Inicializar arreglos
        av = new ArregloVentas(); // Por defecto usar BD
        ap = new ArrayProducto();

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBounds(10, 10, 680, 30);
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(panelSuperior);

        chkUsarBD = new JCheckBox("Usar Base de Datos", true);
        chkUsarBD.addActionListener(this);
        panelSuperior.add(chkUsarBD);

        btnProductos = new JButton("Gestionar Productos");
        btnProductos.addActionListener(this);
        panelSuperior.add(btnProductos);

        btnConsultas = new JButton("Consultas");
        btnConsultas.addActionListener(this);
        panelSuperior.add(btnConsultas);

        // Campos de entrada
        JLabel lblId = new JLabel("ID Venta:");
        lblId.setBounds(20, 60, 70, 25);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(100, 60, 80, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Cliente:");
        lblNombre.setBounds(200, 60, 70, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(270, 60, 180, 25);
        add(txtNombre);

        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(20, 100, 70, 25);
        add(lblProducto);
        cboProducto = new JComboBox<>();
        cboProducto.setBounds(100, 100, 200, 25);
        cboProducto.addActionListener(this);
        add(cboProducto);

        JButton btnRefrescar = new JButton("↻");
        btnRefrescar.setBounds(305, 100, 45, 25);
        btnRefrescar.addActionListener(e -> cargarProductos());
        add(btnRefrescar);

        JLabel lblCant = new JLabel("Cantidad:");
        lblCant.setBounds(370, 100, 70, 25);
        add(lblCant);
        txtCant = new JTextField();
        txtCant.setBounds(440, 100, 80, 25);
        add(txtCant);

        JLabel lblPrecio = new JLabel("Precio Unit:");
        lblPrecio.setBounds(540, 100, 70, 25);
        add(lblPrecio);
        txtPrecio = new JTextField();
        txtPrecio.setEditable(false);
        txtPrecio.setBounds(615, 100, 80, 25);
        add(txtPrecio);

        // Botones de operaciones
        btnAdicionar = new JButton("Registrar Venta");
        btnAdicionar.setBounds(20, 150, 130, 30);
        btnAdicionar.addActionListener(this);
        add(btnAdicionar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(160, 150, 100, 30);
        btnBuscar.addActionListener(this);
        add(btnBuscar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(270, 150, 100, 30);
        btnEliminar.addActionListener(this);
        add(btnEliminar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(380, 150, 100, 30);
        btnModificar.addActionListener(this);
        add(btnModificar);

        btnReportar = new JButton("Listar Todo");
        btnReportar.setBounds(490, 150, 120, 30);
        btnReportar.addActionListener(this);
        add(btnReportar);

        // Área de texto
        txtS = new JTextArea();
        txtS.setEditable(false);
        txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtS);
        scroll.setBounds(20, 200, 670, 300);
        add(scroll);

        // Cargar productos al iniciar
        cargarProductos();  // ← VERIFICAR QUE ESTA LÍNEA ESTÉ AQUÍ
    }

    private void cargarProductos() {
        cboProducto.removeAllItems();
        cboProducto.addItem("-- Seleccione un producto --");
        
        ArrayProducto ap = new ArrayProducto();
        ArrayList<Producto> productos = ap.listarProductos();
        
        // AGREGAR ESTO PARA DEPURAR
        System.out.println("Total productos cargados: " + productos.size());
        
        for (Producto p : productos) {
            // AGREGAR ESTO PARA DEPURAR
            System.out.println("Cargando: " + p.getCodProd() + " - " + p.getNombre());
            
            cboProducto.addItem(p.getCodProd() + " - " + p.getNombre());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chkUsarBD) {
            av.setUsarBD(chkUsarBD.isSelected());
            String msg = chkUsarBD.isSelected() ? 
                "Ahora usando Base de Datos" : "Ahora usando memoria local";
            JOptionPane.showMessageDialog(this, msg);
        } else if (e.getSource() == btnProductos) {
            abrirVentanaProductos();
        } else if (e.getSource() == btnConsultas) {
            abrirVentanaConsultas();
        } else if (e.getSource() == cboProducto) {
            actualizarPrecio();
        } else if (e.getSource() == btnAdicionar) {
            adicionarVenta();
        } else if (e.getSource() == btnBuscar) {
            buscarVenta();
        } else if (e.getSource() == btnEliminar) {
            eliminarVenta();
        } else if (e.getSource() == btnModificar) {
            modificarVenta();
        } else if (e.getSource() == btnReportar) {
            reportarVentas();
        }
    }

    private void actualizarPrecio() {
        String seleccion = (String) cboProducto.getSelectedItem();
        if (seleccion != null && !seleccion.startsWith("--")) {
            String codigo = seleccion.split(" - ")[0];
            ArrayList<Producto> lista = ap.consultarPorCodigo(codigo);
            if (!lista.isEmpty()) {
                txtPrecio.setText(String.valueOf(lista.get(0).getPrecio()));
            }
        } else {
            txtPrecio.setText("");
        }
    }

    private void abrirVentanaProductos() {
        VentanaProductos vp = new VentanaProductos();
        vp.setVisible(true);
    }

    private void abrirVentanaConsultas() {
        JOptionPane.showMessageDialog(this, 
            "Ventana de Consultas - En desarrollo\n" +
            "Aquí podrá consultar productos y ventas", 
            "Consultas", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void adicionarVenta() {
        String sId = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String seleccion = (String) cboProducto.getSelectedItem();
        String sCant = txtCant.getText().trim();
        String sPreuni = txtPrecio.getText().trim();

        if (sId.isEmpty() || nombre.isEmpty() || seleccion == null || 
            seleccion.startsWith("--") || sCant.isEmpty() || sPreuni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return;
        }

        int id, cant;
        double preuni;
        try {
            id = Integer.parseInt(sId);
            cant = Integer.parseInt(sCant);
            preuni = Double.parseDouble(sPreuni);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID, Cantidad y Precio deben ser numéricos válidos");
            return;
        }

        Venta vExistente = av.buscar(id);
        if (vExistente != null) {
            JOptionPane.showMessageDialog(this, "Ya existe una venta con el ID: " + id);
            return;
        }

        String codigo = seleccion.split(" - ")[0];
        String nombreProd = seleccion.split(" - ")[1];

        // Verificar stock
        ArrayList<Producto> listaProd = ap.consultarPorCodigo(codigo);
        if (!listaProd.isEmpty()) {
            Producto prod = listaProd.get(0);
            if (prod.getStock() < cant) {
                JOptionPane.showMessageDialog(this, 
                    "Stock insuficiente. Stock disponible: " + prod.getStock());
                return;
            }
            
            // Actualizar stock
            ap.actualizarStock(codigo, prod.getStock() - cant);
        }

        Venta v = new Venta(id, nombre, codigo, nombreProd, cant, preuni, new java.util.Date());
        av.adicionar(v);
        JOptionPane.showMessageDialog(this, "Venta registrada correctamente");

        reportarVentas();
        limpiarCampos();
    }

    private void buscarVenta() {
        String s = txtId.getText().trim();
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID a buscar");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido");
            return;
        }

        Venta v = av.buscar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "No se encontró venta con ID: " + id);
        } else {
            txtS.setText("Venta encontrada:\n" + 
                         "ID: " + v.getIden() + "\n" +
                         "Cliente: " + v.getNombreCliente() + "\n" +
                         "Producto: " + v.getNombreProd() + "\n" +
                         "Cantidad: " + v.getCant() + "\n" +
                         "Precio: " + v.getPreuni() + "\n" +
                         "Total: " + v.total());
        }
    }

    private void eliminarVenta() {
        String s = txtId.getText().trim();
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID a eliminar");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido");
            return;
        }

        Venta v = av.buscar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "No se encontró venta con ID: " + id);
            return;
        }

        int r = JOptionPane.showConfirmDialog(this, "¿Eliminar esta venta?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            av.eliminar(v);
            JOptionPane.showMessageDialog(this, "Venta eliminada correctamente");
            reportarVentas();
            limpiarCampos();
        }
    }

    private void modificarVenta() {
        JOptionPane.showMessageDialog(this, "Función en desarrollo");
    }

    private void reportarVentas() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-8s %-20s %-20s %-8s %-10s %-10s\n", 
            "ID", "Cliente", "Producto", "Cant", "Precio", "Total"));
        sb.append("=".repeat(80)).append("\n");
        
        ArrayList<Venta> ventas = av.listarVentas();
        for (Venta v : ventas) {
            sb.append(String.format("%-8d %-20s %-20s %-8d %-10.2f %-10.2f\n",
                v.getIden(),
                v.getNombreCliente(),
                v.getNombreProd(),
                v.getCant(),
                v.getPreuni(),
                v.total()));
        }
        txtS.setText(sb.toString());
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        cboProducto.setSelectedIndex(0);
        txtCant.setText("");
        txtPrecio.setText("");
        txtId.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vp = new VentanaPrincipal();
            vp.setVisible(true);
        });
    }
    
    
}