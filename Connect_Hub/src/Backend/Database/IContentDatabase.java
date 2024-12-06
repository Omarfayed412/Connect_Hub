/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend.Database;

import Backend.ContentCreation.IContent;

/**
 *
 * @author Omar Fayed
 */
public interface IContentDatabase extends Database{
    public boolean addContent(IContent content);
    public boolean removeContent(String contentID);
    public IContent getContent(String contentId);
}
