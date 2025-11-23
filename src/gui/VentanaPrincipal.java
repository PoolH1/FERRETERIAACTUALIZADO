package gui;

import javax.swing.*;
import java.awt.event.*;
import Arreglo.ArregloVentas;
import Clase.Venta;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
    private JTextField txtId, txtNombre, txtProd, txtCant, txtPrecio;
    private JTextArea txtS;
    private JButton btnAdicionar, btnBuscar, btnEliminar, btnModificar, btnReportar;
    private ArregloVentas av = new ArregloVentas();

    public VentanaPrincipal() {
        super("Ferretería - Ventas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(620, 460);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 50, 25);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(80, 20, 100, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Cliente:");
        lblNombre.setBounds(200, 20, 70, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(270, 20, 150, 25);
        add(txtNombre);

        JLabel lblProd = new JLabel("Producto:");
        lblProd.setBounds(20, 60, 70, 25);
        add(lblProd);
        txtProd = new JTextField();
        txtProd.setBounds(80, 60, 100, 25);
        add(txtProd);

        JLabel lblCant = new JLabel("Cantidad:");
        lblCant.setBounds(200, 60, 70, 25);
        add(lblCant);
        txtCant = new JTextField();
        txtCant.setBounds(270, 60, 80, 25);
        add(txtCant);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(370, 60, 50, 25);
        add(lblPrecio);
        txtPrecio = new JTextField();
        txtPrecio.setBounds(420, 60, 80, 25);
        add(txtPrecio);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(20, 100, 110, 25);
        btnAdicionar.addActionListener(this);
        add(btnAdicionar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(140, 100, 100, 25);
        btnBuscar.addActionListener(this);
        add(btnBuscar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(250, 100, 100, 25);
        btnEliminar.addActionListener(this);
        add(btnEliminar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(360, 100, 100, 25);
        btnModificar.addActionListener(this);
        add(btnModificar);

        btnReportar = new JButton("Reportar");
        btnReportar.setBounds(470, 100, 100, 25);
        btnReportar.addActionListener(this);
        add(btnReportar);

        txtS = new JTextArea();
        txtS.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtS);
        scroll.setBounds(20, 150, 540, 240);
        add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdicionar) {
            adicionarVenta();
        } else if (e.getSource() == btnBuscar) {
            buscarVenta();
        } else if (e.getSource() == btnEliminar) {
            eliminarVenta();
        } else if (e.getSource() == btnModificar) {
            txtS.setText("Función Modificar (pendiente)");
        } else if (e.getSource() == btnReportar) {
            reportarVentas();
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
            JOptionPane.showMessageDialog(this, "ID inválido (ingresa un número entero)");
            return;
        }

        Venta v = av.buscar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "No se encontró venta con ID: " + id);
            return;
        }

        int r = JOptionPane.showConfirmDialog(this, "¿Eliminar esta venta?\n" + v.toString(),
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            av.eliminar(v);
            JOptionPane.showMessageDialog(this, "Venta eliminada correctamente");
            reportarVentas();
        }
    }
    private void adicionarVenta() {
        // TODO: Completar por Humber
    	    String sId = txtId.getText().trim();
    	    String nombre = txtNombre.getText().trim();
    	    String prod = txtProd.getText().trim();
    	    String sCant = txtCant.getText().trim();
    	    String sPreuni = txtPrecio.getText().trim();

    	    if (sId.isEmpty() || nombre.isEmpty() || prod.isEmpty() ||
    	        sCant.isEmpty() || sPreuni.isEmpty()) {
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

    	    Venta v = new Venta(id, nombre, prod, cant, preuni);
    	    av.adicionar(v);
    	    JOptionPane.showMessageDialog(this, "Venta registrada correctamente");

    	    reportarVentas();

    	    txtId.setText("");
    	    txtNombre.setText("");
    	    txtProd.setText("");
    	    txtCant.setText("");
    	    txtPrecio.setText("");
    	    txtId.requestFocus();
    	}

 
    private void buscarVenta() {
        // TODO: Completar por Ced
		String s = txtId.getText().trim();
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID a buscar");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido (ingrese un número entero)");
            return;
        }

        Venta v = av.buscar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "No se encontró venta con ID: " + id);
        } else {
            txtS.setText("Venta encontrada:\n" + 
                         "ID: " + v.getIden() + "\n" +
                         "Cliente: " + v.getNombre() + "\n" +
                         "Producto: " + v.getProd() + "\n" +
                         "Cantidad: " + v.getCant() + "\n" +
                         "Precio: " + v.getPreuni() + "\n" +
                         "Total: " + v.total());
        }
    }

    private void modificarVenta() {
        // TODO: Completar por Richi
    }

    private void reportarVentas() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID\tCliente\tProducto\tCant\tPrecio\tTotal\n");
        for (int i = 0; i < av.tamaño(); i++) {
            Venta v = av.obtener(i);
            sb.append(v.getIden()).append("\t")
              .append(v.getNombre()).append("\t")
              .append(v.getProd()).append("\t")
              .append(v.getCant()).append("\t")
              .append(v.getPreuni()).append("\t")
              .append(v.total()).append("\n");
        }
        txtS.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vp = new VentanaPrincipal();
            vp.setVisible(true);
        });
    }
    
}
