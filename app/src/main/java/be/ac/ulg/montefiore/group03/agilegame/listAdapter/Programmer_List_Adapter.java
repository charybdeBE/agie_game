package be.ac.ulg.montefiore.group03.agilegame.listAdapter;

import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

            vi.findViewById(R.id.profile_photo).setOnTouchListener(new programmerTouchListener(data.get(position)));

            TextView name = (TextView) vi.findViewById(R.id.programmer_name);
            if(data.get(position).hasId()){
                Resources res = context.getResources();
                name.setText(res.getStringArray(R.array.persons)[data.get(position).getId()]);
            }
            else {
                name.setText(data.get(position).getName());
            }

            TextView salary = (TextView) vi.findViewById(R.id.programmers_salary);
            salary.setText("Salary: " + data.get(position).getSalary() + " $");

            TextView assignedTask = (TextView) vi.findViewById(R.id.programmers_tasks);

            if (data.get(position).getWork() != null) {

                /* Amelioration : mettre un nom a Feature ou bien faire une fonction qui le renvoie */
                Resources res = context.getResources();

                switch (data.get(position).getWork().getNeeded()) {
                    case Android:
                        assignedTask.setText(res.getStringArray(R.array.features_ANDROID)[data.get(position).getWork().getId() % res.getStringArray(R.array.features_ANDROID).length]);
                        break;
                    case JAVA:
                        assignedTask.setText(res.getStringArray(R.array.features_JAVA)[data.get(position).getWork().getId() % res.getStringArray(R.array.features_JAVA).length]);
                        break;
                    case Network:
                        assignedTask.setText(res.getStringArray(R.array.features_NETWORK)[data.get(position).getWork().getId() % res.getStringArray(R.array.features_NETWORK).length]);
                        break;
                    case Design:
                        assignedTask.setText(res.getStringArray(R.array.features_DESIGN)[data.get(position).getWork().getId() % res.getStringArray(R.array.features_DESIGN).length]);
                        break;
                    case Diagrams:
                        assignedTask.setText(res.getStringArray(R.array.features_DIAGRAMS)[data.get(position).getWork().getId() % res.getStringArray(R.array.features_DIAGRAMS).length]);
                        break;
                }
            }
            else
                assignedTask.setText("None assigned task");
        }
        return vi;
    }

    private final class programmerTouchListener implements View.OnTouchListener {

        private Programmer p = null;

        public programmerTouchListener(Programmer p) {
            super();
            this.p = p;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                ClipData data = ClipData.newPlainText("hello", "word");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(null, shadowBuilder, p, 0);
                view.setVisibility(View.VISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }
}


