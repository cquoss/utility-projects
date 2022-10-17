package de.quoss.date.of.birth.provider;

import de.quoss.date.of.birth.api.DateOfBirthProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultDateOfBirthProvider implements DateOfBirthProvider {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDateOfBirthProvider.class);
    
    public Date provideDateOfBirth()  {
        final int currentYear = LocalDate.now().getYear() - 1900;
        final int year = currentYear - ThreadLocalRandom.current().nextInt(18, 80);
        final int month = ThreadLocalRandom.current().nextInt(1, 13);
        int dayOfMonthUpperBound;
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12:
                dayOfMonthUpperBound = 32;
                break;
            case 2:
                if (year % 400 == 0) {
                    dayOfMonthUpperBound = 30;
                    break;
                }
                if (year % 100 == 0) {
                    dayOfMonthUpperBound = 29;
                    break;
                }
                if (year % 4 == 0) {
                    dayOfMonthUpperBound = 30;
                    break;
                }
                dayOfMonthUpperBound = 29;
                break;
            default:
                dayOfMonthUpperBound = 31;
        }
        final int dayOfMonth = ThreadLocalRandom.current().nextInt(1, dayOfMonthUpperBound);
        return new Date(year, month, dayOfMonth);
    }

    public static void main(final String[] args) {
        final DateOfBirthProvider provider = new DefaultDateOfBirthProvider();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("{}", provider.provideDateOfBirth());
        }
    }
    
}
