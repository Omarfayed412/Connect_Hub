/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend;

import java.time.LocalDate;

/**
 *
 * @author Omar Fayed
 */
public interface Content {
    public int getDuration();
    public LocalDate getTimeStamp();
    public String getContentId();
    public String getAuthorId();
    
            
}