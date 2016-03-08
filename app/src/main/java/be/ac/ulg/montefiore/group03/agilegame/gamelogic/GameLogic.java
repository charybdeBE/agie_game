package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;
import java.util.List;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Event;

/**
 * Created by charybde on 08.03.16.
 */
public class GameLogic {

    private ArrayList<ArrayList<Event>> events; //One ArrayList by month
    private List<Programmer> team;


    public ArrayList<String> getStringEventsOfMonth(int month) {
        int nr = this.events.get(month).size();
        ArrayList<String> toReturn = new ArrayList<>();
        for(int i = 0; i < nr; ++i){
            toReturn.add(this.events.get(month).get(i).getName());
        }
        return toReturn;
    }
}
