package photomarathon.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import photomarathon.GodClass;

public class MainFrame extends JFrame {
    public MainFrame() {
        final GodClass godClass = new GodClass();

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(5, 5, 5, 5);

        this.setLayout(layout);

        final ParticipantIdRow idRow = new ParticipantIdRow(godClass);
        layout.addLayoutComponent(idRow, constraints);
        this.add(idRow);

        constraints.gridy = 1;

        final CategoryPicker categoryPicker = new CategoryPicker(godClass);
        layout.addLayoutComponent(categoryPicker, constraints);
        this.add(categoryPicker);

        constraints.gridy = 2;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        final PhotoDropTarget dropTarget = new PhotoDropTarget(godClass);
        layout.addLayoutComponent(dropTarget, constraints);
        this.add(dropTarget);

        constraints.gridy = 3;
        constraints.weighty = 0.3;
        constraints.fill = GridBagConstraints.BOTH;

        final SkipButton skipButton = new SkipButton(godClass);
        layout.addLayoutComponent(skipButton, constraints);
        this.add(skipButton);

        this.setVisible(true);
    }
}
