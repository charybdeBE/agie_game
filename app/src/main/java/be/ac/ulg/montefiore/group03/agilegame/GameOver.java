package be.ac.ulg.montefiore.group03.agilegame;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import be.ac.ulg.montefiore.group03.agilegame.gamelogic.GameLogic;

/**
 * Created by charybde on 29.04.16.
 */

public class GameOver extends DialogFragment{

    public GameOver(){
        super();
    }
    OnDialogDismissListener mCallback;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Resources res = getResources();
        String result;
        Boolean win;
        if(GameLogic.getInstance().getBudget() <= 0){
            result = res.getString(R.string.lose);
            win = false;
        }
        else{
            result = res.getString(R.string.win);
            win = true;
        }

        String msg = String.format(res.getString(R.string.game_over), result , GameLogic.getInstance().getTurn(), GameLogic.getInstance().getBudget(), GameLogic.getInstance().getScore(win));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.end)
                .setMessage(msg)
                .setPositiveButton(R.string.new_hard, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameLogic.newGame(3);
                        mCallback.onDialogDismissListener(0);
                    }
                })
                .setNeutralButton(R.string.new_medium, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameLogic.newGame(2);
                        mCallback.onDialogDismissListener(0);
                    }
                })
                .setNegativeButton(R.string.new_easy, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameLogic.newGame(1);
                        mCallback.onDialogDismissListener(0);
                    }
                })
        ;
        // Create the AlertDialog object and return it
        return builder.create();
    }


    //Solution to make a callback to the Manager (ie refresh) : http://stackoverflow.com/questions/16883578/activity-to-wait-for-dialog-fragment-input
    // Container Activity must implement this interface
    public interface OnDialogDismissListener {
        public void onDialogDismissListener(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnDialogDismissListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnDialogDismissListener");
        }
    }
}
