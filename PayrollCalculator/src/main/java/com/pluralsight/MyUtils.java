package com.pluralsight;

import java.util.Scanner;

public class MyUtils {
    static Scanner input=new Scanner(System.in);
    //prints n character long "-" text divider
    public static void printDivider(int n){
        StringBuilder dividerBuilder=new StringBuilder();
        for(int i=0;i<n;i++){
            dividerBuilder.append("-");
        }
        System.out.println(dividerBuilder);
    }
    //input the question you want answered, returns answer, expects string
    public static String askQuestionGetString(String q){
        System.out.print(q);
        return input.nextLine();
    }
    //input the question you want answered, returns answer, expects integer
    public static int askQuestionGetInt(String q){
        boolean asking=true;
        int answer=-1;
        while(asking){
            try{
                System.out.print(q);
                answer = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                input.nextLine();
                printDivider(30);
                System.out.println("Error: must enter a number.");
                printDivider(30);
                continue;
            }
            asking=false;
        }
        return answer;
    }
}
