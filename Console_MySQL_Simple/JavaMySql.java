package javamysql;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MEDAC
 */
public class JavaMySql {

    static Conexion conn = null;

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        boolean salir = false; //Condición para el bucle
        int opcion = 6;
        do {
            try {
                mostrarMenu();//Metodo que muestra el menu por consola
                System.out.println("");
                System.out.print("Elije una opción >> ");
                opcion = sc.nextInt();//Pedimos una opcion al usuario

                switch (opcion) {
                    case 1://Conexion a la BBDD
                        conn = new Conexion();
                        conn.establecerConexion();//Llamada al metodo para establecer la conexion
                        break;
                    case 2://Inercion, actualizacion, borrado de datos de las tablas
                        boolean exit = false;
                        if (conn == null) {//Si no hizo la conexion antes, saltara el mensaje para que haga la conexion
                            System.out.println("DEBES DE CREAR LA CONEXION ANTES!!");
                        } else {
                            //DML -> Insercion , actualizacion y borrado de registros
                            do {
                                try {
                                    mostrarMenuDML();// Metodo mostrar el menu DML
                                    System.out.println("");
                                    System.out.print("Eliga una operacion >> ");
                                    opcion = sc.nextInt();//Le pedimos una opcion
                                    System.out.println("");

                                    switch (opcion) {
                                        case 1:
                                            //INSERTAR
                                            insertarDatos();//Llamamos al metodo de los INSERTS
                                            break;
                                        case 2:
                                            //ACTUALIZAR
                                            actualizarDatos();//Llamamos al metodo de los UPDATES
                                            break;
                                        case 3:
                                            //BORRAR
                                            eliminarDatos();//Llamamos al metodo de los DELETES
                                            break;
                                        case 4:
                                            System.out.println("Saliendo de Ejemplos...");//Salir, volviendo al menu principal
                                            exit = true;
                                            break;
                                        default:
                                            System.out.println("NO ES UNA OPCIÓN!");//En caso de que introduzca una opcion que no exista, avisamos por consola
                                            break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("ERROR EN DATO");//En caso de que meta un tipo de dato que no corresponda al del registro, avisamos por consola
                                    sc.next();//Sigue con el bucle, y vuelve a pedir una opcion
                                }
                            } while (!exit);//Salida
                        }

                        break;
                    case 3:
                    try {
                        if (conn == null) {//Si no hizo la conexion antes, saltara el mensaje para que haga la conexion
                            System.out.println("DEBES DE CREAR LA CONEXION ANTES!!");
                        } else {
                            //Consultas con JDBC (Importante usar metodos/funciones)
                            /*
                        1. Uso de LIKE
                        2. JOIN de dos/tres tablas
                        3. GROUP BY
                             */
                            exit = false;

                            do {
                                try {
                                mostrarMenuEjemplos();// Metodo mostrar el menu SELECTS
                                System.out.println("");
                                System.out.print("Eliga un ejemplo >> ");
                                opcion = sc.nextInt();//Le pedimos una opcion
                                System.out.println("");
                                switch (opcion) {
                                    case 1://Ejemplo con LIKE
                                    try {
                                        conn.usoLike();//Llamamos al metodo del ejemplo con LIKE
                                    } catch (SQLException ex) {
                                        Logger.getLogger(JavaMySql.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                    case 2:
                                    try {
                                        conn.usoJoin();//Llamamos al metodo del ejemplo con INNER JOIN
                                    } catch (SQLException ex) {
                                        Logger.getLogger(JavaMySql.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                    case 3:
                                    try {
                                        conn.usoGroupBy();//Llamamos al metodo del ejemplo con GROUP BY
                                    } catch (SQLException ex) {
                                        Logger.getLogger(JavaMySql.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                    case 4:
                                        System.out.println("Saliendo de Ejemplos...");//Salir, volviendo al menu principal
                                        exit = true;
                                        break;
                                    default:
                                        System.out.println("NO ES UNA OPCIÓN!");//En caso de que introduzca una opcion que no exista, avisamos por consola
                                        break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("ERROR EN DATO");//En caso de que meta un tipo de dato que no corresponda al del registro, avisamos por consola
                                    sc.next();//Sigue con el bucle, y vuelve a pedir una opcion
                                }
                            } while (!exit);//Salida
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR EN DATO");//En caso de que meta un tipo de dato que no corresponda al del registro, avisamos por consola
                        sc.next();
                    }
                    break;
                    case 4:
                        System.out.println("Saliendo...");
                        salir = true;//Salimos del menu principal
                        break;
                    default:
                        System.out.println("NO ES UNA OPCIÓN!");//Si no elige una opcion existente, avisamos por consola
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR EN DATO");
                sc.next();
            }
        } while (!salir);//Salida

    }

    //Metodo para insertar los datos
    public static void insertarDatos() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String tabla;
        boolean salir = false;
        do {
            try {
                System.out.println("");
                System.out.println("En que tabla quieres insetar datos?");
                System.out.println("Opción 1 -> Clientes");
                System.out.println("Opción 2 -> Coche");
                System.out.println("Opción 3 -> Concesionario");
                System.out.println("Opción 4 -> Distribuidor");

                System.out.println("");
                System.out.print("Eliga una tabla >> ");
                int opcion = sc.nextInt();
                System.out.println("");
                switch (opcion) {
                    case 1:
                    try {
                        //Si elige la tabla cliente, le pediremos que introduzca datos correspondientes a la tabla
                        tabla = "cliente";
                        System.out.print("Dime su ID >> ");
                        int id = sc.nextInt();

                        System.out.print("\nDime su DNI >> ");
                        String dni = sc.next();

                        System.out.print("\nDime su Nombre >> ");
                        String nombre = sc.next();

                        System.out.print("\nDime su primer Apellido >> ");
                        String apellido1 = sc.next();

                        System.out.print("\nDime su segundo Apellido >> ");
                        String apellido2 = sc.next();

                        String apellidos = apellido1 + " " + apellido2;

                        System.out.print("\nDime su Telefono >> ");
                        int telefono = sc.nextInt();

                        System.out.print("\nDime su Concesionario >> ");
                        int concesionario = sc.nextInt();

                        //Le pasamos los datos que hemos obtenido del usuario a un metodo, en ese metodo haremos el INSERT en la tabla cliente
                        conn.insertarCliente(id, dni, nombre, apellidos, telefono, concesionario);

                        salir = true;
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR EN DATO");
                        sc.next();
                    }
                    break;
                    case 2:
                        //Si elige la tabla coche, le pediremos que introduzca datos correspondientes a la tabla
                        tabla = "coche";
                        System.out.println("Se inserta un peugeot del 96 con matricula 0000AAA");
                        conn.insertarCoche();
                        salir = true;
                        break;
                    case 3:
                        //Si elige la tabla concesionario, le pediremos que introduzca datos correspondientes a la tabla
                        tabla = "concesionario";
                        System.out.println("Inserta un concesionario en avenida de la libertad 48");
                        conn.insertarConcesionario();
                        salir = true;
                        break;
                    case 4:
                        //Si elige la tabla distribuidor, le pediremos que introduzca datos correspondientes a la tabla
                        tabla = "distribuidor";
                        System.out.println("Se inserta el distribuidor Coches la Paz con id 4");
                        conn.insertarDistribuidor();
                        salir = true;
                        break;
                    default:
                        System.out.println("Esa no es una opción!!");//Por si elige una opcion no existente, avisamos
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR EN DATO");
                sc.next();
            }
        } while (!salir);

    }

    //Metodo para actualizar los datos
    public static void actualizarDatos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Se actualiza al cliente especificado dandole de nombre Aberto:");
        System.out.print("Dime un id >> ");
        int numActualizar = sc.nextInt();
        conn.actualizarCliente(numActualizar);
    }

    //Metodo para eliminar los datos
    public static void eliminarDatos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Se borra el cliente especificado:");
        System.out.print("Dime un id >> ");
        int numActualizar = sc.nextInt();
        conn.eliminarCliente(numActualizar);
    }

    public static void mostrarMenu() { //MENÚ DE OPCIONES
        System.out.println("");
        System.out.println("---------- MENU ----------");
        System.out.println("1.-Conexion BBDD");
        System.out.println("2.-Insercion, actualizacion y borrado de registros");
        System.out.println("3.-Consultas");
        System.out.println("4.-Salir");
    }

    public static void mostrarMenuEjemplos() { //MENÚ DE OPCIONES
        System.out.println("");
        System.out.println("---------- Ejemplos Consultas ----------");
        System.out.println("1.-Uso del LIKE");
        System.out.println("2.-Prueba del JOIN");
        System.out.println("3.-GROUP BY");
        System.out.println("4.-Salir");
    }

    public static void mostrarMenuDML() { //MENÚ DE OPCIONES
        System.out.println("");
        System.out.println("---------- Inserción, Actualización y Borrado ----------");
        System.out.println("1.-Insertar datos");
        System.out.println("2.-Actualizar datos");
        System.out.println("3.-Borrar datos");
        System.out.println("4.-Salir");

    }

}
