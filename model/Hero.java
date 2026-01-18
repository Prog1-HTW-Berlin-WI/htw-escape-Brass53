package model;
import java.io.Serializable;
import java.util.Random;


/**
 * Klasse für den helden des spiels um seriellisiert zu werden.
 * @author Berkant Kaygan
 * @author Luca Weber */

public class Hero implements Serializable {

    private String name;
    private int healthpoints;
    private int experiencePoints;
    private Lecturer[] signedExerciseLeaders;
    private int roundsPlayed;

    private boolean smallRestUsed = false;

    private static final int Max_HP = 50;
    private static final int Signatures_Slots = 5;


    /**
     * RNG für zufallszahlen.
     */
    private final Random random = new Random();



    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;

    /**
     * Konstruktor für den Helden.
     */
    public Hero(){
        this.name = "Blank Hero";
        this.healthpoints = 50;
        this.experiencePoints = 0;
        this.signedExerciseLeaders = new Lecturer[Signatures_Slots];
        this.roundsPlayed = 0;
    }


    /**
     * take damage methode um schaden zu nehmen.
     * @param damage schaden der genommen wird.
     */


    private void takeDamage(int amount){
        this.healthpoints -= amount;
        if(this.healthpoints < 0){
            this.healthpoints = 0;
        }
    }

    /**
     * Heile nach einer Langen pause 10 HP oder nach einer kurzen pause 3 HP.
     * bei der langen pause wird die runde um 1 erhöht.
     * eine kurze pause kann nur einmal pro level genutzt werden.
     * @param longRest ob es eine lange pause ist.
     * 
     * anmerkung: round variable fehlt noch !!!
     */


    public void regenerate(String longRest){
        int heal;

        if(longRest == "1"){
            heal = 10;
            this.roundsPlayed += 1;
        }
        else if(longRest == "2"){
            if(smallRestUsed == false){
                heal = 3;
                this.smallRestUsed = true;
            }
            else{
                System.out.println("Small rest already used in this level. No health restored.");
                heal = 0;
            }
        
    
        }
        else{
                System.out.println("Invalid rest type. No health restored.");
                heal = 0;
            }
        

        this.healthpoints += heal;
        if(this.healthpoints > Max_HP){
            this.healthpoints = Max_HP;
        }
    }

    /**
     * 42 % chance zum fliehen.
     * @return true wenn geflohen wurde, sonst false.
     * 
     * anmerukung: ggf. print statment zur (nicht) erfolgten flucht hinzufügen !!!
     */


    private boolean flee(){
        int chance = this.random.nextInt(100);
        if (chance < 42){
            System.out.println("You successfully fled the battle!");
            return true;
        }
        else{
            System.out.println("Flee attempt failed!");
            return false;
        }
    }

    /**
     * Angrriff hat eine 13 % chance zu verfehlen und 12 % chance für einen kritischen treffer.
     * badsdamge wird durch erfahrungspunkte skaliert.
     * @return schaden der verursacht wurde.
     */

    private int attack(){
        double baseDamage = this.experiencePoints * 2.3 + 1;
        int rollToHit = this.random.nextInt(100);

        if(rollToHit < 13){
            System.out.println("Attack missed!");
            return 0;
        }

        if(rollToHit > 87){
            System.out.println("Critical hit!");
            baseDamage = baseDamage * 2;
        }

        return (int)baseDamage;
    }

    /**
     * Lecturer wird in leere stelle des arrays signedExerciseLeaders eingetragen.
     * wwenn der lecturer schon eingetragen ist, passiert nichts.
     * @param lecturer der lecturer der eingetragen werden soll.
     */
    private void signExerciseLeader(Lecturer lecturer){
        for(int i = 0; i < signedExerciseLeaders.length; i++){
            if(signedExerciseLeaders[i].equals(lecturer)){
                break;
            }
            else if(signedExerciseLeaders[i] == null){
                signedExerciseLeaders[i] = lecturer;
                break;
            }
        }
    }

    /**
     * Gibt die aktuellen Erfahrungspunkte zurück.
    * @return die aktuellen Erfahrungspunkte.
    */
    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    /**
     * Fügt Erfahrungspunkte hinzu.
     * @param experiencePoints die hinzuzufügenden Erfahrungspunkte.
     * 
     * variable experiencePoints muss definiert werden !!!
     */

    private void addExperiencePoints(int xp){
        this.experiencePoints += xp;
    }


    /**
     * Prüft ob der Held noch Lebenspunkte hat.
     * @return true wenn der Held noch Lebenspunkte hat, sonst false.
     */

    public boolean isOperational() {
        return this.healthpoints > 0;

    }

    //getter für name, healthpoints, signedExerciseLeaders experiencePoints
    public String getName() {
        return this.name;
    }

    public int getRoundsPlayed() {
        return this.roundsPlayed;
    }

    public void increaseRoundsPlayed() {
        ++this.roundsPlayed;
    }

    public int getHealthpoints() {
        return this.healthpoints;
    }

    public Lecturer[] getSignedExerciseLeaders() {
        return this.signedExerciseLeaders;
    }

    public int getExperience() {
        return this.experiencePoints;
    }


    //setter für name, healthpoints, signedExerciseLeaders experiencePoints

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthpoints(int healthpoints) {
        this.healthpoints = healthpoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public void setSignedExerciseLeaders(Lecturer[] signedExerciseLeaders) {
        this.signedExerciseLeaders = signedExerciseLeaders;
    }

    public void setLo
    


}
