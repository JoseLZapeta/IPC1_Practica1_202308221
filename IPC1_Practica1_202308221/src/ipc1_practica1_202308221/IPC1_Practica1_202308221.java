package ipc1_practica1_202308221;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Jose Juan Laynez Zapeta
 */
public class IPC1_Practica1_202308221 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        int option;
        int multiFunct;
        String[] studData = {"Jose Juan Laynez Zapeta","202308221", "A"};
        String[] tempUserData = new String[3];
        String[][] playerData = null;
        System.out.println("Bienvenido a la Sopa de Letras");
        do {
            System.out.println("1 - Nueva partida.");
            System.out.println("2 - Historial de partidas.");
            System.out.println("3 - Puntuaciones mas altas.");
            System.out.println("4 - Informacion del estudiante.");
            System.out.println("5 - Salir.");
            option = scanner.nextInt();
            
            if (option == 1) {
                tempUserData = newGame(scanner, playerData);
            } else if (option == 2) {
                
            } else if (option == 3) {
                
            } else if (option == 4) {
                System.out.println("Estudiante: "+studData[0]+"\nCarnet: "+studData[1]+"\nSeccion: "
                + studData[2]);
            } else if (option == 5) {
                System.out.println("Saliendo, que la pase bien...");
            }
            
        } while (option!=5);
        
        scanner.close();
    }
    
    public static String[] newGame(Scanner scanner, String[][] playerData){
        String[] currUser = new String[3];
        System.out.println("Ingrese su nombre");
        currUser[0]=scanner.next();
        String[] wordList=null;
        int option;
        
        do {
            System.out.println("-> 1 - Menu de Palabras");
            System.out.println("-> 2 - Jugar");
            System.out.println("-> 3 - Terminar Partida");
            option=scanner.nextInt();
            
            switch (option){
                case 1:
                    wordList = editMenu(scanner);
                    break;
                case 2:
                    if (wordList.length!=0) {
                        
                    }else{
                        System.out.println("No se han ingresado palabras.\nIngresa algunas en el Menu de Palabras.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo, gracias por jugar...");
                    break;
                default:
                    throw new AssertionError();
            }
            
            
        } while (option!=3);
        
        return currUser;
    }
    
    public static String[] editMenu(Scanner scanner){
        int option;
        String[] wordList = null;
        do {
            System.out.println("-> 1 - Insertar palabras");
            System.out.println("-> 2 - Modificar palabras");
            System.out.println("-> 3 - Eliminar Palabras");
            System.out.println("-> 4 - Regresar");
            option=scanner.nextInt();
            
            switch (option) {
                case 1:
                    wordList = addWords(scanner);
                    break;
                case 2:
                    repWord(scanner, wordList);
                    break;
                case 3:
                    wordList=delWord(scanner,wordList);
                    break;
                case 4:
                    System.out.println("Regresando...");
                    break;
                default:
                    break;
            }
        } while (option!=4);
        
        return wordList;
    }
   
    
    public static String[] addWords(Scanner scanner){
        int wordNum;
        String wordCheck;
        String[]tempList; 
        System.out.println("¿Cuantas palabras desea en el tablero?");
                do {
                wordNum = scanner.nextInt();
                
                    if (wordNum>0) {
                        /*Corre el codigo si el numero ingresado es valido.*/
                        String[] wordList = new String[wordNum];
                        for (int i = 0; i < wordNum; i++) {
                            do {
                                System.out.println("Ingrese palabra la palabra No."+(i+1));
                                wordCheck=scanner.next();
                                if (wordCheck.length()>=4&&wordCheck.length()<=10) {
                                    wordList[i] = wordCheck;
                                } else {
                                    System.out.println("Palabra no cumple con caractersticas.\nDebe tener menos de 10 caracteres y mas de 4.");
                                }
                            } while (wordCheck.length()>10||wordCheck.length()<4);
                        }
                        tempList = wordList;
                        break;
                    } else {
                        /*Vuelve a pedir el numero*/
                        System.out.println("El numero ingresado no es valido, ingrese otro."); 
                        tempList=null;
                    }
                } while (wordNum>=0);
                
                return tempList;
    }
    
    public static void repWord(Scanner scanner, String[] wordList){
        String toRepWord;
        String repWord;
        int verif=0;
        System.out.println("Ingrese la palabra a modificar.");
        toRepWord = scanner.next();
        System.out.println("Ingrese la palabra nueva.");
        repWord = scanner.next();
        
        for (int i = 0; i < wordList.length; i++) {
            if (wordList[i].contains(toRepWord)) {
                if (repWord.length()>=4&&repWord.length()<=10) {
                    wordList[i]=repWord;
                    verif=1;
                } else{
                    verif=0;
                }
            } else {
                verif=2;
            }
        }
        
        switch (verif) {
            case 0:
                System.out.println("Palabra no cumple con caractersticas.\nDebe tener menos de 10 caracteres y mas de 4.");
                break;
            case 1:
                System.out.println("La palabra a ha sido cambiada correctamente.");
                break;
            case 2:
                System.out.println("La palabra ingresada no coincide con ninguna de la lista.");
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public static String[] delWord(Scanner scanner, String[] wordList){
        String toDelWord;
        int verif=0;
        System.out.println("Ingrese la palabra que desea borrar");
        toDelWord=scanner.next();
        for (int i = 0; i < wordList.length; i++) {
            if (wordList[i].matches(toDelWord)) {
                verif=+1;
            }
        }
        String[] newWordList = new String[wordList.length-verif];
        if (verif>0) {
            verif=0;
            for (int i = 0; i < wordList.length; i++) {
            if (wordList[i].matches(toDelWord)) {
            } else {
                newWordList[verif]=wordList[i];
                verif+=1;
            }
        }
            System.out.println("Se ha eliminado la palabra con éxito.");
        } else{
            newWordList=wordList;
            System.out.println("La palabra no se ha encontrado, intente de nuevo.");
        }
        
        return newWordList;
    }

    public static String[] gameSetup(Scanner scanner, String[] wordList){
        /*To create the board*/
        String[] playerStats = new String[3];
        char[][] board = new char[17][17];
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                board[i][j]=' ';
            }
        }
        /*division of a word to a sequence of characters inside an array*/
        
        
    return playerStats;
    
    
    
}
    
}

