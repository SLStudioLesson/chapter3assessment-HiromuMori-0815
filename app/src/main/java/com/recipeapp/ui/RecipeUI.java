package com.recipeapp.ui;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    // 設問5
    // CSVDataHandlerから送られたデータを整形してコンソールに表示する
    private void displayRecipes() {
        try {
            ArrayList<Recipe> recipes = dataHandler.readData();
            if (recipes.isEmpty()) {
                System.out.println("No recipes available.");
                return;
            }
            System.out.println("Recipes: ");
            for (Recipe menu : recipes) {
                String recipeName = menu.getName();
                ArrayList<Ingredient> ingredients = menu.getIngredients();
                String ingredientName = "";
                for (Ingredient ingredient : ingredients) {
                    ingredientName += ingredient.getName() + ", ";
                }

                if (!ingredientName.isEmpty()) {
                    ingredientName = ingredientName.substring(0, ingredientName.length() - 2);
                }
                System.out.println("-------------------------------------");
                System.out.println("Recipe Name: " + recipeName);
                System.out.println("Main Ingredients: " + ingredientName);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 設問6
    // レシピ名や材料をここで入力する
    private void addNewRecipe() {
        try {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();

            System.out.println("Enter ingredients (type 'done' when finished): ");
            while (true) {
                System.out.print("Ingredient: ");
                String ingredientName = reader.readLine();
                if (ingredientName.equals("done")) {
                    Recipe recipe = new Recipe(recipeName, ingredients);
                    dataHandler.writeData(recipe);
                    break;
                } else {
                    ingredients.add(new Ingredient(ingredientName));
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
