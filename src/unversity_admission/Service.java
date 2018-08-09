/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unversity_admission;

import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class Service {
    private ArrayList<ArrayList<Double>> information;
    private boolean idle = true;
    private double service_time;
    private ArrayList<Applicant> quene;
    private double start_time = 0;
    private double end_time = 0;
    private int counter = 1;
    
    public Service(double service_time){
        this.service_time = service_time;
        this.quene = new ArrayList<>();
        this.information= new ArrayList<>();
    }
    
    public void set_information(){
        ArrayList<Double> temp = new ArrayList<>();
//        temp.add((double)counter);
        temp.add(Double.valueOf(this.get_quene_length()));
        temp.add(Double.valueOf(Boolean.compare(!idle, false)));
        temp.add(Double.valueOf(this.get_start_time()));
        this.information.add(temp);
        counter ++;
    }
    
    public ArrayList<ArrayList<Double>> get_information(){
        return information;
    }
    
    public Applicant get_lastapp_quene(){
        return quene.get(quene.size()-1);
    }
    
    public void addtoquene(Applicant applicant){
        quene.add(applicant);
    }
    
    public void removefromquene(){
        quene.remove(0);
    }
    
    public void flush_quene(String name){
        while(!quene.isEmpty()){
            if(this.start_time>=this.get_firstapp_in_quene().get_end_time(name)){
                this.removefromquene();
            }
            else{
                break;
            }
        }
    }
    
    public boolean get_status(){
        if(quene.size()>0){
            idle = false;
            return false;
        }
        else{
            if (this.start_time< this.end_time){
                idle = false;
                return false;
            }
            else{
                idle = true;
                return true;
            }
        }
    }
    
    public double get_service_time(){
        return this.service_time;
    }
    
    public void set_service_time(double time){
        this.service_time = time;
    }
    
    public double get_start_time(){
        return this.start_time;
    }
    
    public void set_start_time(double time){
        this.start_time = time;
    }
    
    public double get_end_time(){
        return this.end_time;
    }
    
    public void set_end_time(double time){
        this.end_time = time;
    }
    
    public int get_quene_length(){
        return this.quene.size();
    }
    
    public Applicant get_firstapp_in_quene(){
            return this.quene.get(0);
 
    }
    
    
    
    
}
