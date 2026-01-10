import java.io.Serializable;


/**
 * Klasse für den helden des spiels um seriellisiert zu werden.
 * @author Berkant Kaygan
 * @author Luca Weber */

public class Hero implements Serializable {

    private String name = "Blank";
    private int healthpoints;
    private int experiencePoints;
    private Lecturer[] signedExerciseLeaders;

    private static final int Max_HP = 50;
    private static final int Signatures_Slots = 5;

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;

    /**
     * Konstruktor für den Helden.
     */


}