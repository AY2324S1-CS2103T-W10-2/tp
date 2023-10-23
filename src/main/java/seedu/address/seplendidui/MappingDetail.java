package seedu.address.seplendidui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.partnercourse.PartnerCourse;

/**
 * An UI component that displays information of a Mapping between a {@code LocalCourse} and {@code PartnerCourse}.
 */
public class MappingDetail extends UiPart<Region> {

    private static final String FXML = "mappingDetailCard.fxml";

    public final LocalCourse localCourse;
    public final PartnerCourse partnerCourse;

    @FXML
    private HBox mappingDetailPane;
    @FXML
    private Label id;
    @FXML
    private Label localName;
    @FXML
    private Label localCode;
    @FXML
    private Label partnerName;
    @FXML
    private Label partnerCode;
    @FXML
    private Label partnerUniversity;

    /**
     * Creates a {@code MappingDetail} with the given {@code LocalCourse} and {@code PartnerCourse}
     */
    public MappingDetail(LocalCourse localCourse, PartnerCourse partnerCourse) {
        super(FXML);
        this.localCourse = localCourse;
        this.partnerCourse = partnerCourse;
        localName.setText(localCourse.getLocalName().toString());
        localCode.setText(localCourse.getLocalCode().toString());
        partnerName.setText(partnerCourse.getPartnerName().toString());
        partnerCode.setText(partnerCourse.getPartnerCode().toString());
        partnerUniversity.setText(partnerCourse.getPartnerUniversity().getUniversityName().toString());
    }
}
