package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

/**
 * Created by charybde on 08.03.16.
 */
public class Person {

    private String name, firstname;

    public Person(String name, String firstname) {
        this.name = name;
        this.firstname = firstname;
    }


    public String getName() { return this.name; }

    public String getFirstname() { return this.firstname; }

}
