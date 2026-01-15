import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;  
import java.util.Scanner;

/**
 * Klasse zum Starten und Verwalten des Spiels.
 * @author Berkant Kaygan
 * @author Luca Weber
 */

public class EscapeApp {

    public static final String SAVE_FILE_NAME = "save";
    private EscapeGame game;
    private boolean gameRunning = true;


    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();
        EscapeGame gameMenu = new EscapeGame();
        



        while (!app.isGameRunning()) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.clearConsole();
            app.handleUserInput(choice);
            System.out.println("====================");
        }

        // creates hero and ask for name

        // start game menü



      
    }

    /**
     * Zeigt die optionen im Hauptmenü an.
     */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");
        if(true == isGameRunning()){
            System.out.println("(2) Resume game");
        }
        if(true == hasSavedGame()){
            System.out.println("(3) Load game");
        }
        if(true == isGameRunning()){
            System.out.println("(4) Save game");
        }
        if(true == hasSavedGame()){
            System.out.println("(5) Delete saved game");
        }
        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
    }

    /**
     * liest die eingabe des benutzers.
     * @return gibt die eingabe des nutzers zurück.
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }


    /**
     * Fügt 50 Leerzeilen ein, um die Konsole zu "löschen".
     */
    private void clearConsole(){
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    /**
     * Settzt die eingabe des benutzers um.
     * @param input eingabe des benutzers.
     */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startGame();
                break;
            case "2":
                if(true == isGameRunning()){
                    this.resumeGame();
                }
                else{
                    System.out.println("No game is currently running to resume.");
                }
                break;
            case "3":
                if(true == hasSavedGame()){
                    this.loadGame();
                }
                else{
                    System.out.println("No saved game found to load.");
                }
                break;
            case "4":
                if(true == isGameRunning()){
                    this.saveGame();
                }
                else{
                    System.out.println("No game is currently running to save.");
                }
                break;
            case "5":
                if(true == hasSavedGame()){
                    this.deleteGame();
                }
                else{
                    System.out.println("No saved game found to delete.");
                }
                break;
            case "6":
                this.quitGame();
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }

    /**
     * Startet ein neues spiel.
     */
    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
    }

    /**
     * fährt mit dem aktellen spiel fort
     */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
     * löscht das gespeicherte spiel.
     */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    /**
     * speichert das aktuelle spiel.
     */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }

    /**
     * lädt ein gespeichertes spiel.
     */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
     * Verabschiedet und beendet das spiel
     */
    private void quitGame(){
        System.out.println("Thanks for playing Bye!");
        System.exit(0);
    }

    /**
     * prüft ob ein spiel läuft.
     * @return true wenn ein spiel läuft, sonst false.
     */
    private boolean isGameRunning() {
        return game != null;
    }

    /**
     * prüft ob das spiel beendet ist.
     * @return true wenn das spiel beendet ist, sonst false.
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * prüft ob ein gespeichertes spiel existiert.
     * @return true wenn ein gespeichertes spiel existiert, sonst false.
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}
