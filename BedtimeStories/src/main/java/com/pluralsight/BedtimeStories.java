package com.pluralsight;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class BedtimeStories {

    public static void main(String[] args) {
        boolean appRunning=true;
        String[] stories={
                "Goldilocks",
                "Hansel and Gretel",
                "Mary had a little lamb"
        };
        while(appRunning){
            String filePath;
            System.out.println("Available stories:");
            int i=1;
            for(String story:stories){
                System.out.printf("%d. %s\n",i,story);
                i++;
            }
            switch(MyUtils.askQuestionGetInt("Which story would you like to read?\n")){
                case 1:{
                    filePath ="src/main/resources/goldilocks.txt";
                    break;
                }
                case 2:{
                    filePath ="src/main/resources/hansel_and_gretel.txt";
                    break;
                }
                case 3:{
                    filePath ="src/main/resources/mary_had_a_little_lamb.txt";
                    break;
                }
                default:{
                    System.out.println("Invalid input.");
                    continue;
                }
            }
            MyUtils.printDivider(30);
            try{
                Scanner fileScanner=new Scanner(new FileInputStream(filePath));
                i=1;
                while(fileScanner.hasNextLine()){
                    System.out.println(i+".  "+fileScanner.nextLine());
                    i++;
                }
                fileScanner.close();
            } catch (IOException e) {
                System.out.println("Error:file not found.");
                continue;
            }
            MyUtils.printDivider(30);
            if(MyUtils.askQuestionGetString("Type Y to read another story: ").equalsIgnoreCase("Y")){
                MyUtils.printDivider(30);
                continue;
            }
            appRunning=false;
        }
    }



}
