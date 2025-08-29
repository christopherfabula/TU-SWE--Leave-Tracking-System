package org.tu.swe;

import org.tu.swe.pojo.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class for Capstone Part 2 Feature Branch
 *
 */
public class LeaveTracker
{
    public static void main( String[] args ) {
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<LeaveRequest> leaveRequests = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int requestId = 0;

        // Display home screen
        int option = 0;
        do {
            System.out.println("Welcome to the HR Leave Management System!\n");
            System.out.println("--- Main Menu ---");
            System.out.println("1. Create New Leave Request");
            System.out.println("2. Process a Pending Request");
            System.out.println("3. View All Request Histories");
            System.out.println("4. Exit.");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            if (option == 1) {
                Employee emp1 = new Employee(1, "Alice", "Sales");
                Employee emp2 = new Employee(2, "Bob", "Marketing");
                Employee emp3 = new Employee(3, "Kevin", "Operations");
                employees.add(emp1);
                employees.add(emp2);
                employees.add(emp3);

                System.out.println("\n--- Create New Leave Request ---");
                employees.forEach(employee -> System.out.println(employee.getEmployeeId() + ". " + employee.getName()));
                System.out.print("Enter employee number: ");
                int empId = scanner.nextInt();
                Employee employee = employees.get(empId - 1);

                System.out.println("\n--- Select leave type: ---");
                System.out.println("1. Sick Leave");
                System.out.println("2. Vacation Leave");
                System.out.println("3. Maternity Leave");
                System.out.print("Enter leave type number: ");
                int leaveTypeNumber = scanner.nextInt();

                String startDate = null;
                String endDate = null;
                String medicalCertificateProvided = null;
                String isPaidTimeOff = null;
                String expectedDeliveryDate = null;

                System.out.print("Enter Start Date (YYYY-MM-DD): ");
                try {
                    startDate = reader.readLine();
                } catch (IOException e) {
                    System.err.println("Error reading input: " + e.getMessage());
                }

                System.out.print("Enter End Date (YYYY-MM-DD): ");
                try {
                    endDate = reader.readLine();
                } catch (IOException e) {
                    System.err.println("Error reading input: " + e.getMessage());
                }

                if (leaveTypeNumber == 1) {
                    System.out.print("Is a medical certificate provided? (true/false): ");
                    try {
                        medicalCertificateProvided = reader.readLine();
                    } catch (IOException e) {
                        System.err.println("Error reading input: " + e.getMessage());
                    }

                    SickLeaveRequest sickLeaveRequest = new SickLeaveRequest(
                            ++requestId, employee, startDate, endDate, Boolean.valueOf(medicalCertificateProvided));
                    leaveRequests.add(sickLeaveRequest);
                    System.out.println("\nSuccessfully created " + sickLeaveRequest.getLeaveType() + " for " + employee.getName() + "\n");
                }

                if (leaveTypeNumber == 2) {
                    System.out.print("Is this a paid time-off? (true/false): ");
                    try {
                        isPaidTimeOff = reader.readLine();
                    } catch (IOException e) {
                        System.err.println("Error reading input: " + e.getMessage());
                    }
                    VacationLeaveRequest vacationLeaveRequest = new VacationLeaveRequest(
                            ++requestId, employee, startDate, endDate, Boolean.valueOf(isPaidTimeOff));
                    leaveRequests.add(vacationLeaveRequest);
                    System.out.println("\nSuccessfully created " + vacationLeaveRequest.getLeaveType() + " for " + employee.getName() + "\n");
                }

                if (leaveTypeNumber == 3) {
                    System.out.print("Enter Expected Delivery Date (YYYY-MM-DD): ");
                    try {
                        expectedDeliveryDate = reader.readLine();
                    } catch (IOException e) {
                        System.err.println("Error reading input: " + e.getMessage());
                    }
                    MaternityLeaveRequest maternityLeaveRequest = new MaternityLeaveRequest(
                            ++requestId, employee, startDate, endDate, expectedDeliveryDate);
                    leaveRequests.add(maternityLeaveRequest);
                    System.out.println("\nSuccessfully created " + maternityLeaveRequest.getLeaveType() + " for " + employee.getName() + "\n");
                }
            }

            if (option == 2) {
                System.out.println("\n--- Processing Next Pending Request ---");
                LeaveRequest pendingLeaveRequest = null;
                for (LeaveRequest leaveRequest : leaveRequests) {
                    if (leaveRequest.getStatus().equals("Pending")) {
                        pendingLeaveRequest = leaveRequest;
                        break;
                    }
                }
                System.out.println("Request ID: " + pendingLeaveRequest.getRequestId());
                System.out.println("Employee: " + pendingLeaveRequest.getEmployee().getName());
                String leaveType = pendingLeaveRequest.getLeaveType();
                System.out.println("Leave Type: " + leaveType);
                System.out.println("Dates " + pendingLeaveRequest.getStartDate() + " to " + pendingLeaveRequest.getEndDate());

                if (leaveType.equals("Sick Leave")) {
                    if (!pendingLeaveRequest.processRequest()) {
                        pendingLeaveRequest.deny("System", "System validation failed.");
                    } else {
                        pendingLeaveRequest.approve("System");
                    }
                    pendingLeaveRequest.printStatusHistory();
                } else {
                    pendingLeaveRequest.processRequest();
                    pendingLeaveRequest.approve("System");
                    pendingLeaveRequest.printStatusHistory();
                }
            }
        } while (option != 4);

        if (option == 4) {
            System.out.println("Exiting System. Goodbye!");
        }
        scanner.close();
    }


}
