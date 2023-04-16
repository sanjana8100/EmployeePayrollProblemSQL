package com.bridgelabz.EmployeePayrollProblem;

import java.sql.*;
import java.util.Enumeration;

public class EmployeePayrollJDBC {
    static final String DB_URL = "jdbc:mysql://localhost:3306/payroll_service";
    static final String USER = "root";
    static final String PASS = "Admin";

    public static void main(String[] args) {
        Connection connection;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.print("Driver Loaded!");
        } catch (ClassNotFoundException e){
            throw new IllegalStateException("Cannot find the driver in the classpath!",e);
        }

        listDrivers();

        try {
            System.out.println("Connecting to database: "+DB_URL);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connection is successful!!!! "+connection);

            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();

            int count = statement1.executeUpdate("update employee_payroll set basic_pay = \"3000000.0\" where id =2;");
            System.out.println(count);

            ResultSet resultSet = statement2.executeQuery("select * from employee_payroll");
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
        } catch (SQLException e) {
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