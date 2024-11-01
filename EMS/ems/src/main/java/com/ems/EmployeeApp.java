package com.ems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EmployeeApp extends JFrame {
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField salaryField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private EmployeeDAO employeeDAO;

    public EmployeeApp() {
        employeeDAO = new EmployeeDAO();

        setTitle("Employee Management System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // Department label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        departmentField = new JTextField(20);
        add(departmentField, gbc);

        // Salary label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1;
        salaryField = new JTextField(20);
        add(salaryField, gbc);

        // Add button
        gbc.gridx = 0;
        gbc.gridy = 3;
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addEmployee();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(addButton, gbc);

        // Update button
        gbc.gridx = 1;
        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateEmployee();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(updateButton, gbc);

        // Delete button
        gbc.gridx = 2;
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteEmployee();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(deleteButton, gbc);
    }

    private void addEmployee() throws SQLException {
        String name = nameField.getText();
        String department = departmentField.getText();
        double salary = Double.parseDouble(salaryField.getText());

        Employee newEmployee = new Employee(0, name, department, salary);
        employeeDAO.insertEmployee(newEmployee);

        JOptionPane.showMessageDialog(this, "Employee added successfully!");
    }

    private void updateEmployee() throws SQLException {
        String name = nameField.getText();
        String department = departmentField.getText();
        double salary = Double.parseDouble(salaryField.getText());

        // Assume we're updating an employee with ID 1 for this example
        Employee updatedEmployee = new Employee(1, name, department, salary);
        employeeDAO.updateEmployee(updatedEmployee);

        JOptionPane.showMessageDialog(this, "Employee updated successfully!");
    }

    private void deleteEmployee() throws SQLException {
        // Assume we're deleting an employee with ID 1 for this example
        Employee employeeToDelete = new Employee(1, null, null, 0);
        employeeDAO.deleteEmployee(employeeToDelete);

        JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeApp().setVisible(true);
            }
        });
    }
}
