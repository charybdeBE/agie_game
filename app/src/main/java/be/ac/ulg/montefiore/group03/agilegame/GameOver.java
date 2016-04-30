package be.ac.ulg.montefiore.group03.agilegame;

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

//TODO Lose the game (find a way to put a boolean here)
public class GameOver extends DialogFragment{

    public GameOver(){
        super();
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction

        Resources res = getResources();
        String msg = String.format(res.getString(R.string.game_over), res.getString(R.string.win), GameLogic.getInstance().getTurn(), GameLogic.getInstance().getBudget(), GameLogic.getInstance().getScore(true));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.end)
                .setMessage(msg)
                .setPositiveButton(R.string.new_hard, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameLogic.newGame(3);
                    }
                })
                .setNeutralButton(R.string.new_medium, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameLogic.newGame(2);
                    }
                })
                .setNegativeButton(R.string.new_easy, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameLogic.newGame(1);
                    }
                })
        ;
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
