package seedu.addressbook.commands;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.tag.UniqueTagList.DuplicateTagException;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Sort the addressbook in lexicographical order.\n\t" + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "List sorted";
    
    private AddressBook oldAddressBook;
    
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute() {
        oldAddressBook = new AddressBook(addressBook.getAllPersons(), addressBook.getAllTags());
        addressBook.sort();
        lastMutatingCommands.push(this);    
        return new CommandResult(MESSAGE_SUCCESS);
    }
    
    @Override
    public boolean undo() throws DuplicatePersonException, DuplicateTagException {
        addressBook.clear();
        addressBook.copyPersonsAndTags(oldAddressBook);
        return true;
    }
}
