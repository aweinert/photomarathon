package photomarathon.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import photomarathon.GodClass;

public class ParticipantIdRow extends JPanel {
    public class ParticipantIdField extends JTextField {
        ParticipantIdField() {
            super(3);
        }

        public int getParticipantId() {
            return Integer.parseInt(this.getText());
        }
    }

    ParticipantIdRow(GodClass godClass) {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(0, 0, 0, 5);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 1.0;

        final StationPicker stationPicker = new StationPicker();
        layout.setConstraints(stationPicker, constraints);
        this.add(stationPicker);
        godClass.setStationPicker(stationPicker);

        constraints.insets = new Insets(0, 5, 0, 5);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 0.0;

        final JLabel label = new JLabel("Teilnehmernummer:");
        layout.setConstraints(label, constraints);
        this.add(label);

        constraints.insets = new Insets(0, 5, 0, 0);
        constraints.fill = GridBagConstraints.NONE;

        final ParticipantIdField idField = new ParticipantIdField();
        layout.setConstraints(idField, constraints);
        this.add(idField);
        godClass.setIdField(idField);

        this.setLayout(layout);
    }
}
