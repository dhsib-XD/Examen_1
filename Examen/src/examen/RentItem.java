package examen;

import javax.swing.ImageIcon;

/**
 *
 * @author marye
 */
public abstract class RentItem {

    protected int codigoItem;
    protected String nombreItem;
    protected double baseRenta;
    protected int cantidadCopias;
    protected ImageIcon imgItem;

    public RentItem(int codigoItem, String nombreItem, double baseRenta) {
        this.codigoItem = codigoItem;
        this.nombreItem = nombreItem;
        this.baseRenta = baseRenta;
        this.cantidadCopias = 0;
    }

    abstract double pagoRenta(int dias);

    public int getCodigoItem() {
        return codigoItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public double getBaseRenta() {
        return baseRenta;
    }

    public int getCantidadCopias() {
        return cantidadCopias;
    }

    public ImageIcon imgItem() {
        return imgItem;
    }

    public String toString() {
        return "Codigo: " + codigoItem + "\n Nombre: " + nombreItem + "\n Base Renta: " + baseRenta + "\nCantidad de copias: " + cantidadCopias;
    }

}
