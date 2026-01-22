package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import model.Hero;

/**
 * Klasse zum Starten und Verwalten des Spiels.
 * @author Berkant Kaygan
 * @author Luca Weber
 */

public class EscapeApp {

    public static final String SAVE_FILE_NAME = "save";
    private EscapeGame game;
    private final boolean gameRunning = true;
    private final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

    EscapeApp app = new EscapeApp();
    

        while (true) {
            app.showMainMenu();
            String choice = app.readUserInput();
            clearConsole();
            app.handleUserInput(choice);
            System.out.println("====================");
        }

        // creates hero and ask for name

        // start game menü
      
    }

    /**
     * Zeit die optionen im Hauptmenü an.
     */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");
        if(this.game != null){
            System.out.println("(2) Resume game"); //läuft nicht wenn istGameRunning false ist. gameExists methode hinzufügen (gameExists = true)
        }
        if(true == hasSavedGame()){
            System.out.println("(3) Load game");
        }
        if(this.game != null){
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
        String userInput = this.scanner.nextLine();
        return userInput;
    }


    /**
     * Fügt 50 Leerzeilen ein, um die Konsole zu "löschen".
     */
    public static void clearConsole(){
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
                if(this.game != null){
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
                if(this.game != null){
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
     * erfragt den namen des helden und initialisiert das spiel.
     */
    private void startGame() {
        System.out.println("Starting a new game...");
        System.out.println("Please enter your hero's name: ");
        System.out.print("> ");
        String name = readUserInput();
        clearConsole();

        if(name == null || name.trim().isEmpty()){
            name = "Blank";
        }
        
        Hero hero = new Hero();
        hero.setName(name.trim());


        this.game = new EscapeGame(hero);
        System.out.println("Welcome " + hero.getName() + "! Your adventure begins now."); 
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
        // close shared scanner and exit
        try {
            this.scanner.close();
        } catch (Exception ignored) {}
        System.exit(0);
    }

    /**
     * prüft ob ein spiel läuft.
     * @return true wenn ein spiel läuft, sonst false.
     */
    private boolean isGameRunning() {
        return game != null && game.isGameRunning()
        ;
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
