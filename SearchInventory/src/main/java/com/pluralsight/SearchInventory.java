package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class SearchInventory {
    static String inventoryFilePath="src/main/resources/inventory.csv";
    public static void main(String[] args) {
        ArrayList<Product> inventory = getInventory();
        menu(inventory);
    }

    public static void menu(ArrayList<Product> inventory){
        System.out.println("Welcome to *dundundundundundun*");
        boolean menuRunning=true;
        while(menuRunning) {
            MyUtils.printDivider(75);
            System.out.println("How can we help you today?\n   1-List all products\n   2-Lookup a product\n   3-Find all products within a price range\n   4-Add a new product\n   5-Quit the application");
            switch (MyUtils.askQuestionGetInt("Enter command: ")){
                case 1:{
                    listProducts(inventory);
                    break;
                }
                case 2:{
                    productLookUp(inventory);
                    break;
                }
                case 3:{
                    priceRangeLookUp(inventory);
                    break;
                }
                case 4:{
                    Product newProduct=newProduct();
                    inventory.add(newProduct);
                    inventory.sort(Comparator.comparing(Product::getName));
                    break;
                }
                case 5:{
                    menuRunning=false;
                    break;
                }
                default:{
                    System.out.println("Error:Invalid Input");
                }

            }

        }
    }

    public static void listProducts(ArrayList<Product> inventory){
        MyUtils.printDivider(75);
        System.out.println("We carry the following products: ");
        for(Product product:inventory){
            printProduct(product);
        }
    }

    public static void productLookUp(ArrayList<Product> inventory){
        MyUtils.printDivider(75);
        int id=MyUtils.askQuestionGetInt("Enter the Product ID: ");
        for(Product product:inventory){
            if(product.getId()==id){
                printProduct(product);
            }
        }
    }

    public static void priceRangeLookUp(ArrayList<Product> inventory){
        MyUtils.printDivider(75);
        float min=-1;
        float max=-1;
        while (min<0){
            min=MyUtils.askQuestionGetFloat("Please enter the minimum price: $");
            if(min<0){
                System.out.println("Minimum price cannot be less than 0.");
            }
        }
        while (max<min){
            max=MyUtils.askQuestionGetFloat("Please enter the maximum price: $");
            if(max<min){
                System.out.printf("Maximum price must be greater than than minimum($%.2f).\n",min);
            }
        }
        for(Product product:inventory){
            if(product.getPrice()>=min&&product.getPrice()<=max){
                printProduct(product);
            }
        }
    }

    public static Product newProduct(){
        MyUtils.printDivider(75);
        int id=MyUtils.askQuestionGetInt("Enter Product ID: ");
        String name=MyUtils.askQuestionGetString("Enter Product Name: ");
        float price=MyUtils.askQuestionGetFloat("Enter Price: $");
        Product product=new Product(id,name,price);
        printProduct(product);
        return product;
    }

    public static void printProduct(Product product){
        StringBuilder line=new StringBuilder();
        line.append("   ID: ");
        line.append(product.getId());
        line.append(" | ");
        line.append(product.getName());
        line.append(" ".repeat(Math.max(0,(40-product.getName().length()))));
        line.append("| $");
        line.append(String.format("%.2f",product.getPrice()));
        System.out.println(line);
    }

    //returns sorted inventory from inventory.csv file
    public static ArrayList<Product> getInventory(){
        ArrayList<Product> inventory = new ArrayList<>();
        try{
            FileReader fr = new FileReader(inventoryFilePath);
            BufferedReader readInventory = new BufferedReader(fr);
            String line;
            while ((line = readInventory.readLine()) != null) {
                String[] splitLine = line.split("\\|");
                Product product = new Product(Integer.parseInt(splitLine[0]), splitLine[1], Float.parseFloat(splitLine[2]));
                inventory.add(product);
            }
            readInventory.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        inventory.sort(Comparator.comparing(Product::getName));
        return inventory;
    }
}
