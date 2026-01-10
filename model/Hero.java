import java.io.Serializable;
import java.util.Random;


/**
 * Klasse für den helden des spiels um seriellisiert zu werden.
 * @author Berkant Kaygan
 * @author Luca Weber */

public class Hero implements Serializable {

    private String name = "Blank";
    private int healthpoints;
    private int experiencePoints;
    private Lecturer[] signedExerciseLeaders;

    private boolean smallRestUsed = false;

    private static final int Max_HP = 50;
    private static final int Signatures_Slots = 5;


    /**
     * RNG für die Fluchtchance.
     */
    private final Random fleechance = new Random();



    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;

    /**
     * Konstruktor für den Helden.
     */


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


    private void  regenerate(boolean longRest){
        int heal;

        if(longRest == true){
            heal = 10;
            this.round += 1;
        }
        else{
            if(smallRestUsed == false){
                heal = 3;
                this.smallRestUsed = true;
            }
            else{
                heal = 0;
            }
        
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
        int chance = this.fleechance.nextInt(100);
        return chance < 42;
    }

    /**
     * attack metthode um einen gegner anzugreifen.
     * @param enemy gegner der angegriffen wird.
     */

    


}