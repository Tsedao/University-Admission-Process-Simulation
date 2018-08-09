/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unversity_admission;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author acer
 */
public class Applicant {
    private HashMap<String, ArrayList<Double>> Dic = new HashMap<>();
    private boolean scholarship;
    private boolean language; // 1: Native-English speaker 0:Non-native
    private double test_score; // GRE/GMAT
    private double GPA;
    private String recommend; //level of recommendation letter
    private String PS; //level of personal statenment
    private double respond_time;
    private double wait_time;
    private boolean admission; //1: sent the admission
    private double pro; // the probability to reject the admission
    private double inter_arr;
    private double store_arr_time;
    private int store_num;
    private double comprehensive;
    private static double arr_time = 0;
    private static int num = 0;
    private static double average_wait = 0;
    
    public Applicant(boolean language,double test_score,double GPA,
            String recommend,String PS,double inter_arr){
        this.language = language;
        this.test_score = test_score;
        this.GPA = GPA;
        this.recommend = recommend;
        this.PS = PS;
        this.wait_time = 0;
        this.admission = false;
        this.scholarship = false;
        this.pro = 0;
        this.inter_arr = inter_arr;
        Applicant.arr_time += this.inter_arr;
        num++;
        this.store_arr_time = arr_time;
        this.store_num = num;
        this.comprehensive = 0.4*get_GPA() + 0.2*get_test_score() + 0.3*ToolBox.level_to_grade(get_PS())+ 0.1*ToolBox.level_to_grade(get_recommend());

        
    }
    
    public ArrayList<String> get_profile(){
        ArrayList<String> profile = new ArrayList<>();
        profile.add(Boolean.toString(this.get_language()));
        profile.add(Double.toString(test_score));
        profile.add(Double.toString(GPA));
        profile.add(recommend);
        profile.add(PS);
        profile.add(Double.toString(inter_arr));
        profile.add(Double.toString(comprehensive));
        profile.add(Double.toString(num));
        profile.add(Double.toString(arr_time));
        return profile;
    }
    
    
    public void set_startend_time(String name,double start_time, double end_time){
        ArrayList<Double> temp = new ArrayList<>();
        temp.add(Double.valueOf(start_time));
        temp.add(Double.valueOf(end_time));
        Dic.put(name, temp);
    }
    
    public double get_start_time(String name){
        return Dic.get(name).get(0);
    }
    
    public double get_end_time(String name){
        return Dic.get(name).get(1);
    }
    
    
    
    public double get_avg_wait(){
        return Applicant.average_wait;
    }
    
    public void set_avg_wait(double time){
        Applicant.average_wait = time;
    }
    
    
    public boolean get_scholarship(){
        return this.scholarship;
    }
    
    public void set_scholarship(){
        this.scholarship = true;
    }
    
    public double get_comprehensive(){
        return this.comprehensive;
    }
    
    
    public boolean get_language(){
        return this.language;
    }
    
    public void set_language(boolean language){
        this.language = language;
    }
    
    public double get_test_score(){
        return this.test_score;
    }
    
    public void set_test_score(double test_score){
        this.test_score = test_score;
    }
    
    public double get_GPA(){
        return this.GPA;
    }
    
    public void set_GPA(double GPA){
        this.GPA = GPA;
    }
    
    public String get_recommend(){
        return this.recommend;
    }
    
    public void set_recommend(String recommend){
        this.recommend = recommend;
    }
    
    public String get_PS(){
        return this.PS;
    }
    
    public void set_PS(String PS){
        this.PS = PS;
    }
    
    public double get_respond_time(){
        return this.respond_time;
    }
    
    public void set_respond_time(double time){
        this.respond_time = time;
    }
    
    public double get_wait_time(){
        return this.wait_time;
    }
    
    public void set_wait_time(double time){
        this.wait_time = time;
    }
    
    public boolean get_admission(){
        return this.admission;
    }
    
    public void set_admission(boolean condition){
        this.admission = condition;
    }
    
    public double get_pro(){
        return this.pro;
    }
    
    public void set_pro(double pro){
        this.pro = pro;
    }
    
    public int get_num(){
        return this.store_num;
    }
    
    public double get_inter_arr(){
        return this.inter_arr;
    }
    
    public double get_arr_time(){
        return this.store_arr_time;
    }
    
    
    public static void return_default(){
        Applicant.arr_time=0;
        Applicant.average_wait = 0;
        Applicant.num = 0;
    } 
    
    public void to_string(){
        System.out.println("Applicant No. " + this.get_num() + " Admission status: " + this.get_admission()+ "\n" +
                            "Arrive time: " + this.get_arr_time() +  "\n" +
                            "Language proficiency: " + this.get_language() +"\n" + 
                            "GPA: " + this.get_GPA() + "  Standized test score: " + this.get_test_score()+ "\n"+
                            "Recommendation level " + this.get_recommend() + "\n" +
                            "Personal statement level " + this.get_PS() + "\n" +
                            "Comprehensive score: " + this.get_comprehensive() + "\n" +
                            "Scholorship status: " + this.get_scholarship() + "\n"+
                            "Wait time: " + this.wait_time + " h \n" +
                            "Probablity to accept the admission: " + this.get_pro());
        System.out.println();
    }
    
}
