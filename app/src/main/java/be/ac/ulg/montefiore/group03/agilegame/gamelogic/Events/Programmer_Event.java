package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Interest;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;

/**
 * Created by charybde on 08.03.16.
 */
public class Programmer_Event extends Event {

    private Interest depend;

    public Programmer_Event(String s, Date d) {
        super();
        occurs = d;
        name = s;
    }

    public void effect(Programmer _p) { //Could be inherited by special programmer_event
        if(_p.like(depend)){
            if(_p.hasSkill(this.amelioration.getType())){
                if(this.amelioration.getLevel() > 0)
                    _p.getSkill(this.amelioration.getType()).levelUp();
                else
                    _p.getSkill(this.amelioration.getType()).levelDown();

            }
        }
    }
}
