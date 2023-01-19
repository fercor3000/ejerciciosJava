
package juegofuga;


public class posicion {
    int filas;
    int columnas;

    posicion(){
        this.filas = (int) (Math.random() * 10); 
        this.columnas = (int) (Math.random() * 10); 
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    
    
}
