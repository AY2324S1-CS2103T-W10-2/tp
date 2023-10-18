package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedLocalCourse.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.CS2030S;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalName;

public class JsonAdaptedLocalCourseTest {

    @Test
    public void toModelType_validLocalCourseDetails_returnsLocalCourse() throws Exception {
        JsonAdaptedLocalCourse localCourse = new JsonAdaptedLocalCourse(CS2030S);
        assertEquals(CS2030S, localCourse.toModelType());
    }

    @Test
    public void toModelType_invalidLocalCode_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse =
                new JsonAdaptedLocalCourse(INVALID_LOCAL_COURSE_CODE, TYPICAL_LOCAL_COURSE_NAME);
        String expectedMessage = LocalCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }

    @Test
    public void toModelType_nullLocalCode_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse = new JsonAdaptedLocalCourse(null, TYPICAL_LOCAL_COURSE_NAME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }

    @Test
    public void toModelType_invalidLocalName_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse =
                new JsonAdaptedLocalCourse(TYPICAL_LOCAL_COURSE_CODE, INVALID_LOCAL_COURSE_NAME);
        String expectedMessage = LocalName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }

    @Test
    public void toModelType_nullLocalName_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse = new JsonAdaptedLocalCourse(TYPICAL_LOCAL_COURSE_CODE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }

}