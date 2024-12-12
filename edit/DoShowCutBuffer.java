package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;

import java.util.List;
import xxl.core.Cell;

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

    DoShowCutBuffer(Spreadsheet receiver) {
        super(Label.SHOW_CUT_BUFFER, receiver);
    }

    @Override
    protected final void execute() {
        List<Cell> cutBuffer = _receiver.getCutBuffer();
        if (!cutBuffer.isEmpty()) {
            for (Cell cell : cutBuffer) {
                _display.addLine(cell.printContent());
            }
        }
    }
}
