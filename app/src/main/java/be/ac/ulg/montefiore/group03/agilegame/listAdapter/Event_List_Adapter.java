package be.ac.ulg.montefiore.group03.agilegame.listAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.R;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.events.Programmer_Event;

/**
 * Created by charybde on 08.04.16.
 */
public class Event_List_Adapter extends Array_List_Adapter{

    private ArrayList<Programmer_Event> data;

    public Event_List_Adapter(Context context, ArrayList<Programmer_Event> data) {
        super(context, data);
        this.data = data;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if (vi == null)
            vi = inflater.inflate(R.layout.list, null);


        Resources res = context.getResources();
        TextView name = (TextView) vi.findViewById(R.id.event_name);
        if(data.get(position) instanceof  Programmer_Event )
            name.setText(res.getStringArray(R.array.event_interests_name_array)[data.get(position).getId()]);

        return vi;
    }
}

