
package juegofuga;

public class guardia {
    private String tipoPersonaje;
    private int posX;
    private int posY;
    
    guardia(){
        this.tipoPersonaje = "G ";
        
        this.posX = (int) (Math.random() * 10);
        this.posY = (int) (Math.random() * 10);
    }

    //GETTERS
    public String getTipoPersonaje() {
        return tipoPersonaje;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    //SETTERS
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    
}
