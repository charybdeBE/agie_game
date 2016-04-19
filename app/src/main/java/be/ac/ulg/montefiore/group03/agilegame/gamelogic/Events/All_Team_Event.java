package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;

/**
 * Created by charybde on 19.04.16.
 */
public class All_Team_Event extends Programmer_Event {
    public All_Team_Event(int id, Date d, double v){
        super(id, d, null, null, v);
    }

    public void effect(Programmer _p){
        _p.setBonus(_p.getBonus() * this.delay);
    }
}
