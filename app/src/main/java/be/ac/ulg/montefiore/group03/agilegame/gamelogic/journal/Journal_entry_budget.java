package be.ac.ulg.montefiore.group03.agilegame.gamelogic.journal;

/**
 * Created by charybde on 21.04.16.
 */
public class Journal_entry_budget implements Journal_entry {
    private int start;
    private int salaries; //Depenses

    public void addSalaryDepense(int money){
        salaries += money;
    }

    public int getDepenses(){
        return salaries;
    }

    public int getNewBudget(){
        return start - salaries;
    }

    public int getStartBudget() {return start; }

    public void setStartBudget(int budget){
        start = budget;
    }

    @Override
    public void setId(int id) {
        
    }

    @Override
    public int getId() {
        return 0;
    }
}
