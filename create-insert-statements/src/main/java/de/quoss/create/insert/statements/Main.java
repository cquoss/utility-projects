package de.quoss.create.insert.statements;

import de.quoss.date.of.birth.api.DateOfBirthProvider;
import de.quoss.date.of.birth.provider.DefaultDateOfBirthProvider;
import de.quoss.name.provider.DefaultNameProvider;
import de.quoss.name.provider.api.NameProvider;
import de.quoss.postal.code.provider.DefaultPostalCodeProvider;
import de.quoss.postal.code.provider.api.PostalCodeProvider;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private void run() {
        final List<Row> rows = new ArrayList<>();
        final NameProvider nameProvider = new DefaultNameProvider();
        final DateOfBirthProvider dateOfBirthProvider = new DefaultDateOfBirthProvider();
        final PostalCodeProvider postalCodeProvider = new DefaultPostalCodeProvider();
        // create list of rows
        for (int i = 0; i < 200; i++) {
            rows.add(new Row(nameProvider.provideSurname(), nameProvider.provideGivenName(), 
                    dateOfBirthProvider.provideDateOfBirth(), postalCodeProvider.providePostalCode()));
        }
        // write file of insert statements from list of rows
        
    }
    
    public static void main(final String[] args) {
        new Main().run();
    }
    
}
