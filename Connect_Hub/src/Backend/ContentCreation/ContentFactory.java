/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentCreation;

/**
 *
 * @author Omar Fayed
 */

//Applying the factory builder method with loose coupling
//Creator abstract class
public abstract class ContentFactory {
    public abstract AbstractContent getContent();
}
