package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MEDAC
 */
public class Conexion {
    //Importante tener el JAR de MySql dentro del proyecto para poder hacer la conexion
    Connection conectar = null;
    String user = "root";//Usuario BBDD
    String password = "root";//Contraseña BBDD
    String bd = "bdconcesionario";//Nombre BBDD
    String ip = "localhost";//Ip BBDD
    String puerto = "3306";//Puerto BBDD
    
    String url ="jdbc:mysql://"+ip+":"+puerto+"/"+bd;//URL para el driver

    PreparedStatement ps = null;
    ResultSet rs = null;
    
    //Metodo para establecer la conexion con la BBDD
    public Connection establecerConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//Ruta del DRIVER de MySQL
            conectar = DriverManager.getConnection(url, this.user, this.password);//Obtener conexion pasandole la URL, el usuario y la contraseña
            System.out.println("Se conecto correctamente a la BBDD");
        }catch(Exception e){
            System.out.println("No se conecto a la BBDD, error: " + e.toString());
        }
        return conectar;
    }
    
    //Metodo con ejemplo de LIKE
    public void usoLike() throws SQLException{
        //Setencia SQL con ejemplo de LIKE
        ps = conectar.prepareStatement("SELECT cliente.nombre, cliente.apellidos, cliente.telefono "
                + "FROM bdconcesionario.cliente "
                + "WHERE cliente.nombre LIKE 'p%';");
        //Ejecutamos la sentencia
        rs = ps.executeQuery();
        
        System.out.println("Recogeme de la tabla CLIENTE los nombres, apellidos y telefonos de aquellos clientes cuyo nombre empieza por la letra P");
        while(rs.next()){
            String nombre = rs.getString(1);
            String apellidos = rs.getString(2);
            int telefono = rs.getInt(3);
            
            System.out.println("Nombre Cliente -> "+ nombre);
            System.out.println("Apellidos Cliente -> "+ apellidos);
            System.out.println("Telefono Cliente -> "+ telefono);
            System.out.println("");
        }
    }
    
    //Metodo con ejemplo de INNER JOIN
    public void usoJoin() throws SQLException{
        //Setencia SQL con ejemplo de INNER JOIN
        ps = conectar.prepareStatement("SELECT distribuidor.nombre, distribuidor_has_concesionario.precioDistribucion "
                + "FROM distribuidor "
                + "INNER JOIN distribuidor_has_concesionario ON distribuidor.iddistribuidor = distribuidor_has_concesionario.distribuidor_iddistribuidor "
                + "WHERE distribuidor_has_concesionario.precioDistribucion < 15000;");
        //Ejecutamos la sentencia
        rs = ps.executeQuery();
    
        System.out.println("Recogeme el nombre del distribuidor y el precio de distribucion donde el precio es menor a 15000");
        while(rs.next()){
            String nomDistribuidor = rs.getString(1);
            int precioDistribucion = rs.getInt(2);
            
            System.out.println("Nombre Distribuidor -> "+ nomDistribuidor);
            System.out.println("Precio Distribucion -> "+ precioDistribucion);
            System.out.println("");
        }
    }
    
    //Metodo con ejemplo de GROUP BY
    public void usoGroupBy() throws SQLException{
        //Setencia SQL con ejemplo de GROUP BY
        ps = conectar.prepareStatement("SELECT COUNT(distribuidor.iddistribuidor),distribuidor.pais AS NumeroDistribuidores,distribuidor.pais "
                + "FROM distribuidor "
                + "WHERE iddistribuidor "
                + "GROUP BY pais;");
        //Ejecutamos la sentencia
        rs = ps.executeQuery();
        
        System.out.println("Cuentame el numero de distribuidores y recoge su pais, estos agrupandolos por el pais");
        while(rs.next()){
            String countNumeroDistribuidores = rs.getString(1);
            String pais = rs.getString(2);
            
            System.out.println("Numero Distribuidores -> "+ countNumeroDistribuidores);
            System.out.println("Pais -> "+ pais);
            System.out.println("");
        }
    }
    
    //Metodo insertar datos en la tabla CLIENTE
    public void insertarCliente(int id, String dni, String nombre, String apellido, int telefono, int concesionario) throws SQLException {
        Statement miStat = conectar.createStatement();
        
        //Los datos que le pedimos al usuario, los hemos pasado al metodo y los insertamos en la tabla
        int cont = miStat.executeUpdate("INSERT INTO bdconcesionario.cliente VALUES(" + id + ",'" + dni 
                + "','" + nombre + "','" + apellido + "'," + telefono + "," + concesionario + ");");
        
        System.out.println("\nDatos insertados!\n");
    }
    
    //Metodo eliminar fila en la tabla CLIENTE
    public void eliminarCliente(int numero) {
        //Eliminara la fila correspondiente al ID que haya pasado el usuario
        try {
            Statement miStat = conectar.createStatement();
            
            miStat.executeUpdate("DELETE FROM bdconcesionario.cliente WHERE bdconcesionario.cliente.idcliente = " + numero + ";");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        System.out.println("\nCliente con id " + numero + " eliminado!!\n");
    }
    
    //Metodo eliminar fila en la tabla CLIENTE
    public void actualizarCliente(int numero) {
        //Actualizara la fila correspondiente al ID que haya pasado el usuario
        try {
            Statement miStat = conectar.createStatement();
            
            miStat.executeUpdate("UPDATE bdconcesionario.cliente SET bdconcesionario.cliente.nombre = 'Alberto' WHERE bdconcesionario.cliente.idcliente = " + numero + ";");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        System.out.println("\nCliente con id " + numero + " actualizado a Alberto!!\n");
    }
    
    //Metodo ejemplo de insertar en la tabla COCHE
    public void insertarCoche() {
        try {
            Statement miStat = conectar.createStatement();
            
            miStat.executeUpdate("INSERT INTO bdconcesionario.coche VALUES('0000AAA','PEUGEOT','1996-02-26','ESPAÑA',30000,1);");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        System.out.println("\nInsertado Peugeot del 1996\n");
    }
    
    //Metodo ejemplo de insertar en la tabla CONCESIONARIO
    public void insertarConcesionario() {
        try {
            Statement miStat = conectar.createStatement();
            
            miStat.executeUpdate("INSERT INTO bdconcesionario.concesionario VALUES(4,'ESPAÑA','Avda de la Libertad, 48');");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        System.out.println("\nInsertado nuevo concesionario en avenida de la libertad\n");
    }
      
    //Metodo ejemplo de insertar en la tabla DISTRIBUIDOR
    public void insertarDistribuidor() {
        try {
            Statement miStat = conectar.createStatement();
            
            miStat.executeUpdate("INSERT INTO bdconcesionario.distribuidor VALUES(4,'Coches La Paz','ESPAÑA');");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        System.out.println("\nInsertado nuevo distribuidor\n");
    }
}