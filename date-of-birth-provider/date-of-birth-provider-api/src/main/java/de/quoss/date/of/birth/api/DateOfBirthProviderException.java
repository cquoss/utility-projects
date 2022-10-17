package de.quoss.date.of.birth.api;

public class DateOfBirthProviderException extends RuntimeException {

    public DateOfBirthProviderException(final String s) {
        super(s);
    }

    public DateOfBirthProviderException(final Throwable t) {
        super(t);
    }

    public DateOfBirthProviderException(final String s, final Throwable t) {
        super(s, t);
    }

}
