package com.codingfly.generator;

import com.codingfly.config.Config;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class GeneratePane extends SplitPane {

    public GeneratePane() {
        TreeItem rootItem = new TreeItem("数据库链接");  // TreeItem名字
        TreeView<Object> tree = new TreeView(rootItem);      // 创建TreeView
        tree.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            if (event.getButton() == MouseButton.PRIMARY) { // 鼠标左键事件

            } else if (event.getButton() == MouseButton.SECONDARY) { // 鼠标右键事件
                /** 删除选中的TreeItem
                 TreeItem c = tree.getSelectionModel().getSelectedItem();
                 boolean remove = c.getParent().getChildren().remove(c);
                 */
                TreeItem treeItem = tree.getSelectionModel().getSelectedItem();
                if (treeItem==null) return;
                Models.DBConfig dbItem = (Models.DBConfig)tree.getSelectionModel().getSelectedItem().getValue();
                if ("POSTGRESQL".equals(dbItem.getDbType())) {

                } else if ("MYSQL".equals(dbItem.getDbType())) {
                    Node node = event.getPickResult().getIntersectedNode(); // 给node对象添加下来菜单；
                    new GenerateRightMenu(treeItem).show(node, javafx.geometry.Side.BOTTOM, event.getX(), 0);
//                  String name = (String) ((TreeItem) tree.getSelectionModel().getSelectedItem()).getValue();
                }
            }
        });
        rootItem.setExpanded(true);
        tree.setShowRoot(false); // 隐藏根节点
        // 每个Item下又可以添加新的Item
//        for (int i = 1; i < 24; i++) {
//            TreeItem item = new TreeItem(new DbItem("Message" + i, "POSTGRESQL", 0));
//            if (i%2==0) {
//                item.getChildren().add(new TreeItem(new DbItem("第三级" + i, "POSTGRESQL", 1)));
//            }
//            rootItem.getChildren().add(item);
//        }
        Button button1 = new Button("新增连接");

        VBox leftPane = new VBox();
        leftPane.setSpacing(10);
        leftPane.setPadding(new Insets(5, 5, 5, 5));
        leftPane.getChildren().addAll(button1, tree);
        tree.setPrefHeight(Config.height);

        TabPane rightPane = new TabPane();

        Tab tab1 = new Tab("Planes", new ConfigPane());

        rightPane.setPrefWidth(Config.width);
        rightPane.setPrefHeight(Config.height);

        rightPane.getTabs().add(tab1);
        leftPane.setMinWidth(200);
        leftPane.setMaxWidth(200);
        super.getItems().add(leftPane);
        super.getItems().add(rightPane);
    }
}
