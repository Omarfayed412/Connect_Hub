/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Notifications;

/**
 *
 * @author Omar Fayed
 */
public class Notification implements INotification{
    private String Id;
    private String content;

    public Notification() {
    }

    @Override
    public String getSContent() {
        return content;
    }

    @Override
    public void setSContent(String content) {
        this.content = content;
    }  
    
    @Override
    public void setID(String Id) {
        this.Id = Id;
    }

    @Override
    public String getID() {
        return this.Id;
    }
    
    
}
