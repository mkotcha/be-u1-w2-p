package emmek.Library;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<LibraryItem> library;

    public Library(List<LibraryItem> library) {
        this.library = new ArrayList<>();
    }

    public void save() {
        File file = new File("src/library.json");\

        Gson gson = new Gson();
        String jsonStr = gson.toJson(this.library);

        try {
            FileUtils.writeStringToFile(file, jsonStr + System.lineSeparator(), StandardCharsets.UTF_8, false);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void load() {
        File file = new File("src/library.json");
        String jsonStr = "";
        try {
            jsonStr = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        Type listOfMyClassObject = new TypeToken<ArrayList<LibraryItem>>() {}.getType();

        Gson gson = new Gson();
        this.library = gson.fromJson(jsonStr, listOfMyClassObject);

    }

    public void add(LibraryItem item) {
        this.library.add(item);
    }

    public void rem(LibraryItem item) {
        this.library = this.library.stream().filter(elm -> !(elm.getIsbn().equals(item.getIsbn()))).toList();
    }

    public List<LibraryItem> findIsbn(String filter) {
        return this.library.stream().filter(elm -> (elm.getIsbn().equals(filter))).toList();
    }

    public List<LibraryItem> findYear(String filter) {
        return this.library.stream().filter(elm -> (elm.getYear().equals(filter))).toList();
    }

    public List<LibraryItem> findAuthor(String filter) {
        return this.library.stream().filter(elm -> (elm instanceof Book)).filter(book -> ((Book) book).getAuthor().equals(filter)).toList();
    }

}
