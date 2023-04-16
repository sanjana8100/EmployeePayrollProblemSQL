package com.bridgelabz.EmployeePayrollProblem;

import java.sql.*;
import java.time.LocalDate;
import java.util.Enumeration;

public class EmployeePayrollJDBC {
    static final String DB_URL = "jdbc:mysql://localhost:3306/payroll_service";
    static final String USER = "root";
    static final String PASS = "Admin";

    public static void main(String[] args) {
        Connection connection;

        //UC1
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.print("Driver Loaded!");
        } catch (ClassNotFoundException e){
            throw new IllegalStateException("Cannot find the driver in the classpath!",e);
        }

        listDrivers();

        try {
            //UC1
            System.out.println("\n");
            System.out.println("Connecting to database: "+DB_URL);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connection is successful!!!! "+connection);

            //UC2
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee_payroll");
            printResultSet(resultSet);

            //UC3
            System.out.println("\n");
            PreparedStatement preparedStatement1;
            preparedStatement1 = connection.prepareStatement("update employee_payroll set basic_pay = ? where id = ?;");
            preparedStatement1.setDouble(1, 3000000.0);
            preparedStatement1.setInt(2, 2);
            int count1 = preparedStatement1.executeUpdate();
            System.out.println(count1);

            //UC4
            System.out.println("\n");
            PreparedStatement preparedStatement2;
            preparedStatement2 = connection.prepareStatement("select * from employee_payroll where name = ?");
            preparedStatement2.setString(1, "Stanley");
            ResultSet resultSet1 = preparedStatement2.executeQuery();
            printResultSet(resultSet1);

            //UC5
            System.out.println("\n");
            PreparedStatement preparedStatement3;
            preparedStatement3 = connection.prepareStatement("select * from employee_payroll where start between ? and ?;");
            preparedStatement3.setDate(1, Date.valueOf("2022-08-01"));
            preparedStatement3.setDate(2, Date.valueOf(LocalDate.now()));
            boolean value = preparedStatement3.execute();
            if (value){
               ResultSet resultSet2 = preparedStatement3.getResultSet();
               printResultSet(resultSet2);
            }else {
                int count2 = preparedStatement3.getUpdateCount();
                System.out.println(count2);
            }

            //UC6
            System.out.println("\n");
            Statement statement1 = connection.createStatement();
            ResultSet sumResultSet = statement1.executeQuery("select sum(salary) from employee_payroll where gender = \"Male\" group by gender;");
            while (sumResultSet.next())
                System.out.println("Sum Of All Male Employee's Salaries: "+ sumResultSet.getInt("sum(salary)"));

            System.out.println();
            Statement statement2 = connection.createStatement();
            ResultSet avgResultSet = statement2.executeQuery("select avg(salary) from employee_payroll where gender = \"Male\" group by gender;");
            while (avgResultSet.next())
                System.out.println("Average Of All Male Employee's Salaries: "+ avgResultSet.getInt("avg(salary)"));

            System.out.println();
            Statement statement3 = connection.createStatement();
            ResultSet minResultSet = statement3.executeQuery("select min(salary) from employee_payroll where gender = \"Male\" group by gender;");
            while (minResultSet.next())
                System.out.println("Minimum Of All Male Employee's Salaries: "+ minResultSet.getInt("min(salary)"));

            System.out.println();
            Statement statement4 = connection.createStatement();
            ResultSet maxResultSet = statement4.executeQuery("select max(salary) from employee_payroll where gender = \"Male\" group by gender;");
            while (maxResultSet.next())
                System.out.println("Maximum Of All Male Employee's Salaries: "+ maxResultSet.getInt("max(salary)"));

            System.out.println();
            Statement statement5 = connection.createStatement();
            ResultSet noOfMaleEmpResultSet = statement5.executeQuery("select count(id) from employee_payroll where gender = \"Male\" group by gender;");
            while (noOfMaleEmpResultSet.next())
                System.out.println("Number Of Male Employees: "+ noOfMaleEmpResultSet.getInt("count(id)"));

            System.out.println();
            Statement statement6 = connection.createStatement();
            ResultSet noOfFemaleEmpResultSet = statement6.executeQuery("select count(id) from employee_payroll where gender = \"Female\" group by gender;");
            while (noOfFemaleEmpResultSet.next())
                System.out.println("Number Of Female Employees: "+ noOfFemaleEmpResultSet.getInt("count(id)"));

        } catch (SQLException e) {
            System.out.println("Catch");
        }
    }

    private static void printResultSet(ResultSet resultSet){
        try {
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("id"));
                System.out.print(", Name: " + resultSet.getString("name"));
                System.out.print(", Gender: " + resultSet.getString("gender"));
                System.out.print(", Salary: " + resultSet.getDouble("salary"));
                System.out.print(", Basic Pay: " + resultSet.getString("basic_pay"));
                System.out.print(", Deductions: " + resultSet.getString("deductions"));
                System.out.print(", Taxable Pay: " + resultSet.getString("taxable_pay"));
                System.out.print(", Income Tax: " + resultSet.getString("income_tax"));
                System.out.print(", Net Pay: " + resultSet.getString("net_pay"));
                System.out.print(", Department: " + resultSet.getString("department"));
                System.out.print(", Start Date: " + resultSet.getDate("start"));
                System.out.print(", Phone Number: " + resultSet.getString("phonenumber"));
                System.out.print(", Address: " + resultSet.getString("address"));
                System.out.println();
            }
        } catch (SQLException e){
            System.out.println("Catch");
        }
    }

    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()){
            Driver driverClass = driverList.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }
}