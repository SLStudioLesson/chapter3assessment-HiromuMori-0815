package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }

    // 設問4
    // recipe.csvからデータを読み込みそれを送る。
    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] recipe = line.split(",", 2);
            if (recipe.length == 2) {
                String recipeName = recipe[0];
                String[] ingredientNames = recipe[1].split(",");
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (String ingredient : ingredientNames) {
                    ingredients.add(new Ingredient(ingredient.trim()));
                }
                recipes.add(new Recipe(recipeName, ingredients));
            }
        }
        return recipes;
    }

    // 設問6
    // ユーザーの側から入力してしたものをここで受け取ってファイルに追加で書き込む。
    @Override
    public void writeData(Recipe recipe) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        String writing = recipe.getName() + ", ";
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            writing += ingredients.get(i).getName();
            if (i < ingredients.size() - 1) {
                writing += ", ";
            }
        }
        writer.write(writing);
        writer.newLine();
        writer.close();
        System.out.println("Recipe added successfully.");
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }
}
