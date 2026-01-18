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
    }

    int explore = randomNumber.nextInt(rooms.length);


    private HTWRoom[] initRooms() {

        int roomindex = 0;
        roomindex = explore;

        if(viseteddRooms[roomindex] == false) {
            viseteddRooms[roomindex] = true;
            rooms[0]  = new HTWRoom("TA A 027", "Seminarraum (Gebäude A)", new Lecturer("Herr Poeser"));
            rooms[1]  = new HTWRoom("TA C 707", "IT-/Medien-Unterrichtsraum (Gebäude C)", new Lecturer("Frau Safitri"));
            rooms[2]  = new HTWRoom("TA D 105", "Audimax / großer Veranstaltungsraum (Gebäude D)", new Lecturer("Vaseva"));
            rooms[3]  = new HTWRoom("TA D 204", "Internetcafé im Mensagebäude (Gebäude D)", new Lecturer("Gärtner"));
            rooms[4]  = new HTWRoom("TA A 024", "Seminar-/Unterrichtsraum (Gebäude A)", new Lecturer("Ganoui"));
            rooms[5]  = new HTWRoom("TA A 021", "Seminarraum (Gebäude A)",null);
            rooms[6]  = new HTWRoom("TA A 026", "Seminarraum (Gebäude A)",null);
            rooms[7]  = new HTWRoom("TA A 003", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[8]  = new HTWRoom("TA A 118", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[9]  = new HTWRoom("TA A 124", "Seminar-/Unterrichtsraum (Gebäude A)",null);
            rooms[10] = new HTWRoom("TA A 128", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[11] = new HTWRoom("TA A 130", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[12] = new HTWRoom("TA A 132", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[13] = new HTWRoom("TA A 134", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[14] = new HTWRoom("TA A 149", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[15] = new HTWRoom("TA A 219", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[16] = new HTWRoom("TA A 220", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[17] = new HTWRoom("TA A 239", "Seminar-/Unterrichtsraum (Gebäude A)", null);
            rooms[18] = new HTWRoom("TA C 218", "Seminar-/Unterrichtsraum (Gebäude C)", null);
            rooms[19] = new HTWRoom("TA C 729", "Seminar-/Unterrichtsraum (Gebäude C)", null);
            rooms[20] = new HTWRoom("TA C 829", "Seminar-/Unterrichtsraum (Gebäude C)", null);
            rooms[21] = new HTWRoom("TA A Cafeteria", "Cafeteria (Gebäude A)",  null);
            rooms[22] = new HTWRoom("TA B Bibliothek-Ausleihe", "Bibliothek – Ausleihe (Gebäude B)" , null);
            rooms[23] = new HTWRoom("TA D Mensa", "Mensa (Gebäude D)" , null);
        }
        else {
            roomindex = randomNumber.nextInt(rooms.length);
        }

        return rooms;


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

    public HTWRoom[] getRooms() {
        return rooms;
    }

    // setter Methoden
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }






}
