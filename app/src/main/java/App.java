import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            // 1を選択した場合と2を選択した場合、それ以外を選択した場合に分けてインスタンスをし、その後にUIのdisplayMenuメソッドにわたるようにする。
            DataHandler dataHandler;
            switch (choice) {
                case "1":
                    dataHandler = new CSVDataHandler();
                    break;
                case "2":
                    dataHandler = new JSONDataHandler();
                    break;
                default:
                    dataHandler = new CSVDataHandler();
            }
            RecipeUI recipeUI = new RecipeUI(dataHandler);
            recipeUI.displayMenu();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}