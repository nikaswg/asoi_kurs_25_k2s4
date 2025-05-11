package car.service.commands;

public interface Command {
    void execute();
    String getDescription();
}