package org.tu.swe.pojo;

public class SickLeaveRequest extends LeaveRequest {

    private boolean medicalCertificateProvided;

    public SickLeaveRequest(int requestId, Employee employee, String startDate, String endDate, boolean medicalCertificateProvided) {
        super(requestId, employee, startDate, endDate, "Sick Leave");
        this.medicalCertificateProvided = medicalCertificateProvided;
    }

    @Override
    public int calculateLeaveDays() {
        return 5;
    }

    @Override
    public boolean processRequest() {
        System.out.println("Processing sick leave request for ");
        if (calculateLeaveDays() > 2 && !this.medicalCertificateProvided) {
            System.out.println("-> VALIDATION FAILED: Sick leave longer than 2 days requires a medical certificate.");
            return false;
        }
        return true;
    }
}
