package kma.databases.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Check {

    private String number;
    private Employee employee;
    private CustomerCard customerCard;
    private LocalDateTime printDate;
    private BigDecimal totalSum;
    private BigDecimal vat;
    private List<Sale> sales;

    public static class Builder implements IBuilder<Check> {

        Check check = new Check();

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

        public Builder setSales(List<Sale> sales) {
            check.sales = new ArrayList<>();
            check.sales.addAll(sales);
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

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = new ArrayList<>();
        this.sales.addAll(sales);
    }
}
