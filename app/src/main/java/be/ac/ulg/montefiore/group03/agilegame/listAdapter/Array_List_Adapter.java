package be.ac.ulg.montefiore.group03.agilegame.listAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;

/**
 * Created by charybde on 07.04.16.
 */
public abstract class Array_List_Adapter extends BaseAdapter {

    protected Context context;
    protected ArrayList data_abstract;
    protected static LayoutInflater inflater = null;

    public Array_List_Adapter(Context context, ArrayList data) {
        this.context = context;
        this.data_abstract = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data_abstract.size();
    }

    @Override
    public Object getItem(int position) {
        return data_abstract.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
