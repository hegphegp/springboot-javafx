package com.codingfly.generator;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MyTreeCell extends TreeCell<String> {
    private Label textField;
    private Button button;
    private HBox hBox;
    private ContextMenu contextMenu = new ContextMenu();

    // dbType取值MYSQL,POSTGRES
    // type=0表示数据库，type=1表示schema
    public MyTreeCell(String id, String dbType, String name, String type) {
        //自定义TreeCell的界面
        hBox = new HBox();
        button = new Button(dbType);
        button.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 10));
        button.setMaxSize(40, 16);
        button.setPadding(new Insets(0));
        textField = new Label();
        hBox.getChildren().add(button);
        hBox.getChildren().add(textField);
//        MenuItem closeMenuItem = new MenuItem("关闭");
//        closeMenuItem.setOnAction((ActionEvent t) -> {
//            TreeItem newEmployee = new TreeItem("关闭");
//            getTreeItem().getChildren().add(newEmployee);
//        });
//        contextMenu.getItems().add(closeMenuItem);
        MenuItem deleteMenuItem = new MenuItem("删除");
        contextMenu.getItems().add(deleteMenuItem);
        deleteMenuItem.setOnAction((ActionEvent t) -> {
            TreeItem node = getTreeView().getSelectionModel().getSelectedItem();
            if (node==null) return;
            TreeItem parent = node.getParent();
            if (parent==null) return;
            parent.getChildren().remove(node);
            String id1 = id;

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