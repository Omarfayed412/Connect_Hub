/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Database;

import Backend.Content;
import java.util.ArrayList;

/**
 *
 * @author Omar Fayed
 */

public class ContentDatabase implements IContentDatabase{
    private static ContentDatabase database = null;
    private ArrayList<Content> contentList;
            
    private ContentDatabase() {
        contentList = new ArrayList<Content>();
    }
    
    @Override
    public boolean addContent() {
        return false;
    }

    @Override
    public boolean removeContent() {
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
