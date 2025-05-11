package car.service.commands;

import car.model.Expense;
import car.repository.FileFacade;
import car.service.strategies.SummaryStrategy;
import java.util.List;

public class SummaryCommand implements Command {
    private final FileFacade fileFacade;
    private final SummaryStrategy strategy;

    public SummaryCommand(FileFacade fileFacade, SummaryStrategy strategy) {
        this.fileFacade = fileFacade;
        this.strategy = strategy;
    }

    @Override
    public void execute() {
        List<Expense> expenses = fileFacade.loadExpenses();
        strategy.execute(expenses);
    }

    @Override
    public String getDescription() {
        return strategy.getName();
    }
}