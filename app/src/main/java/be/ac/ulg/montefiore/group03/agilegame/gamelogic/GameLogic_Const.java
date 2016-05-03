package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

/**
 * Created by laurent on 03.05.16.
 */
public interface GameLogic_Const {

    //Events :
     int MAX_EVENT = 10; //Max Nr of events per month
     double RANDOMNESS_EVENT = 0.7; //Randomness of the nr of events
     int NR_OF_PROG_EVENT = 9; //Nr of differents prog event
     int NR_OF_FEAT_EVENT = 4; // Nr of different prog event

    //Application
    int MAX_TASK_SIZE = 20; //Max size of a task
    int INITIAL_BUDGET = 1000000;


    //Programmer
    int MAX_PROG = 8; //Maximum number of free programmer (not in team)
    int MAX_SKILLS = 3; //Maximum nr of skill in a new prog
    int MAX_INTEREST = 2; //Max nr of interest in a new prog
    int MAX_LEVEL_START = 3; //Max level of a skill in a new prog
}
