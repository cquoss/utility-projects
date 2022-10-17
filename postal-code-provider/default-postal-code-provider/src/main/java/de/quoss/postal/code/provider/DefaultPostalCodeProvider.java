package de.quoss.postal.code.provider;

import de.quoss.postal.code.provider.api.PostalCodeProvider;
import de.quoss.postal.code.provider.api.PostalCodeProviderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultPostalCodeProvider implements PostalCodeProvider {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPostalCodeProvider.class);
    
    private static final List<String> POSTAL_CODES = new ArrayList<>();

    static {
        try {
            POSTAL_CODES.addAll(readList("postal-codes.txt"));
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("Error reading postal-codes.txt from classpath.");
        }
    }
    
    @Override
    public String providePostalCode() {
        final String result = providePostalCodeWithCityName();
        if (result == null) {
            return null;
        }
        return result.split(" ")[0];
    }

    @Override
    public String providePostalCodeWithCityName() {
        if (POSTAL_CODES.isEmpty()) {
            return null;
        }
        return POSTAL_CODES.get(ThreadLocalRandom.current().nextInt(0, POSTAL_CODES.size()));
    }

    private static List<String> readList(final String name) throws IOException, URISyntaxException {
        final List<String> result = new ArrayList<>();
        try (final InputStream stream = DefaultPostalCodeProvider.class.getClassLoader().getResourceAsStream(name)) {
            if (stream == null) {
                throw new PostalCodeProviderException("Resource " + name + " not found in classpath.");
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
        final PostalCodeProvider provider = new DefaultPostalCodeProvider();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Postal Code with city name: {}", provider.providePostalCodeWithCityName());
            LOGGER.info("Postal Code: {}", provider.providePostalCode());
        }
    }
    
}
