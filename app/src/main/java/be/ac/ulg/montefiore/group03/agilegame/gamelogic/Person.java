package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.Observable;

/**
 * Created by charybde on 08.03.16.
 */
public class Person extends Observable{

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

    /**
     * has person an id ?
     * @return true if the person has an id
     *         false otherwise
     */
    public Boolean hasId() { return id != -1 ? true : false;}

    /**
     * Get the id of the person
     * @return the id of the person
     */
    public int getId()  { return id;}

    /**
     * Get the name of the person
     * @return person's name (may be null)
     */
    public String getName() { return this.name; }

    /**
     * notify pattern TODO explain more
     * @param o
     */
    public void notify(Object o){
        setChanged();
        notifyObservers(o);
    }

}
