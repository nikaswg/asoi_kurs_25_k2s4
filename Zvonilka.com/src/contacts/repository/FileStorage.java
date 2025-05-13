package contacts.repository;

import contacts.model.Contact;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private static final String FILE_PATH = "data/contacts.dat";

    public void saveContacts(List<Contact> contacts) throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {
            oos.writeObject(contacts);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Contact> readContacts() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH))) {
            return (List<Contact>) ois.readObject();
        }
    }
}