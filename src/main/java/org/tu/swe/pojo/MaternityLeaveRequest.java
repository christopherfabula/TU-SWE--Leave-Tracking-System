package org.tu.swe.pojo;

public class MaternityLeaveRequest extends LeaveRequest {

    private String expectedDeliveryDate;

    public MaternityLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String expectedDeliveryDate) {
        super(requestId, employee, startDate, endDate, "Maternity Leave");
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
