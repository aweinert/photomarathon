package photomarathon.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import photomarathon.GodClass;

public class PhotoDropTarget extends JLabel {
    PhotoDropTarget(GodClass godClass) {
        super("Photo hier ablegen");
        this.setHorizontalAlignment(CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        godClass.setDropTarget(this);
    }
}
