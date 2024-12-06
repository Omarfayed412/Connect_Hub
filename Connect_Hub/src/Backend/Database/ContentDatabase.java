/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Database;

import Backend.ContentCreation.IContent;
import Backend.User;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Omar Fayed
 */

public class ContentDatabase implements IContentDatabase{
    private static ContentDatabase database = null;
    private static ArrayList<IContent> contentList;
    private static final String contnetJson = "content.json";
    private static Gson gson = null;
    
    //Apllying the singlton design pattern 
    private ContentDatabase() {
        contentList = new ArrayList<IContent>();
    }
    
    public synchronized static Database getInstance() {
        if (database == null) {
            database = new ContentDatabase();
            initialLoad();
        }
        return database;
    }
     
    @Override
    public boolean addContent(IContent content) {
        try {
            contentList.add(content);
            System.out.println("Content added");
            this.save();
            return true;
        } catch (NullPointerException e) {
            System.out.println("Error adding content: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeContent(String contentID) {
        try {
        IContent content = getContent(contentID);
        contentList.remove(content);
        this.save();
        return true;
        } catch(NullPointerException e) {
            System.out.println("Error removing content: " + e.getMessage());
            return false;
        }
    }

    //Seriallizing content to json file
    @Override
    public void save() {
        try {
            FileWriter writer = new FileWriter(this.contnetJson);
            gson.toJson(this.contentList, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void load() {
        try {
            FileReader reader = new FileReader(contnetJson);
            // generic method to return the type of the object inside the List
            Type type = new TypeToken<List<User>>() {
            }.getType();
            contentList = gson.fromJson(reader, type);
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Empty json");
        }
    }
    
    //Loading data from json
    private static void initialLoad() {
        try {
            FileReader reader = new FileReader(contnetJson);
            // generic method to return the type of the object inside the List
            Type type = new TypeToken<List<User>>() {
            }.getType();
            contentList = gson.fromJson(reader, type);
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Empty json");
        }
    }

    public static ArrayList<IContent> getContentList() {
        return contentList;
    }
    
    // getContent() method return content if found
    // return null if content not found
    @Override
    public IContent getContent(String contentId)
    {
        for (IContent i : contentList)
            if(i.getContentId().equalsIgnoreCase(contentId)) return i;
        return null;
    }
    
    
}
