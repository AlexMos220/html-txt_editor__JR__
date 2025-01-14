package task3209.actions;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

public class SuperscriptAction extends StyledEditorKit.StyledTextAction {

    public SuperscriptAction() {
        super(StyleConstants.Superscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JEditorPane jEditorPane = getEditor(actionEvent);
        if(jEditorPane!=null){
            MutableAttributeSet mas = getStyledEditorKit(jEditorPane).getInputAttributes();
            SimpleAttributeSet sas = new SimpleAttributeSet();
            StyleConstants.setSuperscript(sas, !StyleConstants.isSuperscript(mas));
            setCharacterAttributes(jEditorPane,sas,false);
        }

    }


}
