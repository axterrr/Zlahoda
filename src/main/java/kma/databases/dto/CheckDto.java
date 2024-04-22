package kma.databases.dto;

import kma.databases.entities.IBuilder;

public class CheckDto {

    private String number;
    private String employeeId;
    private String customerCardNumber;
    private String printDate;
    private String totalSum;
    private String vat;

    public static class Builder implements IBuilder<CheckDto> {

        CheckDto check = new CheckDto();

        public Builder setNumber(String number) {
            check.number = number;
            return this;
        }

        public Builder setEmployeeId(String employeeId) {
            check.employeeId = employeeId;
            return this;
        }

        public Builder setCustomerCardNumber(String customerCardNumber) {
            check.customerCardNumber = customerCardNumber;
            return this;
        }

        public Builder setPrintDate(String printDate) {
            check.printDate = printDate;
            return this;
        }

        public Builder setTotalSum(String totalSum) {
            check.totalSum = totalSum;
            return this;
        }

        public Builder setVat(String vat) {
            check.vat = vat;
            return this;
        }

        @Override
        public CheckDto build() {
            return check;
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerCardNumber() {
        return customerCardNumber;
    }

    public void setCustomerCardNumber(String customerCardNumber) {
        this.customerCardNumber = customerCardNumber;
    }

    public String getPrintDate() {
        return printDate;
    }

    public void setPrintDate(String printDate) {
        this.printDate = printDate;
    }

    public String getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(String totalSum) {
        this.totalSum = totalSum;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
}
