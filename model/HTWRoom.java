package model;

import java.io.Serializable;

/**
 * Klasse für HTW Räume um seriellisiert zu werden.
 * @author Berkant Kaygan
 * @author Luca Weber */

public class HTWRoom implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 9065680017147292999L;

    private String identifier;
    private String description;
    private Lecturer lecturer;

     public HTWRoom(String identifier, String description, Lecturer lecturer) {
        this.identifier = identifier;
        this.description = description;
        this.lecturer = null;
    }


    // getter Methoden
    public String getIdentifier() {
        return identifier;
    }
    public String getDescription() {
        return description;
    }   
    public Lecturer getLecturer() {
        return lecturer;
    }   

    // setter Methoden
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }






}
