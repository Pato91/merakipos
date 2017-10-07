/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Icons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Meraki
 */
public class standardElements {
  
    public ImageView image(String name,Double width,Double height){
        
        ImageView vv=new ImageView();
        Image imageForward = new Image(getClass().getResourceAsStream(name));
        vv.setImage(imageForward);
        vv.setFitHeight(height);
        vv.setFitWidth(width);
        
        return vv;
    }
    public Image image2(String name){
        
        Image imageForward = new Image(getClass().getResourceAsStream(name));
        
        
        return imageForward;
    }
    
}
