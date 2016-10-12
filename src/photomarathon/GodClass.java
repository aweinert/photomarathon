package photomarathon;

import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.TransferHandler;

import photomarathon.gui.CategoryPicker;
import photomarathon.gui.ParticipantIdRow.ParticipantIdField;
import photomarathon.gui.PhotoDropTarget;
import photomarathon.gui.SkipButton;
import photomarathon.gui.StationPicker;

public class GodClass {
    final static String[][] topics = new String[][] {
            new String[] { "Thema 1 - Déjà vu", "Thema 2 - Erste Küsse", "Thema 3 - Trümmer der Vergangenheit",
                    "Thema 4 - Familienportrait" },
            new String[] { "Thema 5 - Herrscherportrait", "Thema 6 - Zeitkapsel", "Thema 7 - Generationen",
                    "Thema 8 - Ich in 20 Jahren" },
            new String[] { "Thema 9 - Nostalgie", "Thema 10 - Aus der Zeit gefallen", "Thema 11 - Reiseziel ...",
                    "Thema 12 - Schmetterlingseffekt" },
            new String[] { "Thema 13 - Träume und Alpträume", "Thema 14 - Ich bereue nichts",
                    "Thema 15 - Parallelwelt", "Thema 16 - Nebel der Erinnerung", "Thema 17 - Stillstand",
                    "Thema 18 - Jungbrunnen", "Thema 19 - Chaos", "Thema 20 - Schwarz-Weiß" },
            new String[] { "Thema 21 - Countdown", "Thema 22 - Fahndungsphoto", "Thema 23 - Früher war alles besser",
                    "Thema 24 - Alles wird gut" }, };

    private StationPicker stationPicker;
    private CategoryPicker topicPicker;

    private ParticipantIdField idField;
    private PhotoDropTarget photoDropTarget;

    private SkipButton skipButton;

    class StationListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int stationNumber = ((JComboBox<String>) event.getSource()).getSelectedIndex();
            assert stationNumber >= 0;
            assert stationNumber < 5;
            topicPicker.setModel(new DefaultComboBoxModel<String>(topics[stationNumber]));
        }
    }

    class ParticipantListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            topicPicker.setSelectedIndex(0);
        }
    }

    class SkipListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            topicPicker.setSelectedIndex((topicPicker.getSelectedIndex() + 1) % topicPicker.getModel().getSize());
        }
    }

    public void setStationPicker(StationPicker stationPicker) {
        this.stationPicker = stationPicker;
        stationPicker.addActionListener(new StationListener());
    }

    public void setTopicPicker(CategoryPicker topicPicker) {
        this.topicPicker = topicPicker;
        topicPicker.setModel(new DefaultComboBoxModel<String>(topics[0]));
    }

    public void setIdField(ParticipantIdField idField) {
        this.idField = idField;
        idField.addActionListener(new ParticipantListener());
    }

    public void setDropTarget(PhotoDropTarget photoDropTarget) {
        this.photoDropTarget = photoDropTarget;
        this.setupDropSupport();
    }

    private void setupDropSupport() {
        this.photoDropTarget.setTransferHandler(new TransferHandler() {
            public boolean canImport(TransferHandler.TransferSupport support) {
                support.setDropAction(COPY);
                return true;
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    try {
                        final List<File> fileList = (List<File>) support.getTransferable().getTransferData(
                                DataFlavor.javaFileListFlavor);
                        for (final File file : fileList) {
                            this.copyFile(file);
                            this.advanceTopic();
                        }
                    } catch (Throwable t) {
                        System.err.println(t);
                        return true;
                    }
                }
                return true;
            }

            private void advanceTopic() {
                final int currentTopicId = topicPicker.getTopicIdActual();
                if (currentTopicId < stationPicker.getMaxTopicId()) {
                    topicPicker.setTopicId(currentTopicId + 1);
                } else {
                    topicPicker.setTopicId(0);
                    idField.setText("");
                    idField.grabFocus();
                }
            }

            private void copyFile(File file) {
                try {
                    Files.copy(file.toPath(), this.getTargetPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private Path getTargetPath() {
                final int participantId = idField.getParticipantId();
                final int topicId = topicPicker.getTopicId(stationPicker.getStationId()) + 1;

                return FileSystems.getDefault().getPath("photos",
                        String.format("%02d", topicId) + "_" + String.format("%02d", participantId) + ".jpg");
            }
        });
    }

    public void setSkipButton(SkipButton skipButton) {
        this.skipButton = skipButton;
        skipButton.addActionListener(new SkipListener());
    }
}
