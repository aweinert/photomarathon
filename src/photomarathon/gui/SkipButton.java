package photomarathon.gui;

import java.awt.Dimension;

import javax.swing.JButton;

import photomarathon.GodClass;

public class SkipButton extends JButton {
    SkipButton(GodClass godClass) {
        super("Thema überspringen");
        godClass.setSkipButton(this);
    }
}
