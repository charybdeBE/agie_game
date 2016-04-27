package be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events;

import java.util.ArrayList;
import java.util.Date;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.App;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;

/**
 * Created by charybde on 27.04.16.
 */
public class Completion_Event extends Feature_Event {

    public Completion_Event(int id, Date d, Features f)
    {
        super(id, d, f, null, -1);
    }

    public void effect(App app) {
        ArrayList<Features> features = app.getFeatures();
        if (features.contains(depend)) {
            Features f = features.get(features.indexOf(depend));
            f.setDuration(0);
            f.notify(new Double(-1));
            f.notify(this);
        }
    }
}
