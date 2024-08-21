package task3209.listeners;

import task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class TextEditMenuListener implements MenuListener {

    private View view;

    public TextEditMenuListener(View view){

        this.view=view;
    }
    @Override
    public void menuSelected(MenuEvent menuEvent) {

        JMenu object = (JMenu) menuEvent.getSource();
        Component [] items = object.getMenuComponents();
        for(Component component:items){

            component.setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent) {

    }

    @Override
    public void menuCanceled(MenuEvent menuEvent) {

    }
}
