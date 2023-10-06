package seedu.address.model.person;

/**
 * Represents a Person's remark in the address book.
 */
public class Remark {

    public final String remark;

    public Remark(String r) {
        remark = r;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj instanceof Remark) {
            Remark otherRemark = (Remark) otherObj;
            return remark.equals(otherRemark.remark);
        }
        return false;
    }

    @Override
    public String toString() {
        return remark;
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }
}
