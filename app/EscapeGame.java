
import model.Hero;
import model.HTWRoom;
import java.io.Serializable;
import java.util.Scanner; 
/*Konnte ich nur mit Ki lösen; laden und speichern haben exception geworfen, nachdem
* ich die serializable schnittstelle hinzugefügt habe, wie auch bei der Hero Klasse, hat es geklappt. Muss nochmal
* durchgegangen werden, warum das so ist.*/


/**
 * Klasse der Haupteil des Escape Games.
 * @author Berkant Kaygan
 * @author Luca Weber
 */


public class EscapeGame /*implements Serializable*/ { 
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    
    Scanner scanner1 = new Scanner(System.in);


    /**
     * Konstruktoor für das Escape Game.
     */
    public EscapeGame() {
        this.hero = new Hero();
    }
    

    public void intializeGame(){
        System.out.println("Please enter your hero's name: ");
        hero.setName(scanner1.nextLine());



    }

    /**
     * Zeigt das Spielmenü an.
     */
    public void printGameMenu(){
        System.out.println("What do you want to do next?");
        System.out.println("");
        System.out.println("=== Game Menu ===");
        System.out.println("Round: " + hero.getRoundsPlayed());
        System.out.println("1. Exlore College");
        System.out.println("2. Show Hero Status");
        System.out.println("3. Check Routing sheet");
        System.out.println("4. Take a break");
        System.out.println("5.Quit Game");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 5: ");


    }


    /**
     * verarbeitet die eingabe im spielmenü.
     * @param choice die eingabe des nutzers.
     */
    public void handleGameMenuInput(String choice){
        switch (choice) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            default:
                System.out.println("Invalid choice Try again.");
                break;

        }
    }





    /**
     * Prüft ob das spiel läuft.
     * @return true wenn das spiel läuft, sonst false.
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Setzt den spielstatus.
     * @param gameRunning true wenn das spiel läuft, sonst false.
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
     * Prüft ob das spiel beendet ist.
     * @return true wenn das spiel beendet ist, sonst false.
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Setzt den spiel beendet status.
     * @param gameFinished true wenn das spiel beendet ist, sonst false.
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }


    /**
     * fragt nutzer ob das spiel läuft.
     */
    public void run() {
        System.out.println("Welcome to HTW Escape Game!");
        System.out.println("----------------------------");
        System.out.println("What is your hero's name?");

    }

    /**
     * gibt den helden zurück.
     * @return der held.
     */
    public Hero getHero() {
        return hero;
    }
}
