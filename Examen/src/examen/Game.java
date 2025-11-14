package examen;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CarlosXl
 */
public class Game  extends RentItem implements MenuActions {
    Calendar publi;
    ArrayList<String> tecnicas;
    

    public Game(int codigoItem, String nombreItem, double baseRenta) {
        super(codigoItem,nombreItem,baseRenta);
         this.publi = Calendar.getInstance();
         this.tecnicas = new ArrayList<>();
         this.cantidadCopias = 0;
        
    }
    
    public String toString(){
        
        return "Juego"+ this.nombreItem + ", Codigo : "+this.codigoItem+")";
    };

    @Override
    double pagoRenta(int dias) {
     return 20*dias;   
    }

    @Override
    public void subMenu() {
        
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        
    }
    
    public void setFecha(int year,int mes,int dia){
        this.publi.set(year, mes-1, dia);
        JOptionPane.showConfirmDialog(null, "La fecha de publicacion fue actualizada");
    }
    
    public void listEspecificaciones() {
        if (this.tecnicas.isEmpty()) {
            JOptionPane.showInputDialog("No hay especificaciones registradas para este juego.");
            return;
        }
        
        JOptionPane.showInputDialog("Especificaciones de " + this.nombreItem + ":");
        
        index(0);
    }

    
    private void index(int i) {
        if (i >= this.tecnicas.size()) {
            return;
        }
        System.out.println("- " + this.tecnicas.get(i));
        index(i + 1);
    }
    
    
    
    
    
    
}
