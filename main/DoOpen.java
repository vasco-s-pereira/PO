package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.UnavailableFileException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

    DoOpen(Calculator receiver) {
        super(Label.OPEN, receiver);
        addStringField("filename", Message.openFile());
    }

    @Override
    protected final void execute() throws CommandException {
        if (_receiver.getSpreadsheet() != null && _receiver.getSpreadsheet().hasChanged()) {
            if (Form.confirm(Message.saveBeforeExit())) {
                (new DoSave(_receiver)).execute();
            }
        }
        String filename = stringField("filename");
        try {
            _receiver.load(filename);
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }
}
