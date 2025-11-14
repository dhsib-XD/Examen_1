/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;

import examen.AppGeneral;
import examen.Movie;
import examen.RentItem;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class DialogRentarItem extends JDialog {

    private JTextField txtCodigo;
    private JTextField txtDias;
    private JButton btnBuscar;
    private JButton btnCalcular;
    private JButton btnCancelar;
    private JLabel lblImagenPreview;
    private JTextArea txtInfoItem;

    private final Color FONDO_NEGRO = new Color(10, 10, 10);
    private final Color BLUE_ELEGANTE = new Color(30, 144, 255);
    private ArrayList<RentItem> items = AppGeneral.getItems();
    private RentItem itemEncontrado;

    public DialogRentarItem(Frame owner) {
        super(owner, "Rentar Ítem", true);

        setSize(600, 600);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(FONDO_NEGRO);

        JLabel lblTitulo = new JLabel("Rentar ítem", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBackground(FONDO_NEGRO);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblCodigo = crearLabel("Código del ítem:");
        panelCentro.add(lblCodigo, gbc);

        gbc.gridx = 1;
        txtCodigo = crearTextField();
        panelCentro.add(txtCodigo, gbc);

        gbc.gridx = 2;
        btnBuscar = crearBoton("Buscar");
        panelCentro.add(btnBuscar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        JLabel lblInfo = crearLabel("Datos del ítem:");
        panelCentro.add(lblInfo, gbc);

        gbc.gridy++;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        JPanel panelInfo = new JPanel(new BorderLayout(8, 8));
        panelInfo.setBackground(FONDO_NEGRO);

        txtInfoItem = new JTextArea();
        txtInfoItem.setEditable(false);
        txtInfoItem.setOpaque(false);
        txtInfoItem.setForeground(Color.WHITE);
        txtInfoItem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtInfoItem.setBorder(BorderFactory.createLineBorder(BLUE_ELEGANTE, 1));
        txtInfoItem.setText("Ingrese un codigo.");

        JScrollPane scrollInfo = new JScrollPane(txtInfoItem);
        scrollInfo.setBorder(null);
        scrollInfo.getViewport().setBackground(FONDO_NEGRO);

        lblImagenPreview = new JLabel("Sin imagen", SwingConstants.CENTER);
        lblImagenPreview.setForeground(Color.LIGHT_GRAY);
        lblImagenPreview.setPreferredSize(new Dimension(150, 180));
        lblImagenPreview.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        panelInfo.add(scrollInfo, BorderLayout.CENTER);
        panelInfo.add(lblImagenPreview, BorderLayout.EAST);

        panelCentro.add(panelInfo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblDias = crearLabel("Dias de renta:");
        panelCentro.add(lblDias, gbc);

        gbc.gridx = 1;
        txtDias = crearTextField();
        panelCentro.add(txtDias, gbc);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelInferior.setBackground(FONDO_NEGRO);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

        btnCalcular = crearBoton("Calcular renta");
        btnCancelar = crearBoton("Cancelar");

        panelInferior.add(btnCalcular);
        panelInferior.add(btnCancelar);

        add(panelInferior, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> {

            String codigoStr = txtCodigo.getText().trim();
            if (codigoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un código primero.", "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int codigoBuscado;
            try {
                codigoBuscado = Integer.parseInt(codigoStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El código debe ser numérico.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            itemEncontrado = null;
            for (RentItem ri : items) {    
                if (ri.getCodigoItem() == codigoBuscado) {
                    itemEncontrado = ri;
                    break;
                }
            }

            if (itemEncontrado == null) {
                JOptionPane.showMessageDialog(this, "Item No Existe");
                txtInfoItem.setText("");
                lblImagenPreview.setIcon(null);
                lblImagenPreview.setText("Sin imagen");
                return;
            }

            String tipo;
            String estado = "";

            if (itemEncontrado instanceof Movie) {
                tipo = "Movie";
                estado = ((Movie) itemEncontrado).getEstado();
            } else if (itemEncontrado instanceof Game) {
                tipo = "Game";
            } else {
                tipo = "Otro";
            }

            txtInfoItem.setText("Código: " + itemEncontrado.getCodigoItem() + "\n"
                    + "Nombre: " + itemEncontrado.getNombreItem() + "\n"
                    + "Tipo: " + tipo + "\n"
                    + "Precio base: Lps " + itemEncontrado.getBaseRenta() + "\n"
                    + (tipo.equals("Movie") ? "Estado: " + estado : "")
            );

            // Imagen
            ImageIcon icon = itemEncontrado.getImagen();
            if (icon != null) {
                Image img = icon.getImage().getScaledInstance(150, 180, Image.SCALE_SMOOTH);
                lblImagenPreview.setIcon(new ImageIcon(img));
                lblImagenPreview.setText("");
            } else {
                lblImagenPreview.setIcon(null);
                lblImagenPreview.setText("Sin imagen");
            }
        });

        btnCalcular.addActionListener(e -> {

            if (itemEncontrado == null) {
                JOptionPane.showMessageDialog(this,
                        "Primero debe buscar y seleccionar un ítem.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String diasStr = txtDias.getText().trim();
            if (diasStr.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese la cantidad de días.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int dias;
            try {
                dias = Integer.parseInt(diasStr);
                if (dias <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Los días deben ser mayores que 0.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese un número válido de días.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double total = itemEncontrado.pagoRenta(dias);

            JOptionPane.showMessageDialog(this,
                    "Ítem: " + itemEncontrado.getNombreItem() + "\n"
                    + "Días de renta: " + dias + "\n"
                    + "Monto total: Lps " + total,
                    "Total de la renta",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        btnCancelar.addActionListener(e -> dispose());
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
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BLUE_ELEGANTE, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return txt;
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(FONDO_NEGRO);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BLUE_ELEGANTE, 2),
                BorderFactory.createEmptyBorder(6, 16, 6, 16)
        ));
        return btn;
    }

    private void buscarItemVisual(String codigo, String nombre, String tipo, double precio, String estado) {

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un código primero.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Código: ").append(codigo).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Tipo: ").append(tipo).append("\n");
        sb.append("Precio base: Lps ").append(precio).append("\n");

        if ("Movie".equals(tipo)) {
            sb.append("Estado: ").append(estado).append("\n");
        }

        txtInfoItem.setText(sb.toString());
    }

    private void calcularRentaVisual() {
        String codigo = txtCodigo.getText().trim();
        String diasStr = txtDias.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Primero debe buscar un ítem por código.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (diasStr.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese la cantidad de días de renta.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int dias;
        try {
            dias = Integer.parseInt(diasStr);
            if (dias <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Los días deben ser mayores que 0.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un número válido de días.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Simulación visual\n\n"
                + "Código: " + codigo + "\n"
                + "Días de renta: " + dias + "\n"
                + "Monto total: aquí usarías item.pagoRenta(días).",
                "Total de la renta",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {

    }
}
