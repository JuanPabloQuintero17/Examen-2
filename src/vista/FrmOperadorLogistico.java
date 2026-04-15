package vista;

import modelo.*;
import logica.*,

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class FrmOperadorLogistico extends JFrame {

    private JTextField txtCodigo, txtCliente, txtPeso, txtDistancia;
    private JComboBox<String> cbTipo;
    private JTable tabla;
    private DefaultTableModel modelo;

    private GestorEnvios gestor = new GestorEnvios();

    public FrmOperadorLogistico() {

        setTitle("Operador Logístico");
        setSize(700, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ICONOS
        ImageIcon iconoAgregar = new ImageIcon(getClass().getResource("/iconos/agregar.jpeg"));
        JLabel lblAgregar = new JLabel(iconoAgregar);
        lblAgregar.setBounds(20, 5, 64, 64);
        add(lblAgregar);

        ImageIcon iconoEliminar = new ImageIcon(getClass().getResource("/iconos/eliminar.jpeg"));
        JLabel lblEliminar = new JLabel(iconoEliminar);
        lblEliminar.setBounds(90, 5, 64, 64);
        add(lblEliminar);

        // EVENTOS EN ICONOS
        lblAgregar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                guardar();
            }
        });

        lblEliminar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                eliminar();
            }
        });

        // CAMPOS
        addLabel("Número:", 20, 80);
        txtCodigo = addTextField(100, 80);

        addLabel("Cliente:", 20, 110);
        txtCliente = addTextField(100, 110);

        addLabel("Peso (kg):", 20, 140);
        txtPeso = addTextField(100, 140);

        addLabel("Distancia (km):", 205, 140);
        txtDistancia = addTextField(300, 140);

        addLabel("Tipo:", 220, 80);
        cbTipo = new JComboBox<>(new String[]{"Terrestre", "Aéreo", "Marítimo"});
        cbTipo.setBounds(300, 80, 120, 20);
        add(cbTipo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(450, 80, 100, 25);
        add(btnGuardar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(450, 110, 100, 25);
        add(btnEliminar);

        modelo = new DefaultTableModel(
                new String[]{"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"}, 0
        );

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 180, 640, 150);
        add(scroll);

        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());

        setVisible(true);
    }

    private JLabel addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 100, 20);
        add(lbl);
        return lbl;
    }

    private JTextField addTextField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 100, 20);
        add(txt);
        return txt;
    }

    private void guardar() {
        int cod = Integer.parseInt(txtCodigo.getText());
        String cli = txtCliente.getText();
        double peso = Double.parseDouble(txtPeso.getText());
        double dist = Double.parseDouble(txtDistancia.getText());

        Envio envio;

        switch (cbTipo.getSelectedItem().toString()) {
            case "Terrestre":
                envio = new EnvioTerrestre(cod, cli, peso, dist);
                break;
            case "Aéreo":
                envio = new EnvioAereo(cod, cli, peso, dist);
                break;
            default:
                envio = new EnvioMaritimo(cod, cli, peso, dist);
        }

        gestor.agregar(envio);
        actualizarTabla();
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            int codigo = (int) modelo.getValueAt(fila, 1);
            gestor.eliminar(codigo);
            actualizarTabla();
        }
    }

    private void actualizarTabla() {
        modelo.setRowCount(0);
        for (Envio e : gestor.getLista()) {
            modelo.addRow(new Object[]{
                    e.getTipo(),
                    e.getCodigo(),
                    e.getCliente(),
                    e.getPeso(),
                    e.getDistancia(),
                    e.calcularCosto()
            });
        }
    }
}
