package seedu.addressbook.commands;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.tag.UniqueTagList.DuplicateTagException;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Clears address book permanently.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    
    private AddressBook oldAddressBook;

    public ClearCommand() {}

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute() {
        oldAddressBook = new AddressBook(addressBook.getAllPersons(), addressBook.getAllTags());
        addressBook.clear();
        lastMutatingCommands.push(this);
        return new CommandResult(MESSAGE_SUCCESS);
    }
    
    @Override
    public boolean undo() throws DuplicatePersonException, DuplicateTagException {
        addressBook.copyPersonsAndTags(oldAddressBook);
        return true;
    }
}
