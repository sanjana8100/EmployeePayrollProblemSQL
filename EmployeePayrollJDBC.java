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

            PreparedStatement preparedStatement1;
            PreparedStatement preparedStatement2;

            preparedStatement1 = connection.prepareStatement("update employee_payroll set basic_pay = ? where id = ?;");
            preparedStatement1.setDouble(1, 3000000.0);
            preparedStatement1.setInt(2, 2);
            int count = preparedStatement1.executeUpdate();
            System.out.println(count);

            preparedStatement2 = connection.prepareStatement("select * from employee_payroll where name = ?");
            preparedStatement2.setString(1, "Stanley");
            ResultSet resultSet = preparedStatement2.executeQuery();
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