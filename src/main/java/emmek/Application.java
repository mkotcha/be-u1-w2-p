package emmek;

import emmek.Library.Book;
import emmek.Library.Library;
import emmek.Library.Magazine;
import emmek.Library.Periodicity;
import net.datafaker.Faker;

import java.util.Random;

public class Application {


    public static void main(String[] args) {
        Library library = new Library();
        Faker faker = new Faker();
        Random rnd = new Random();

        for (int i = 0; i < 100; i++) {
            Periodicity[] periodicity = Periodicity.values();
            if (rnd.nextBoolean()) {
                library.add(new Book(faker.code().isbn10(),
                        faker.book().title(),
                        faker.date().birthday("yyyy"),
                        faker.number().numberBetween(35, 1000),
                        faker.book().author(),
                        faker.book().genre()
                ));
            } else {
                library.add(new Magazine(faker.code().isbn10(),
                        faker.yoda().quote(),
                        faker.date().birthday("yyyy"),
                        faker.number().numberBetween(16, 100),
                        periodicity[rnd.nextInt(periodicity.length)]
                ));
            }
        }
        ;
        System.out.println(library.toString());

    }


}
