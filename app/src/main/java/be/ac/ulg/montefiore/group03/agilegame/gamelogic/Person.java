package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

/**
 * Created by charybde on 08.03.16.
 */
public class Person {

    private String name;
    private int id;


    public Person () {
        this.name = "Unknown Name";
        this.id = -1;
    }

    public Person(String name) {
        this.id = -1;
        this.name = name;
    }

    public Person(int id){
        this.id = id;
    }


    public Boolean hasId() { return id != -1 ? true : false;}
    public int getId()  { return id;}
    public String getName() { return this.name; }
}
