package juegofuga;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JuegoFuga {

    static String[][] tablero = new String[10][10];

    static pasaporte passport = new pasaporte();
    static alicates pliers = new alicates();
    static uniforme uniform = new uniforme();

    static guardia guard1;
    static guardia guard2;
    static guardia guard3;
    static ArrayList<guardia> arrayGuardias = new ArrayList<>();

    static boolean bucle = true;
    static boolean salir = false;

    static int numG;
    static char tecla;

    static personaje character = new personaje();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //Inicializo tablero 10x10
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //Toda casilla no ocupada se representa con X
                if (j == 9) {
                    tablero[i][j] = "x";
                } else {
                    tablero[i][j] = "x ";
                }
            }
        }
        do {
            try {
                System.out.println("Introduzca nivel de dificultad: ");
                System.out.println("1- Facil");
                System.out.println("2- Media");
                System.out.println("3- Dificil");
                System.out.print(">> ");
                numG = sc.nextInt();
                System.out.println("");

                createGuardias(numG);

                checkCasillas();

                //Pasaporte: Ocupa una casilla, se representa con P
                tablero[passport.getPosiciones().get(0).getFilas()][passport.getPosiciones().get(0).getColumnas()] = passport.getTipoHerramienta();
                //Alicates: Ocupa dos casillas, se representa con A
                tablero[pliers.getPosiciones().get(0).getFilas()][pliers.getPosiciones().get(0).getColumnas()] = pliers.getTipoHerramienta();
                tablero[pliers.getPosiciones().get(1).getFilas()][pliers.getPosiciones().get(1).getColumnas()] = pliers.getTipoHerramienta();
                //Uniforme: Ocupa tres casillas, se representa con U
                tablero[uniform.getPosiciones().get(0).getFilas()][uniform.getPosiciones().get(0).getColumnas()] = uniform.getTipoHerramienta();
                tablero[uniform.getPosiciones().get(1).getFilas()][uniform.getPosiciones().get(1).getColumnas()] = uniform.getTipoHerramienta();
                tablero[uniform.getPosiciones().get(2).getFilas()][uniform.getPosiciones().get(2).getColumnas()] = uniform.getTipoHerramienta();

                if (numG == 1) {
                    //Guardia: Puede haber hasta 3 guardias dependiendo de la dificultad, se representa con G
                    tablero[guard1.getPosY()][guard1.getPosX()] = guard1.getTipoPersonaje();
                } else if (numG == 2) {
                    tablero[guard1.getPosY()][guard1.getPosX()] = guard1.getTipoPersonaje();
                    tablero[guard2.getPosY()][guard2.getPosX()] = guard2.getTipoPersonaje();
                } else {
                    tablero[guard1.getPosY()][guard1.getPosX()] = guard1.getTipoPersonaje();
                    tablero[guard2.getPosY()][guard2.getPosX()] = guard2.getTipoPersonaje();
                    tablero[guard3.getPosY()][guard3.getPosX()] = guard3.getTipoPersonaje();
                }

                tablero[character.getPosPersonaje().getFilas()][character.getPosPersonaje().getColumnas()] = character.getTipoPersonaje();

                while (bucle) {
                    mostrarTablero();
                    System.out.println("Personaje pos: F" + character.getPosPersonaje().getFilas() + " - C" + character.getPosPersonaje().getColumnas());
                    System.out.println("Meta W-A-S-D:");
                    tecla = sc.next().charAt(0);
                    moverPersonaje(tecla);
                    checkGanar();
                }
                //Controlamos que no ponga letras ni num mayores a 3    
            } catch (InputMismatchException e) {
                System.out.println("\nELIJA LA OPCION CORRECTA\n");
                sc.nextLine();
            } catch (java.lang.NullPointerException e) {
                System.out.println("ELIJA LA OPCION CORRECTA\n");
                sc.nextLine();
            }

        } while (!salir);
    }

    static public void mostrarTablero() {
        //Mostramos por pantalla
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 9) {
                    System.out.println(tablero[i][j]);
                } else {
                    System.out.print(tablero[i][j]);
                }
            }
        }
    }

    static public void moverPersonaje(char tecla) {

        int y = character.getPosPersonaje().getFilas();
        int x = character.getPosPersonaje().getColumnas();

        switch (Character.toLowerCase(tecla)) { //Pasamos la tecla
            case 'w':
                y--;
                if (y < 0) {
                    y = 0;
                }
                checkGuardias(y, x);
                checkObjetos(y, x);

                if (!tablero[y][x].equals("P ") && !tablero[y][x].equals("A ") && !tablero[y][x].equals("U ")) {
                    tablero[y][x] = "O ";
                    tablero[y + 1][x] = "x ";
                    character.getPosPersonaje().setColumnas(x);
                    character.getPosPersonaje().setFilas(y);
                }
                movimientoGuardias(y, x);
                break;
            case 'a':
                x--;
                if (x < 0) {
                    x = 0;
                }
                checkGuardias(y, x);
                checkObjetos(y, x);

                if (!tablero[y][x].equals("P ") && !tablero[y][x].equals("A ") && !tablero[y][x].equals("U ")) {
                    tablero[y][x] = "O ";
                    tablero[y][x + 1] = "x ";
                    character.getPosPersonaje().setColumnas(x);
                    character.getPosPersonaje().setFilas(y);
                }
                movimientoGuardias(y, x);
                break;
            case 's':
                y++;
                if (y > 9) {
                    y = 9;
                }
                checkGuardias(y, x);
                checkObjetos(y, x);

                if (!tablero[y][x].equals("P ") && !tablero[y][x].equals("A ") && !tablero[y][x].equals("U ")) {
                    tablero[y][x] = "O ";
                    tablero[y - 1][x] = "x ";
                    character.getPosPersonaje().setColumnas(x);
                    character.getPosPersonaje().setFilas(y);
                }
                movimientoGuardias(y, x);
                break;
            case 'd':
                x++;
                if (x > 9) {
                    x = 9;
                }
                checkGuardias(y, x);
                checkObjetos(y, x);
                if (!tablero[y][x].equals("P ") && !tablero[y][x].equals("A ") && !tablero[y][x].equals("U ")) {
                    tablero[y][x] = "O ";
                    tablero[y][x - 1] = "x ";
                    character.getPosPersonaje().setColumnas(x);
                    character.getPosPersonaje().setFilas(y);
                }
                movimientoGuardias(y, x);
                break;
            default:
                System.out.println("\nError, no has introducido una tecla permitida\n");
                break;
        }
    }

    static public void checkGanar() {
        if (character.isTienePasaporte() && character.isTieneAlicates() && character.isTieneUniforme()) {
            System.out.println("\nTE HAS ESCAPADO!!!");

            exit(0);
        }
    }

    static public void checkGuardias(int y, int x) {
        switch (numG) {
            case 1:
                if (tablero[y][x].equals(guard1.getTipoPersonaje()) || tablero[y][x].equals(guard2.getTipoPersonaje()) || tablero[y][x].equals(guard3.getTipoPersonaje())) {
                    System.out.println("\nTE HAS ENCONTRADO UN GUARDIA... HAS PERDIDO!");

                    exit(0);
                }
                break;
            case 2:
                if (tablero[y][x].equals(guard1.getTipoPersonaje()) || tablero[y][x].equals(guard2.getTipoPersonaje()) || tablero[y][x].equals(guard3.getTipoPersonaje())) {
                    System.out.println("\nTE HAS ENCONTRADO UN GUARDIA... HAS PERDIDO!");

                    exit(0);
                }
                break;
            default:
                if (tablero[y][x].equals(guard1.getTipoPersonaje()) || tablero[y][x].equals(guard2.getTipoPersonaje()) || tablero[y][x].equals(guard3.getTipoPersonaje())) {
                    System.out.println("\nTE HAS ENCONTRADO UN GUARDIA... HAS PERDIDO!");

                    exit(0);
                }
                break;
        }

    }

    static public void checkObjetos(int y, int x) {
        boolean checkGanador = false;

        //ENCUENTRA PASAPORTE
        if (tablero[y][x].equals(passport.getTipoHerramienta())) {
            checkGanador = obtenerHerramientaMiniJuego();

            if (checkGanador) {
                character.setTienePasaporte(true);
                System.out.println("OBTENIDO PASAPORTE");

                tablero[passport.getPosiciones().get(0).getFilas()][passport.getPosiciones().get(0).getColumnas()] = "x ";
            }
        } //ENCUENTRA ALICATES
        else if (tablero[y][x].equals(pliers.getTipoHerramienta())) {
            checkGanador = obtenerHerramientaMiniJuego();

            if (checkGanador) {
                character.setTieneAlicates(true);
                System.out.println("OBTENIDO ALICATES");

                tablero[pliers.getPosiciones().get(0).getFilas()][pliers.getPosiciones().get(0).getColumnas()] = "x ";
                tablero[pliers.getPosiciones().get(1).getFilas()][pliers.getPosiciones().get(1).getColumnas()] = "x ";
            }
        } //ENCUENTRA UNIFORME
        else if (tablero[y][x].equals(uniform.getTipoHerramienta())) {
            checkGanador = obtenerHerramientaMiniJuego();

            if (checkGanador) {
                character.setTieneUniforme(true);
                System.out.println("OBTENIDO UNIFORME");

                tablero[uniform.getPosiciones().get(0).getFilas()][uniform.getPosiciones().get(0).getColumnas()] = "x ";
                tablero[uniform.getPosiciones().get(1).getFilas()][uniform.getPosiciones().get(1).getColumnas()] = "x ";
                tablero[uniform.getPosiciones().get(2).getFilas()][uniform.getPosiciones().get(2).getColumnas()] = "x ";
            }
        }
    }

    //MINIJUEGO PARA OBTENER LAS HERRAMIENTAS
    static public boolean obtenerHerramientaMiniJuego() {
        int opcionJugador = 0;
        boolean ganador = false;

        try {
            int opcionMaquina = (int) (Math.random() * 3 + 1); //Elige numero del 1 al 3
            System.out.println("La maquina : " + opcionMaquina);

            System.out.println("");
            System.out.println("# MINIJUEGO #");
            System.out.println("1- Piedra");
            System.out.println("2- Papel");
            System.out.println("3- Tijera");
            System.out.print(">>");
            opcionJugador = sc.nextInt();
            System.out.println("");

            if (opcionJugador == opcionMaquina) {
                do {
                    opcionMaquina = (int) (Math.random() * 3 + 1);
                    System.out.println("HAS EMPATADO, INTENTALO DE NUEVO...");
                    System.out.print(">> ");
                    opcionJugador = sc.nextInt();
                } while (opcionJugador == opcionMaquina);

            }

            if ((opcionJugador == 1 && opcionMaquina == 2)
                    || (opcionJugador == 2 && opcionMaquina == 3)
                    || (opcionJugador == 3 && opcionMaquina == 1)) {

                System.out.println("HAS PERDIDO, INTENTALO DE NUEVO...");
                ganador = false;
                System.out.println("");
            } else {
                System.out.println("HAS GANADO EL MINIJUEGO...");
                ganador = true;
            }

        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        return ganador;
    }

    //FUNCIONAMIENTO DE LOS GUARDIAS
    static public void movimientoGuardias(int y, int x) {
        //ARREGLAR: EL PERSONAJE SE PUEDE COMER AL GUARDIA
        int newPosition1 = (int) (Math.random() * 4);
        int newPosition2 = (int) (Math.random() * 4);
        int newPosition3 = (int) (Math.random() * 4);
        if (numG == 1) {
            int y_G1 = guard1.getPosY();
            int x_G1 = guard1.getPosX();

            switch (newPosition1) {
                case 0:
                    //SUBIR
                    y_G1--;
                    if (y_G1 < 0) {
                        y_G1 = 0;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        y_G1++;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {

                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1 + 1][x_G1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
                case 1:
                    //IZQUIERDA
                    x_G1--;
                    if (x_G1 < 0) {
                        x_G1 = 0;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        x_G1++;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {

                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1][x_G1 + 1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
                case 2:
                    //BAJAR
                    y_G1++;
                    if (y_G1 > 9) {
                        y_G1 = 9;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        y_G1--;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {

                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1 - 1][x_G1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
                default:
                    //DERECHA
                    x_G1++;
                    if (x_G1 > 9) {
                        x_G1 = 9;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        x_G1--;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {

                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1][x_G1 - 1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
            }
        } else if (numG == 2) {
            int y_G1 = guard1.getPosY();
            int x_G1 = guard1.getPosX();

            int y_G2 = guard2.getPosY();
            int x_G2 = guard2.getPosX();

            switch (newPosition1) {
                case 0:
                    //SUBIR
                    y_G1--;
                    if (y_G1 < 0) {
                        y_G1 = 0;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        y_G1++;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1 + 1][x_G1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
                case 1:
                    //IZQUIERDA
                    x_G1--;
                    if (x_G1 < 0) {
                        x_G1 = 0;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        x_G1++;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1][x_G1 + 1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
                case 2:
                    //BAJAR
                    y_G1++;
                    if (y_G1 > 9) {
                        y_G1 = 9;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        y_G1--;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1 - 1][x_G1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
                default:
                    //DERECHA
                    x_G1++;
                    if (x_G1 > 9) {
                        x_G1 = 9;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        x_G1--;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1][x_G1 - 1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
            }
            switch (newPosition2) {
                case 0:
                    //SUBIR
                    y_G2--;
                    if (y_G2 < 0) {
                        y_G2 = 0;
                    }
                    if (tablero[y_G2][x_G2].equals("P ") || tablero[y_G2][x_G2].equals("A ") || tablero[y_G2][x_G2].equals("U ") || tablero[y_G2][x_G2].equals("G ")) {
                        y_G2++;
                    } else if (tablero[y_G2][x_G2].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G2][x_G2] = "G ";
                        tablero[y_G2 + 1][x_G2] = "x ";
                        guard2.setPosY(y_G2);
                        guard2.setPosX(x_G2);
                    }
                    break;
                case 1:
                    //IZQUIERDA
                    x_G2--;
                    if (x_G2 < 0) {
                        x_G2 = 0;
                    }
                    if (tablero[y_G2][x_G2].equals("P ") || tablero[y_G2][x_G2].equals("A ") || tablero[y_G2][x_G2].equals("U ") || tablero[y_G2][x_G2].equals("G ")) {
                        x_G2++;
                    } else if (tablero[y_G2][x_G2].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G2][x_G2] = "G ";
                        tablero[y_G2][x_G2 + 1] = "x ";
                        guard2.setPosY(y_G2);
                        guard2.setPosX(x_G2);
                    }
                    break;
                case 2:
                    //BAJAR
                    y_G2++;
                    if (y_G2 > 9) {
                        y_G2 = 9;
                    }
                    if (tablero[y_G2][x_G2].equals("P ") || tablero[y_G2][x_G2].equals("A ") || tablero[y_G2][x_G2].equals("U ") || tablero[y_G2][x_G2].equals("G ")) {
                        y_G2--;
                    } else if (tablero[y_G2][x_G2].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G2][x_G2] = "G ";
                        tablero[y_G2 - 1][x_G2] = "x ";
                        guard2.setPosY(y_G2);
                        guard2.setPosX(x_G2);
                    }
                    break;
                default:
                    //DERECHA
                    x_G2++;
                    if (x_G2 > 9) {
                        x_G2 = 9;
                    }
                    if (tablero[y_G2][x_G2].equals("P ") || tablero[y_G2][x_G2].equals("A ") || tablero[y_G2][x_G2].equals("U ") || tablero[y_G2][x_G2].equals("G ")) {
                        x_G2--;
                    } else if (tablero[y_G2][x_G2].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G2][x_G2] = "G ";
                        tablero[y_G2][x_G2 - 1] = "x ";
                        guard2.setPosY(y_G2);
                        guard2.setPosX(x_G2);
                    }
                    break;
            }
        } else {
            int y_G1 = guard1.getPosY();
            int x_G1 = guard1.getPosX();

            int y_G2 = guard2.getPosY();
            int x_G2 = guard2.getPosX();

            int y_G3 = guard3.getPosY();
            int x_G3 = guard3.getPosX();

            switch (newPosition1) {
                case 0:
                    //SUBIR
                    y_G1--;
                    if (y_G1 < 0) {
                        y_G1 = 0;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        y_G1++;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1 + 1][x_G1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
                case 1:
                    //IZQUIERDA
                    x_G1--;
                    if (x_G1 < 0) {
                        x_G1 = 0;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        x_G1++;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1][x_G1 + 1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
                case 2:
                    //BAJAR
                    y_G1++;
                    if (y_G1 > 9) {
                        y_G1 = 9;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        y_G1--;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1 - 1][x_G1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
                default:
                    //DERECHA
                    x_G1++;
                    if (x_G1 > 9) {
                        x_G1 = 9;
                    }
                    if (tablero[y_G1][x_G1].equals("P ") || tablero[y_G1][x_G1].equals("A ") || tablero[y_G1][x_G1].equals("U ") || tablero[y_G1][x_G1].equals("G ")) {
                        x_G1--;
                    } else if (tablero[y_G1][x_G1].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G1][x_G1] = "G ";
                        tablero[y_G1][x_G1 - 1] = "x ";
                        guard1.setPosY(y_G1);
                        guard1.setPosX(x_G1);
                    }
                    break;
            }
            switch (newPosition2) {
                case 0:
                    //SUBIR
                    y_G2--;
                    if (y_G2 < 0) {
                        y_G2 = 0;
                    }
                    if (tablero[y_G2][x_G2].equals("P ") || tablero[y_G2][x_G2].equals("A ") || tablero[y_G2][x_G2].equals("U ") || tablero[y_G2][x_G2].equals("G ")) {
                        y_G2++;
                    } else if (tablero[y_G2][x_G2].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G2][x_G2] = "G ";
                        tablero[y_G2 + 1][x_G2] = "x ";
                        guard2.setPosY(y_G2);
                        guard2.setPosX(x_G2);
                    }
                    break;
                case 1:
                    //IZQUIERDA
                    x_G2--;
                    if (x_G2 < 0) {
                        x_G2 = 0;
                    }
                    if (tablero[y_G2][x_G2].equals("P ") || tablero[y_G2][x_G2].equals("A ") || tablero[y_G2][x_G2].equals("U ") || tablero[y_G2][x_G2].equals("G ")) {
                        x_G2++;
                    } else if (tablero[y_G2][x_G2].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G2][x_G2] = "G ";
                        tablero[y_G2][x_G2 + 1] = "x ";
                        guard2.setPosY(y_G2);
                        guard2.setPosX(x_G2);
                    }
                    break;
                case 2:
                    //BAJAR
                    y_G2++;
                    if (y_G2 > 9) {
                        y_G2 = 9;
                    }
                    if (tablero[y_G2][x_G2].equals("P ") || tablero[y_G2][x_G2].equals("A ") || tablero[y_G2][x_G2].equals("U ") || tablero[y_G2][x_G2].equals("G ")) {
                        y_G2--;
                    } else if (tablero[y_G2][x_G2].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G2][x_G2] = "G ";
                        tablero[y_G2 - 1][x_G2] = "x ";
                        guard2.setPosY(y_G2);
                        guard2.setPosX(x_G2);
                    }
                    break;
                default:
                    //DERECHA
                    x_G2++;
                    if (x_G2 > 9) {
                        x_G2 = 9;
                    }
                    if (tablero[y_G2][x_G2].equals("P ") || tablero[y_G2][x_G2].equals("A ") || tablero[y_G2][x_G2].equals("U ") || tablero[y_G2][x_G2].equals("G ")) {
                        x_G2--;
                    } else if (tablero[y_G2][x_G2].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G2][x_G2] = "G ";
                        tablero[y_G2][x_G2 - 1] = "x ";
                        guard2.setPosY(y_G2);
                        guard2.setPosX(x_G2);
                    }
                    break;
            }
            switch (newPosition3) {
                case 0:
                    //SUBIR
                    y_G3--;
                    if (y_G3 < 0) {
                        y_G3 = 0;
                    }
                    if (tablero[y_G3][x_G3].equals("P ") || tablero[y_G3][x_G3].equals("A ") || tablero[y_G3][x_G3].equals("U ") || tablero[y_G3][x_G3].equals("G ")) {
                        y_G3++;
                    } else if (tablero[y_G3][x_G3].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G3][x_G3] = "G ";
                        tablero[y_G3 + 1][x_G3] = "x ";
                        guard3.setPosY(y_G3);
                        guard3.setPosX(x_G3);
                    }
                    break;
                case 1:
                    //IZQUIERDA
                    x_G3--;
                    if (x_G3 < 0) {
                        x_G3 = 0;
                    }
                    if (tablero[y_G3][x_G3].equals("P ") || tablero[y_G3][x_G3].equals("A ") || tablero[y_G3][x_G3].equals("U ") || tablero[y_G3][x_G3].equals("G ")) {
                        x_G3++;
                    } else if (tablero[y_G3][x_G3].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G3][x_G3] = "G ";
                        tablero[y_G3][x_G3 + 1] = "x ";
                        guard3.setPosY(y_G3);
                        guard3.setPosX(x_G3);
                    }
                    break;
                case 2:
                    //BAJAR
                    y_G3++;
                    if (y_G3 > 9) {
                        y_G3 = 9;
                    }
                    if (tablero[y_G3][x_G3].equals("P ") || tablero[y_G3][x_G3].equals("A ") || tablero[y_G3][x_G3].equals("U ") || tablero[y_G3][x_G3].equals("G ")) {
                        y_G3--;
                    } else if (tablero[y_G3][x_G3].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G3][x_G3] = "G ";
                        tablero[y_G3 - 1][x_G3] = "x ";
                        guard3.setPosY(y_G3);
                        guard3.setPosX(x_G3);
                    }
                    break;
                default:
                    //DERECHA
                    x_G3++;
                    if (x_G3 > 9) {
                        x_G3 = 9;
                    }
                    if (tablero[y_G3][x_G3].equals("P ") || tablero[y_G3][x_G3].equals("A ") || tablero[y_G3][x_G3].equals("U ") || tablero[y_G3][x_G3].equals("G ")) {
                        x_G3--;
                    } else if (tablero[y_G3][x_G3].equals(character.getTipoPersonaje())) {
                        System.out.println("\nTE HAN ATRAPADO... HAS PERDIDO!");

                        exit(0);
                    } else {
                        tablero[y_G3][x_G3] = "G ";
                        tablero[y_G3][x_G3 - 1] = "x ";
                        guard3.setPosY(y_G3);
                        guard3.setPosX(x_G3);
                    }
                    break;
            }
        }
    }

    //CREACION Y CANTIDAD DE GUARDIAS
    static public void createGuardias(int num) {
        switch (num) {
            case 1:
                guard1 = new guardia();
                break;
            case 2:
                guard1 = new guardia();
                guard2 = new guardia();
                arrayGuardias.add(guard1);
                arrayGuardias.add(guard2);
                break;
            case 3:
                guard1 = new guardia();
                guard2 = new guardia();
                guard3 = new guardia();
                arrayGuardias.add(guard1);
                arrayGuardias.add(guard2);
                arrayGuardias.add(guard3);
                break;
        }
    }

    //DESPLIEGUE HERRAMIENTAS
    //El despliegue de herramientas será aleatorio en todo el tablero. HECHO
    //No podrá solapar casillas ni con guardias ni con el personaje.
    //No puede estar adyacente a ninguna otra herramienta.
    //Todas las casillas ocupadas por la herramienta deben ser adyacentes entre ellas (en forma de + no de x).
    //Posiciones de los elementos
    static int P_Columna;
    static int P_Fila;

    static int A_Columna_0;
    static int A_Fila_0;
    static int A_Columna_1;
    static int A_Fila_1;

    static int U_Columna_0;
    static int U_Fila_0;
    static int U_Columna_1;
    static int U_Fila_1;
    static int U_Columna_2;
    static int U_Fila_2;

    static int G_Columna_1;
    static int G_Fila_1;
    static int G_Columna_2;
    static int G_Fila_2;
    static int G_Columna_3;
    static int G_Fila_3;

    static int C_Columna;
    static int C_Fila;

    public static void checkCasillas() {

        P_Columna = passport.getPosiciones().get(0).columnas;
        P_Fila = passport.getPosiciones().get(0).filas;

        A_Columna_0 = pliers.getPosiciones().get(0).columnas;
        A_Fila_0 = pliers.getPosiciones().get(0).filas;
        A_Columna_1 = pliers.getPosiciones().get(1).columnas;
        A_Fila_1 = pliers.getPosiciones().get(1).filas;

        U_Columna_0 = uniform.getPosiciones().get(0).columnas;
        U_Fila_0 = uniform.getPosiciones().get(0).filas;
        U_Columna_1 = uniform.getPosiciones().get(1).columnas;
        U_Fila_1 = uniform.getPosiciones().get(1).filas;
        U_Columna_2 = uniform.getPosiciones().get(2).columnas;
        U_Fila_2 = uniform.getPosiciones().get(2).filas;

        C_Columna = character.getPosPersonaje().getColumnas();
        C_Fila = character.getPosPersonaje().getFilas();

        if (numG == 1) {
            G_Columna_1 = guard1.getPosX();
            G_Fila_1 = guard1.getPosY();
        } else if (numG == 2) {
            G_Columna_2 = guard2.getPosX();
            G_Fila_2 = guard2.getPosY();
        } else {
            G_Columna_3 = guard3.getPosX();
            G_Fila_3 = guard3.getPosY();
        }

        //CONTROLAR QUE NO SE ELIMINEN ENTRE ELLOS
        boolean cercania = true;

        while (cercania) {
            //Comparamos los Alicates
            if ((P_Columna == A_Columna_0 && P_Fila == A_Fila_0) || (P_Columna == A_Columna_1 && P_Fila == A_Fila_1) || (U_Columna_0 == A_Columna_0 && U_Fila_0 == A_Fila_0) || (U_Columna_1 == A_Columna_0 && U_Fila_1 == A_Fila_0) || (U_Columna_2 == A_Columna_0 && U_Fila_2 == A_Fila_0) || (U_Columna_0 == A_Columna_1 && U_Fila_0 == A_Fila_1) || (U_Columna_1 == A_Columna_1 && U_Fila_1 == A_Fila_1) || (U_Columna_2 == A_Columna_1 && U_Fila_2 == A_Fila_1)) {
                while ((P_Columna == A_Columna_0 && P_Fila == A_Fila_0) || (P_Columna == A_Columna_1 && P_Fila == A_Fila_1) || (U_Columna_0 == A_Columna_0 && U_Fila_0 == A_Fila_0) || (U_Columna_1 == A_Columna_0 && U_Fila_1 == A_Fila_0) || (U_Columna_2 == A_Columna_0 && U_Fila_2 == A_Fila_0) || (U_Columna_0 == A_Columna_1 && U_Fila_0 == A_Fila_1) || (U_Columna_1 == A_Columna_1 && U_Fila_1 == A_Fila_1) || (U_Columna_2 == A_Columna_1 && U_Fila_2 == A_Fila_1)) {
                    pliers = new alicates();// Si coinciden, creo nuevos alicates hasta que las posiciones no coincidan

                    //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Alicates PARA ACTUALIZAR LAS VARIABLES DE POSICION
                    A_Columna_0 = pliers.getPosiciones().get(0).columnas;
                    A_Fila_0 = pliers.getPosiciones().get(0).filas;
                    A_Columna_1 = pliers.getPosiciones().get(1).columnas;
                    A_Fila_1 = pliers.getPosiciones().get(1).filas;
                }

            } //Comparamos el Uniforme
            else if ((P_Columna == U_Columna_0 && P_Fila == U_Fila_0) || (P_Columna == U_Columna_1 && P_Fila == U_Fila_1) || (P_Columna == U_Columna_2 && P_Fila == U_Fila_2) || (A_Columna_0 == U_Columna_0 && A_Fila_0 == U_Fila_0) || (A_Columna_0 == U_Columna_1 && A_Fila_0 == U_Fila_1) || (A_Columna_0 == U_Columna_2 && A_Fila_0 == U_Fila_2) || (A_Columna_1 == U_Columna_0 && A_Fila_1 == U_Fila_0) || (A_Columna_1 == U_Columna_1 && A_Fila_1 == U_Fila_1) || (A_Columna_1 == U_Columna_2 && A_Fila_1 == U_Fila_2)) {
                while ((P_Columna == U_Columna_0 && P_Fila == U_Fila_0) || (P_Columna == U_Columna_1 && P_Fila == U_Fila_1) || (P_Columna == U_Columna_2 && P_Fila == U_Fila_2)) {
                    uniform = new uniforme();// Si coinciden, creo un nuevo uniforme hasta que las posiciones no coincidan

                    //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Uniforme PARA ACTUALIZAR LAS VARIABLES DE POSICION
                    U_Columna_0 = uniform.getPosiciones().get(0).columnas;
                    U_Fila_0 = uniform.getPosiciones().get(0).filas;
                    U_Columna_1 = uniform.getPosiciones().get(1).columnas;
                    U_Fila_1 = uniform.getPosiciones().get(1).filas;
                    U_Columna_2 = uniform.getPosiciones().get(2).columnas;
                    U_Fila_2 = uniform.getPosiciones().get(2).filas;
                }
            } //Comparamos el Personaje
            else if (numG == 2) {
                if ((P_Columna == C_Columna && P_Fila == C_Fila) || (A_Columna_0 == C_Columna && A_Fila_0 == C_Fila) || (A_Columna_1 == C_Columna && A_Fila_1 == C_Fila) || (U_Columna_0 == C_Columna && U_Fila_0 == C_Fila) || (U_Columna_1 == C_Columna && U_Fila_1 == C_Fila) || (U_Columna_2 == C_Columna && U_Fila_2 == C_Fila)) {
                    while ((P_Columna == C_Columna && P_Fila == C_Fila) || (A_Columna_0 == C_Columna && A_Fila_0 == C_Fila) || (A_Columna_1 == C_Columna && A_Fila_1 == C_Fila) || (U_Columna_0 == C_Columna && U_Fila_0 == C_Fila) || (U_Columna_1 == C_Columna && U_Fila_1 == C_Fila) || (U_Columna_2 == C_Columna && U_Fila_2 == C_Fila)) {
                        character = new personaje();

                        C_Columna = character.getPosPersonaje().getColumnas();
                        C_Fila = character.getPosPersonaje().getFilas();
                    }
                }
            }
            //Comparamos el Guardia
            if (numG == 1) {
                if ((P_Columna == G_Columna_1 && P_Fila == G_Fila_1) || (A_Columna_0 == G_Columna_1 && A_Fila_0 == G_Fila_1) || (A_Columna_1 == G_Columna_1 && A_Fila_1 == G_Fila_1) || (U_Columna_0 == G_Columna_1 && U_Fila_0 == G_Fila_1) || (U_Columna_1 == G_Columna_1 && U_Fila_1 == G_Fila_1) || (U_Columna_2 == G_Columna_1 && U_Fila_2 == G_Fila_1) || (C_Columna == G_Columna_1 && C_Fila == G_Fila_1)) {
                    while ((P_Columna == G_Columna_1 && P_Fila == G_Fila_1) || (A_Columna_0 == G_Columna_1 && A_Fila_0 == G_Fila_1) || (A_Columna_1 == G_Columna_1 && A_Fila_1 == G_Fila_1) || (U_Columna_0 == G_Columna_1 && U_Fila_0 == G_Fila_1) || (U_Columna_1 == G_Columna_1 && U_Fila_1 == G_Fila_1) || (U_Columna_2 == G_Columna_1 && U_Fila_2 == G_Fila_1) || (C_Columna == G_Columna_1 && C_Fila == G_Fila_1)) {
                        guard1 = new guardia();

                        G_Columna_1 = guard1.getPosX();
                        G_Fila_1 = guard1.getPosY();
                    }
                }
            } else if (numG == 2) {
                if ((P_Columna == G_Columna_2 && P_Fila == G_Fila_2) || (A_Columna_0 == G_Columna_2 && A_Fila_0 == G_Fila_2) || (A_Columna_1 == G_Columna_2 && A_Fila_1 == G_Fila_2) || (U_Columna_0 == G_Columna_2 && U_Fila_0 == G_Fila_2) || (U_Columna_1 == G_Columna_2 && U_Fila_1 == G_Fila_2) || (U_Columna_2 == G_Columna_2 && U_Fila_2 == G_Fila_2) || (C_Columna == G_Columna_2 && C_Fila == G_Fila_2) || (G_Columna_1 == G_Columna_2 && G_Fila_1 == G_Fila_2)) {
                    while ((P_Columna == G_Columna_2 && P_Fila == G_Fila_2) || (A_Columna_0 == G_Columna_2 && A_Fila_0 == G_Fila_2) || (A_Columna_1 == G_Columna_2 && A_Fila_1 == G_Fila_2) || (U_Columna_0 == G_Columna_2 && U_Fila_0 == G_Fila_2) || (U_Columna_1 == G_Columna_2 && U_Fila_1 == G_Fila_2) || (U_Columna_2 == G_Columna_2 && U_Fila_2 == G_Fila_2) || (C_Columna == G_Columna_2 && C_Fila == G_Fila_2) || (G_Columna_1 == G_Columna_2 && G_Fila_1 == G_Fila_2)) {
                        guard2 = new guardia();

                        G_Columna_2 = guard2.getPosX();
                        G_Fila_2 = guard2.getPosY();
                    }
                }
            } else {
                if ((P_Columna == G_Columna_3 && P_Fila == G_Fila_3) || (A_Columna_0 == G_Columna_3 && A_Fila_0 == G_Fila_3) || (A_Columna_1 == G_Columna_3 && A_Fila_1 == G_Fila_3) || (U_Columna_0 == G_Columna_3 && U_Fila_0 == G_Fila_3) || (U_Columna_1 == G_Columna_3 && U_Fila_1 == G_Fila_3) || (U_Columna_2 == G_Columna_3 && U_Fila_2 == G_Fila_3) || (C_Columna == G_Columna_3 && C_Fila == G_Fila_3) || (G_Columna_1 == G_Columna_3 && G_Fila_1 == G_Fila_3) || (G_Columna_2 == G_Columna_3 && G_Fila_2 == G_Fila_3)) {
                    while ((P_Columna == G_Columna_3 && P_Fila == G_Fila_3) || (A_Columna_0 == G_Columna_3 && A_Fila_0 == G_Fila_3) || (A_Columna_1 == G_Columna_3 && A_Fila_1 == G_Fila_3) || (U_Columna_0 == G_Columna_3 && U_Fila_0 == G_Fila_3) || (U_Columna_1 == G_Columna_3 && U_Fila_1 == G_Fila_3) || (U_Columna_2 == G_Columna_3 && U_Fila_2 == G_Fila_3) || (C_Columna == G_Columna_3 && C_Fila == G_Fila_3) || (G_Columna_1 == G_Columna_3 && G_Fila_1 == G_Fila_3) || (G_Columna_2 == G_Columna_3 && G_Fila_2 == G_Fila_3)) {
                        guard3 = new guardia();

                        G_Columna_3 = guard3.getPosX();
                        G_Fila_3 = guard3.getPosY();
                    }
                }
            }

            cercania = checkCercania();
        }
    }

    public static boolean checkCercania() {
        if (((Math.abs(P_Columna - A_Columna_0) == 1 || P_Columna - A_Columna_0 == 0) && (Math.abs(P_Fila - A_Fila_0) == 1 || P_Fila - A_Fila_0 == 0))
                || (Math.abs(P_Columna - A_Columna_1) == 1 || P_Columna - A_Columna_1 == 0) && (Math.abs(P_Fila - A_Fila_1) == 1 || P_Fila - A_Fila_1 == 0)) {
            pliers = new alicates();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Alicates PARA ACTUALIZAR LAS VARIABLES DE POSICION
            A_Columna_0 = pliers.getPosiciones().get(0).columnas;
            A_Fila_0 = pliers.getPosiciones().get(0).filas;
            A_Columna_1 = pliers.getPosiciones().get(1).columnas;
            A_Fila_1 = pliers.getPosiciones().get(1).filas;
            return true;
        } else if (((Math.abs(P_Columna - U_Columna_0) == 1 || P_Columna - U_Columna_0 == 0) && (Math.abs(P_Fila - U_Fila_0) == 1 || P_Fila - U_Fila_0 == 0))
                || (Math.abs(P_Columna - U_Columna_1) == 1 || P_Columna - U_Columna_1 == 0) && (Math.abs(P_Fila - U_Fila_1) == 1 || P_Fila - U_Fila_1 == 0)
                || (Math.abs(P_Columna - U_Columna_2) == 1 || P_Columna - U_Columna_2 == 0) && (Math.abs(P_Fila - U_Fila_2) == 1 || P_Fila - U_Fila_2 == 0)) {
            uniform = new uniforme();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Uniforme PARA ACTUALIZAR LAS VARIABLES DE POSICION
            U_Columna_0 = uniform.getPosiciones().get(0).columnas;
            U_Fila_0 = uniform.getPosiciones().get(0).filas;
            U_Columna_1 = uniform.getPosiciones().get(1).columnas;
            U_Fila_1 = uniform.getPosiciones().get(1).filas;
            U_Columna_2 = uniform.getPosiciones().get(2).columnas;
            U_Fila_2 = uniform.getPosiciones().get(2).filas;
            return true;
        } else if ((Math.abs(P_Columna - G_Columna_1) == 1 || P_Columna - G_Columna_1 == 0) && (Math.abs(P_Fila - G_Fila_1) == 1 || P_Fila - G_Fila_1 == 0)) {
            guard1 = new guardia();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Guardia PARA ACTUALIZAR LAS VARIABLES DE POSICION
            G_Columna_1 = guard1.getPosX();
            G_Fila_1 = guard1.getPosY();
            return true;
        } else if (((Math.abs(A_Columna_0 - U_Columna_0) == 1 || A_Columna_0 - U_Columna_0 == 0) && (Math.abs(A_Fila_0 - U_Fila_0) == 1 || A_Fila_0 - U_Fila_0 == 0))
                || (Math.abs(A_Columna_0 - U_Columna_1) == 1 || A_Columna_0 - U_Columna_1 == 0) && (Math.abs(A_Fila_0 - U_Fila_1) == 1 || A_Fila_0 - U_Fila_1 == 0)
                || (Math.abs(A_Columna_0 - U_Columna_2) == 1 || A_Columna_0 - U_Columna_2 == 0) && (Math.abs(A_Fila_0 - U_Fila_2) == 1 || A_Fila_0 - U_Fila_2 == 0)) {
            uniform = new uniforme();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Uniforme PARA ACTUALIZAR LAS VARIABLES DE POSICION
            U_Columna_0 = uniform.getPosiciones().get(0).columnas;
            U_Fila_0 = uniform.getPosiciones().get(0).filas;
            U_Columna_1 = uniform.getPosiciones().get(1).columnas;
            U_Fila_1 = uniform.getPosiciones().get(1).filas;
            U_Columna_2 = uniform.getPosiciones().get(2).columnas;
            U_Fila_2 = uniform.getPosiciones().get(2).filas;
            return true;
        } else if (((Math.abs(A_Columna_1 - U_Columna_0) == 1 || A_Columna_1 - U_Columna_0 == 0) && (Math.abs(A_Fila_1 - U_Fila_0) == 1 || A_Fila_1 - U_Fila_0 == 0))
                || (Math.abs(A_Columna_1 - U_Columna_1) == 1 || A_Columna_1 - U_Columna_1 == 0) && (Math.abs(A_Fila_1 - U_Fila_1) == 1 || A_Fila_1 - U_Fila_1 == 0)
                || (Math.abs(A_Columna_1 - U_Columna_2) == 1 || A_Columna_1 - U_Columna_2 == 0) && (Math.abs(A_Fila_1 - U_Fila_2) == 1 || A_Fila_1 - U_Fila_2 == 0)) {
            uniform = new uniforme();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Uniforme PARA ACTUALIZAR LAS VARIABLES DE POSICION
            U_Columna_0 = uniform.getPosiciones().get(0).columnas;
            U_Fila_0 = uniform.getPosiciones().get(0).filas;
            U_Columna_1 = uniform.getPosiciones().get(1).columnas;
            U_Fila_1 = uniform.getPosiciones().get(1).filas;
            U_Columna_2 = uniform.getPosiciones().get(2).columnas;
            U_Fila_2 = uniform.getPosiciones().get(2).filas;
            return true;
        } else if (((Math.abs(P_Columna - G_Columna_1) == 1 || P_Columna - G_Columna_1 == 0) && (Math.abs(P_Fila - G_Fila_1) == 1 || P_Fila - G_Fila_1 == 0))) {
            guard1 = new guardia();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Guardia PARA ACTUALIZAR LAS VARIABLES DE POSICION
            G_Columna_1 = guard1.getPosX();
            G_Fila_1 = guard1.getPosY();
            return true;
        } else if (((Math.abs(P_Columna - G_Columna_2) == 1 || P_Columna - G_Columna_2 == 0) && (Math.abs(P_Fila - G_Fila_2) == 1 || P_Fila - G_Fila_2 == 0))) {
            guard2 = new guardia();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Guardia PARA ACTUALIZAR LAS VARIABLES DE POSICION
            G_Columna_2 = guard2.getPosX();
            G_Fila_2 = guard2.getPosY();
            return true;
        } else if (((Math.abs(P_Columna - G_Columna_3) == 1 || P_Columna - G_Columna_3 == 0) && (Math.abs(P_Fila - G_Fila_3) == 1 || P_Fila - G_Fila_3 == 0))) {
            guard3 = new guardia();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Guardia PARA ACTUALIZAR LAS VARIABLES DE POSICION
            G_Columna_3 = guard3.getPosX();
            G_Fila_3 = guard3.getPosY();
            return true;
        } else if (((Math.abs(A_Columna_0 - G_Columna_1) == 1 || A_Columna_0 - G_Columna_1 == 0) && (Math.abs(A_Fila_0 - G_Fila_1) == 1 || A_Fila_0 - G_Fila_1 == 0))
                || (Math.abs(A_Columna_1 - G_Columna_1) == 1 || A_Columna_1 - G_Columna_1 == 0) && (Math.abs(A_Fila_1 - G_Fila_1) == 1 || A_Fila_1 - G_Fila_1 == 0)) {
            guard1 = new guardia();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Guardia PARA ACTUALIZAR LAS VARIABLES DE POSICION
            G_Columna_1 = guard1.getPosX();
            G_Fila_1 = guard1.getPosY();
            return true;
        } else if (((Math.abs(A_Columna_0 - G_Columna_2) == 1 || A_Columna_0 - G_Columna_2 == 0) && (Math.abs(A_Fila_0 - G_Fila_2) == 1 || A_Fila_0 - G_Fila_2 == 0))
                || (Math.abs(A_Columna_1 - G_Columna_2) == 1 || A_Columna_1 - G_Columna_2 == 0) && (Math.abs(A_Fila_1 - G_Fila_2) == 1 || A_Fila_1 - G_Fila_2 == 0)) {
            guard2 = new guardia();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Guardia PARA ACTUALIZAR LAS VARIABLES DE POSICION
            G_Columna_2 = guard2.getPosX();
            G_Fila_2 = guard2.getPosY();
            return true;
        } else if (((Math.abs(A_Columna_0 - G_Columna_3) == 1 || A_Columna_0 - G_Columna_3 == 0) && (Math.abs(A_Fila_0 - G_Fila_3) == 1 || A_Fila_0 - G_Fila_3 == 0))
                || (Math.abs(A_Columna_1 - G_Columna_3) == 1 || A_Columna_1 - G_Columna_3 == 0) && (Math.abs(A_Fila_1 - G_Fila_3) == 1 || A_Fila_1 - G_Fila_3 == 0)) {
            guard3 = new guardia();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Guardia PARA ACTUALIZAR LAS VARIABLES DE POSICION
            G_Columna_3 = guard3.getPosX();
            G_Fila_3 = guard3.getPosY();
            return true;
        } else if (((Math.abs(P_Columna - C_Columna) == 1 || P_Columna - C_Columna == 0) && (Math.abs(P_Fila - C_Fila) == 1 || P_Fila - C_Fila == 0))) {
            character = new personaje();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Personaje PARA ACTUALIZAR LAS VARIABLES DE POSICION
            C_Columna = character.getPosPersonaje().getColumnas();
            C_Fila = character.getPosPersonaje().getFilas();
            return true;
        } else if (((Math.abs(A_Columna_0 - C_Columna) == 1 || A_Columna_0 - C_Columna == 0) && (Math.abs(A_Fila_0 - C_Fila) == 1 || A_Fila_0 - C_Fila == 0))
                || (Math.abs(A_Columna_1 - C_Columna) == 1 || A_Columna_1 - C_Columna == 0) && (Math.abs(A_Fila_1 - C_Fila) == 1 || A_Fila_1 - C_Fila == 0)) {
            character = new personaje();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Personaje PARA ACTUALIZAR LAS VARIABLES DE POSICION
            C_Columna = character.getPosPersonaje().getColumnas();
            C_Fila = character.getPosPersonaje().getFilas();
            return true;
        } else if (((Math.abs(U_Columna_0 - C_Columna) == 1 || U_Columna_0 - C_Columna == 0) && (Math.abs(U_Fila_0 - C_Fila) == 1 || U_Fila_0 - C_Fila == 0))
                || ((Math.abs(U_Columna_1 - C_Columna) == 1 || U_Columna_1 - C_Columna == 0) && (Math.abs(U_Fila_1 - C_Fila) == 1 || U_Fila_1 - C_Fila == 0))
                || ((Math.abs(U_Columna_2 - C_Columna) == 1 || U_Columna_2 - C_Columna == 0) && (Math.abs(U_Fila_2 - C_Fila) == 1 || U_Fila_2 - C_Fila == 0))) {

            character = new personaje();

            //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Personaje PARA ACTUALIZAR LAS VARIABLES DE POSICION
            C_Columna = character.getPosPersonaje().getColumnas();
            C_Fila = character.getPosPersonaje().getFilas();
            return true;
        }

        //Guardia
        if (numG == 1) {
            if (((Math.abs(G_Columna_1 - U_Columna_0) == 1 || G_Columna_1 - U_Columna_0 == 0) && (Math.abs(G_Fila_1 - U_Fila_0) == 1 || G_Fila_1 - U_Fila_0 == 0))
                    || ((Math.abs(G_Columna_1 - U_Columna_1) == 1 || G_Columna_1 - U_Columna_1 == 0) && (Math.abs(G_Fila_1 - U_Fila_1) == 1 || G_Fila_1 - U_Fila_1 == 0))
                    || ((Math.abs(G_Columna_1 - U_Columna_2) == 1 || G_Columna_1 - U_Columna_2 == 0) && (Math.abs(G_Fila_1 - U_Fila_2) == 1 || G_Fila_1 - U_Fila_2 == 0))) {

                uniform = new uniforme();

                //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Uniforme PARA ACTUALIZAR LAS VARIABLES DE POSICION
                U_Columna_0 = uniform.getPosiciones().get(0).columnas;
                U_Fila_0 = uniform.getPosiciones().get(0).filas;
                U_Columna_1 = uniform.getPosiciones().get(1).columnas;
                U_Fila_1 = uniform.getPosiciones().get(1).filas;
                U_Columna_2 = uniform.getPosiciones().get(2).columnas;
                U_Fila_2 = uniform.getPosiciones().get(2).filas;
                return true;
            }
        } else if (numG == 2) {
            if (((Math.abs(G_Columna_2 - U_Columna_0) == 1 || G_Columna_2 - U_Columna_0 == 0) && (Math.abs(G_Fila_2 - U_Fila_0) == 1 || G_Fila_2 - U_Fila_0 == 0))
                    || ((Math.abs(G_Columna_2 - U_Columna_1) == 1 || G_Columna_2 - U_Columna_1 == 0) && (Math.abs(G_Fila_2 - U_Fila_1) == 1 || G_Fila_2 - U_Fila_1 == 0))
                    || ((Math.abs(G_Columna_2 - U_Columna_2) == 1 || G_Columna_2 - U_Columna_2 == 0) && (Math.abs(G_Fila_2 - U_Fila_2) == 1 || G_Fila_2 - U_Fila_2 == 0))) {

                uniform = new uniforme();

                //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Uniforme PARA ACTUALIZAR LAS VARIABLES DE POSICION
                U_Columna_0 = uniform.getPosiciones().get(0).columnas;
                U_Fila_0 = uniform.getPosiciones().get(0).filas;
                U_Columna_1 = uniform.getPosiciones().get(1).columnas;
                U_Fila_1 = uniform.getPosiciones().get(1).filas;
                U_Columna_2 = uniform.getPosiciones().get(2).columnas;
                U_Fila_2 = uniform.getPosiciones().get(2).filas;
                return true;
            }
        } else {
            if (((Math.abs(G_Columna_3 - U_Columna_0) == 1 || G_Columna_3 - U_Columna_0 == 0) && (Math.abs(G_Fila_3 - U_Fila_0) == 1 || G_Fila_3 - U_Fila_0 == 0))
                    || ((Math.abs(G_Columna_3 - U_Columna_1) == 1 || G_Columna_3 - U_Columna_1 == 0) && (Math.abs(G_Fila_3 - U_Fila_1) == 1 || G_Fila_3 - U_Fila_1 == 0))
                    || ((Math.abs(G_Columna_3 - U_Columna_2) == 1 || G_Columna_3 - U_Columna_2 == 0) && (Math.abs(G_Fila_3 - U_Fila_2) == 1 || G_Fila_3 - U_Fila_2 == 0))) {

                uniform = new uniforme();

                //HAY QUE LLAMAR DE NUEVO A LAS POSICIONES DE Uniforme PARA ACTUALIZAR LAS VARIABLES DE POSICION
                U_Columna_0 = uniform.getPosiciones().get(0).columnas;
                U_Fila_0 = uniform.getPosiciones().get(0).filas;
                U_Columna_1 = uniform.getPosiciones().get(1).columnas;
                U_Fila_1 = uniform.getPosiciones().get(1).filas;
                U_Columna_2 = uniform.getPosiciones().get(2).columnas;
                U_Fila_2 = uniform.getPosiciones().get(2).filas;
                return true;
            }
        }

        //Hay que poner mas ELSE IF para las demas herramientas, no solo para el pasaporte
        return false;
    }
}
