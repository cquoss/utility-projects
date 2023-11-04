package de.quoss.create.insert.statements;

import de.quoss.date.of.birth.api.DateOfBirthProvider;
import de.quoss.date.of.birth.provider.DefaultDateOfBirthProvider;
import de.quoss.name.provider.DefaultNameProvider;
import de.quoss.name.provider.api.NameProvider;
import de.quoss.postal.code.provider.DefaultPostalCodeProvider;
import de.quoss.postal.code.provider.api.PostalCodeProvider;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private void run() throws IOException {
        final List<Row> rows = new ArrayList<>();
        final NameProvider nameProvider = new DefaultNameProvider();
        final DateOfBirthProvider dateOfBirthProvider = new DefaultDateOfBirthProvider();
        final PostalCodeProvider postalCodeProvider = new DefaultPostalCodeProvider();
        // create list of rows
        int insertCount = 0;
        for (int i = 0; i < 200000; i++) {
            rows.add(new Row(nameProvider.provideSurname(), nameProvider.provideGivenName(), 
                    dateOfBirthProvider.provideDateOfBirth(), postalCodeProvider.providePostalCode()));
            if (rows.size() % 20000 == 0) {
                // write file of insert statements from list of rows
                try (final FileWriter writer = new FileWriter(String.format("insert-%s.sql", insertCount++))) {
                    for (final Row row : rows) {
                        writer.write(String.format("insert into customer values (%n"));
                        writer.write(String.format("    default,%n"));
                        writer.write(String.format("    '%s',%n", row.getSurname()));
                        writer.write(String.format("    '%s',%n", row.getGivenName()));
                        writer.write(String.format("    '%s-%s-%s',%n", 1900 + row.getDateOfBirth().getYear(),
                                1 + row.getDateOfBirth().getMonth(), row.getDateOfBirth().getDate()));
                        writer.write(String.format("    '%s'%n", row.getPostalCode()));
                        writer.write(String.format(");%n"));
                    }
                }
                rows.clear();
            }
        }
        
    }
    
    public static void main(final String[] args) throws IOException {
        new Main().run();
    }
    
}
