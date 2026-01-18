package model;

import java.io.Serializable;
import java.util.Random;

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
    private HTWRoom[] rooms = new HTWRoom[24];
    private Random randomNumber = new Random();
    private boolean[]viseteddRooms = new boolean[24];
    

    public HTWRoom(String identifier, String description, Lecturer lecturer) {
        this.identifier = identifier;
        this.description = description;
        this.lecturer = lecturer;
        

    int explore = randomNumber.nextInt(rooms.length);
    }

    /**private HTWRoom[] initRooms() {

        int roomindex = 0;
        roomindex = explore;

        if(viseteddRooms[roomindex] == false) {
            viseteddRooms[roomindex] = true;

        }
        else {
            roomindex = randomNumber.nextInt(rooms.length);
        }

        return rooms;


    } */


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

    public HTWRoom[] getRooms() {
        return rooms;
    }

    // setter Methoden
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }






}
