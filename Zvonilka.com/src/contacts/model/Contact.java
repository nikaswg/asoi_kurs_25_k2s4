package contacts.model;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String email;
    private Group group;

    public Contact(String name, String phone, String email, Group group) {
        setName(name);
        setPhone(phone);
        setEmail(email);
        this.group = group;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name.trim();
    }

    private void setPhone(String phone) {
        if (!Pattern.matches("^\\+?[0-9\\s-]{6,}$", phone)) {
            throw new IllegalArgumentException("Неверный формат телефона");
        }
        this.phone = phone.trim();
    }

    private void setEmail(String email) {
        if (email != null && !email.isEmpty() &&
                !Pattern.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            throw new IllegalArgumentException("Неверный формат email");
        }
        this.email = email != null ? email.trim() : null;
    }

    // Геттеры
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public Group getGroup() { return group; }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s", name, phone, email != null ? email : "N/A", group);
    }
}