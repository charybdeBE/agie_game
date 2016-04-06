package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

/**
 * Created by charybde on 08.03.16.
 */
public class Person {

    private String name;

    public Person () {
        this.name = "Unknown Name";
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
