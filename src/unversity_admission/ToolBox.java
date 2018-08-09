/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unversity_admission;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author acer
 */
public class ToolBox {
    
    public static double generator_nom(double mean, double var){
        // genertor_nom(0,0.1) generates values which
        // follows normal distribution with mean 0, and variance 0.1
        double x1 = Math.sqrt((-2*Math.log(Math.random())))*Math.cos(2*Math.PI*Math.random());
        return Math.sqrt(var)*x1+mean;
    }
    
    public static double generator_exp(double mean){
        //genertor_exp(5) generates a value which follows expenatial distribution with mean 5 
        return -mean * Math.log(Math.random());
    }
    
    public static boolean generator_bin(double ratio){
        //generator_bin(x)   (0<x<1) returns a boolean value 
        double rand = Math.random();
        if (rand<ratio){
            return true;
        }
        return false;
    }
    
    public static String generator_level(double mean, double var){
        double val = generator_nom(mean, var);
        if(val>=90){
            return "A";
        }
        else if(val>=80){
            return "B";
        }
        else if(val>=70){
            return "C";
        }
        else if(val>=60){
            return "D";
        }
        else{
            return "E";
        }
    }
    
    public static int level_to_grade(String level){
        if(level.equals("A")){
            return 90;
        }
        else if (level.equals("B")){
            return 80;
        }
        else if (level.equals("C")){
            return 70;
        }
        else if (level.equals("D")){
            return 60;
        }
        else
            return 50;
    }
    
    public static int solve_equ(int N, int M, int n){
        for(int p = 1; p<=M;p++){
            if(M%p==0){
                double temp1 = Math.ceil(Double.valueOf(N-n)/(Math.floor(0.5*Double.valueOf(n-p))+p)) + 1;
//                double temp1 = Math.ceil(Double.valueOf(N-n)/p) + 1;
                double temp2 = M/p;
                if(temp1 == temp2){
                    return p;
                }
            }
        }
        return 0;
    }
    
    
    
    public static void Filewriter(File file,ArrayList<ArrayList<Double>> data){
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(file, false));
            for(int i = 0; i<data.size();i++){
                for(int j =0;j<data.get(i).size();j++){
                    fw.write(String.valueOf(data.get(i).get(j)));
                    fw.write(",");
                }
                fw.newLine();
                //System.out.println("Data written to file");
            }
            fw.flush();       
            fw.close();
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
    
    
    public static void Filewriter_str(File file,ArrayList<ArrayList<String>> data){
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(file, false));
            for(int i = 0; i<data.size();i++){
                for(int j =0;j<data.get(i).size();j++){
                    fw.write(data.get(i).get(j));
                    if(j == data.get(i).size() - 1){

                    }
                    else{
                        fw.write(",");
                    }
                }
                fw.newLine();
                //System.out.println("Data written to file");
            }
            fw.flush();       
            fw.close();
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
    
    public static ArrayList<ArrayList<String>> Filereader_str(String filename){
        ArrayList<ArrayList<String>> record = new ArrayList<>();
        File file = new File(filename);
        String line = null;
        try {
            BufferedReader fr = new BufferedReader(new FileReader(file));
            while ( (line = fr.readLine() ) != null) {      // read next line if it exists
                String[] parts = line.split(",");
                ArrayList<String> temp = new ArrayList<>();
                for(int i = 0;i<parts.length;i++){
                    temp.add(parts[i]);
                }
                record.add(temp);
            }
            fr.close();     // close file
        }catch (IOException e) {
            e.printStackTrace();
        }
        return record;
    }
    
    
    public static double logistic_fun(double x){
        return 1/(Math.exp(x) + 1);
    }
    
    
    
    
    

}
