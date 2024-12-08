/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Database;

import Backend.ContentCreation.*;

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
    private static ArrayList<AbstractContent> contentList = null;
    private static final String contnetJson = "content.json";
    private static Gson gson = null;
    private static int numberOfUsers;
    
    //Apllying the singlton design pattern 
    private ContentDatabase() {
        contentList = new ArrayList<AbstractContent>();
        System.out.println("inside constructor");
        gson = new Gson();
        numberOfJSONOBJECTS();
    }
    
    public synchronized static ContentDatabase getInstance() {
        if (database == null) {
            System.out.println("Content Database created");
            database = new ContentDatabase();
            /// Avoid null Exceptions
            if (numberOfUsers > 0)
                initialLoad();
        }
        return database;
    }
    
    public void numberOfJSONOBJECTS() {
        List<AbstractContent> content = null;
        try {
            FileReader reader = new FileReader(contnetJson);
            Type type = new TypeToken<List<AbstractContent>>() {
            }.getType();
            content = gson.fromJson(reader, type);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (content == null)
            numberOfUsers = 0;
        else
            numberOfUsers = content.size();
    }
    
    @Override
    public boolean addContent(AbstractContent content) {
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
        AbstractContent content = getContent(contentID);
        contentList.remove(content);
        this.save();
            System.out.println("Content Removed");
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
            System.out.println("Content Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void load() {
        try {
            FileReader reader = new FileReader(contnetJson);
            // generic method to return the type of the object inside the List
            Type type = new TypeToken<List<AbstractContent>>() {
            }.getType();
            ArrayList<AbstractContent>content = gson.fromJson(reader, type);
            if (content != null) 
                contentList = content;            
            reader.close();
            System.out.println("Content Loaded");
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
            Type type = new TypeToken<List<AbstractContent>>() {
            }.getType();
            ArrayList<AbstractContent>content = gson.fromJson(reader, type);
            if (content != null) 
                contentList = content;
            reader.close();
            System.out.println("Contnet Initial Load");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Empty json");
        }
    }

    public ArrayList<AbstractContent> getContentList() {
        return contentList;
    }
    
    // getContent() method return content if found
    // return null if content not found
    @Override
    public AbstractContent getContent(String contentId) {
        for (AbstractContent i : contentList)
            if(i.getContentId().equalsIgnoreCase(contentId)) return i;
        return null;
    }
}
