package xxl.app.search;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.Cell;

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

    DoShowValues(Spreadsheet receiver) {
        super(Label.SEARCH_VALUES, receiver);
        addStringField("search", Message.searchValue());
    }

    @Override
    protected final void execute() {
        String search = stringField("search");
        List<Cell> cells = _receiver.searchValue(search);
        if (!cells.isEmpty()) {
            for (Cell cell : cells) {
                _display.addLine(cell.printContent());
            }
        }
    }
}
