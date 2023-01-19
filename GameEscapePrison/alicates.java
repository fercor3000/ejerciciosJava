
package juegofuga;

import java.util.ArrayList;


public class alicates {
    private posicion pos1;
    private posicion pos2;
    private String tipoHerramienta;
    private ArrayList<posicion> pos_Alicates = new ArrayList<>(2);
    
    alicates(){
        this.tipoHerramienta = "A ";
        
        pos1 = new posicion();
        pos_Alicates.add(pos1);
        
        pos2 = new posicion();
        
        if (pos1.getFilas() == 9){
            pos2.setColumnas(pos1.getColumnas());
            pos2.setFilas(pos1.getFilas() - 1);
        } else {
            pos2.setColumnas(pos1.getColumnas());
            pos2.setFilas(pos1.getFilas() + 1);
        }
        
        pos_Alicates.add(pos2);
    }
    
    public String getTipoHerramienta() {
        return tipoHerramienta;
    }

    public ArrayList<posicion> getPosiciones() {
        return pos_Alicates;
    }
}
