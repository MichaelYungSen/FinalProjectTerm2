/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class applog 
        extends Application
{
  public void start(Stage stage)
    throws Exception
  {
    Parent root = (Parent)FXMLLoader.load(getClass().getResource("LoginMain.fxml"));
    
    Scene scene = new Scene(root);
    
    stage.setScene(scene);
    stage.setTitle("PT MICNIL MICIN");
    stage.show();
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }
    
}
