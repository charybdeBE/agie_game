package be.ac.ulg.montefiore.group03.agilegame.listAdapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.R;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Interest;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.SkillType;

import static be.ac.ulg.montefiore.group03.agilegame.gamelogic.SkillType.Android;

//http://stackoverflow.com/questions/15832335/android-custom-row-item-for-listview
public class Task_List_Adapter extends Array_List_Adapter {

    ArrayList<Features> data;

    public Task_List_Adapter(Context context, ArrayList<Features> data) {
        super(context, data);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.task_list_row, null);

        vi.setOnDragListener(new taskDragListener());

        Resources res = context.getResources();
        TextView name = (TextView) vi.findViewById(R.id.name);

        ImageView skill = (ImageView) vi.findViewById(R.id.skill);

        switch(data.get(position).getNeeded()){
            case Android :
                skill.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.android));
                name.setText(res.getStringArray(R.array.features_ANDROID)[data.get(position).getId() % res.getStringArray(R.array.features_ANDROID).length]);
                break;
            case JAVA:
                skill.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.java));
                name.setText(res.getStringArray(R.array.features_JAVA)[data.get(position).getId() % res.getStringArray(R.array.features_JAVA).length]);
                break;
            case Network:
                skill.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.network));
                name.setText(res.getStringArray(R.array.features_NETWORK)[data.get(position).getId() % res.getStringArray(R.array.features_NETWORK).length]);
                break;
            case  Design:
                skill.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.design));
                name.setText(res.getStringArray(R.array.features_DESIGN)[data.get(position).getId() % res.getStringArray(R.array.features_DESIGN).length]);
                break;
            case Diagrams:
                skill.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.diagrams));
                name.setText(res.getStringArray(R.array.features_DIAGRAMS)[data.get(position).getId() % res.getStringArray(R.array.features_DIAGRAMS).length]);
                break;
        }

        System.out.println("aaaaaaaaaaaaaaaaaaaaa" + name.getText());

        TextView time = (TextView) vi.findViewById(R.id.size_Task);
        double t = data.get(position).getDuration();
        if(t > 14.0)
            time.setTextColor(Color.RED);
        else if (t > 7.0)
            time.setTextColor(Color.rgb(255,165,0));
        else
            time.setTextColor(Color.rgb(0,102,0));

        time.setText(" " + Double.toString(t));

        return vi;
    }

    class taskDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    // when we enter in a zone in which the view can be dropped in
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    // when we are not in a zone that is droppable (in this case, when we leave a task
                    break;
                case DragEvent.ACTION_DROP:
                    // when it is dropped: Assign task to the programmer
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    // when the drop is finished (after): Nothing to do
                default:
                    break;
            }
            return true;
        }
    }
}