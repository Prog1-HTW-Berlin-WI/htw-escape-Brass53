package model;
import java.util.Random;

public class HostileAlien extends Alien{

    Random random = new Random();

    public HostileAlien(){
        super("'Grumpy'", 35, false, "\"You're not supposed to be here, little human!\"");
        setDamage(random.nextInt(10) + 1);
    }
}
