package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerName;
import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;


public class JsonAdaptedPartnerCourse {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "PartnerCourse's %s field is missing!";

    private final String universityName;
    private final String partnerCode;
    private final String partnerName;

    /**
     * Constructs a {@code JsonAdaptedPartnerCourse} with the given partnerCourse details.
     */
    @JsonCreator
    public JsonAdaptedPartnerCourse(@JsonProperty("university") String universityName,
                                    @JsonProperty("partnerCode") String partnerCode,
                                    @JsonProperty("partnerName") String partnerName) {
        this.universityName = universityName;
        this.partnerCode = partnerCode;
        this.partnerName = partnerName;
    }

    /**
     * Converts a given {@code PartnerCourse} into this class for Jackson use.
     */
    public JsonAdaptedPartnerCourse(PartnerCourse source) {
        universityName = source.getPartnerUniversity().getUniversityName().value;
        partnerCode = source.getPartnerCode().value;
        partnerName = source.getPartnerName().value;
    }

    /**
     * Converts this Jackson-friendly adapted partnerCourse object into the model's {@code PartnerCourse} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted partnerCourse.
     */
    public PartnerCourse toModelType() throws IllegalValueException {

        if (universityName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, UniversityName.class.getSimpleName())
            );
        }
        if (!UniversityName.isValidUniversityName(universityName)) {
            throw new IllegalValueException(UniversityName.MESSAGE_CONSTRAINTS);
        }
        final University modelUniversity = new University(new UniversityName(universityName));

        if (partnerCode == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, PartnerCode.class.getSimpleName()));
        }
        if (!PartnerCode.isValidPartnerCode(partnerCode)) {
            throw new IllegalValueException(PartnerCode.MESSAGE_CONSTRAINTS);
        }
        final PartnerCode modelPartnerCode = new PartnerCode(partnerCode);

        if (partnerName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PartnerName.class.getSimpleName()));
        }
        if (!PartnerName.isValidPartnerName(partnerName)) {
            throw new IllegalValueException(PartnerName.MESSAGE_CONSTRAINTS);
        }
        final PartnerName modelPartnerName = new PartnerName(partnerName);

        return new PartnerCourse(modelUniversity, modelPartnerCode, modelPartnerName);
    }
}