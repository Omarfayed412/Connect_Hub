/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentCreation;

/**
 *
 * @author Omar Fayed
 */
//Concrete creator class
public class PostFactory extends ContentFactory{

    @Override
    public AbstractContent getContent() {
        return new Post();
    }
}
