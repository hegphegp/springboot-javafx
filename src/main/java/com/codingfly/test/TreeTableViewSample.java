package com.codingfly.test;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class TreeTableViewSample extends Application {
 
    List<Employee> employees = Arrays.asList(
        new Employee("Ethan Williams", "ethan.williams@example.com"),
        new Employee("Emma Jones", "emma.jones@example.com"),
        new Employee("Michael Brown", "michael.brown@example.com"),
        new Employee("Anna Black", "anna.black@example.com"),
        new Employee("Rodger York", "roger.york@example.com"),
        new Employee("Susan Collins", "susan.collins@example.com"));

    final TreeItem<Employee> root = new TreeItem(new Employee("Sales Department", ""));

    public static void main(String[] args) {
        Application.launch(TreeTableViewSample.class, args);
    }

    @Override
    public void start(Stage stage) {
        root.setExpanded(true);
        employees.forEach(item -> root.getChildren().add(new TreeItem(item)) );
        stage.setTitle("Tree Table View Sample");
        final Scene scene = new Scene(new Group(), 400, 400);
        scene.setFill(Color.LIGHTGRAY);
        Group sceneRoot = (Group) scene.getRoot();

        TreeTableColumn<Employee, String> empColumn = new TreeTableColumn("Employee");
        empColumn.setPrefWidth(150);
        empColumn.setCellValueFactory(
            (TreeTableColumn.CellDataFeatures<Employee, String> param) -> 
            new ReadOnlyStringWrapper(param.getValue().getValue().getName())
        );

        TreeTableColumn<Employee, String> emailColumn = new TreeTableColumn("Email");
        emailColumn.setPrefWidth(90);
        emailColumn.setCellValueFactory(
            (TreeTableColumn.CellDataFeatures<Employee, String> param) -> 
            new ReadOnlyStringWrapper(param.getValue().getValue().getEmail())
        );

        TreeTableView<Employee> treeTableView = new TreeTableView(root);
        treeTableView.getColumns().setAll(empColumn, emailColumn);
        sceneRoot.getChildren().add(treeTableView);
        stage.setScene(scene);
        stage.show();
    }
 
    public class Employee {
        private SimpleStringProperty name;
        private SimpleStringProperty email;
        private Employee(String name, String email) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
        }
        public String getName() {
            return name.get();
        }
        public String getEmail() {
            return email.get();
        }
    }
}