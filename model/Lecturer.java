package model;

import java.io.Serializable;

/**
 * Klasse für Dozenten um seriellisiert zu werden.
 * @author Berkant Kaygan
 * @author Luca Weber
 *  */

public class Lecturer implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 540082607047283589L;

    private String name;
    private boolean hasSigned;
    private boolean readyToSign;
    // ob dialoge mit dem Dozenten abgeschlossen ist, danach kann der Spieler unterschreiben lassen.
    private boolean dialogeCompleted; 


    /**
     * Lecturer ist bereit zu unterschreiben
     * wenn dialogeCompleted true ist und hasSigned false ist.
     */

    private void isReadyToSign(){
        if(dialogeCompleted && !hasSigned){
            this.readyToSign = true;    
        }   
        
    }

    /**
     * Wenn bereit zu unterschreiben und noch nicht unterschrieben wurde,
     * wird hasSigned auf true gesetzt.
     */
    private void sign(){
        if(readyToSign && !hasSigned){
            this.hasSigned = true;
        }
    }

     // Konstruktor
    public Lecturer(String name) {
        this.name = name;
        this.hasSigned = false;
        this.readyToSign = false;
        this.dialogeCompleted = false;
    }
    // getter Methoden
    public String getName() {
        return this.name;
    }

    public boolean getHasSigned() {
        return this.hasSigned;
    }

    public boolean getReadyToSign() {
        return this.readyToSign;
    }   
    public boolean getDialogeCompleted() {
        return this.dialogeCompleted;
    }   

    // setzt dialogeCompleted auf true (Dialoge abgeschlossen)
    public void setDialogeCompleted(boolean completed) {
        this.dialogeCompleted = completed;
    }





   // setzt readdyToSign auf true (ist bereit zu unterschreiben)
    public void setReadyToSign(boolean ready) {
        this.readyToSign = ready;
    }





}