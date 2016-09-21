package seedu.addressbook.commands;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Sort the addressbook in lexicographical order.\n\t" + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "List sorted";

    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
