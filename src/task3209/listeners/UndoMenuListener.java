package task3209.listeners;


import task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class UndoMenuListener implements MenuListener {

    private View view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;

    public UndoMenuListener(View view, JMenuItem undoItem, JMenuItem redoItem) {
        this.view=view;
        this.undoMenuItem=undoItem;
        this.redoMenuItem=redoItem;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent) {

       undoMenuItem.setEnabled(view.canUndo());
       redoMenuItem.setEnabled(view.canRedo());
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent) {

    }

    @Override
    public void menuCanceled(MenuEvent menuEvent) {

    }
}
