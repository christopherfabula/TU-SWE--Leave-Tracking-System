package org.tu.swe.pojo;

import org.tu.swe.interfaces.Approvable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class LeaveRequest implements Approvable {

    protected int requestId;

    protected Employee employee;

    protected String startDate;

    protected String endDate;

    protected String status;

    protected String leaveType;

    private final ArrayList<StatusChange> statusHistory;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String leaveType) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.status = "Pending";
        this.statusHistory = new ArrayList<>();
    }

    public class StatusChange {
        private String newStatus;
        private String changeDate;
        private String changedBy;

        public StatusChange(String newStatus, String changeDate, String changedBy) {
            this.newStatus = newStatus;
            this.changeDate = changeDate;
            this.changedBy = changedBy;
        }

        public String getNewStatus() {
            return newStatus;
        }

        public void setNewStatus(String newStatus) {
            this.newStatus = newStatus;
        }

        public String getChangeDate() {
            return changeDate;
        }

        public void setChangeDate(String changeDate) {
            this.changeDate = changeDate;
        }

        public String getChangedBy() {
            return changedBy;
        }

        public void setChangedBy(String changedBy) {
            this.changedBy = changedBy;
        }
    }

    @Override
    public boolean approve(String approverName) {
        this.status = "Approved";
        System.out.println("Request #" + requestId + " approved by " + approverName);

        Date today = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        StatusChange statusChange = new StatusChange(this.status, df.format(today), approverName);
        statusHistory.add(statusChange);

        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        this.status = "Denied";
        System.out.println("Request #" + requestId + " denied by " + approverName + ". Reason: " + reason);

        Date today = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        StatusChange statusChange = new StatusChange(this.status, df.format(today), approverName);
        statusHistory.add(statusChange);

        return true;
    }

    public abstract int calculateLeaveDays();

    public boolean processRequest() {
        System.out.println("Processing generic leave request...");
        return true;
    }

    public void printStatusHistory() {
        System.out.println("---- Status History for Request #" + requestId + " ----");
        this.statusHistory.forEach(entry ->
                System.out.println(" -> Status set to " + entry.getNewStatus() + " by " + entry.getChangedBy() + " on " + entry.getChangeDate()));
        System.out.println("------------------------------------");
    }
}
