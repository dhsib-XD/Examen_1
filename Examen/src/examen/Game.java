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
    ArrayList<String> tecnicas;
    private static final double renta = 20;

    public Game(int codigoItem, String nombreItem, double baseRenta) {
        super(codigoItem, nombreItem, baseRenta);
        this.publi = Calendar.getInstance();
        this.tecnicas = new ArrayList<>();
        this.cantidadCopias = 0;

    }

    public String toString() {

        return "Juego" + this.nombreItem + ", Codigo : " + this.codigoItem + ")";
    }

    ;

    @Override
    double pagoRenta(int dias) {
        return renta * dias;
    }
// Aqui el Submenu Para ejecutarOpcion segun el documento

    @Override
    public void subMenu() {

    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1: {
                this.actualizarFecha();
                break;
            }
            case 2: {
                this.listEspecificaciones();
                break;
            }
            case 3: {
                this.agregarEspecificacion();
                break;
            }
            case 0:

                JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setFecha(int year, int mes, int dia) {
        this.publi.set(year, mes - 1, dia);
        JOptionPane.showMessageDialog(null, "La fecha de publicacion fue actualizada");
    }

    public void listEspecificaciones() {

        if (this.tecnicas.isEmpty()) {

            JOptionPane.showMessageDialog(null, "No hay especificaciones registradas para este juego.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Especificaciones de ").append(this.nombreItem).append(":\n\n");
        for (String especificacion : this.tecnicas) {
            sb.append("- ").append(especificacion).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void actualizarFecha() {
        try {
            String Syear = JOptionPane.showInputDialog(null, "Ingrese el nuevo año:", "Actualizar Fecha", JOptionPane.QUESTION_MESSAGE);
            if (Syear == null) {
                return;
            }

            String Smes = JOptionPane.showInputDialog(null, "Ingrese el nuevo mes (1-12):", "Actualizar Fecha", JOptionPane.QUESTION_MESSAGE);
            if (Smes == null) {
                return;
            }

            String Sdia = JOptionPane.showInputDialog(null, "Ingrese el nuevo día:", "Actualizar Fecha", JOptionPane.QUESTION_MESSAGE);
            if (Sdia == null) {
                return;
            }

            int year = Integer.parseInt(Syear);
            int mes = Integer.parseInt(Smes);
            int dia = Integer.parseInt(Sdia);

            this.setFecha(year, mes, dia);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese solo números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void agregarEspecificacion() {
        String especificacion = JOptionPane.showInputDialog(null, "Ingrese la nueva especificacion", "Agregar especificacion", JOptionPane.QUESTION_MESSAGE);

        if (especificacion != null && !especificacion.trim().isEmpty()) {
            this.tecnicas.add(especificacion);
            JOptionPane.showMessageDialog(null, "¡Especificación '" + especificacion + "' agregada!");
        }
    }
;

}
