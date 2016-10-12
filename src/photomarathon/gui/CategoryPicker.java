package photomarathon.gui;

import javax.swing.JComboBox;

import photomarathon.GodClass;

public class CategoryPicker extends JComboBox<String> {
    CategoryPicker(GodClass godClass) {
        godClass.setTopicPicker(this);
    }

    /**
     * @param stationId
     *            The ID of the current station. Must be in range [0;4].
     * @return The id of the current topic. Is in range [0;23]
     */
    public int getTopicId(int stationId) {
        // We have to account for the midnight-station with 8 topics
        if (stationId <= 4) {
            return stationId * 4 + this.getSelectedIndex();
        } else {
            return 20 + this.getSelectedIndex();
        }
    }

    public int getTopicIdActual() {
        return this.getSelectedIndex();
    }

    public void setTopicId(int i) {
        this.setSelectedIndex(i);

    }
}
