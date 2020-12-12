package com.codingfly.generator;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

public class TreeViewSample extends Application {

    private final Button rootIcon = new Button("图片1");
    private final Button depIcon = new Button("图片2");
    List<Employee> employees = Arrays.asList(
            new Employee("Jacob Smith", "Accounts Department"),
            new Employee("Isabella Johnson", "Accounts Department"),
            new Employee("Ethan Williams", "Sales Department"),
            new Employee("Emma Jones", "Sales Department"),
            new Employee("Michael Brown", "Sales Department"),
            new Employee("Anna Black", "Sales Department"),
            new Employee("Rodger York", "Sales Department"),
            new Employee("Susan Collins", "Sales Department"),
            new Employee("Mike Graham", "IT Support"),
            new Employee("Judy Mayer", "IT Support"),
            new Employee("Gregory Smith", "IT Support"));
    TreeItem<String> rootNode = null;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        rootIcon.setFont(Font.font("Segoe Script",8));
        rootIcon.setMinSize(30, 10);
        depIcon.setMinSize(30, 10);
        rootNode = new TreeItem("MyCompany Human Resources");

        rootNode.setExpanded(true);
        for (Employee employee : employees) {
            TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(employee.getDepartment())) {
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem depNode = new TreeItem(employee.getDepartment());
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }

        stage.setTitle("Tree View Sample");
        VBox box = new VBox();
        final Scene scene = new Scene(box, 400, 300);
        scene.setFill(Color.LIGHTBLUE);

        TreeView<String> treeView = new TreeView<>(rootNode);
        treeView.setEditable(true);
        treeView.setCellFactory((TreeView<String> p) -> new MyTreeCellImpl());

        box.getChildren().add(treeView);
        stage.setScene(scene);
        stage.show();
    }

    private final class MyTreeCellImpl extends TreeCell<String> {
        private Label textField;
        private Button button;
        private HBox hBox;
        private final ContextMenu contextMenu = new ContextMenu();

        public MyTreeCellImpl() {
            //自定义TreeCell的界面
            hBox = new HBox();
            button = new Button("Alex");
            button.setFont(Font.font("Segoe Script", FontWeight.EXTRA_BOLD, 10));
            button.setMinSize(40, 16);
            button.setMaxSize(40, 16);
            button.setPadding(new Insets(0));
            textField = new Label();
            hBox.getChildren().add(button);
            hBox.getChildren().add(textField);
            MenuItem addMenuItem = new MenuItem("Add Employee");
            MenuItem delMenuItem = new MenuItem("Delete Employee");
            contextMenu.getItems().add(addMenuItem);
            contextMenu.getItems().add(delMenuItem);
            addMenuItem.setOnAction((ActionEvent t) -> {
                TreeItem newEmployee = new TreeItem("New Employee");
                getTreeItem().getChildren().add(newEmployee);
            });
            delMenuItem.setOnAction((ActionEvent t) -> {
                TreeItem node = getTreeView().getSelectionModel().getSelectedItem();
                TreeItem parent = node.getParent();
                parent.getChildren().remove(node);
            });
        }

        /**
         * 重载此方法来自定义Cell的界面
         * @param item
         * @param empty
         */
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                textField.setText(item);
                //此处将hBox设置为Cell的界面
                setGraphic(hBox);
                setContextMenu(contextMenu);
            }
        }
    }

    public static class Employee {
        private final SimpleStringProperty name;
        private final SimpleStringProperty department;

        private Employee(String name, String department) {
            this.name = new SimpleStringProperty(name);
            this.department = new SimpleStringProperty(department);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }

        public String getDepartment() {
            return department.get();
        }

        public void setDepartment(String fName) {
            department.set(fName);
        }
    }
}