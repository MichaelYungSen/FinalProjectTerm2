/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applog;

import admin.adminctlr;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import karyawan.karyawanFXMLController;

public class mainlogctlr  implements Initializable
{
  modellog modelLog = new modellog();
  @FXML
  private Label dbstatus;
  @FXML
  private Button login;
  @FXML
  private TextField id;
  @FXML
  private PasswordField password;
  @FXML
  private ComboBox<option> combobox;
  
  public void initialize(URL url, ResourceBundle rb)
  {
    if (this.modelLog.isDatabaseConnected()) {
      this.dbstatus.setText("Connected");
    } else {
      this.dbstatus.setText("Not Connected");
    }
    this.combobox.setItems(FXCollections.observableArrayList(option.values()));
  }
  
  @FXML
  public void Login(ActionEvent event)
  {
    try
    {
      if (this.modelLog.isLogin(this.id.getText(), this.password.getText(), ((option)this.combobox.getValue()).toString()))
      {
        Stage stage = (Stage)this.login.getScene().getWindow();
        stage.close();
        switch (((option)this.combobox.getValue()).toString())
        {
        case "Admin": 
          adminLogin();
          break;
        case "Karyawan": 
          studentLogin();
        }
      }
      else
      {
        this.dbstatus.setText("Wrong Creditials");
      }
    }
    catch (Exception localException) {}
  }
  
  public void studentLogin()
  {
    try
    {
      Stage userStage = new Stage();
      FXMLLoader loader = new FXMLLoader();
      Pane root = (Pane)loader.load(getClass().getResource("/karyawan/karyawanFXML.fxml").openStream());
      karyawanFXMLController karyawanController = (karyawanFXMLController)loader.getController();
      
      Scene scene = new Scene(root);
      userStage.setScene(scene);
      userStage.setTitle("Karyawan Dashboard");
      userStage.setResizable(false);
      userStage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void adminLogin()
  {
    try
    {
      Stage adminStage = new Stage();
      FXMLLoader adminLoader = new FXMLLoader();
      Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
      adminctlr adminController = (adminctlr)adminLoader.getController();
      
      Scene adminscene = new Scene(adminroot);
      
      adminStage.setScene(adminscene);
      adminStage.setTitle("Admin Dashboard");
      adminStage.setResizable(true);
      adminStage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

