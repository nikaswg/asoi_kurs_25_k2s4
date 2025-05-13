package contacts.service.commands;

import contacts.model.Contact;
import contacts.repository.ContactRepository;
import java.util.List;
import java.util.Scanner;

public class SearchCommand implements Command {
    private final Scanner scanner;
    private final ContactRepository repository;

    public SearchCommand(Scanner scanner, ContactRepository repository) {
        this.scanner = scanner;
        this.repository = repository;
    }

    @Override
    public void execute() {
        try {
            System.out.println("\n╔══════════════════════════╗");
            System.out.println("║       ПОИСК КОНТАКТОВ    ║");
            System.out.println("╚══════════════════════════╝");

            System.out.print("├─ Введите имя или телефон: ");
            String query = scanner.nextLine();

            List<Contact> results = repository.searchContacts(query);

            if (results.isEmpty()) {
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║          Контакты не найдены               ║");
                System.out.println("╚════════════════════════════════════════════╝");
                return;
            }

            // Форматирование как в ListAllCommand
            String header =
                    "║ %-4s │ %-20s │ %-15s │ %-26s │ %-8s ║%n";
            String rowFormat =
                    "║ %-4d │ %-20s │ %-15s │ %-26s │ %-8s ║%n";

            System.out.println("╔══════╤══════════════════════╤═════════════════╤════════════════════════════╤══════════╗");
            System.out.println("║  ID  │        Имя           │   Телефон       │           Email            │  Группа  ║");
            System.out.println("╟──────┼──────────────────────┼─────────────────┼────────────────────────────┼──────────╢");

            for (int i = 0; i < results.size(); i++) {
                Contact c = results.get(i);
                System.out.printf(
                        rowFormat,
                        i + 1,
                        padRight(c.getName(), 20),
                        padRight(c.getPhone(), 15),
                        padRight(c.getEmail() != null ? c.getEmail() : "N/A", 26),
                        padRight(c.getGroup().toString(), 8)
                );
            }

            System.out.println("╚══════╧══════════════════════╧═════════════════╧════════════════════════════╧══════════╝");
            System.out.println(" Найдено контактов: " + results.size());

        } catch (Exception e) {
            System.err.println("\n╔══════════════════════════╗");
            System.err.println("║  ОШИБКА ПРИ ПОИСКЕ!  ⚠   ║");
            System.err.println("╚══════════════════════════╝");
            System.err.println(" Причина: " + e.getMessage());
        }
    }

    private String padRight(String s, int length) {
        return String.format("%-" + length + "s", s != null ? s : "");
    }

    @Override
    public String getDescription() {
        return "Поиск контактов";
    }
}