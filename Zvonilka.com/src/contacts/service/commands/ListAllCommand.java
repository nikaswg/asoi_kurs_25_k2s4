package contacts.service.commands;

import contacts.model.Contact;
import contacts.repository.ContactRepository;
import java.util.List;

public class ListAllCommand implements Command {
    private final ContactRepository repository;

    public ListAllCommand(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        List<Contact> contacts = repository.getAllContacts();

        // Фиксированные ширины столбцов
        String header =
                "║ %-4s │ %-20s │ %-15s │ %-26s │ %-8s ║%n";
        String rowFormat =
                "║ %-4d │ %-20s │ %-15s │ %-26s │ %-8s ║%n";

        System.out.println("\n╔══════╤══════════════════════╤═════════════════╤════════════════════════════╤══════════╗");
        System.out.println("║  ID  │        Имя           │   Телефон       │           Email            │  Группа  ║");
        System.out.println("╟──────┼──────────────────────┼─────────────────┼────────────────────────────┼──────────╢");

        if (contacts.isEmpty()) {
            System.out.println("║                             Нет сохраненных контактов                             ║");
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                Contact c = contacts.get(i);
                System.out.printf(
                        rowFormat,
                        i + 1,
                        padRight(c.getName(), 20),
                        padRight(c.getPhone(), 15),
                        padRight(c.getEmail() != null ? c.getEmail() : "N/A", 26),
                        padRight(c.getGroup().toString(), 8)
                );
            }
        }

        System.out.println("╚══════╧══════════════════════╧═════════════════╧════════════════════════════╧══════════╝");
    }

    private String padRight(String s, int length) {
        return String.format("%-" + length + "s", s != null ? s : "");
    }



    @Override
    public String getDescription() {
        return "Показать все контакты";
    }
}