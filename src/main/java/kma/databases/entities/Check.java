package kma.databases.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Check {

    private String number;
    private String employeeId;
    private String customerCardNumber;
    private LocalDateTime printDate;
    private BigDecimal totalSum;
    private BigDecimal vat;

    public static class Builder implements IBuilder<Check> {

        Check check = new Check();

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

        public Builder setPrintDate(LocalDateTime printDate) {
            check.printDate = printDate;
            return this;
        }

        public Builder setTotalSum(BigDecimal totalSum) {
            check.totalSum = totalSum;
            return this;
        }

        public Builder setVat(BigDecimal vat) {
            check.vat = vat;
            return this;
        }

        @Override
        public Check build() {
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

    public LocalDateTime getPrintDate() {
        return printDate;
    }

    public void setPrintDate(LocalDateTime printDate) {
        this.printDate = printDate;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }
}
