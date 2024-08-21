package task3209;

import task3209.*;
import task3209.actions.*;
import task3209.listeners.TextEditMenuListener;
import task3209.listeners.UndoMenuListener;


import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuHelper {

    public static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        parent.add(menuItem);
        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, String text, Action action) {
        JMenuItem menuItem = addMenuItem(parent, action);
        menuItem.setText(text);
        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, Action action) {
        JMenuItem menuItem = new JMenuItem(action);
        parent.add(menuItem);
        return menuItem;
    }

    public static void initHelpMenu(View view, JMenuBar menuBar) {
        JMenu helpMenu = new JMenu("Допомога");
        menuBar.add(helpMenu);

        addMenuItem(helpMenu, "Про програму", view);
    }

    public static void initFontMenu(View view, JMenuBar menuBar) {
        JMenu fontMenu = new JMenu("Шрифт");
        menuBar.add(fontMenu);

        JMenu fontTypeMenu = new JMenu("Шрифт");
        fontMenu.add(fontTypeMenu);

        String[] fontTypes = {Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED, Font.DIALOG, Font.DIALOG_INPUT};
        for (String fontType : fontTypes) {
            addMenuItem(fontTypeMenu, fontType, new StyledEditorKit.FontFamilyAction(fontType, fontType));
        }

        JMenu fontSizeMenu = new JMenu("Розмір шрифту");
        fontMenu.add(fontSizeMenu);

        String[] fontSizes = {"6", "8", "10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
        for (String fontSize : fontSizes) {
            addMenuItem(fontSizeMenu, fontSize, new StyledEditorKit.FontSizeAction(fontSize, Integer.parseInt(fontSize)));
        }

        fontMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initColorMenu(View view, JMenuBar menuBar) {
        JMenu colorMenu = new JMenu("Колір");
        menuBar.add(colorMenu);

        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Червоний", Color.red));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Помаранчевий", Color.orange));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Жовтий", Color.yellow));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Зелений", Color.green));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Синій", Color.blue));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Блакитний", Color.cyan));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Пурпурний", Color.magenta));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Чорний", Color.black));

        colorMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initAlignMenu(View view, JMenuBar menuBar) {
        JMenu alignMenu = new JMenu("Вирівнювання");
        menuBar.add(alignMenu);

        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("Ліворуч", StyleConstants.ALIGN_LEFT));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По центру", StyleConstants.ALIGN_CENTER));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("Праворуч", StyleConstants.ALIGN_RIGHT));

        alignMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initStyleMenu(View view, JMenuBar menuBar) {
        JMenu styleMenu = new JMenu("Стиль");
        menuBar.add(styleMenu);

        addMenuItem(styleMenu, "Напівжирний", new StyledEditorKit.BoldAction());
        addMenuItem(styleMenu, "Підкреслений", new StyledEditorKit.UnderlineAction());
        addMenuItem(styleMenu, "Курсив", new StyledEditorKit.ItalicAction());

        styleMenu.addSeparator();

        addMenuItem(styleMenu, "Підрядковий знак", new SubscriptAction());
        addMenuItem(styleMenu, "Надрядковий знак", new SuperscriptAction());
        addMenuItem(styleMenu, "Закреслений", new StrikeThroughAction());

        styleMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initEditMenu(View view, JMenuBar menuBar) {
        JMenu editMenu = new JMenu("Редагувати");
        menuBar.add(editMenu);

        JMenuItem undoItem = addMenuItem(editMenu, "Скасувати", new UndoAction(view));
        JMenuItem redoItem = addMenuItem(editMenu, "Повернути", new RedoAction(view));
        addMenuItem(editMenu, "Вирізати", new DefaultEditorKit.CutAction());
        addMenuItem(editMenu, "Копіювати", new DefaultEditorKit.CopyAction());
        addMenuItem(editMenu, "Вставити", new DefaultEditorKit.PasteAction());

        editMenu.addMenuListener(new UndoMenuListener(view, undoItem, redoItem));
    }

    public static void initFileMenu(View view, JMenuBar menuBar) {
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        addMenuItem(fileMenu, "Новий", view);
        addMenuItem(fileMenu, "Відкрити", view);
        addMenuItem(fileMenu, "Зберегти", view);
        addMenuItem(fileMenu, "Зберегти як...", view);
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Вихід", view);
    }
}
