/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend.Database;

import Backend.Content;

/**
 *
 * @author Omar Fayed
 */
public interface IContentDatabase extends Database{
    public boolean addContent(Content content);
    public boolean removeContent(String contentID);
}
