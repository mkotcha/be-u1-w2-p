package emmek;

import emmek.Library.Book;
import emmek.Library.Library;
import emmek.Library.Magazine;
import emmek.Library.Periodicity;
import net.datafaker.Faker;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Application {
    public static Library library = new Library();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        fakerize();
//        System.out.println(library.toString());
        menu();
        scanner.close();
    }

    public static void menu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println();
            System.out.println("FAKE LIBRARY SYSTEM 1.0");
            System.out.println();
            System.out.println("make your choice");
            System.out.println();
            System.out.println("1 - add an item to the library");
            System.out.println("2 - remove an item to the library");
            System.out.println("3 - find item by isbn");
            System.out.println("4 - find item by year");
            System.out.println("5 - find item by author");
            System.out.println("6 - save on disk");
            System.out.println("7 - load from disk");
            System.out.println("0 - exit");
            System.out.println();

            try {
                choice = abs(Integer.parseInt(scanner.nextLine()));
            } catch (NumberFormatException ex) {
                System.err.println("not a number");
            }

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("4");
                    break;
                case 5:
                    System.out.println("5");
                    break;
                case 6:
                    System.out.println("6");
                    break;
                case 7:
                    System.out.println("7");
                    break;
                case 0:
                    System.out.println("0");
                    break;
            }
            ;
        }
        ;
    }

    private static void addItem() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("1 - add a book");
            System.out.println("2 - add a magazine");
            System.out.println("0 - back");
            try {
                choice = abs(Integer.parseInt(scanner.nextLine()));
            } catch (NumberFormatException ex) {
                System.err.println("not a number");
            }
            switch (choice) {
                case 1:
                    System.out.println("ISBN");
                    String isbn = scanner.nextLine();
                    System.out.println("title");
                    String title = scanner.nextLine();
                    System.out.println("year");
                    String year = scanner.nextLine();
                    int pages = 0;
                    System.out.println("pages");
                    try {
                        pages = abs(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException ex) {
                        System.err.println("not a number");
                        break;
                    }
                    System.out.println("author");
                    String author = scanner.nextLine();
                    System.out.println("genre");
                    String genre = scanner.nextLine();
                    library.add(new Book(isbn, title, year, pages, author, genre));
                    System.out.println(library.toString());
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 0:
                    System.out.println("ciao");
                    break;
            }
        }
    }

    public static void fakerize() {
        Faker faker = new Faker();
        Random rnd = new Random();

        for (int i = 0; i < 3; i++) {
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

    }

}


