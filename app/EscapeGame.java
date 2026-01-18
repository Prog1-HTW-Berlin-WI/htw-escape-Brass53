package app;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import model.Alien;
import model.FriendlyAlien;
import model.HTWRoom;
import model.Hero;
import model.HostileAlien;
import model.Lecturer;


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
        initializeRooms();
    }


    

    public void intializeGame(){
        //System.out.println("Please enter your hero's name: ");
        //hero.setName(scanner1.nextLine());

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
        System.out.println("Exploring the college... This is round " + currentRound + ". You have (" + (24 - currentRound) + ") rounds left.");
        
    
        System.out.println("====================");
        System.out.println("");

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
        else if (outcome < 72){
            alienEncounter();
            /**System.out.println("You encounter an alien!");
            System.out.println("");*/

        }
        else{
            int roomindex = randomNumber.nextInt(5);
            HTWRoom currentRoom = rooms[roomindex];

            String currentLecturerRoom = rooms[roomindex].getIdentifier();
            String currentLecturerName = rooms[roomindex].getLecturer().getName();
  
            System.out.println("You enter the Room " + currentLecturerRoom + " and see a lecturer.");
            System.out.println(currentLecturerName + " gives you a signature for your routing sheet!");
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
            String choiceFriendly = scanner1.nextLine();
            handleChoiceFriendly(choiceFriendly);
        }
        else{
            System.out.println("The alien looks hostile and prepares to attack you!");
            System.out.println("Kampfmechanik wird hier noch implementiert");
            System.out.println("");
            //kampfmechanik
        }
    }

    public void handleChoiceFriendly(String choice){
        switch (choice) {
            case "1":
                EscapeApp.clearConsole();
                System.out.println("You give the alien a compliment and he seems pleased.");
                System.out.println("You keep going on your way safely.");
                System.out.println("");
                break;
            case "2":
                EscapeApp.clearConsole();
                System.out.println("You decide to fight the alien!");
                System.out.println("Kampfmechanik wird hier noch implementiert");
                System.out.println("");
                //kampfmechanik
                break;
            default:
                EscapeApp.clearConsole();
                System.out.println("Invalid choice try again.");
                System.out.println("");
                System.out.println("The alien seems friendly how do you want to proceed?");
                System.out.println("1. Give him a compliment and continue your way.");
                System.out.println("2. Fight the alien.");
                System.out.println("");
                System.out.println("Please choose a number between 1 and 2 ");
                String choiceFriendly = scanner1.nextLine();
                handleChoiceFriendly(choiceFriendly);
                break;
        }
    }


    private void initializeRooms(){
        
        this.rooms[0]  = new HTWRoom("TA A 027", "Seminarraum (Gebäude A)", new Lecturer("Herr Poeser"));
        this.rooms[1]  = new HTWRoom("TA C 707", "IT-/Medien-Unterrichtsraum (Gebäude C)", new Lecturer("Frau Safitri"));
        this.rooms[2]  = new HTWRoom("TA D 105", "Audimax / großer Veranstaltungsraum (Gebäude D)", new Lecturer("Vaseva"));
        this.rooms[3]  = new HTWRoom("TA D 204", "Internetcafé im Mensagebäude (Gebäude D)", new Lecturer("Gärtner"));
        this.rooms[4]  = new HTWRoom("TA A 024", "Seminar-/Unterrichtsraum (Gebäude A)", new Lecturer("Ganoui"));
        this.rooms[5]  = new HTWRoom("TA A 021", "Seminarraum (Gebäude A)",null);
        this.rooms[6]  = new HTWRoom("TA A 026", "Seminarraum (Gebäude A)",null);
        this.rooms[7]  = new HTWRoom("TA A 003", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[8]  = new HTWRoom("TA A 118", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[9]  = new HTWRoom("TA A 124", "Seminar-/Unterrichtsraum (Gebäude A)",null);
        this.rooms[10] = new HTWRoom("TA A 128", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[11] = new HTWRoom("TA A 130", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[12] = new HTWRoom("TA A 132", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[13] = new HTWRoom("TA A 134", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[14] = new HTWRoom("TA A 149", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[15] = new HTWRoom("TA A 219", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[16] = new HTWRoom("TA A 220", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[17] = new HTWRoom("TA A 239", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[18] = new HTWRoom("TA C 218", "Seminar-/Unterrichtsraum (Gebäude C)", null);
        this.rooms[19] = new HTWRoom("TA C 729", "Seminar-/Unterrichtsraum (Gebäude C)", null);
        this.rooms[20] = new HTWRoom("TA C 829", "Seminar-/Unterrichtsraum (Gebäude C)", null);
        this.rooms[21] = new HTWRoom("TA A Cafeteria", "Cafeteria (Gebäude A)",  null);
        this.rooms[22] = new HTWRoom("TA B Bibliothek-Ausleihe", "Bibliothek – Ausleihe (Gebäude B)" , null);
        this.rooms[23] = new HTWRoom("TA D Mensa", "Mensa (Gebäude D)" , null);

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
