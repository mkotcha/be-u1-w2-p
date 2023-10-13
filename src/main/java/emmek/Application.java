package emmek;

import emmek.Library.*;
import net.datafaker.Faker;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
        String query;
        List<LibraryItem> result;
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
            System.out.println("8 - print all library catalogue");
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
                    remItem();
                    break;
                case 3:
                    System.out.println("search by ISBN");
                    query = scanner.nextLine();
                    result = library.findIsbn(query);
                    System.out.println("result:");
                    result.forEach(item -> System.out.print(item.toString()));
                    System.out.println();
                    System.out.println("press enter to continue");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("search by year");
                    query = scanner.nextLine();
                    result = library.findYear(query);
                    System.out.println("result:");
                    result.forEach(item -> System.out.print(item.toString()));
                    System.out.println();
                    System.out.println("press enter to continue");
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("search by author");
                    query = scanner.nextLine().toLowerCase();
                    result = library.findAuthor(query);
                    System.out.println("result:");
                    result.forEach(item -> System.out.print(item.toString()));
                    System.out.println();
                    System.out.println("press enter to continue");
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println("6");
                    break;
                case 7:
                    System.out.println("7");
                    break;
                case 8:
                    System.out.println();
                    System.out.println(library.toString());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("0");
                    break;
            }
            ;
        }
        ;
    }

    private static void remItem() {
        int choice = -1;
        while (choice != 0) {
            System.out.println();
            System.out.println("chose an item to remove from catalogue - 0 to exit");
            library.printIndex();
            try {
                choice = abs(Integer.parseInt(scanner.nextLine()));
                if (choice > 0) library.rem(choice - 1);
            } catch (NumberFormatException ex) {
                System.err.println("not a number");
            }
        }
    }

    private static void addItem() {
        String isbn;
        String title;
        String year;
        int pages = 0;
        Periodicity[] periodicityArr = Periodicity.values();
        Periodicity periodicity = null;
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
                    isbn = scanner.nextLine();
                    System.out.println("title");
                    title = scanner.nextLine();
                    System.out.println("year");
                    year = scanner.nextLine();
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
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        System.err.println(ex.getMessage());
                    }
                    choice = 0;
                    break;
                case 2:
                    System.out.println("ISBN");
                    isbn = scanner.nextLine();
                    System.out.println("title");
                    title = scanner.nextLine();
                    System.out.println("year");
                    year = scanner.nextLine();
                    System.out.println("pages");
                    try {
                        pages = abs(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException ex) {
                        System.err.println("not a number");
                        break;
                    }
                    int periodicityChoice = -1;
                    while (periodicityChoice != 0) {
                        System.out.println("periodicity");
                        for (int i = 0; i < periodicityArr.length; i++) {
                            System.out.println((i + 1) + " - " + periodicityArr[i]);
                        }
                        try {
                            periodicityChoice = abs(Integer.parseInt(scanner.nextLine()));
                        } catch (NumberFormatException ex) {
                            System.err.println("not a number");
                        }
                        periodicity = periodicityArr[periodicityChoice - 1];
                        periodicityChoice = 0;
                    }
                    library.add(new Magazine(isbn, title, year, pages, periodicity));
                    System.out.println(library.toString());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        System.err.println(ex.getMessage());
                    }
                    choice = 0;
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


