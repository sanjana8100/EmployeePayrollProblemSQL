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

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from employee_payroll");

            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("id"));
                System.out.print(", Name: " + resultSet.getString("name"));
                System.out.print(", Salary: " + resultSet.getDouble("salary"));
                System.out.print(", Date: " + resultSet.getDate("start"));
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