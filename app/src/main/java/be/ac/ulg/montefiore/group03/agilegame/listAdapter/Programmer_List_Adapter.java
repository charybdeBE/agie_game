package be.ac.ulg.montefiore.group03.agilegame.listAdapter;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import be.ac.ulg.montefiore.group03.agilegame.R;
import be.ac.ulg.montefiore.group03.agilegame.Utils;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Interest;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

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

            vi.findViewById(R.id.programmer_info).setOnClickListener(new programmerClickListener(data.get(position)));

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
                assignedTask.setText(Utils.getFeatureName(data.get(position).getWork(), context));
            }

        }
        return vi;
    }

    private final class programmerClickListener implements  View.OnClickListener {

        private Programmer p = null;
        private AlertDialog.Builder p_info_builder = null;

        public programmerClickListener (Programmer p) {
            super();
            this.p = p;
            this.p_info_builder = null;
        }

        @Override
        public void onClick(View v) {

            if (this.p_info_builder == null) {
                this.p_info_builder = new AlertDialog.Builder(v.getContext());
                this.p_info_builder.setTitle(this.p.getName() + " test");
                this.p_info_builder.setIcon(R.drawable.default_img);
                this.p_info_builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                // If we are in the management activity, we create a Fire button
                // If we are in the Pole_emploi activity, we create a Hire button
                if (v.getContext().getClass().toString().equals("class be.ac.ulg.montefiore.group03.agilegame.Manager")) {
                    this.p_info_builder.setNegativeButton("Fire", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            p.fire();
                            dialog.dismiss();
                        }
                    });
                } else if (v.getContext().getClass().toString().equals("class be.ac.ulg.montefiore.group03.agilegame.Pole_emploi")) {
                    this.p_info_builder.setNegativeButton("Hire", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            p.hire();
                            dialog.dismiss();
                        }
                    });
                }
            }

            this.p_info_builder.setMessage(this.getInfo(this.p));

            AlertDialog programmer_info = p_info_builder.create();
            programmer_info.show();
        }

        public String getInfo(Programmer p) {
            String info = "Salary: " + p.getSalary() + " $\n";

            ArrayList<Interest> listOfInterest = p.getInterests();
            if (!listOfInterest.isEmpty()) {
                info += "Interested in:\n";
                for (Interest i : listOfInterest) {
                    info += i.name() + "\n";
                }
            }

            ArrayList<Skills> listOfSkills = p.getSkills();
            if (!listOfSkills.isEmpty()) {
                info += "Skills:\n";
                for (Skills s : listOfSkills) {
                    info += s.getType() + " level " + s.getLevel() + "\n";
                }
            }

            int fired_time = p.isFired();
            if (fired_time != -1) {
                info += "Will be fire in " + fired_time + " month\n";
            }

            return info;
        }
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


