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
import model.ProfessorinMajuntke;


/**
 * Klasse der Haupteil des Escape Games.
 * @author Berkant Kaygan
 * @author Luca Weber
 */


public class EscapeGame implements Serializable { 
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[31];
    private final boolean[] visitedRooms = new boolean[31];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private Random randomNumber = new Random();
    private ProfessorinMajuntke majuntke = new ProfessorinMajuntke();
    
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
        System.out.println("(1) Exlore College");
        System.out.println("(2) Show Hero Status");
        System.out.println("(3) Check Routing sheet");
        System.out.println("(4) Take a break");
        System.out.println("(5) Quit Game");
        if(hero.isRoutingSheetComplete()){
            System.out.println("");
            System.out.println("(6) You have collected all signatures! One last challenge awaits you!");
        }
        System.out.println("");
        if(hero.isRoutingSheetComplete()){
            System.out.println("Please choose a number between 1 and 5: ");
        } else {
            System.out.println("Please choose a number between 1 and 6: ");
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
            case "6":
                if(hero.isRoutingSheetComplete()){
                    //MeetMajunkte(); erstellen
                } else{
                    System.out.println("You are not ready yet! Collect all signatures first.");
                }
            default:
                System.out.println("Invalid choice Try again.");
                break;

        }
    }



    //Zeigt den heldenstatus an.
    private void showHeroStatus(){
        if(hero.getRoundsPlayed() == 24){
            System.out.println("=== Final Status ===");
        }
        else{
        System.out.println("=== Hero Status ===");
        }   
        System.out.println("Name: " + hero.getName());
        System.out.println("Rounds Played: " + hero.getRoundsPlayed());
        System.out.println("Health Points: " + hero.getHealthpoints());
        System.out.println("Experience Points: " + hero.getExperiencePoints());
        System.out.println("");

    }



    //zeigtt die routing sheet an.
    private void checkRoutingSheet(){
        System.out.println("=== Routing Sheet ===");

        String signedNames = hero.getSignedExerciseLeaders();
        int missingCount = 0;
        for (int i = 0; i < 5; i++) {
            String lecturerName = rooms[i].getLecturer().getName();
            if (signedNames.contains(lecturerName)) {
                System.out.println("You have signature from: " + lecturerName);
            }
            else{
                System.out.println("Missing signature from: " + lecturerName);
                missingCount++;
            }
        }

        


      
    }



    //lässt den helden eine pause machen gibt ihn dadbei zwei optionen.
    private void takeABreak(){
        System.out.println("You take a break and recover some health points.");
        System.out.println("(1) Short Break (3 HP)");
        System.out.println("(2) Long Break (10 HP, +1 Round Played)");
        System.out.println("(3) Cancel");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 3: ");
        String choice = scanner1.nextLine();
        EscapeApp.clearConsole();
       
        switch (choice) {
            case "1":
                hero.regenerate("1");
                break;
            case "2":
                hero.regenerate("2");
                break;
            case "3":
                System.out.println("Break cancelled. No health restored.");
                break;
            default:
                System.out.println("Invalid choice, Try again.");
                System.out.println("");
                takeABreak();
                break;
        }
    }



    public void exploreCollege(){

        if(hero.getRoundsPlayed() >= 24){
            System.out.println("You have no rounds left to explore the college.");
            System.out.println("Its Game Over for you Prof. Majunkte left with he Rocket.");
            setGameFinished(true);
            setGameRunning(false);
   
            return;
        }
        if(hero.isRoutingSheetComplete() == true){
            majuntke.meetMajunke();
            setGameFinished(true);
            setGameRunning(false);
   
            return;  
        }
        hero.increaseRoundsPlayed();
        int currentRound = hero.getRoundsPlayed();
        System.out.println("Exploring the college... This is round " + currentRound + ". You have " + (24 - currentRound) + " rounds left.");
        
    
        System.out.println("====================");
        System.out.println("");

        int roomIndex;
        int roomIndexLecturer;

        if (currentRound >= 24) {
            System.out.println("Your Time is up! You are stuck here and who knows what will happen to you...");
            System.out.println("Game Over!");
            System.out.println("");
            this.showHeroStatus();
            System.out.println("(1) Return to Main Menu");

            switch (scanner1.nextLine()) {
                case "1":
                    EscapeApp.clearConsole();
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    EscapeApp.clearConsole();
                    exploreCollege();
                    break;
            }
            setGameFinished(true);
            setGameRunning(false);
            return;
        }

        int outcome = randomNumber.nextInt(100);

        if (outcome < 20){
            do { 
                roomIndex = randomNumber.nextInt(24);
                roomIndex += 6;
            } while (visitedRooms[roomIndex] == true);
            wasInThisRoom(roomIndex);
            System.out.println("You enter room " + rooms[roomIndex].getIdentifier() + " and find nothing interesting during your exploration.");
            System.out.println("");
        }
        else if (outcome < 72){
            do { 
                roomIndex = randomNumber.nextInt(24);
                roomIndex += 6;
            } while (visitedRooms[roomIndex] == true);
            wasInThisRoom(roomIndex);
            System.out.println("You enter room " + rooms[roomIndex].getIdentifier() + " and find something lurking in the shadows");
            alienEncounter();
        }
        else{
            int index = 0;
            for(int i=0; i<5; i++){
                if(visitedRooms[i] == true){
                    index++;
                }
                if(index ==5){
                    System.out.println("You have already collected all signatures.");
                    System.out.println("You should check your Options in the Game Menu.");
                    return;
                }
            }

            do{
                roomIndexLecturer = randomNumber.nextInt(5);
            }while(visitedRooms[roomIndexLecturer] == true);
            
            wasInThisRoom(roomIndexLecturer);
            String currentLecturerRoom = rooms[roomIndexLecturer].getIdentifier();
            String currentLecturerName = rooms[roomIndexLecturer].getLecturer().getName();
            
            System.out.println("You enter the Room " + currentLecturerRoom + " and see a lecturer.");
            System.out.println(currentLecturerName + " gives you a signature for your routing sheet!");
            hero.setSignedExerciseLeaders(rooms[roomIndexLecturer].getLecturer());
        }

    }


    public void wasInThisRoom(int roomIndex){
        visitedRooms[roomIndex] = true;
    }

    public boolean alienDecider(){
        return randomNumber.nextBoolean();
    }

    public void alienEncounter(){
        Alien currentEnemy;
        boolean alienDecision = alienDecider();
        if(alienDecision == true){
            currentEnemy = new FriendlyAlien();
        }
        else{
            currentEnemy = new HostileAlien();
        }

        System.out.println(currentEnemy.getName() + " steps forward!");
        System.out.println(currentEnemy.getGreetings());
        System.out.println("");
        System.out.println("====================");
        System.out.println("");

        if(alienDecision == true){
            System.out.println("The alien seems friendly how do you want to proceed?");
            handleChoiceFriendly();
        }
        else{
            System.out.println("The alien looks hostile and prepares to attack you!");
            fightOrFlee();
            System.out.println("");
            //kampfmechanik
        }
    }




    public void handleChoiceFriendly(){
        System.out.println("(1) Give him a compliment and continue your way.");
        System.out.println("(2) Fight the alien.");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 2 ");
        String choiceFriendly = scanner1.nextLine();
        
        
        switch (choiceFriendly) {
            case "1":
                EscapeApp.clearConsole();
                System.out.println("You give the alien a compliment and he seems pleased.");
                System.out.println("You keep going on your way safely.");
                System.out.println("");
                break;
            case "2":
                EscapeApp.clearConsole();
                System.out.println("You decide to fight the alien!");
                System.out.println("===================");
                fightLogic();
                System.out.println("");
                //kampfmechanik
                break;
            default:
                EscapeApp.clearConsole();
                System.out.println("Invalid choice try again.");
                System.out.println("");
                /**System.out.println("The alien seems friendly how do you want to proceed?");
                System.out.println("1. Give him a compliment and continue your way.");
                System.out.println("2. Fight the alien.");
                System.out.println("");
                System.out.println("Please choose a number between 1 and 2 ");*/
                handleChoiceFriendly();
                break;
        }
    }

    private void fightOrFlee(){
        int fleeChance = randomNumber.nextInt(100);
        
        System.out.println("Do you want to fight the alien or try to flee?");
        System.out.println("(1) Fight");
        System.out.println("(2) Flee");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 2: ");
        String choice = scanner1.nextLine();
        EscapeApp.clearConsole();

        switch (choice) {
            case "1":
                System.out.println("You decided to fight the alien!");
                System.out.println("");
                fightLogic();
                break;
            case "2":
                boolean decision = hero.flee();
                if(decision == true){
                    System.out.println("You were able to flee from the alien!");
                    break;
                }
                else{
                    System.out.println("You failed to flee from the alien!");
                    System.out.println("You have to fight now!");
                    System.out.println("");
                    fightLogic();
                    break;
                }
            default:
                System.out.println("Invalid choice, Try again.");
                fightOrFlee();
        }
    }

    private void fightLogic(){
        Alien currentEnemy;
        boolean alienDecision = alienDecider();

        if(alienDecision == true){
            currentEnemy = new FriendlyAlien();
        }
        else{
            currentEnemy = new HostileAlien();
        }

        while(currentEnemy.isDefeated() == false && hero.isOperational() == true){
            System.out.println("=== Battle Menu ===");
            currentEnemy.takeDamage(hero.attack());

            if(currentEnemy.isDefeated() == true){
                System.out.println("====================");
                System.out.println("You have defeated the alien!");
                System.out.println("You gain 5 experience points.");
                System.out.println("");

                hero.addExperiencePoints(5);
                System.out.println("Press any button to continue");
                String input = scanner1.nextLine();
                EscapeApp.clearConsole();
                switch (input) {
                  default:
                    break;
                }
                return;
            }
            

            hero.takeDamageHero(currentEnemy.getDamage());
            System.out.println("====================");

            if(hero.isOperational() == false){
                System.out.println("");
                System.out.println("You have been defeated by the alien.");
                System.out.println("You gain 1 experience point and your health points have been set to 10.");
                System.out.println("You lost 2 hours, hurry up!");
                System.out.println("");
                hero.setHealthpoints(10);
                hero.increaseRoundsPlayed();
                hero.increaseRoundsPlayed();

                if(hero.getRoundsPlayed() > 24){
                    hero.setRoundsPlayed(24);
                }

                hero.addExperiencePoints(1);
                System.out.println("Press any button to continue");
                String input = scanner1.nextLine();
                EscapeApp.clearConsole();
                switch (input) {
                  default:
                    break;
                }
                return;
            }

            System.out.println("");
            System.out.println("Press any button to continue");
            String input = scanner1.nextLine();
            EscapeApp.clearConsole();
            switch (input) {
                default:
                    break;
            }

        }
    }

    private void initializeRooms(){
        
        this.rooms[0]  = new HTWRoom("TA A 027", "Seminarraum (Gebäude A)", new Lecturer("Herr Poeser", "Das kann man so machen, bis nächste Woche"));
        this.rooms[1]  = new HTWRoom("TA C 707", "IT-/Medien-Unterrichtsraum (Gebäude C)", new Lecturer("Frau Safitri", "Das Wetter ist miserabel, du hättest zuhause bleiben sollen, nicht wahr?"));
        this.rooms[2]  = new HTWRoom("TA D 105", "Audimax / großer Veranstaltungsraum (Gebäude D)", new Lecturer("Vaseva", "Dire fehlen noch UNterschirften nicht wahr?"));
        this.rooms[3]  = new HTWRoom("TA D 204", "Internetcafé im Mensagebäude (Gebäude D)", new Lecturer("Gärtner", "Ich hatte doch extra gesagt über Zoom, du hättest dir das alles ersparen können."));
        this.rooms[4]  = new HTWRoom("TA A 024", "Seminar-/Unterrichtsraum (Gebäude A)", new Lecturer("Ganoui", "Du bist spät dran, aber ich werde dir trotzdem eine Unterschrift geben."));
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
        this.rooms[24]  = new HTWRoom("TA A 218", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[25]  = new HTWRoom("TA A 234", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[26]  = new HTWRoom("TA A 232", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[27]  = new HTWRoom("TA A 249", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[28]  = new HTWRoom("TA A 224", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[29]  = new HTWRoom("TA A 228", "Seminar-/Unterrichtsraum (Gebäude A)", null);
        this.rooms[30]  = new HTWRoom("TA A 120", "Seminar-/Unterrichtsraum (Gebäude A)", null);


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
