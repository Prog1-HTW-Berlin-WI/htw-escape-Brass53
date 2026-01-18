package app;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import model.Alien;
import model.FriendlyAlien;
import model.HTWRoom;
import model.Hero;
import model.HostileAlien;


/**
 * Klasse der Haupteil des Escape Games.
 * @author Berkant Kaygan
 * @author Luca Weber
 */


public class EscapeGame implements Serializable { 
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[24];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private Random randomNumber = new Random();
    
    Scanner scanner1 = new Scanner(System.in);



    public EscapeGame(Hero hero){
        this.hero = hero;
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
        System.out.println("Round: " + (hero.getRoundsPlayed() + 1));
        System.out.println("1. Exlore College");
        System.out.println("2. Show Hero Status");
        System.out.println("3. Check Routing sheet");
        System.out.println("4. Take a break");
        System.out.println("5. Quit Game");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 5: ");


    }

    //Zeigt den heldenstatus an.
    private void showHeroStatus(){
        System.out.println("=== Hero Status ===");
        System.out.println("Name: " + hero.getName());
        System.out.println("Rounds Played: " + hero.getRoundsPlayed());
        System.out.println("Health Points: " + hero.getHealthpoints());
        System.out.println("Experience Points: " + hero.getExperiencePoints());
        System.out.println("");

    }

    //zeigtt die routing sheet an.
    private void checkRoutingSheet(){
        System.out.println("=== Routing Sheet ===");
        System.out.println("Signatures Collected: " + hero.getSignedExerciseLeaders());
        System.out.println("");

    }

    //lässt den helden eine pause machen gibt ihn dadbei zwei optionen.
    private void takeABreak(){
        System.out.println("You take a break and recover some health points.");
        System.out.println("for a short one press 1, for a long one press 2:");
        String choice = scanner1.nextLine();
        if (choice.equals("1")){
            hero.regenerate("1");
        } else if (choice.equals("2")){
            hero.regenerate("2");
        } else {
            System.out.println("Invalid choice. No break taken.");
        }    
    
    }


    /**
     * verarbeitet die eingabe im spielmenü.
     * @param choice die eingabe des nutzers.
     */
    public void handleGameMenuInput(String choice){
        switch (choice) {
            case "1":
                exploreCollege();
                break;
            case "2":
                showHeroStatus();
                break;
            case "3":
                 checkRoutingSheet();
                break;
            case "4":
                takeABreak();
                break;
            case "5":
                setGameRunning(false);
                break;
            default:
                System.out.println("Invalid choice Try again.");
                break;

        }
    }







    public void exploreCollege(){
        hero.increaseRoundsPlayed();
        int currentRound = hero.getRoundsPlayed();
        System.out.println("Exploring the college... This is round " + currentRound + ". You have (24 - " + currentRound + ") rounds left.");
        System.out.println("");
    
        System.out.println("====================");

        if (currentRound >= 24) {
            System.out.println("Your Time is up! You are stuck here and who knows what will happen to you...");
            setGameFinished(true);
            setGameRunning(false);
        }

        int outcome = randomNumber.nextInt(100);

        if (outcome < 20){
            System.out.println("You find nothing interesting during your exploration.");
            System.out.println("");

        }
        else if (outcome > 72){
            /**alienEncounter();*/
            System.out.println("You encounter an alien!");
            System.out.println("");

        }
        else{
            /**int roomindex = randomNumber.nextInt(5);
            HTWRoom currentRoom = rooms[roomindex];

            String currentLecturerRoom = rooms[roomindex].getIdentifier();
            String currentLecturerName = rooms[roomindex].getLecturer().getName();
  
            System.out.println("You enter the Room " + currentLecturerRoom + " and see a lecturer.");
            System.out.println(currentLecturerName + " gives you a signature for your routing sheet!");*/
        }

    }

    public void alienEncounter(){
        
        Alien currentEnemy;

        boolean alienDecider = randomNumber.nextBoolean();
        if(alienDecider == true){
            currentEnemy = new FriendlyAlien();
        }
        else{
            currentEnemy = new HostileAlien();
        }

        System.out.println(currentEnemy.getName() + " steps forward!");
        System.out.println(currentEnemy.getGreetings());

        if(alienDecider == true){
            System.out.println("The alien seems friendly how do you want to proceed?");
            System.out.println("1. Give him a compliment and continue your way.");
            System.out.println("2. Fight the alien.");
            System.out.println("");
            System.out.println("Please choose a number between 1 and 2 ");
            String userChoiceAlienEncounterFriendly = scanner1.nextLine();
        }
        else{
            System.out.println("The alien looks hostile and prepares to attack you!");
            System.out.println("");
            //kampfmechanik
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
        if(hero.getName().equals("Blank Hero")){
            System.out.println("Welcome to HTW Escape Game!");
            System.out.println("----------------------------");
            System.out.println("What is your hero's name?");
        }
        
        while (isGameRunning()) {
            printGameMenu();
            String choice = scanner1.nextLine();
            EscapeApp.clearConsole();
            handleGameMenuInput(choice);
        }

    }

    /**
     * gibt den helden zurück.
     * @return der held.
     */
    public Hero getHero() {
        return hero;
    }
}
