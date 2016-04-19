package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

/**
 * Created by charybde on 18.03.16.
 */
public class Feature_Event extends Event{

    private Features depend;

    public Feature_Event(String s, Date d) {
        super(s, d);
    }

    public Feature_Event(Features f, String s, Date d){
        super(s,d);
        this.depend = f;
    }

    public Feature_Event(int id, Date d, Features f, Skills s, double delay ){
        super(id,d,s,delay);
        this.depend = f;
    }
    @Override
    public void effect(Programmer _p) {

    }
}
