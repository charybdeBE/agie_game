package be.ac.ulg.montefiore.group03.agilegame.gamelogic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by charybde on 18.03.16.
 * Builder and "controller" of programmers
 */
public class Programmer_Builder {
    private static final int MAX_PROG = 8;
    private static final int MAX_SKILLS = 3;
    private static final int MAX_INTEREST = 2;
    private static final int MAX_LEVEL = 3;

    private static Programmer_Builder instance = null;

    private Random generator;
    private ArrayList<Programmer> avaiableCoders;
    private int total_nr_of_prog;

    private Programmer_Builder(){
        this.avaiableCoders = new ArrayList<Programmer>();
        this.generator = new Random();
        this.total_nr_of_prog = 0;
    }

    public static Programmer_Builder getInstance() {
        if(Programmer_Builder.instance == null){
            Programmer_Builder.instance = new Programmer_Builder();
        }
        return Programmer_Builder.instance;
    }
    public ArrayList<Programmer> getAvaiableCoders(){
        if(this.avaiableCoders.size() < MAX_PROG) {
            SkillType[] val_skill = SkillType.values(); //Cache the table for performencies
            Interest[] val_int = Interest.values();

            for(int i = this.avaiableCoders.size(); i < MAX_PROG; ++i){
                int nr_skills = generator.nextInt(MAX_SKILLS + 1);
                int nr_int = generator.nextInt(MAX_INTEREST + 1);
                ArrayList<Skills> skills = new ArrayList<Skills>();
                ArrayList<Interest> interest = new ArrayList<Interest>();

                for(int j = 0; j < nr_skills; ++j){
                    int level = generator.nextInt(MAX_LEVEL + 1);
                    int skill_int = generator.nextInt(val_skill.length);
                    skills.add(new Skills(val_skill[skill_int], level));
                }
                for(int j = 0; j < nr_int; ++j){
                    interest.add(val_int[generator.nextInt(val_int.length)]);
                }


                avaiableCoders.add(new Programmer(total_nr_of_prog,skills, interest));
                total_nr_of_prog++;
            }
        }

        return avaiableCoders;
    }

    /**
     * Remove a programmer from the list of avaiable programmer, it does not add it in the team
     * @param p the programmer to engage
     */
    public void engage(Programmer p){
        avaiableCoders.remove(p);
    }
}
