package examen;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author CarlosXl
 */
public class Game extends RentItem implements MenuActions {

    Calendar publi;
    ArrayList<String> especificaciones;

    public Game(int codigoItem, String nombreItem, double baseRenta) {
        super(codigoItem, nombreItem, baseRenta);
        this.publi = Calendar.getInstance();
        this.especificaciones = new ArrayList<>();
        this.cantidadCopias = 0;

    }

    public String toString() {

        return "Juego" + this.nombreItem + ", Codigo : " + this.codigoItem + ")";
    }

    @Override
    public double pagoRenta(int dias) {
        return 20 * dias;
    }

    public void subMenu() {
        String menu
                = "SUBMENÚ DE GAME\n\n"
                + "1. Actualizar fecha de publicación\n"
                + "2. Agregar especificación\n"
                + "3. Mostrar especificaciones\n"
                + "4. Cerrar\n\n"
                + "Ingrese una opción:";

        String opcionStr = JOptionPane.showInputDialog(null, menu, "Submenú Game", JOptionPane.QUESTION_MESSAGE);

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
                agregarEspecificacion();
                break;

            case 3:
                mostrarEspecificaciones();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opción inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void agregarEspecificacion() {
        String espec = JOptionPane.showInputDialog(null, "Ingrese la especificación:", "Agregar Especificación", JOptionPane.QUESTION_MESSAGE);

        if (espec != null && !espec.trim().isEmpty()) {
            especificaciones.add(espec.trim());
            JOptionPane.showMessageDialog(null, "Especificación agregada.");
        }
    }

    private void mostrarEspecificaciones() {

        if (especificaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay especificaciones registradas.");
            return;
        }

        String texto = listarEspecificaciones(0);

        JOptionPane.showMessageDialog(null, texto, "Especificaciones", JOptionPane.INFORMATION_MESSAGE);
    }

    private String listarEspecificaciones(int index) {
        if (index == especificaciones.size()) {
            return "";
        }
        return "- " + especificaciones.get(index) + "\n" + listarEspecificaciones(index + 1);
    }

    private String construirEspecificacionesRec(int index) {
        if (index == especificaciones.size()) {
            return "";
        }
        return "- " + especificaciones.get(index) + "\n" + construirEspecificacionesRec(index + 1);
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

            publi.set(anio, mes - 1, dia);

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
