package seedu.addressbook.commands;

import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

public class UndoCommand extends Command {
    
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Undo the previous command "
            + "(only if it is add/delete).\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Previous command has been undone!";
    public static final String MESSAGE_FAILURE = "Previous command cannot been undone!";

    public UndoCommand() {}


    @Override
    public CommandResult execute() {
        try {
            if (lastCommand.undo()) {
                lastCommand = this;
                return new CommandResult(MESSAGE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_FAILURE);
            }
        } catch (NullPointerException | DuplicatePersonException | PersonNotFoundException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }       
    }
}
