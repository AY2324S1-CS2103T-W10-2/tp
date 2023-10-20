package seedu.address.testutil;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.PartnerName;

/**
 * A utility class to help with building LocalCourse objects.
 */
public class LocalCourseBuilder {

    public static final String DEFAULT_LOCAL_CODE = "CS2103T";
    public static final String DEFAULT_LOCAL_NAME = "Software Engineering";

    private LocalCode localCode;
    private PartnerName localName;

    /**
     * Creates a {@code LocalCourseBuilder} with the default details.
     */
    public LocalCourseBuilder() {
        localCode = new LocalCode(DEFAULT_LOCAL_CODE);
        localName = new PartnerName(DEFAULT_LOCAL_NAME);
    }

    /**
     * Initializes the LocalCourseBuilder with the data of {@code localCourseToCopy}.
     */
    public LocalCourseBuilder(LocalCourse localCourseToCopy) {
        localCode = localCourseToCopy.getLocalCode();
        localName = localCourseToCopy.getLocalName();
    }

    /**
     * Sets the {@code LocalCode} of the {@code LocalCourse} that we are building.
     */
    public LocalCourseBuilder withLocalCode(String localCode) {
        this.localCode = new LocalCode(localCode);
        return this;
    }

    /**
     * Sets the {@code LocalName} of the {@code LocalCourse} that we are building.
     */
    public LocalCourseBuilder withLocalName(String localName) {
        this.localName = new PartnerName(localName);
        return this;
    }

    public LocalCourse build() {
        return new LocalCourse(localCode, localName);
    }

}
