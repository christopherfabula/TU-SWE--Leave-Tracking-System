package org.tu.swe.pojo;

public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private int leaveBalance;

    public Employee() {
        this(0, "Unknown", "Unknown");
        this.leaveBalance = 15;
    }

    public Employee(int employeeId, String name, String department) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.leaveBalance = 20;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        if (leaveBalance < 0) {
            System.out.println("Invalid leaveBalance (leaveBalance >= 0)");
        } else {
            this.leaveBalance = leaveBalance;
        }
    }

    public boolean requestLeave(int numberOfDays) {
        if (numberOfDays <= this.leaveBalance) {
            this.leaveBalance = this.leaveBalance - numberOfDays;
            return true;
        }
        System.out.println("Insufficient Leave Balance");
        return false;
    }
}
