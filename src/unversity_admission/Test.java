/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unversity_admission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author acer
 */
public class Test {
    public static void main(String[] args) {
        HashMap<String, ArrayList<Double>> Dic = new HashMap<>();
        ArrayList<Double> temp = new ArrayList<>();
        temp.add(Double.valueOf(1.2));
        temp.add(Double.valueOf(3.2));
        Dic.put("name", temp);
        System.out.println(Dic.get("name").get(0));
        System.out.println(25/17);
//        for (int N =1000; N<=1000; N +=100){
//            for(int M = 200; M<=200; M +=50){
                int N =1000;
                int M = 800;
                for(int n = 50; n<=N; n +=5){
                    int temp1 = ToolBox.solve_equ(N, M, n);
                    if(temp1 != 0){
                        System.out.println("N: "+ N + " M: " + M + " n: " +n+ " p: "+temp1 + " rounds: " + M/temp1);
                    }
                    else{
                        System.out.println("N: "+ N + " M: " + M + " n: " +n+ " p: "+temp1);
                    }
                }
//            }
//        }
    }
}
