package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

    DoNew(Calculator receiver) {
        super(Label.NEW, receiver);
        addIntegerField("rows", Message.lines());
        addIntegerField("columns", Message.columns());
    }

    @Override
    protected final void execute() throws CommandException {
        if (_receiver.getSpreadsheet() != null && _receiver.getSpreadsheet().hasChanged()) {
            if (Form.confirm(Message.saveBeforeExit())) {
                (new DoSave(_receiver)).execute();
            }
        }
        int rows = integerField("rows");
        int columns = integerField("columns");
        _receiver.createNewSpreadsheet(rows, columns);
        _receiver.setFilename(null);
    }
}
