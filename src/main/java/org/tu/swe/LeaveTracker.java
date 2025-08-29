package org.tu.swe;

import org.tu.swe.pojo.Employee;
import org.tu.swe.pojo.LeaveRequest;

/**
 * Driver class for Capstone Part 1 Feature Branch
 *
 */
public class LeaveTracker
{
    public static void main( String[] args ) {
        Employee emp1 = new Employee(1, "John Doe", "Sales");
        Employee emp2 = new Employee(2, "John Dela Cruz", "Marketing");
        Integer numberOfDays = 2;

        LeaveRequest leaveRequestEmp1 = new LeaveRequest(1, emp1, "08/29/2025", "09/01/2025");

        // Simulate leave request for emp1
        System.out.println("Leave request simulation (happy path)");
        System.out.printf("Leave balance before requesting leave: %d %n", emp1.getLeaveBalance());
        System.out.printf("Number of Days: %d %n", numberOfDays);
        if (emp1.requestLeave(numberOfDays)) {
            System.out.printf("Leave balance after requesting leave: %d %n", emp1.getLeaveBalance());
        }

        System.out.println("\nLeave request simulation (invalid request)");
        System.out.printf("Leave balance before requesting leave: %d %n", emp1.getLeaveBalance());
        numberOfDays = emp1.getLeaveBalance() + 1;
        System.out.printf("Number of Days: %d %n", numberOfDays);
        if (!emp1.requestLeave(numberOfDays)) {
            System.out.printf("Leave balance after requesting leave: %d %n", emp1.getLeaveBalance());
        }

        System.out.println("\nLeave request details: ");
        System.out.printf("Employee Name: %s %nStart Date: %s %nEnd Date: %s %nStatus: %s %n",
                leaveRequestEmp1.getEmployee().getName(),
                leaveRequestEmp1.getStartDate(),
                leaveRequestEmp1.getEndDate(),
                leaveRequestEmp1.getStatus());
    }
}
