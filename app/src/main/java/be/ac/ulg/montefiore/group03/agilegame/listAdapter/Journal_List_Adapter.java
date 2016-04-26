package be.ac.ulg.montefiore.group03.agilegame.listAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.Manager;
import be.ac.ulg.montefiore.group03.agilegame.R;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Feature_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Events.Programmer_Event;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Journal.*;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.SkillType;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Skills;

/**
 * Created by charybde on 20.04.16.
 */
public class Journal_List_Adapter extends Array_List_Adapter{

    ArrayList<Journal_entry> data;

    public Journal_List_Adapter(Context context, Journal journal) {
        super(context, journal.getArray());
        this.data = super.data_abstract;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //TODO use registrated string
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.summary_item, null);

        Resources res = context.getResources();
        TextView name = (TextView) vi.findViewById(R.id.journal_entry_name);
        ImageView img = (ImageView) vi.findViewById(R.id.journal_entry_pic);
        TextView text = (TextView) vi.findViewById(R.id.journal_entry);
        Journal_entry entry = data.get(position);
        String txt = "";

        //TODO use a visitor pattern to avoid "instanceof"
        //TODO MAP features and strings
        if(entry instanceof Journal_entry_programmer){
            String[] names = res.getStringArray(R.array.persons);
            String[] events_name = res.getStringArray(R.array.event_interests_name_array);
            img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.default_img));

            if(entry.getId() != -1)
                name.setText(names[entry.getId()]);
            else
                name.setText(new Integer(entry.getId()).toString()); //TODO remove only for testing


            for(Programmer_Event p : ((Journal_entry_programmer) entry).getEvents()){
                String s = String.format(res.getString(R.string.has_participated),events_name[p.getId()]);
                txt += s;
            }
            for(Skills s : ((Journal_entry_programmer) entry).getUpSkills()){
                String st = String.format(res.getString(R.string.is_now_level),s.getLevel(), s.getType().name());
                txt += st;
            }
            if(((Journal_entry_programmer) entry).getWorkDone() != 0){
                String s = String.format(res.getString(R.string.has_worked),((Journal_entry_programmer) entry).getWorkDone(),  ((Journal_entry_programmer) entry).getFeat().getId());
                txt += s;
            }
        }

        if(entry instanceof Journal_entry_feature){
            String[] names = null;
            String[] events_names = res.getStringArray(R.array.events_features_name_array);
            switch(((Journal_entry_feature) entry).getType()){
                case Android:
                    names = res.getStringArray(R.array.features_ANDROID);
                    img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.android));
                    break;
                case JAVA:
                    names = res.getStringArray(R.array.features_JAVA);
                    img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.java));
                    break;
                case Network:
                    names = res.getStringArray(R.array.features_NETWORK);
                    img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.network));
                    break;
                case  Design:
                    names = res.getStringArray(R.array.features_DESIGN);
                    img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.design));
                    break;
                case Diagrams:
                    names = res.getStringArray(R.array.features_DIAGRAMS);
                    img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.diagrams));
                    break;
            }

            name.setText(names[entry.getId()]);
            for(Feature_Event event : ((Journal_entry_feature) entry).getEvents()){
                String st = String.format(res.getString(R.string.there_was), events_names[event.getId()]);
                txt += st;
            }
            if(((Journal_entry_feature) entry).getWorkDone() != 0) {
                String st = String.format(res.getString(R.string.progress), ((Journal_entry_feature) entry).getWorkDone());
                txt += st;
            }
            if(((Journal_entry_feature) entry).isFinish())
                txt +=res.getString(R.string.complete);
            if(((Journal_entry_feature) entry).isCancel())
                txt +=res.getString(R.string.cancel);
        }

        if(entry instanceof Journal_entry_budget){
            name.setText(res.getString(R.string.budget));
            String st = String.format(res.getString(R.string.start_budget), Manager.getBudgetReadable(((Journal_entry_budget) entry).getStartBudget()));
            txt += st;
            st = String.format(res.getString(R.string.salaries), Manager.getBudgetReadable(((Journal_entry_budget) entry).getDepenses()));
            txt += st;
            st = String.format(res.getString(R.string.new_budget), Manager.getBudgetReadable(((Journal_entry_budget) entry).getNewBudget()));
            txt += st;
            img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bourse));
        }


        text.setText(txt);
        System.out.println("At least here");
        return vi;
    }
}
