/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend.Notifications;

/**
 *
 * @author Omar Fayed
 */
public interface INotification {
    public void setID(String Id);
    public String getID();
    public String getSContent();
    public void setSContent(String content);
}
