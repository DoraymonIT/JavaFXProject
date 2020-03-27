/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyty.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tyty.bean.Employee;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class EmployyeGestionController implements Initializable {

    @FXML
    private JFXTextField fonction;

    @FXML
    private JFXTextField nomPrenom;

    @FXML
    private JFXTextField id;

    @FXML
    private TableView<Employee> tableEmployes;

    @FXML
    private TableColumn<Employee, String> idTable;

    @FXML
    private TableColumn<Employee, String> nomPrenomTable;

    @FXML
    private TableColumn<Employee, String> fonctionTable;

    ObservableList<Employee> employes = FXCollections.observableArrayList();

    @FXML
    void enregitrer(ActionEvent e) {
//        boolean edit = false;
        String c1 = id.getText();
        String c2 = nomPrenom.getText();
        String c3 = fonction.getText();
       
        if (c1 == null || c2 == null || c3 == null) {
            Alert l = new Alert(Alert.AlertType.WARNING);
            l.setContentText("Veuillez Entrez d abord tous les informations !!");
            l.setTitle("Ouuups !!!");
            l.showAndWait();
        
        } else {
            employes.add(new Employee(c1, c2, c3));
            tableEmployes.getItems().clear();
            tableEmployes.getItems().setAll(employes());
            id.setText("");
            nomPrenom.setText("");
            fonction.setText("");

        }

    }
    Employee employeeSelected = null;

    @FXML
    void modifier(ActionEvent event) {
        if (employeeSelected != null) {
            id.setText(employeeSelected.getiD());
        nomPrenom.setText(employeeSelected.getNomComplet());
        fonction.setText(employeeSelected.getFonction());
        }
//            enregitrer(event);

    }

    @FXML
    void delete(ActionEvent event) {
        if (employeeSelected != null) {
            employes.remove(employeeSelected);
            tableEmployes.getItems().remove(employeeSelected);
        } else {
            Alert l = new Alert(Alert.AlertType.WARNING);
            l.setContentText("Veuillez Selectionnez une ligne / un employee pour le supprimer!!");
            l.setTitle("Ouuups !!!");
            l.showAndWait();
        }
    }

    @FXML
    void deconnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AdminSideController.class.getResource("../view/AdminSide.fxml"));
            Parent root;
            root = (Parent) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(new Scene(root));
            Node node = (Node) event.getSource();
            Stage stage1 = (Stage) node.getScene().getWindow();
            stage1.hide();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminSideController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        idTable.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nomPrenomTable.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        fonctionTable.setCellValueFactory(new PropertyValueFactory<>("fonction"));

        tableEmployes.getItems().setAll(employes());

        tableEmployes.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                // Saving the item selected by user for future action
                employeeSelected = tableEmployes.getSelectionModel().getSelectedItem();

            }
        });
    }

    private ObservableList<Employee> employes() {
//employes.add(new Employee("12-24", "Mohmmad", "moulL-7anouta"));
        return employes;
    }

//    int generateEmployeeID() {
//        int a = 1;
//   
//        for (Employee o : employes) {
//            if (Integer.parseInt(o.getiD())  >= a) {
//                a = Integer.parseInt(o.getiD())  + 1;
//            }
//        }
//
//        return a;
//    }
}
