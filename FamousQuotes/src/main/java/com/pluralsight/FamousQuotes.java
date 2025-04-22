package com.pluralsight;

import java.util.Scanner;

public class FamousQuotes {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        String[] famousQuotes={
                "Why I outa",
                "He was number one",
                "Science, *****",
                "*R2D2 noises*",
                "A second plane has hit",
                "Gotta blast",
                "He can't keep getting away with it",
                "Somebody toucha my spaget",
                "Did you see the look on his face when he saw the gyat",
                "Hello there"
        };
        int choice;
        while(true){
            //asks if you want a random quote, y gives a random, anything else goes to user choice
            if(askQuestionGetString("Get a random quote? Y/N: ").equalsIgnoreCase("Y")){
                System.out.println(famousQuotes[(int)(Math.random()* famousQuotes.length)]);
            }
            //user choice
            else {
                while(true) {
                    //tries getting user input for given range, makes them give a new answer if out of range
                    try {
                        choice = askQuestionGetInt("Choose a number 1-10 to display a quote: ") - 1;
                        System.out.println(famousQuotes[choice]);
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                        continue;
                    }
                    break;
                }
            }
            //asks if they want to get another quote
            if (askQuestionGetString("Get another quote? Y/N: ").equalsIgnoreCase("Y")){
                continue;
            }
            break;
        }
    }

    //input the question you want answered, returns answer, expects string
    public static String askQuestionGetString(String q){
        System.out.print(q);
        return scanner.nextLine();
    }
    //input the question you want answered, returns answer, expects integer
    public static int askQuestionGetInt(String q){
        boolean asking=true;
        int answer=-1;
        while(asking){
            try{
                System.out.print(q);
                answer = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                printDivider(30);
                System.out.println("Error: must enter a number.");
                printDivider(30);
                continue;
            }
            asking=false;
        }
        return answer;
    }
    public static void printDivider(int n){
        StringBuilder dividerBuilder=new StringBuilder();
        for(int i=0;i<n;i++){
            dividerBuilder.append("-");
        }
        System.out.println(dividerBuilder);
    }
}
