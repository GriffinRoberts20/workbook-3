package com.pluralsight;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class PayrollCalculator {
    static String folderPath="src/main/resources/";
    public static void main(String[] args) {

        String filePath=folderPath+MyUtils.askQuestionGetString("Enter the name of the employee file to process: ");

        processPayroll(filePath);
    }

    public static void processPayroll(String filePath){
        try {
            FileReader fileReader = new FileReader(filePath);
            //stores new file name
            String writeFileName=MyUtils.askQuestionGetString("Enter the name of the payroll file to create: ");
            FileWriter fileWriter = new FileWriter(folderPath+writeFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //string for reading
            String line;
            //string for writing
            String lineWrite;
            //stores file type of new write; example "example.csv"->"csv"
            String fileType=writeFileName.split("\\.")[1];
            //eats header
            bufferedReader.readLine();
            //write file is type csv
            if(fileType.equalsIgnoreCase("csv")){
                fileWriter.write("id|name|gross pay\n");
                //reads each line, splits on |, initializes employee and prints id, name, and gross pay, writes those properties to csv
                while ((line = bufferedReader.readLine()) != null) {
                    //split input line into parts
                    String[] splitLine = line.split("\\|");
                    //initialize employee object with input data
                    Employee employee = new Employee(Integer.parseInt(splitLine[0]), splitLine[1], Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]));
                    //shows payroll info in terminal
                    MyUtils.printDivider(20);
                    System.out.printf("Employee ID #%d\n%s\nGross Pay:$%.2f\n", employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
                    //store formatting of next line to write
                    lineWrite = String.format("%d|%s|%.2f\n", employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
                    //write next line
                    bufferedWriter.write(lineWrite);
                }
              //write file is type json
            } else if (fileType.equalsIgnoreCase("json")) {
                //initialize json array
                JSONArray jsonArray=new JSONArray();
                while ((line = bufferedReader.readLine()) != null){
                    //initialize json object
                    JSONObject jsonObject=new JSONObject();
                    //split input line into parts
                    String[] splitLine = line.split("\\|");
                    //initialize employee object with input data
                    Employee employee = new Employee(Integer.parseInt(splitLine[0]), splitLine[1], Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]));
                    //shows payroll info in terminal
                    MyUtils.printDivider(20);
                    System.out.printf("Employee ID #%d\n%s\nGross Pay:$%.2f\n", employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
                    //adds employee data to json object
                    jsonObject.put("id",employee.getEmployeeId());
                    jsonObject.put("name",employee.getName());
                    jsonObject.put("grossPay",employee.getGrossPay());
                    //adds json object to array
                    jsonArray.put(jsonObject);
                }
                //example print of data from json array
                System.out.println(jsonArray.getJSONObject(0).get("name"));
                //write json file
                bufferedWriter.write(jsonArray.toString(2));
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
