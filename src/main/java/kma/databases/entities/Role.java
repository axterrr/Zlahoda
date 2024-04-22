package kma.databases.entities;

public enum Role {

    MANAGER("manager"),
    CASHIER("cashier");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role getRole(String value) {
        for (Role role : Role.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new RuntimeException("Role with such string value doesn't exist");
    }
}
