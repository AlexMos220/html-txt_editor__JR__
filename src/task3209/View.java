package task3209;

import task3209.listeners.FrameListener;
import task3209.listeners.TabbedPaneChangeListener;
import task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    public View() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    private Controller controller;

    private JTabbedPane tabbedPane = new JTabbedPane();        //  це буде панель із двома вкладками.
    private JTextPane htmlTextPane = new JTextPane();        //це буде компонент для візуального редагування html.
    private JEditorPane plainTextPane = new JEditorPane();    //це буде компонент для редагування html у вигляді тексту
    private UndoManager undoManager = new UndoManager();       // компонент для керування undo/redo
    private UndoListener undoListener = new UndoListener(undoManager);


    public UndoListener getUndoListener() {
        return undoListener;
    }


    ///////////////////////////////////////////////////////////////////////////////////

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public void init() {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command){

            case "Новий":
                controller.createNewDocument();
                break;

            case "Відкрити":
                controller.openDocument();
                break;

            case "Зберегти":
                controller.saveDocument();
                break;

            case "Зберегти як...":
                controller.saveDocumentAs();
                break;

            case "Вихід":
                controller.exit();
                break;

            case "Про програму":
                showAbout();
                break;

        }
    }

    public void initMenuBar() {

        JMenuBar menuBar = new JMenuBar();


        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);


        getContentPane().add(menuBar, BorderLayout.NORTH);


    }

    public void initEditor() {

        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", jScrollPane);
        tabbedPane.setPreferredSize(new Dimension(1280, 960));

        JScrollPane jScrollPane1 = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст", jScrollPane1);

        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {

        int indexOfTab = tabbedPane.getSelectedIndex();

        if (indexOfTab==0) {
            controller.setPlainText(plainTextPane.getText());
        } else {
           plainTextPane.setText(controller.getPlainText());
        }

        resetUndo();

    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo() {
        try {
            undoManager.discardAllEdits();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public boolean isHtmlTabSelected() {

        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab() {

        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {

        JOptionPane.showMessageDialog(tabbedPane, "Программа розроблена JavaRush & AlexMos", "Information", JOptionPane.INFORMATION_MESSAGE);

    }


}


