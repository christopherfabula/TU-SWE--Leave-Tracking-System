package org.tu.swe.pojo;

import org.tu.swe.interfaces.Approvable;

public abstract class LeaveRequest implements Approvable {

    protected int requestId;

    protected Employee employee;

    protected String startDate;

    protected String endDate;

    protected String status;

    protected String leaveType;

    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String leaveType) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.status = "Pending";
    }

    @Override
    public boolean approve(String approverName) {
        this.status = "Approved";
        System.out.println(approverName + " has approved your " + this.leaveType + ".");
        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        this.status = "Denied";
        System.out.println(approverName + " has denied your " + this.leaveType + " due to " + reason + ".");
        return true;
    }


}
