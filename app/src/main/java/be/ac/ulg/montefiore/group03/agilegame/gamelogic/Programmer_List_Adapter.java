package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.R;

/**
 * Created by sylvain on 4/6/16.
 */
public class Programmer_List_Adapter extends BaseAdapter {

    Context context;
    ArrayList<Programmer> data;
    private static LayoutInflater inflater = null;

    public Programmer_List_Adapter(Context context, ArrayList<Programmer> programmers) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = programmers;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
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
