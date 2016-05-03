package be.ac.ulg.montefiore.group03.agilegame.listAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import be.ac.ulg.montefiore.group03.agilegame.activities.Manager;
import be.ac.ulg.montefiore.group03.agilegame.R;
import be.ac.ulg.montefiore.group03.agilegame.Utils;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Features;
import be.ac.ulg.montefiore.group03.agilegame.gamelogic.Programmer;

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

        vi.setOnDragListener(new taskDragListener(data.get(position)));

        Resources res = context.getResources();
        TextView name = (TextView) vi.findViewById(R.id.name);

        ImageView skill = (ImageView) vi.findViewById(R.id.skill);

        skill.setImageDrawable(Utils.getFeatureImg(data.get(position), context));
        name.setText(Utils.getFeatureName(data.get(position), context));

        TextView time = (TextView) vi.findViewById(R.id.size_Task);
        double t = data.get(position).getDuration();
        if(t > 14.0)
            time.setTextColor(Color.RED);
        else if (t > 7.0)
            time.setTextColor(Color.rgb(255,165,0));
        else
            time.setTextColor(Color.rgb(0,102,0));

        time.setText(String.format(" %.2f", t));

        return vi;

    }

    class taskDragListener implements View.OnDragListener {

        private Features task = null;

        public taskDragListener (Features t) {
            super();
            this.task = t;
        }

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
                    Programmer p = (Programmer) event.getLocalState();
                    p.setWork(this.task);
                    ((Manager) context).refreshProgList(); //TODO protect from bad casts
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    // when the drop is finished (after)
                default:
                    break;
            }
            return true;
        }
    }
}