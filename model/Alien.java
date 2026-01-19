package model;
import java.io.Serializable;


/**
 * Klasse für Aliens serielle Speicherung.
 * @author Berkant Kaygan
 * @author Luca Weber
 */


public abstract class Alien implements Serializable {

    private String name;
    private int lifePoints;
    private boolean friendly;
    private String greetings;
    private int damage;

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 1729389822767173584L;


    public Alien(String name, int lifePoints, boolean friendly, String greetings){
        this.name = name;
        this.lifePoints = lifePoints;
        this.friendly = friendly;
        this.greetings = greetings;
        this.damage = damage;
    }

    //metthoden

    public void takeDamage(int damage) {
        this.lifePoints -= damage;
        if (this.lifePoints < 0) {
            this.lifePoints = 0;
        }

        System.out.println("");
        System.out.println(this.name + " took " + damage + " damage. HP left: " + this.lifePoints);
        System.out.println("");
    }

    public boolean isDefeated() {
        return this.lifePoints <= 0;
    }

    public String getName() {
        return this.name;
    }

    public String getGreetings() {
        return this.greetings;
    }
    
    public int getLifePoints() {
        return this.lifePoints;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getDamage(){
        return this.damage;
    }
}