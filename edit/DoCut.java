package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {

    DoCut(Spreadsheet receiver) {
        super(Label.CUT, receiver);
        addStringField("gama", Message.address());
    }

    @Override
    protected final void execute() throws CommandException {
        String gama = stringField("gama");
        String parts[] = gama.split(":");
        if (parts.length == 1) {
            int row = Integer.parseInt(parts[0].split(";")[0]);
            int column = Integer.parseInt(parts[0].split(";")[1]);
            if (row > _receiver.getRows() || column > _receiver.getColumns() || row < 0 || column < 0) {
                throw new InvalidCellRangeException(gama);
            }
            _receiver.copy(row, column);
            _receiver.clear(row, column);
        } else {
            String start = gama.split(":")[0];
            String end = gama.split(":")[1];
            int startRow = Integer.parseInt(start.split(";")[0]);
            int startColumn = Integer.parseInt(start.split(";")[1]);
            int endRow = Integer.parseInt(end.split(";")[0]);
            int endColumn = Integer.parseInt(end.split(";")[1]);
            boolean sameRow = startRow == endRow;
            boolean sameColumn = startColumn == endColumn;
            if (startRow < 0 || startColumn < 0 || endRow > _receiver.getRows()
                    || endColumn > _receiver.getColumns() || (!sameRow && !sameColumn)) {
                throw new InvalidCellRangeException(gama);
            }
            _receiver.copy(startRow, startColumn, endRow, endColumn);
            _receiver.clear(startRow, startColumn, endRow, endColumn);
        }
    }
}
