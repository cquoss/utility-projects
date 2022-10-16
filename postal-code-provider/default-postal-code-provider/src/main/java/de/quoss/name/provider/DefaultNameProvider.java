package de.quoss.name.provider;

import de.quoss.name.provider.api.NameProvider;
import de.quoss.name.provider.api.NameProviderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultNameProvider implements NameProvider {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultNameProvider.class);
    
    private static final List<String> SURNAMES = new ArrayList<>();

    private static final List<String> GIVEN_NAMES = new ArrayList<>();

    static {
        try {
            SURNAMES.addAll(readList("surnames.txt"));
            GIVEN_NAMES.addAll(readList("given-names.txt"));
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("Error reading surnames.txt and/or given-names.txt from classpath.");
        }
    }
    
    public String provideSurname()  {
        if (SURNAMES.isEmpty()) {
            return null;
        }
        return SURNAMES.get(ThreadLocalRandom.current().nextInt(0, SURNAMES.size()));
    }

    public String provideGivenName()  {
        if (GIVEN_NAMES.isEmpty()) {
            return null;
        }
        return GIVEN_NAMES.get(ThreadLocalRandom.current().nextInt(0, GIVEN_NAMES.size()));
    }

    private static List<String> readList(final String name) throws IOException, URISyntaxException {
        final List<String> result = new ArrayList<>();
        try (final InputStream stream = DefaultNameProvider.class.getClassLoader().getResourceAsStream(name)) {
            if (stream == null) {
                throw new NameProviderException("Resource " + name + " not found in classpath.");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = bufferedReader.readLine())!= null) {
                result.add(line);
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        final NameProvider nameProvider = new DefaultNameProvider();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("{} {}", nameProvider.provideGivenName(), nameProvider.provideSurname());
        }
    }
    
}
