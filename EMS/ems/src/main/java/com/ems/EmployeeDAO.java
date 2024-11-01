package com.ems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/employee";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mysql123";
    private Connection jdbcConnection;

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee (name, department, salary) VALUES (?, ?, ?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getDepartment());
        statement.setDouble(3, employee.getSalary());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Employee> listAllEmployees() throws SQLException {
        List<Employee> listEmployee = new ArrayList<>();

        String sql = "SELECT * FROM Employee";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String department = resultSet.getString("department");
            double salary = resultSet.getDouble("salary");

            Employee employee = new Employee(id, name, department, salary);
            listEmployee.add(employee);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listEmployee;
    }

    // Additional methods for update and delete operations
    public boolean updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET name = ?, department = ?, salary = ? WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getDepartment());
        statement.setDouble(3, employee.getSalary());
        statement.setInt(4, employee.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteEmployee(Employee employee) throws SQLException {
        String sql = "DELETE FROM Employee WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, employee.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }
}
