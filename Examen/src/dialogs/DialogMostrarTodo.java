/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dialogs;
import examen.Game;
import examen.Movie;
import examen.RentItem;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ferna
 */
public class DialogMostrarTodo extends JDialog {

    private java.util.List<RentItem> items;

    private final Color FONDO_NEGRO = new Color(10, 10, 10);
    private final Color BLUE_ELEGANTE = new Color(30, 144, 255);

    public DialogMostrarTodo(Frame owner, java.util.List<RentItem> items) {
        super(owner, "Listado de Items", true);
        this.items = items;

        setSize(800, 600);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        getContentPane().setBackground(FONDO_NEGRO);

        JLabel lblTitulo = new JLabel("Items registrados", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelLista = new JPanel();
        panelLista.setBackground(FONDO_NEGRO);
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (items.isEmpty()) {
            JLabel lblVacio = new JLabel("No hay Items registrados.", SwingConstants.CENTER);
            lblVacio.setForeground(Color.LIGHT_GRAY);
            lblVacio.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            panelLista.add(lblVacio);
        } else {
            for (RentItem item : items) {
                JPanel tarjeta = crearTarjetaItem(item);
                panelLista.add(tarjeta);
                panelLista.add(Box.createVerticalStrut(10));
            }
        }

        JScrollPane scroll = new JScrollPane(panelLista);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(FONDO_NEGRO);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearTarjetaItem(RentItem item) {
        JPanel tarjeta = new JPanel(new BorderLayout(10, 10));
        tarjeta.setBackground(new Color(20, 20, 20));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BLUE_ELEGANTE, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel lblImagen = new JLabel("Sin imagen", SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(120, 160));
        lblImagen.setForeground(Color.LIGHT_GRAY);
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        ImageIcon icon = item.imgItem(); 
        if (icon != null) {
            Image img = icon.getImage().getScaledInstance(
                    120, 160, Image.SCALE_SMOOTH
            );
            lblImagen.setIcon(new ImageIcon(img));
            lblImagen.setText("");
        }

        tarjeta.add(lblImagen, BorderLayout.WEST);
        JPanel panelTexto = new JPanel();
        panelTexto.setBackground(new Color(20, 20, 20));
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));

        JLabel lblNombre = new JLabel(item.getNombreItem()); 
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));

        String tipo = (item instanceof Movie) ? "Movie" :
                      (item instanceof Game)  ? "Game"  : "Otro";

        JLabel lblTipo = new JLabel("Tipo: " + tipo);
        lblTipo.setForeground(Color.LIGHT_GRAY);
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        String estado = "";
        if (item instanceof Movie) {
            estado = ((Movie) item).getEstado();
        }

        JLabel lblEstado = new JLabel();
        if (item instanceof Movie) {
            lblEstado.setText("Estado: " + estado);
        } else {
            lblEstado.setText("Estado: N/A");
        }
        lblEstado.setForeground(Color.LIGHT_GRAY);
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblPrecio = new JLabel("Precio base de renta: Lps " + item.getBaseRenta());
        lblPrecio.setForeground(Color.WHITE);
        lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        panelTexto.add(lblNombre);
        panelTexto.add(Box.createVerticalStrut(5));
        panelTexto.add(lblTipo);
        panelTexto.add(lblEstado);
        panelTexto.add(Box.createVerticalStrut(5));
        panelTexto.add(lblPrecio);

        tarjeta.add(panelTexto, BorderLayout.CENTER);

        return tarjeta;
    }
}