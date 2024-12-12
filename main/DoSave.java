package xxl.app.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

    DoSave(Calculator receiver) {
        super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
        addStringField("filename", Message.newSaveAs());
    }

    @Override
    protected final void execute() {
        try {
            _receiver.save();
        } catch (MissingFileAssociationException mfae) {
            String filename = stringField("filename");
            try {
                _receiver.saveAs(filename);
            } catch (FileNotFoundException fne) {
                _display.popup(Message.fileNotFound(filename));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (MissingFileAssociationException mfae2) {
                mfae2.printStackTrace();
            }
        } catch (FileNotFoundException fne) {
            _display.popup(Message.fileNotFound());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
