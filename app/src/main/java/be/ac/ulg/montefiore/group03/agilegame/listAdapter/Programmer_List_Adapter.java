package be.ac.ulg.montefiore.group03.agilegame.listAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.R;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;

/**
 * Created by sylvain on 4/6/16.
 */
public class Programmer_List_Adapter extends Array_List_Adapter {

    ArrayList<Programmer> data;

    public Programmer_List_Adapter(Context context, ArrayList<Programmer> programmers) {
        super(context, programmers);
        data = programmers;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.programmer_item, null);
        if (vi != null) {
            TextView text = (TextView) vi.findViewById(R.id.programmer_name);
            if(data.get(position).hasId()){
                Resources res = context.getResources();
                text.setText(res.getStringArray(R.array.persons)[data.get(position).getId()]);
            }
            else {
                text.setText(data.get(position).getName());
            }
        }
        return vi;
    }
}
