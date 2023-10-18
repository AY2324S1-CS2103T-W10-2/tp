package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.UniquePartnerCourseList;

public class PartnerCourseCatalogue implements ReadOnlyPartnerCourseCatalogue {
    private final UniquePartnerCourseList partnerCourses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     * <p>
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        partnerCourses = new UniquePartnerCourseList();
    }

    public PartnerCourseCatalogue() {

    }

    /**
     * Creates an PartnerCourseCatalogue using the PartnerCourses in the {@code toBeCopied}
     */
    public PartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the PartnerCourse list with {@code partnerCourses}.
     * {@code partnerCourses} must not contain duplicate PartnerCourses.
     */
    public void setPartnerCourses(List<PartnerCourse> partnerCourses) {
        this.partnerCourses.setPartnerCourses(partnerCourses);
    }

    /**
     * Resets the existing data of this {@code PartnerCourseCatalogue} with {@code newData}.
     */
    public void resetData(ReadOnlyPartnerCourseCatalogue newData) {
        requireNonNull(newData);
        setPartnerCourses(newData.getPartnerCourseList());
    }

    /**
     * Returns true if a PartnerCourse with the same identity as {@code partnerCourseQuery} exists in the catalogue.
     */
    public boolean hasPartnerCourse(PartnerCourse partnerCourseQuery) {
        requireNonNull(partnerCourseQuery);
        return partnerCourses.contains(partnerCourseQuery);
    }

    /**
     * Adds a PartnerCourse to the PartnerCourseCatalogue.
     * The PartnerCourse must not already exist in the list.
     */
    public void addPartnerCourse(PartnerCourse pc) {
        partnerCourses.add(pc);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("partnercourses", partnerCourses)
                .toString();
    }

    @Override
    public ObservableList<PartnerCourse> getPartnerCourseList() {
        return partnerCourses.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PartnerCourseCatalogue)) {
            return false;
        }

        PartnerCourseCatalogue otherPartnerCourseCatalogue = (PartnerCourseCatalogue) other;
        return partnerCourses.equals(otherPartnerCourseCatalogue.partnerCourses);
    }

    @Override
    public int hashCode() {
        return partnerCourses.hashCode();
    }
}