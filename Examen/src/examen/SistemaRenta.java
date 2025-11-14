/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import dialogs.DialogAgregarItem;
import dialogs.DialogRentarItem;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class SistemaRenta extends JFrame {

    private JButton btnAgregar;
    private JButton btnRentar;
    private JButton btnSubmenu;
    private JButton btnImprimir;
    private JButton btnSalir;
    private JLabel lblEstado;

    private final Color AZUL = new Color(30, 144, 255);
    private final Color NEGRO = new Color(10, 10, 10);
    private ArrayList<RentItem> items = AppGeneral.getItems();

    public SistemaRenta() {

        setTitle("Sistema de Renta Multimedia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(NEGRO);
        setResizable(false);

        JLabel lblTitulo = new JLabel("Sistema de Renta Multimedia", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(NEGRO);
        panelCentro.setLayout(new GridLayout(5, 1, 15, 15));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnAgregar = crearBoton("Agregar Ítem");
        btnRentar = crearBoton("Rentar");
        btnSubmenu = crearBoton("Submenú");
        btnImprimir = crearBoton("Imprimir Todo");
        btnSalir = crearBoton("Salir");

        panelCentro.add(btnAgregar);
        panelCentro.add(btnRentar);
        panelCentro.add(btnSubmenu);
        panelCentro.add(btnImprimir);
        panelCentro.add(btnSalir);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(NEGRO);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblEstado = new JLabel("Listo.", SwingConstants.LEFT);
        lblEstado.setForeground(Color.WHITE);
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        add(panelInferior, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            DialogAgregarItem dlg = new DialogAgregarItem(this);
            dlg.setVisible(true);
        });
        btnRentar.addActionListener(e -> {
            DialogRentarItem dlg = new DialogRentarItem(this);
            dlg.setVisible(true);
        });

        btnSubmenu.addActionListener(e -> {

            String codigoStr = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el código del ítem:",
                    "Ejecutar Submenú",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (codigoStr == null || codigoStr.trim().isEmpty()) {
                return;
            }

            int codigo;
            try {
                codigo = Integer.parseInt(codigoStr.trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "El código debe ser numérico.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            RentItem encontrado = null;

            for (RentItem ri : items) {
                if (ri.getCodigoItem() == codigo) {
                    encontrado = ri;
                    break;
                }
            }

            if (encontrado == null) {
                JOptionPane.showMessageDialog(this, "Item No Existe");
                return;
            }


            if (encontrado instanceof MenuActions) {
                ((MenuActions) encontrado).subMenu();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Este ítem no tiene submenú disponible.",
                        "Sin submenú",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        btnImprimir.addActionListener(e -> setEstado("Opción seleccionada: Imprimir Todo"));
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(NEGRO);
        btn.setFocusPainted(false);

        btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(AZUL, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        return btn;
    }

    private void setEstado(String msg) {
        lblEstado.setText(msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemaRenta().setVisible(true);
        });
    }
}
