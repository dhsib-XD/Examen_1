/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Nathan
 */
public class Movie extends RentItem implements MenuActions {

    Calendar fechaEstreno;

    public Movie(int codigoItem, String nombreItem, double baseRenta) {
        super(codigoItem, nombreItem, baseRenta);
        this.fechaEstreno = Calendar.getInstance();
    }

    public void setFechaEstreno(Calendar fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public Calendar getFechaEstreno() {
        return fechaEstreno;
    }

    public String toString() {
        return super.toString() + "\n estado" + getEstado() + "\nMovie";
    }

    public String getEstado() {
        if (fechaEstreno.after(fechaEstreno)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error: La fecha no puede ser mayor a la fecha actual.",
                    "Fecha inválida",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        Calendar fechaLimite = Calendar.getInstance();
        fechaLimite.add(Calendar.MONTH, -3);

        if (fechaEstreno.after(fechaLimite) || fechaEstreno.equals(fechaLimite)) {
            return "ESTRENO";
        }
        return "NORMAL";
    }

    public double pagoRenta(int dias) {
        String estado = getEstado();
        int diasExtras;
        double baseRenta = super.baseRenta;
        double montoTotal = 0;

        if (estado.equalsIgnoreCase("ESTRENO")) {
            if (dias > 2) {
                diasExtras = dias - 2;
                montoTotal = (baseRenta * 2) + (diasExtras * 50);
            } else {
                montoTotal = dias * baseRenta;
            }
        } else {
            if (dias > 5) {
                diasExtras = dias - 5;
                montoTotal = (baseRenta * 5) + (diasExtras * 30);
            } else {
                montoTotal = dias * baseRenta;
            }
        }
        return montoTotal;

    }

    @Override
    public void subMenu() {

        String menu
                = "SUBMENÚ DE MOVIE\n\n"
                + "1. Actualizar fecha de estreno.\n"
                + "2. Cerrar\n\n"
                + "Ingrese una opción:";

        String opcionStr = JOptionPane.showInputDialog(null, menu, "Submenú Movie", JOptionPane.QUESTION_MESSAGE);

        if (opcionStr == null) {
            return;
        }

        try {
            int opcion = Integer.parseInt(opcionStr);
            ejecutarOpcion(opcion);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese una opción válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void ejecutarOpcion(int opcion) {

        switch (opcion) {

            case 1:
                actualizarFecha();
                break;

            case 2:
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opción inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void actualizarFecha() {

        String input = JOptionPane.showInputDialog(
                null,
                "Ingrese la fecha en formato dd/MM/yyyy:",
                "Actualizar Fecha",
                JOptionPane.QUESTION_MESSAGE
        );

        if (input == null || input.trim().isEmpty()) {
            return;
        }

        try {
            String[] partes = input.split("/");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anio = Integer.parseInt(partes[2]);

            fechaEstreno.set(anio, mes - 1, dia);

            JOptionPane.showMessageDialog(null,
                    "Fecha actualizada correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Formato inválido. Use dd/MM/yyyy.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
