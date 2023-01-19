
package juegofuga;

import java.util.ArrayList;


public class uniforme {
    private posicion pos1;
    private posicion pos2;
    private posicion pos3;
    private String tipoHerramienta;
    private ArrayList<posicion> pos_Uniforme = new ArrayList<>(3);
    
    uniforme(){
        this.tipoHerramienta = "U ";
        
        pos1 = new posicion();
        pos_Uniforme.add(pos1);
        
        pos2 = new posicion();
        pos3 = new posicion();
        
        if (pos1.getFilas() == 9){
            pos2.setColumnas(pos1.getColumnas());
            pos2.setFilas(pos1.filas - 1);
            pos3.setColumnas(pos1.getColumnas());
            pos3.setFilas(pos2.getFilas() -1);
        } else{
            //CORREGIR, DEBEN SALIR UNA AL LADO DE LA OTRA
            //CONTROLAR QUE NO ELIMINE EL PASAPORTE
            pos2.setColumnas(pos1.getColumnas());
            pos2.setFilas(pos1.getFilas() + 1);
            if (pos2.getFilas() == 9){
                pos3.setColumnas(pos1.getColumnas());
                pos3.setFilas(pos1.getFilas() - 1);
            } else {
                pos3.setColumnas(pos1.getColumnas());
                pos3.setFilas(pos2.getFilas() + 1);
            }
            
        }
        pos_Uniforme.add(pos2);
        pos_Uniforme.add(pos3);
    }
    
    public String getTipoHerramienta() {
        return tipoHerramienta;
    }

    public ArrayList<posicion> getPosiciones() {
        return pos_Uniforme;
    }
}
