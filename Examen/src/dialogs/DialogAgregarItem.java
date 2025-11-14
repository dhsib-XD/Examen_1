/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;

import examen.AppGeneral;
import examen.Game;
import examen.Movie;
import examen.RentItem;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ferna
 */
public class DialogAgregarItem extends JDialog {

    private JComboBox<String> cboTipo;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JLabel lblImagenPreview;
    private JButton btnSeleccionarImagen;
    private JButton btnGuardar;
    private JButton btnCancelar;

    private ImageIcon imagenSeleccionada;
    private RentItem itemSeleccionado;
    private ArrayList<RentItem> items=AppGeneral.getItems();

    private final Color NEGRO = new Color(10, 10, 10);
    private final Color AZUL = new Color(30, 144, 255);

    public DialogAgregarItem(Frame owner) {
        super(owner, "Agregar Ítem", true);

        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(NEGRO);

        JLabel lblTitulo = new JLabel("Agregar nuevo ítem", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBackground(NEGRO);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblTipo = crearLabel("Tipo de ítem:");
        panelCentro.add(lblTipo, gbc);

        gbc.gridx = 1;
        cboTipo = new JComboBox<>(new String[]{"Movie", "Game"});
        estilizarCombo(cboTipo);
        panelCentro.add(cboTipo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblCodigo = crearLabel("Código:");
        panelCentro.add(lblCodigo, gbc);

        gbc.gridx = 1;
        txtCodigo = crearTextField();
        panelCentro.add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblNombre = crearLabel("Nombre:");
        panelCentro.add(lblNombre, gbc);

        gbc.gridx = 1;
        txtNombre = crearTextField();
        panelCentro.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblPrecio = crearLabel("Precio base (Lps):");
        panelCentro.add(lblPrecio, gbc);

        gbc.gridx = 1;
        txtPrecio = crearTextField();
        panelCentro.add(txtPrecio, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblImagen = crearLabel("Imagen:");
        panelCentro.add(lblImagen, gbc);

        gbc.gridx = 1;
        JPanel panelImagen = new JPanel(new BorderLayout(5, 5));
        panelImagen.setBackground(NEGRO);

        btnSeleccionarImagen = crearBoton("Seleccionar imagen");
        panelImagen.add(btnSeleccionarImagen, BorderLayout.NORTH);

        lblImagenPreview = new JLabel("Sin imagen", SwingConstants.CENTER);
        lblImagenPreview.setForeground(Color.LIGHT_GRAY);
        lblImagenPreview.setPreferredSize(new Dimension(150, 180));
        lblImagenPreview.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panelImagen.add(lblImagenPreview, BorderLayout.CENTER);

        panelCentro.add(panelImagen, gbc);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelInferior.setBackground(NEGRO);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

        btnGuardar = crearBoton("Guardar");
        btnCancelar = crearBoton("Cancelar");

        panelInferior.add(btnGuardar);
        panelInferior.add(btnCancelar);

        add(panelInferior, BorderLayout.SOUTH);

        btnSeleccionarImagen.addActionListener(e -> seleccionarImagen());
        btnCancelar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> {
            String tipo = (String) cboTipo.getSelectedItem();
            String codigoStr = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String precioStr = txtPrecio.getText().trim();

            if (codigoStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "No puedes dejar campos vacios.",
                        "Datos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int codigo;
            double precioBase;

            try {
                codigo = Integer.parseInt(codigoStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "El código debe ser un valor numerico y entero.",
                        "Código inválido",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {
                precioBase = Double.parseDouble(precioStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "El precio base debe ser un número.",
                        "Precio inválido",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if ("Game".equals(tipo)) {
                precioBase = 20.0;
            }

            if (imagenSeleccionada == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Debes seleccionar una imagen.",
                        "Imagen requerida",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            for (RentItem ri : items) {
                if (ri.getCodigoItem() == codigo) {  
                    JOptionPane.showMessageDialog(
                            this,
                            "Ya existe un ítem con ese código.",
                            "Código duplicado",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }

            RentItem nuevoItem = null;

            if ("Movie".equals(tipo)) {
                nuevoItem = new Movie(codigo, nombre, precioBase);
            } else if ("Game".equals(tipo)) {
                nuevoItem = new Game(codigo, nombre, precioBase);
            }

            if (nuevoItem == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo crear el ítem.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            nuevoItem.setImagen(imagenSeleccionada);

            items.add(nuevoItem);

            JOptionPane.showMessageDialog(
                    this,
                    "Ítem agregado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose(); 
        });
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return lbl;
    }

    private JTextField crearTextField() {
        JTextField txt = new JTextField(15);
        txt.setBackground(new Color(25, 25, 25));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(AZUL, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return txt;
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(NEGRO);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(AZUL, 2),
                BorderFactory.createEmptyBorder(6, 16, 6, 16)
        ));
        return btn;
    }

    private void estilizarCombo(JComboBox<String> combo) {
        combo.setBackground(new Color(25, 25, 25));
        combo.setForeground(Color.WHITE);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBorder(BorderFactory.createLineBorder(AZUL, 1));
    }

    private void seleccionarImagen() {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Imágenes (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif"
        );
        chooser.setFileFilter(filtro);

        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            String nombre = archivo.getName().toLowerCase();

            if (!(nombre.endsWith(".jpg") || nombre.endsWith(".jpeg")
                    || nombre.endsWith(".png") || nombre.endsWith(".gif"))) {

                JOptionPane.showMessageDialog(
                        this,
                        "Tipo de archivo incompatible.\nSolo se permiten imágenes tipo JPG, JPEG, o PNG",
                        "Archivo inválido",
                        JOptionPane.ERROR_MESSAGE
                );

                imagenSeleccionada = null;
                lblImagenPreview.setIcon(null);
                lblImagenPreview.setText("Sin imagen");
                return;
            }

            imagenSeleccionada = new ImageIcon(archivo.getAbsolutePath());
            Image img = imagenSeleccionada.getImage().getScaledInstance(
                    150, 180, Image.SCALE_SMOOTH
            );
            lblImagenPreview.setIcon(new ImageIcon(img));
            lblImagenPreview.setText("");
        }
    }
}
