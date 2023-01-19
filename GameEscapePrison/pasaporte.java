
package juegofuga;

import java.util.ArrayList;

public class pasaporte {
    private posicion pos;
    private String tipoHerramienta;
    private ArrayList<posicion> pos_Pasaporte = new ArrayList<>(1);
    
    pasaporte(){
        this.tipoHerramienta = "P ";
        
        pos = new posicion();
        
        pos_Pasaporte.add(pos);
    }

    public String getTipoHerramienta() {
        return tipoHerramienta;
    }

    public ArrayList<posicion> getPosiciones() {
        return pos_Pasaporte;
    }
}
