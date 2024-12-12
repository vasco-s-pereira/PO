package xxl.app.search;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Cell;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

    DoShowFunctions(Spreadsheet receiver) {
        super(Label.SEARCH_FUNCTIONS, receiver);
        addStringField("search", Message.searchFunction());
    }

    @Override
    protected final void execute() {
        String search = stringField("search");
        List<Cell> cells = _receiver.searchFunction(search);
        if (!cells.isEmpty()) {
            for (Cell cell : cells) {
                _display.addLine(cell.printContent());
            }
        }
    }
}
