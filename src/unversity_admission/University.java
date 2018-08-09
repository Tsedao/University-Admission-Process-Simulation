/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unversity_admission;

/**
 *
 * @author acer
 */
public class University {
    private double req_test_score;
    private double req_GPA;
    private int quota_scholarship;
    private double req_scholarship;
    private double duration1;  //between start and the ddl of application
    private double duration2; //between start of sending admission and ddl of acception
    private int admitted_stu;
    
    public University(double req_test_score,double req_GPA,int quota,double req_scholarship){
        this.req_scholarship = req_scholarship;
        this.req_GPA = req_GPA;
        this.req_test_score = req_test_score;
        this.quota_scholarship = quota;
    }
    
    public double get_req_test_score(){
        return this.req_test_score;
    }
    
    public double get_req_GPA(){
        return this.req_GPA;
    }
    
    public double get_req_scholarship(){
        return this.req_scholarship;
    }
    
    public int get_quota_scholarship(){
        return this.quota_scholarship;
    }
    
    public void set_duration1(double time){
        this.duration1 = time;
    }
    
    public double get_duration1(){
        return this.duration1;
    }
    
    public void set_duration2(double time){
        this.duration2 = time;
    }
    
    public double get_duration2(){
        return this.duration2;
    }
    
    public void set_admitted_stu(int num){
        this.admitted_stu = num;
    }
    
    public int get_admitted_stu(){
        return this.admitted_stu;
    }
    
}
