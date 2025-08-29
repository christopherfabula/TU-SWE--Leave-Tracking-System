package org.tu.swe.pojo;

public class VacationLeaveRequest extends LeaveRequest {

    private boolean isPaidTimeOff;

    public VacationLeaveRequest(int requestId, Employee employee, String startDate, String endDate, boolean isPaidTimeOff) {
        super(requestId, employee, startDate, endDate, "Vacation Leave");
        this.isPaidTimeOff = isPaidTimeOff;
    }
}
