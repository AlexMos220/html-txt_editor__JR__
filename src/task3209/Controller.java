package task3209;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {

    private View view;
    private HTMLDocument document;
    private File currentFile;

    public HTMLDocument getDocument() {
        return document;
    }


    public Controller(View view) {
        this.view = view;
    }

    public void init() {

        createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    public void resetDocument() {
        try {
            document.removeUndoableEditListener(view.getUndoListener());

        } catch (NullPointerException e) {

        } finally {

            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            HTMLDocument newDocument = (HTMLDocument) htmlEditorKit.createDefaultDocument();
            newDocument.addUndoableEditListener(view.getUndoListener());
            document = newDocument;
            view.update();
        }


    }


    public void setPlainText(String text) {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {

            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {

        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {

            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void createNewDocument() {

        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        currentFile = null;
    }

    public void openDocument() {

        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());

        int usersChoose = chooser.showOpenDialog(view);

        if (usersChoose == JFileChooser.APPROVE_OPTION) {
            resetDocument();
            currentFile = chooser.getSelectedFile();
            view.setTitle(currentFile.getName());

            try (FileReader reader = new FileReader(currentFile)) {
                new HTMLEditorKit().read(reader,document,0);
                view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {

        view.selectHtmlTab();

        if (currentFile != null) {
            try (FileWriter writer = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        } else saveDocumentAs();

    }

    public void saveDocumentAs() {

        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());

        int usersChoose = chooser.showSaveDialog(view);

        if (usersChoose == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            view.setTitle(currentFile.getName());

            try (FileWriter writer = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public static void main(String[] args) {

        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);

        view.init();
        controller.init();

    }


}
