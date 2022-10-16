package de.quoss.name.provider.api;

public class NameProviderException extends RuntimeException {

    public NameProviderException(final String s) {
        super(s);
    }

    public NameProviderException(final Throwable t) {
        super(t);
    }

    public NameProviderException(final String s, final Throwable t) {
        super(s, t);
    }

}
