package com.knoldus.learning.controller;

import com.knoldus.learning.entity.Employee;
import com.knoldus.learning.service.SaveEmployeeService;
import com.knoldus.learning.service.SaveEmployeeServiceImpl;
import com.knoldus.learning.service.ValidateEmployeeService;
import com.knoldus.learning.service.ValidateEmployeeServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class EmployeeController {

    public static void main(String[] a){
        Scanner scn = new Scanner(System.in);
        ValidateEmployeeService validateEmployeeService = new ValidateEmployeeServiceImpl();
        SaveEmployeeService saveEmployeeService = new SaveEmployeeServiceImpl();

        // remove below for loop and
        // read data from employee.csv, csv have id, and name in each row.
        // focus on Functional Interface and mark them Functional if it is.
        // focus on Single responsibility principle.
        // -1,Jiten
        // 2,Ram

        System.out.println("Enter the file path: ");
        String mDir = scn.nextLine();
        File file = new File(mDir);
        FileReader fr = null;
        try{
            fr = new FileReader(file);
        }
        catch (FileNotFoundException e){
            System.out.println("No file found");
        }

        BufferedReader infile = new BufferedReader(fr);
       // ArrayList<String> lines = new ArrayList<String>();
        List<Employee> emplList = new ArrayList<>();
        String line = "";
        boolean done = false;

        try {
            while (!done) {
                line = infile.readLine();
                if (line == null) {
                    done = true;
                }
                else {
                    String[] word = line.split(",");
                    int num = Integer.parseInt(String.valueOf(word[0]));
                    String temp = word[1];
/*                    System.out.println(temp);
                    System.out.println(num);*/
                    Employee e = new Employee(num,temp);
                    emplList.add(e);
                }
            }
            infile.close();
        }
        catch (IOException e) {
            System.out.println("Could not read file");
        }
        for (Employee item : emplList) {
            System.out.println(item);
        }

/*        for(int i = -5;i<10;i++){
            Employee employee = new Employee(i,"Name"+i);
            emplList.add(employee);
        }*/

       System.out.println("Total employee size  :  "+emplList.size());

        //empList iterate and call validate service, then save.
        for (Employee e : emplList) {
            if(validateEmployeeService.validateEmployee(e))
                saveEmployeeService.saveEmployee(e);
        }

        //Remove this error
        SaveEmployeeServiceImpl e1 = new SaveEmployeeServiceImpl();
        System.out.println("Saved employee  : "+e1.getEmployeeCount());

    }
}
