package ipc1_practica1_202308221;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Jose Juan Laynez Zapeta
 * Comentario: No me pude inscribir a tiempo al laboratorio, por lo que no tuve mucho tiempo para hacerlo.
 */
public class IPC1_Practica1_202308221 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        int option;
        String[] studData = {"Jose Juan Laynez Zapeta","202308221", "A"};
        String[] tempUserData;
        String[][] playerData={};
        System.out.println("Bienvenido a la Sopa de Letras");
        do {
            System.out.println("1 - Nueva partida.");
            System.out.println("2 - Historial de partidas.");
            System.out.println("3 - Puntuaciones mas altas.");
            System.out.println("4 - Informacion del estudiante.");
            System.out.println("5 - Salir.");
            option = scanner.nextInt();
            
            if (option == 1) {
                tempUserData = newGame(scanner);
                playerData = addPlayerData(playerData,tempUserData);
            } else if (option == 2) {
                gameHistory(playerData);
            } else if (option == 3) {
            } else if (option == 4) {
                System.out.println("Estudiante: "+studData[0]+"\nCarnet: "+studData[1]+"\nSeccion: "
                + studData[2]);
            } else if (option == 5) {
                System.out.println("Saliendo, que la pase bien...");
            }
            
        } while (option!=5) ;
        
        scanner.close();
    }
    
    public static String[] newGame(Scanner scanner){
        System.out.println("Ingrese su nombre");
        String playerName=scanner.next();
        String[] gameData={};
        String[] wordList={};
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
                        gameData = gameSetup(scanner,wordList,playerName);
                        if (gameData[2]=="0") {
                            System.out.println("Has perdido, intentalo de nuevo.");
                        } else  {
                            System.out.println("Has ganado, gracias por jugar");
                        }
                        option=3;
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
        
        return gameData;
    }
    
    public static String[] editMenu(Scanner scanner){
        int option;
        String[] wordList = {};
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

    public static String[] gameSetup(Scanner scanner, String[] wordList, String currPlayer){
        /*To create the board*/
        Random random = new Random();
        char[][] board = new char[17][17];
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                board[i][j]=' ';
            }
        }
        
        /*division of a word to a sequence of characters inside an array*/
        for (int i = 0; i < wordList.length; i++) {
         char[] wordSep = wordList[i].toUpperCase().toCharArray();
         /*wordStatus represents the status of the word, if it was added already or not*/
         boolean wordStatus = false;
            while (!wordStatus) {
                /*When orientation is true, this means that the word will be added horizontally*/
                boolean orientation = true;               
                int colPos = random.nextInt(17);
                int rowPos = random.nextInt(17);
                
                if (orientation) {
                    if (colPos+wordSep.length<=17&&checkSpace(board,rowPos,colPos,wordSep.length, orientation)) {
                        for (int j = 0; j < wordSep.length; j++) {
                            board[rowPos][colPos+j]=wordSep[j];
                        }
                        wordStatus=true;
                    }
                    } else {
                    if (rowPos+wordSep.length<=17&&checkSpace(board,rowPos,colPos,wordSep.length, orientation)) {
                        for (int j = 0; j < wordSep.length; j++) {
                            board[rowPos+j][colPos]=wordSep[j];
                        }
                        wordStatus=true;
                    }
                }
            }
        }
        
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (board[i][j]==' ') {
                    board[i][j]=(char) ('A'+random.nextInt(26));
                }
            }
        }

        int points=25;
        int attempts=4;
        String wordAtt;
        int wordsLeft=wordList.length;
        String[] gameWords = wordList;
        boolean wordFound=false;
        do {
            showBoard(board);
            System.out.println("Palabras encontradas: " + (wordList.length-wordsLeft));
            System.out.println("Palabras restantes: " + wordsLeft);
            System.out.println("No. de intentos restantes: "+attempts);
            System.out.println("Puntos: "+points);
            System.out.println("Ingrese una palabra encontrada");
            wordAtt = scanner.next().toUpperCase();
            
            for (int i = 0; i < wordList.length; i++) {
                if (wordList[i].toUpperCase().equals(wordAtt)&&gameWords[i].toUpperCase().equals(wordAtt)) {
                    points+=wordAtt.length();
                    wordsLeft-=1;
                    gameWords[i]=" ";
                    wordFound=true;
                    
                    board = markingOut(board,wordList[i]);
                    
                }
            }
            if (!wordFound) {
                System.out.println("La palabra no es correcta");
                points-=5;
                attempts-=1;
            }
            wordFound=false;
            
            
            
        } while (attempts!=0&&wordsLeft>0);
        
        System.out.println("- - - - - Fin del juego- - - - -");
        System.out.println("Palabras Encontradas: "+(wordList.length-wordsLeft)+"/"+wordList.length);
        System.out.println("Intentos restantes:" + attempts);
        System.out.println("Puntos: " + points);
        String[] playerData={currPlayer,Integer.toString(points),Integer.toString(4-attempts),Integer.toString(wordList.length-wordsLeft)};
    return playerData;
    
    }
    
    public static void showBoard(char[][] board){
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 17; j++) {
                    System.out.print(board[i][j]+"\t");
                }
            System.out.println();
            }
    }
    
    public static boolean checkSpace(char[][] board, int row, int col, int wordLen, boolean orientation){
        if (orientation==true) {
            for (int i = 0; i < wordLen; i++) {
                if (board[row][col+i]==' ') {
                    System.out.println("se cambio");
                    return true;
                }
            }
            
        } else {
            for (int i = 0; i < wordLen; i++) {
                if (board[row+i][col]==' ') {
                    System.out.println("se cambio");
                    return true;
                }
            }
        }
        return false;
    }
    
    public static String[][] addPlayerData(String[][] oldData,String[] newPlayerData){
        String[][] newData = new String[oldData.length+1][3];
        for (int i = 0; i < oldData.length; i++) {
            newData[i]=oldData[i].clone();
        }
        newData[oldData.length]=newPlayerData.clone();
        return newData;
    }
    
    public static void gameHistory(String[][] playerData){
        System.out.println("Lista de juegos y resultados:");
        System.out.println("Puntaje | Puntos | Errores | palabras encontradas");
        for (int i = 0; i < playerData.length; i++) {
            System.out.println("- - - - - ");
            System.out.println(playerData[i][0]+ " | " + playerData[i][1] + " | "  + playerData[i][2] + " | "  + playerData[i][3]);
        }
        
    }
    
    public static void showSortedPlayers(String[][] playerData){
        String[][] orderedList = new String[2][1];
        for (int i = 0; i < playerData.length; i++) {
            for (int j = 0; j < (playerData.length-1-i); j++) {
                int score1 = Integer.parseInt(playerData[j][i]);
                int score2 = Integer.parseInt(playerData[j+1][1]);
                
                if (score1<score2&&Integer.parseInt(orderedList[0][1])<score1) {
                    orderedList[0+1][1]=orderedList[0][1];
                    orderedList[0+1][0]=orderedList[0][0];
                    orderedList[0][1]=Integer.toString(score1);
                    orderedList[0][0]=playerData[i][1];
                
                    if (score2>Integer.parseInt(orderedList[0][1])) {
                        orderedList[0][1]=Integer.toString(score2);
                        orderedList[0][0]=playerData[i][1+1];
                    }
                
            }
            
            
        }
    }
    }
    
    public static char[][] markingOut(char[][] oldBoard, String wordToMark){
        char[][] newBoard = oldBoard;
        int lenCheck = 0;
        int startId = 0;
        char[] wordFrags=wordToMark.toUpperCase().toCharArray();
        
        /*Lo revisa en horizontal*/
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (wordFrags[lenCheck]==newBoard[i][j]) {
                    lenCheck+=1;
                    if (lenCheck==1) {
                        startId=j;
                    }
                } else if(lenCheck==wordToMark.length()){
                    for (int k = 0; k < lenCheck; k++) {
                        newBoard[i][startId+k]='#';
                    }
                }else {
                    lenCheck=0;
                }
            }
        }
        lenCheck=0;
        
        /*Lo revisa en vertical*/
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (wordFrags[lenCheck]==newBoard[j][i]) {
                    lenCheck+=1;
                    if (lenCheck==1) {
                        startId=j;
                    }
                } else if(lenCheck==wordToMark.length()){
                    for (int k = 0; k < lenCheck; k++) {
                        newBoard[startId+k][i]='#';
                    }
                } else{
                    lenCheck=0;
                }
            }
        }
        
        return newBoard;
    }

}

