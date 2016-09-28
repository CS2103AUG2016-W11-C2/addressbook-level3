package seedu.addressbook.commands;

import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.UniqueTagList.DuplicateTagException;

public class UndoCommand extends Command {
    
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Undo the previous command "
            + "(only if it is add/delete).\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Previous command '%1$s' has been undone!";
    public static final String MESSAGE_FAILURE = "Nothing to undo!";

    public UndoCommand() {}


    @Override
    public CommandResult execute() {
        try {
            if (lastMutatingCommands.empty()) {
                return new CommandResult(MESSAGE_FAILURE);
            }
            Command lastCommand = lastMutatingCommands.pop();
            if (lastCommand.undo()) {
                return new CommandResult(String.format(MESSAGE_SUCCESS, lastCommand.getCommandWord()));
            } else {
                return new CommandResult(MESSAGE_FAILURE);
            }
        } catch (NullPointerException | DuplicatePersonException | PersonNotFoundException | DuplicateTagException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }       
    }
}
