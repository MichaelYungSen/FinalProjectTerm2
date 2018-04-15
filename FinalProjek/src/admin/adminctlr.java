/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import dbutil.dbutilctlr;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class adminctlr 
    implements Initializable
{
  @FXML
  private TextField id;
  @FXML
  private TextField firstname;
  @FXML
  private TextField lastname;
  @FXML
  private TextField email;
  @FXML
  private DatePicker birth;
  @FXML
  private TableView<datakaryawan> karyawantable;
  @FXML
  private TableColumn<datakaryawan, String> idcolumn;
  @FXML
  private TableColumn<datakaryawan, String> firstnamecolumn;
  @FXML
  private TableColumn<datakaryawan, String> lastnamecolumn;
  @FXML
  private TableColumn<datakaryawan, String> emailcolumn;
  @FXML
  private TableColumn<datakaryawan, String> dobcolumn;
  @FXML
  private Button loadbutton;
  private ObservableList<datakaryawan> data;
  private dbutilctlr dc;
  
  public void initialize(URL url, ResourceBundle rb)
  {
    this.dc = new dbutilctlr();
  }
  
  @FXML
  private void loadStudentData(ActionEvent event)
  {
    try
    {
      Connection conn = dbutilctlr.getConnection();
      this.data = FXCollections.observableArrayList();
      
      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
      while (rs.next()) {
        this.data.add(new datakaryawan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
      }
    }
    catch (SQLException e)
    {
      System.err.println("Error " + e);
    }
    this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
    this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
    this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
    this.emailcolumn.setCellValueFactory(new PropertyValueFactory("email"));
    this.dobcolumn.setCellValueFactory(new PropertyValueFactory("birth"));
    
    this.karyawantable.setItems(null);
    this.karyawantable.setItems(this.data);
  }
  
  @FXML
  private void addStudent(ActionEvent event)
  {
    String sql = "INSERT INTO `students`(`id`, `fname`, `lname`, `email`, `DOB`) VALUES (? , ?, ?, ?, ?)";
    try
    {
      Connection conn = dbutilctlr.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, this.id.getText());
      stmt.setString(2, this.firstname.getText());
      stmt.setString(3, this.lastname.getText());
      stmt.setString(4, this.email.getText());
      stmt.setString(5, this.birth.getEditor().getText());
      
      stmt.execute();
      conn.close();
    }
    catch (SQLException e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
  }
  
  @FXML
  private void clearFields(ActionEvent event)
  {
    this.id.setText("");
    this.firstname.setText("");
    this.lastname.setText("");
    this.email.setText("");
    this.birth.setValue(null);
  }
}

