/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.util.Calendar;

/**
 *
 * @author Nathan
 */

public class Movie extends RentItem {

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
            if(dias>2){
                diasExtras=dias-2;
                montoTotal=(baseRenta*2)+(diasExtras*50);
            }else{
                montoTotal=dias*baseRenta;
            }
        }else{
            if(dias>5){
                diasExtras=dias-5;
                montoTotal=(baseRenta*5)+(diasExtras*30);
            }else{
                montoTotal=dias*baseRenta;
            }
        }
        return montoTotal;

    }

}
