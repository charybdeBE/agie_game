package be.ac.ulg.montefiore.group03.agilegame;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Interest;

//http://stackoverflow.com/questions/15832335/android-custom-row-item-for-listview
public class Task_List_Adapter extends BaseAdapter {

    Context context;
    ArrayList<Features> data;
    private static LayoutInflater inflater = null;

    public Task_List_Adapter(Context context, ArrayList<Features> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            vi = inflater.inflate(R.layout.task_list_row, null);
        TextView skill = (TextView) vi.findViewById(R.id.type);

        skill.setText(data.get(position).getNeeded().name());
        TextView time = (TextView) vi.findViewById(R.id.size_Task);
        int t = data.get(position).getDuration();
        if(t > 14)
            time.setTextColor(Color.RED);
        else if (t > 7)
            time.setTextColor(Color.rgb(255,165,0));
        else
            time.setTextColor(Color.rgb(0,102,0));

        time.setText("       " + Integer.toString(t));

        return vi;
    }
}