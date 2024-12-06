/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend.Database;

import Backend.ContentCreation.AbstractContent;
import Backend.ContentCreation.IContent;

/**
 *
 * @author Omar Fayed
 */
public interface IContentDatabase extends Database{
    public boolean addContent(AbstractContent content);
    public boolean removeContent(String contentID);
    public AbstractContent getContent(String contentId);
}
