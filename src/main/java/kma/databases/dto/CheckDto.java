package kma.databases.dto;

import kma.databases.entities.Check;
import kma.databases.entities.CustomerCard;
import kma.databases.entities.Employee;
import kma.databases.entities.IBuilder;

public class CheckDto {

    private String number;
    private Employee employee;
    private CustomerCard customerCard;
    private String printDate;
    private String totalSum;
    private String vat;

    public static class Builder implements IBuilder<CheckDto> {

        CheckDto check = new CheckDto();

        public Builder setNumber(String number) {
            check.number = number;
            return this;
        }

        public Builder setEmployee(Employee employee) {
            check.employee = employee;
            return this;
        }

        public Builder setCustomerCard(CustomerCard customerCard) {
            check.customerCard = customerCard;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public CustomerCard getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(CustomerCard customerCard) {
        this.customerCard = customerCard;
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
