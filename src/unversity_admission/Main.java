/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unversity_admission;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

/**
 *
 * @author acer
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    private ArrayList<Applicant> arrival;
    private ArrayList<Applicant> applicants;
    private ArrayList<Applicant> candidates;
    private ArrayList<Applicant> admits;
    private ArrayList<Applicant> rejects;
    private ArrayList<ArrayList<Integer>> np;
    private ArrayList<ArrayList<Double>> total_round_info;
    private int n;
    private int p;
    private int r; // the number of rolling rounds
    private int N; // the total number of applied students 
    private int M; // the total number of admitted students
    private double time;
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Main main = new Main(400,1000,200);
                
    }
    
    public Main(int n, int N, int M){
        this.n = n;
        this.N = N;
        this.M = M; 
        //this.init_applicants(N);
        ArrayList<ArrayList<Integer>> np = init_p(N,M,n);  // initiate apps with N, M AND n
        this.init(new University(60,60,10,80),np);
    }
    
    
    public Main(int N,int M){
        this.N = N;
        this.M = M; 
        ArrayList<ArrayList<Integer>> np = init_np(N,M); // initiate apps with N AND M
        this.init(new University(60,60,10,80),np);
    }
    
    public void init(University university,ArrayList<ArrayList<Integer>> np){
        int total_round;
        int iteration = 500;
        ArrayList<ArrayList<Double>> np_data_avg = new ArrayList<>();
//        init_applicants_txt("applicants.txt"); //init the applicant by original data
//        init_applicants(N); //init the applicant by random number
        
           //test replica
            for (int j= 0; j<np.size(); j++){       // test different n-p value                
                ArrayList<ArrayList<Double>> np_data = new ArrayList<>();
                ArrayList<ArrayList<Double>> rolling_start_time = new ArrayList<>();
                ArrayList<ArrayList<Double>> rolling_end_time = new ArrayList<>();
                ArrayList<ArrayList<Double>> rank_end_time = new ArrayList<>();
                ArrayList<ArrayList<Double>> duration = new ArrayList<>();
                ArrayList<ArrayList<Double>> admitted_score = new ArrayList<>();
                ArrayList<ArrayList<Double>> reject_score = new ArrayList<>();
                ArrayList<ArrayList<Double>> first_server_end_time = new ArrayList<>();
                
                ArrayList<ArrayList<Double>> admitted_score_div = new ArrayList<>();
                ArrayList<ArrayList<Double>> reject_score_div = new ArrayList<>();
                for(int k =0; k<iteration;k++){
                    init_applicants_txt("applicants.txt"); //init the applicant by original data
//                    init_applicants(N); //init the applicant by random number
                    n = np.get(j).get(0);
                    p =  np.get(j).get(1);
                    total_round = np.get(j).get(2);
                    applicants = new ArrayList<>();
                    candidates = new ArrayList<>();
                    admits = new ArrayList<>();
                    rejects = new ArrayList<>();
                    total_round_info = new ArrayList<>();
                    time = 0;
                    int counter = 0; // current rolling round
                    int[] p_perroll = new int[total_round];
                    int scholar_quota_perround = university.get_quota_scholarship()/total_round;
                    double time_record_perround[][] = new double[total_round][4];
                    time_record_perround[0][0]=0;
                    Service reviewing = new Service(1);
                    Service reviewing2 = new Service(1.5);
                    int num = 0;
                    int temp = 0;
                    while (num <= N &&counter<total_round){
                        if(counter>temp){
                            time_record_perround[counter][0] = time;
                        }
                        temp = counter;
                        System.out.println(num);
                        if(applicants.size() < n&&!arrival.isEmpty()){
                            Applicant app = arrival.get(0);
                            arrival.remove(0);
                            num++;
                            time = app.get_arr_time();
                            reviewing.set_start_time(time);
                            reviewing.flush_quene("review");
                            System.out.println("Applicant No. " + app.get_num() + " arrive ");
                            if(app.get_language()){
                                if(app.get_GPA()>=university.get_req_GPA()){
                                    applicants.add(app);
                                    if(reviewing.get_status()){
                                        app.set_startend_time("review", time, time + ToolBox.generator_exp(reviewing.get_service_time()));
                                        reviewing.set_end_time(app.get_end_time("review"));
                                    }
                                    else{
                                        if(reviewing.get_quene_length()>0){
                                            double start_time = reviewing.get_lastapp_quene().get_end_time("review");
                                            app.set_startend_time("review", start_time, start_time +  ToolBox.generator_exp(reviewing.get_service_time()));
                                            reviewing.set_end_time(app.get_end_time("review"));
                                        }
                                        else{
                                            app.set_startend_time("review", reviewing.get_end_time(), reviewing.get_end_time()+ToolBox.generator_exp(reviewing.get_service_time()));
                                            reviewing.set_end_time(app.get_end_time("review"));
                                        }
                                        reviewing.addtoquene(app);
                                    }
                                    reviewing2.set_start_time(app.get_end_time("review"));
                                    reviewing2.flush_quene("review2");
                                    if(reviewing2.get_status()){
                                        double start_time = Math.max(reviewing2.get_start_time(), app.get_end_time("review"));
                                        app.set_startend_time("review2", start_time, start_time+ToolBox.generator_exp(reviewing2.get_service_time()));
                                        reviewing2.set_end_time(app.get_end_time("review2"));
                                    }
                                    else{
                                        if(reviewing2.get_quene_length()>0){
                                            double start_time = Math.max(reviewing2.get_lastapp_quene().get_end_time("review2"), app.get_end_time("review"));
                                            app.set_startend_time("review2", start_time, start_time +  ToolBox.generator_exp(reviewing2.get_service_time()));
                                            reviewing2.set_end_time(app.get_end_time("review2"));
                                        }
                                        else{
                                            double start_time = Math.max(reviewing2.get_end_time(), app.get_end_time("review"));
                                            app.set_startend_time("review2", start_time, start_time+ToolBox.generator_exp(reviewing2.get_service_time()));
                                            reviewing2.set_end_time(app.get_end_time("review2"));
                                        }
                                        reviewing2.addtoquene(app);
                                    }

                                }
                            }
                            else{
                                if(app.get_test_score()>=university.get_req_test_score()){
                                    if(app.get_GPA()>=university.get_req_GPA()){
                                        applicants.add(app);
                                        if(reviewing.get_status()){
                                        app.set_startend_time("review", time, time + ToolBox.generator_exp(reviewing.get_service_time()));
                                        reviewing.set_end_time(app.get_end_time("review"));
                                        }
                                        else{
                                            if(reviewing.get_quene_length()>0){
                                                double start_time = reviewing.get_lastapp_quene().get_end_time("review");
                                                app.set_startend_time("review", start_time, start_time +  ToolBox.generator_exp(reviewing.get_service_time()));
                                                reviewing.set_end_time(app.get_end_time("review"));
                                            }
                                            else{
                                                app.set_startend_time("review", reviewing.get_end_time(), reviewing.get_end_time()+ToolBox.generator_exp(reviewing.get_service_time()));
                                                reviewing.set_end_time(app.get_end_time("review"));
                                            }
                                            reviewing.addtoquene(app);
                                        }
                                        reviewing2.set_start_time(app.get_end_time("review"));
                                        reviewing2.flush_quene("review2");
                                        if(reviewing2.get_status()){
                                            double start_time = Math.max(reviewing2.get_start_time(), app.get_end_time("review"));
                                            app.set_startend_time("review2", start_time, start_time+ToolBox.generator_exp(reviewing2.get_service_time()));
                                            reviewing2.set_end_time(app.get_end_time("review2"));
                                        }
                                        else{
                                            if(reviewing2.get_quene_length()>0){
                                                double start_time = Math.max(reviewing2.get_lastapp_quene().get_end_time("review2"), app.get_end_time("review"));
                                                app.set_startend_time("review2", start_time, start_time +  ToolBox.generator_exp(reviewing2.get_service_time()));
                                                reviewing2.set_end_time(app.get_end_time("review2"));
                                            }
                                            else{
                                                double start_time = Math.max(reviewing2.get_end_time(), app.get_end_time("review"));
                                                app.set_startend_time("review2", start_time, start_time+ToolBox.generator_exp(reviewing2.get_service_time()));
                                                reviewing2.set_end_time(app.get_end_time("review2"));
                                            }
                                            reviewing2.addtoquene(app);
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            int current_round = counter+1;
//                            if(current_round ==1){
//                                p=80;
//                            }
//                            else if(current_round == 4){
//                                p=30;
//                            }
//                            else{
//                                p=45;
//                            }
                            time_record_perround[counter][2]=reviewing2.get_end_time();
                            time_record_perround[counter][1]=reviewing.get_end_time();
                            
                            
                            
                            
                            Collections.sort(applicants, new Comparator<Applicant>() {
                            @Override
                            public int compare(Applicant o1, Applicant o2) {
                                Double in1 = o1.get_comprehensive();
                                Double in2 = o2.get_comprehensive();
                                return in2.compareTo(in1);
                            }
                            });


                            double total_time = 0;
                            double max_time = 0;
                            double min_time = 1000000000;
                            int num_stu = applicants.size();
                            for (int i = 0; i<num_stu;i++){
                                if (i<p){
                                    Applicant candidate = applicants.get(0);
                                    if(i<=scholar_quota_perround-1){
                                        if (candidate.get_GPA()>90 && candidate.get_test_score()>85 && ToolBox.level_to_grade(candidate.get_PS())>=80 
                                                && ToolBox.level_to_grade(candidate.get_recommend())>=80){
                                            candidate.set_scholarship();
                                        }
                                    }
                                    candidate.set_admission(true);
                                    candidate.set_wait_time(time_record_perround[counter][2]-candidate.get_arr_time());
                                    double wait_time = candidate.get_wait_time();
                                    if(wait_time>=max_time){
                                        max_time = wait_time;
                                    }
                                    if(wait_time<=min_time){
                                        min_time = wait_time;
                                    }
                                    total_time += wait_time;
                                    candidates.add(candidate);
                                    applicants.remove(0);
                                }
                                else if(i>=p+ Math.floor(0.5*(n-p))){
                                    applicants.remove(applicants.size()-1);
                                }
                            }
                            double avg = total_time/p;
                            double range = max_time-min_time;
                            double sum_admit = 0;
                            double sum_reject = 0;
                            double num_scholar = 0;
                            ArrayList<Applicant> temp2 = new ArrayList<>();
                            ArrayList<Applicant> temp3 = new ArrayList<>();
                            for (int i = 0; i<candidates.size();i++){
                                Applicant can = candidates.get(i);
                                can.set_avg_wait(avg);
                                if(can.get_scholarship()){
                                   can.set_pro(0.3*(ToolBox.logistic_fun((can.get_wait_time()-can.get_avg_wait())/range))+0.5);
//                                   can.set_pro(1);
                                   num_scholar ++;
                                }
                                else{
                                    can.set_pro(ToolBox.logistic_fun((can.get_wait_time()-can.get_avg_wait())/range));
//                                    can.set_pro(1);

                                }
                                if(can.get_pro()>=0.4){
                                    temp2.add(can);
                                    sum_admit += can.get_comprehensive();
                                    can.to_string();
                                }
                                else{
                                    temp3.add(can);
                                    sum_reject += can.get_comprehensive();
                                    can.to_string();
                                }
                            }
                            double avg_score = sum_admit/temp2.size();
                            double sta_score = cal_sta(candidates,avg_score);
                            double avg_score_rej = sum_reject/temp3.size();
                            double sta_reject = cal_sta(rejects,avg_score_rej);



                            double totaltime_roll = time_record_perround[counter][2] - time_record_perround[counter][0];
                            ArrayList<Double> round_info = new ArrayList<>();
                            round_info.add((double)current_round);
                            round_info.add((double)temp2.size());
                            round_info.add((double)temp3.size());
                            round_info.add((double)applicants.size());
                            round_info.add(totaltime_roll);
                            round_info.add(avg_score);              //5
                            round_info.add(sta_score);
                            round_info.add(avg_score_rej);         //7
                            round_info.add(sta_reject);
                            round_info.add(num_scholar);
                            round_info.add(time_record_perround[counter][0]);
                            round_info.add(time_record_perround[counter][1]);
                            round_info.add(time_record_perround[counter][2]);
                            total_round_info.add(round_info);

                            System.out.println("****************************************");
                            System.out.println("****************************************");
                            System.out.println("****************************************");
                            System.out.println("Round No. "+ current_round +" Report: \n"
                            +"Applicants reviewed this roll: " + candidates.size() + "\n"
                            +"Applicants admitted this roll: " + temp2.size() + "\n"
                            +"Applicants rejected in this roll: " + temp3.size() + "\n"
                            +"Applicants in the waitlist: " + applicants.size() + "\n"
                            +"Sevice time: " + totaltime_roll );
                            System.out.println("****************************************");
                            System.out.println("****************************************");
                            System.out.println("****************************************");
                            for(Applicant app: temp2){
                                admits.add(app);
                            }
                            for(Applicant app: temp3){
                                rejects.add(app);
                            }
                            temp3.clear();
                            temp2.clear();
                            candidates.clear();
                            counter++;

                        }
                        reviewing.set_information();
                        reviewing2.set_information();
                    }

                    ToolBox.Filewriter(new File("reviewing.txt"), reviewing.get_information());
                    ToolBox.Filewriter(new File("reviewing2.txt"), reviewing2.get_information());
                    double total_time_period = time_record_perround[counter-1][2];
                    System.out.println("****************************************");
                    System.out.println("****************************************");
                    System.out.println("****************************************");
                    System.out.println("Summary Report: \n"
                            + "Total applicants " + N  + "\n"
                            + "Applicants not reviewed：" + arrival.size()+ "\n"
                            + "Applicants admitted: " + admits.size()+ "\n"
                            + "Applicants rejected: " + rejects.size() +"\n"
                            + "Total rounds " + total_round + "\n"
                            + "Total service time: " + total_time_period);
                    System.out.println("****************************************");
                    System.out.println("****************************************");
                    System.out.println("****************************************");


                    Collections.sort(applicants, new Comparator<Applicant>() {
                        @Override
                        public int compare(Applicant o1, Applicant o2) {
                            Double in1 = o1.get_comprehensive();
                            Double in2 = o2.get_comprehensive();
                            return in2.compareTo(in1);
                        }
                        });


                    double total_time = 0;
                    double max_time = 0;
                    double min_time = 1000000000;
                    for (int i = 0; i<rejects.size();i++){
                        if (!applicants.isEmpty()){
                            Applicant candidate = applicants.get(0);
                            candidate.set_wait_time(time_record_perround[counter-1][2]-candidate.get_arr_time());
                            candidate.set_admission(true);
                            double wait_time = candidate.get_wait_time();
                            if(wait_time>=max_time){
                                max_time = wait_time;
                            }
                            if(wait_time<=min_time){
                                min_time = wait_time;
                            }
                            total_time += wait_time;
                            candidates.add(candidate);
                            applicants.remove(0);
                        }
                    }
                    double avg = total_time/rejects.size();
                    double range = max_time-min_time;
                    ArrayList<Applicant> temp2 = new ArrayList<>();
                    ArrayList<Applicant> temp3 = new ArrayList<>();
                    double sum_admit = 0;
                    double sum_reject = 0;
                    for (int i = 0; i<candidates.size();i++){
                        Applicant can = candidates.get(i);
                        can.set_avg_wait(avg);
                        can.set_pro(ToolBox.logistic_fun((can.get_wait_time()-can.get_avg_wait())/range));
                        if(can.get_pro()>=0.5){
                            temp2.add(can);
                            sum_admit += can.get_comprehensive();
                            can.to_string();
                        }
                        else{
                            temp3.add(can);
                            sum_reject += can.get_comprehensive();
                            can.to_string();
                        }
                    }
                    double avg_score = sum_admit/temp2.size();
                    double sta_score = cal_sta(candidates,avg_score);
                    double avg_score_rej = sum_reject/temp3.size();
                    double sta_reject = cal_sta(rejects,avg_score_rej);

                    ArrayList<Double> round_info = new ArrayList<>();
                    round_info.add(0.0);                        //0
                    round_info.add((double)temp2.size());      //1
                    round_info.add((double)temp3.size());       //2
                    round_info.add((double)applicants.size());  //3
                    round_info.add(0.0);                     //4
                    round_info.add(avg_score);              //5
                    round_info.add(sta_score);
                    round_info.add(avg_score_rej);         //7
                    round_info.add(sta_reject);
                    round_info.add(0.0);                    
                    round_info.add(0.0);
                    round_info.add(0.0);
                    round_info.add(0.0);
                    total_round_info.add(round_info);





                    System.out.println("Final final round " +" Report: \n"
                    +"Applicants reviewed this roll: " + candidates.size() + "\n"
                    +"Applicants admitted this roll: " + temp2.size() + "\n"
                    +"Applicants rejected in this roll: " + temp3.size() + "\n");
                    System.out.println("****************************************");
                    System.out.println("****************************************");
                    System.out.println("****************************************");
                    for(Applicant app: temp2){
                        admits.add(app);
                    }
                    for(Applicant app: temp3){
                        rejects.add(app);
                    }
                    temp3.clear();
                    temp2.clear();
                    ArrayList<ArrayList<Double>> data_collect = new ArrayList<>();
                    double sum_total = 0;
                    double sum_wait_time = 0;
                    for (Applicant app : admits){
                        sum_total += app.get_comprehensive();
                        sum_wait_time += app.get_wait_time();
                        ArrayList<Double> temp4 = new ArrayList<>();
                        temp4.add((double)app.get_num());
                        temp4.add(app.get_comprehensive());
                        temp4.add(app.get_wait_time());
                        if(app.get_scholarship()){
                            temp4.add(1.0);
                        }
                        else{
                            temp4.add(0.0);
                        }
                        temp4.add(app.get_arr_time());
                        temp4.add(app.get_start_time("review"));
                        temp4.add(app.get_end_time("review"));
                        temp4.add(app.get_start_time("review2"));
                        temp4.add(app.get_end_time("review2"));
                        data_collect.add(temp4);
                    }
                    double avg_wait_time = sum_wait_time/admits.size();
                    double avg_total = sum_total/admits.size();
                    double div_total = cal_sta(admits,avg_total);


                    get_info_replic(12,total_round_info,rolling_end_time);
                    get_info_replic(10,total_round_info,rolling_start_time);
                    get_info_replic(11,total_round_info,first_server_end_time);
                    get_info_replic(4,total_round_info,duration);
                    get_info_replic(5,total_round_info,admitted_score);
                    get_info_replic(6,total_round_info,admitted_score_div);
                    get_info_replic(7,total_round_info,reject_score);
                    get_info_replic(8,total_round_info,reject_score_div);



                    ToolBox.Filewriter(new File("round_info.txt"), total_round_info);
                    ToolBox.Filewriter(new File("admitted_stu_information.txt"), data_collect);
                    ArrayList<Double> storage = new ArrayList<>();
                    storage.add((double)n);
                    storage.add((double)p);
                    storage.add((double)total_round);
                    storage.add(avg_total);
                    storage.add(div_total);
                    storage.add(total_time_period);
                    storage.add(avg_wait_time);
                    np_data.add(storage);
                    
                    Applicant.return_default();
                    arrival.clear();







                }
                ArrayList<Double> avg_admit = cal_avg(admitted_score);
                ArrayList<Double> avg_admit_sta = cal_avg(admitted_score_div);
                ArrayList<Double> avg_reject = cal_avg(reject_score);
                ArrayList<Double> avg_reject_sta = cal_avg(reject_score_div);
                ArrayList<Double> avg_rolling_start_time = cal_avg(rolling_start_time);
                ArrayList<Double> avg_first_server_end_time = cal_avg(first_server_end_time);
                ArrayList<Double> avg_rolling_end_time = cal_avg(rolling_end_time);
                ArrayList<Double> avg_duration = cal_avg(duration);
                ArrayList<ArrayList<Double>> final_out = new ArrayList<>();
                final_out.add(avg_admit);
                final_out.add(avg_admit_sta);
                final_out.add(avg_reject);
                final_out.add(avg_reject_sta);
                final_out.add(avg_rolling_start_time);
                final_out.add(avg_first_server_end_time);
                final_out.add(avg_rolling_end_time);
                final_out.add(avg_duration);

                ToolBox.Filewriter(new File("final_out.txt"), final_out);
                ToolBox.Filewriter(new File("start_time.txt"), rolling_start_time);
                ToolBox.Filewriter(new File("end_time.txt"), rolling_end_time);
                ToolBox.Filewriter(new File("duration.txt"), duration);
                ToolBox.Filewriter(new File("admitted_score.txt"), admitted_score);
                ToolBox.Filewriter(new File("reject_score.txt"), reject_score);    
                
                
                ArrayList<Double> avg_np = cal_avg(np_data);
                np_data_avg.add(avg_np);
                
            }
            
            ToolBox.Filewriter(new File("np.txt"), np_data_avg);
        
     
    }
    
    public void init_applicants(int num){
        ArrayList<ArrayList<String>> stu_profile = new ArrayList<>();
        this.arrival = new ArrayList<Applicant>();
        for (double i = 0; i < num; i++){
            
            Applicant app = new Applicant(ToolBox.generator_bin(0.6),ToolBox.generator_nom(70+Math.log(i+1), 100),
                    ToolBox.generator_nom(70+Math.log(i+1), 100),ToolBox.generator_level(70+Math.log(i+1), 100),
                    ToolBox.generator_level(70+Math.log(i+1), 100),ToolBox.generator_exp(6*Math.exp(-(Math.log(3)/(num/2))*i)+2));
            arrival.add(app);
            stu_profile.add(app.get_profile());
        }
        ToolBox.Filewriter_str(new File("applicants.txt"), stu_profile);
        
    }
    
    public void init_applicants_txt(String filename){
        ArrayList<ArrayList<String>> stu_profile = ToolBox.Filereader_str(filename);
        this.arrival = new ArrayList<Applicant>();
        for(int i=0; i<stu_profile.size();i++){
            ArrayList<String> temp = stu_profile.get(i);
            Applicant app = new Applicant(false,Double.valueOf(temp.get(1)), Double.valueOf(temp.get(2)),
                                    temp.get(3),temp.get(4),Double.valueOf(temp.get(5)));
            arrival.add(app);
        }
        System.out.println(stu_profile.size());
    }
    
    public ArrayList<ArrayList<Integer>> init_np(int N, int M){
        ArrayList<ArrayList<Integer>> outcome = new ArrayList<>();
        int num1 = N/5;
        int num2 = 2*(N/5);
        int num3 = 3*(N/5);
        int num4 = 4*(N/5);
        int num5 = N;
        int[] nums = {num1,num2,num3,num4,num5};
        for(int i=0; i<nums.length;i++){
            ArrayList<Integer> temp2 = new ArrayList<>();
            int p = 0;
            int n = nums[i];
            while(true){
                p = ToolBox.solve_equ(N, M, n);
                if(p!=0){
                    break;
                }
                n += 5;
            }
            temp2.add(Integer.valueOf(n));
            temp2.add(Integer.valueOf(p));
            temp2.add(Integer.valueOf(M/p));
            outcome.add(temp2);
        }
        return outcome;
    }
    
    public ArrayList<ArrayList<Integer>> init_p(int N, int M, int n){
        ArrayList<ArrayList<Integer>> outcome = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        int p = ToolBox.solve_equ(N, M, n);
        temp.add(Integer.valueOf(n));
        temp.add(Integer.valueOf(p));
        temp.add(Integer.valueOf(M/p));
        outcome.add(temp);
        return outcome;
    }
    
    public void get_info_replic(int index, ArrayList<ArrayList<Double>> getter, ArrayList<ArrayList<Double>> setter){
        ArrayList<Double> temp = new ArrayList<>();
        for (int i=0;i<getter.size();i++){
            temp.add(getter.get(i).get(index));
        }
        setter.add(temp);
    }
    
    public ArrayList<Double> cal_avg(ArrayList<ArrayList<Double>> object){
        ArrayList<Double> sum = new ArrayList<>();
        double columns = object.get(0).size();
        double rows = object.size();
        for(int i = 0; i<columns;i++){
            sum.add(0.0);
        }
        for (int i =0;i<rows;i++){
            for (int j=0;j<columns;j++){
                sum.set(j, sum.get(j) +object.get(i).get(j));
            }
        }
        ArrayList<Double> avg = new ArrayList<>();
        for(int i =0; i<columns;i++){
            avg.add(sum.get(i)/rows);
        }
        return avg;
    }
    
    
    public double cal_sta(ArrayList<Applicant> apps, double mean){
        double sum = 0;
        for (Applicant app: apps){
            sum += Math.pow((app.get_comprehensive()-mean),2);
        }
        return Math.sqrt(sum/apps.size());
    }
    
    
    public double cal_idle(ArrayList<ArrayList<Double>> input){
        double sum = 0;
        for(int i = 0; i<input.size()-1;i++){
            ArrayList<Double> temp = input.get(i);
            ArrayList<Double> temp2 = input.get(i+1);
            if(temp.get(1)==0){
                sum +=  temp2.get(2)-temp.get(2);
            }
        }
        return sum/input.get(input.size()-1).get(2);
    }

    
}
