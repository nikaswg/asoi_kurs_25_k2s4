package contacts.model;

public enum Group {
    FAMILY("Семья"),
    FRIENDS("Друзья"),
    WORK("Работа"),
    OTHER("Другое");

    private final String displayName;

    Group(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}