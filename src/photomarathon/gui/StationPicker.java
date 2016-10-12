package photomarathon.gui;

import javax.swing.JComboBox;

public class StationPicker extends JComboBox<String> {
    StationPicker() {
        super(new String[] { "Station 1: 12:00 Uhr - 16:00 Uhr", "Station 2: 16:00 Uhr - 16:00 Uhr",
                "Station 3: 20:00 Uhr - 00:00 Uhr", "Station 4: 00:00 Uhr - 08:00 Uhr",
                "Station 5: 08:00 Uhr - 12:00 Uhr" });
    }

    /**
     * @return The ID of the station currently selected. Is in range [0;4].
     */
    public int getStationId() {
        return this.getSelectedIndex();
    }

    public int getMaxTopicId() {
        if (this.getSelectedIndex() != 3) {
            return 3;
        } else {
            return 7;
        }
    }
}
