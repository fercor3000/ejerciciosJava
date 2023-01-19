
package juegofuga;


public class personaje {
    
    private String tipoPersonaje;
    private posicion posPersonaje;
    private boolean tienePasaporte;
    private boolean tieneAlicates;
    private boolean tieneUniforme;
    
    personaje(){
        this.tipoPersonaje = "O ";
        
        this.posPersonaje = new posicion();
        
        this.tienePasaporte = false;
        this.tieneAlicates = false;
        this.tieneUniforme = false;
    }

    public String getTipoPersonaje() {
        return tipoPersonaje;
    }

    public void setTipoPersonaje(String tipoPersonaje) {
        this.tipoPersonaje = tipoPersonaje;
    }

    public posicion getPosPersonaje() {
        return posPersonaje;
    }

    public void setPosPersonaje(posicion posPersonaje) {
        this.posPersonaje = posPersonaje;
    }

    public boolean isTienePasaporte() {
        return tienePasaporte;
    }

    public void setTienePasaporte(boolean tienePasaporte) {
        this.tienePasaporte = tienePasaporte;
    }

    public boolean isTieneAlicates() {
        return tieneAlicates;
    }

    public void setTieneAlicates(boolean tieneAlicates) {
        this.tieneAlicates = tieneAlicates;
    }

    public boolean isTieneUniforme() {
        return tieneUniforme;
    }

    public void setTieneUniforme(boolean tieneUniforme) {
        this.tieneUniforme = tieneUniforme;
    }
    
}
