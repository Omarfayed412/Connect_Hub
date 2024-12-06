/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Database;

import Backend.Content;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Omar Fayed
 */

public class ContentDatabase implements IContentDatabase{
    private static ContentDatabase database = null;
    private ArrayList<Content> contentList;
    private static final String users_json = "content.json";
    private static Gson gson = null;
    
    private ContentDatabase() {
        contentList = new ArrayList<Content>();
    }
    
    @Override
    public boolean addContent(Content content) {
        try {
            contentList.add(content);
            System.out.println("Content added");
            return true;
        } catch (NullPointerException e) {
            System.out.println("Error adding content: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeContent(String contentID) {
        
        return false;
    }

    @Override
    public void save() {
        
    }

    @Override
    public void load() {
        
    }
    
    public static Database getInstance() {
        if (database == null) {
            database = new ContentDatabase();
        }
        return database;
    }
    
}
