package de.quoss.postal.code.provider.api;

public class PostalCodeProviderException extends RuntimeException {

    public PostalCodeProviderException(final String s) {
        super(s);
    }

    public PostalCodeProviderException(final Throwable t) {
        super(t);
    }

    public PostalCodeProviderException(final String s, final Throwable t) {
        super(s, t);
    }

}
