package model;
import java.util.Random;

public class FriendlyAlien extends Alien {

    Random random = new Random();

    public FriendlyAlien() {
        super("'Happy'", 25, "\"You look like an interesting fella, are you lost human?\"");
        setDamage(random.nextInt(5) + 2);
    }
}
