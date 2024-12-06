/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentCreation;

/**
 *
 * @author Omar Fayed
 */

//Client class is the one responsible for creating the final insatnce by passing the required factory to it
//Make only one instance from this class to be able to create new class
public class ContentClient {
    private AbstractContent content;

    public ContentClient(ContentFactory factory) {
        this.content = factory.getContent();
    }

    
    public AbstractContent getContent() {
        return content;
    }
    
}
