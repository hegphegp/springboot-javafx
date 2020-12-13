package com.codingfly.generator;

import com.codingfly.config.Config;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class GeneratePane extends SplitPane {

    public GeneratePane() {
        TreeItem rootItem = new TreeItem(new Models.DBConfig("root", "root", "root", "root", "root", "root"));
        TreeView<Models.DBConfig> tree = new TreeView(rootItem);      // 创建TreeView
        tree.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) { // 鼠标双击事件
                System.out.println("。。。。。。。。。。双击。。。。。。。。。。。");
            }
        });
        rootItem.setExpanded(true);
        tree.setShowRoot(false); // 隐藏根节点
        // 每个Item下又可以添加新的Item
        for (int i = 1; i < 24; i++) {
            TreeItem<String> item = new TreeItem(new Models.DBConfig("id", "MYSQL", "name", "username", "password", "url"));
            rootItem.getChildren().add(item);
        }
        Button addConnectionBth = new Button("新增链接");

        VBox leftPane = new VBox();
        leftPane.setSpacing(10);
        leftPane.setPadding(new Insets(5, 5, 5, 5));
        leftPane.getChildren().addAll(addConnectionBth, tree);
        tree.setCellFactory((TreeView<Models.DBConfig> p) -> new ContextMenuTreeCell());
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

    /**
     * 无尽的绝望，这里写死了代码，我悲愤了很久，解决不了，javafx真的做得不行
     */
    public class ContextMenuTreeCell extends TreeCell<Models.DBConfig> {
        private ContextMenu contextMenu = new ContextMenu();

        public ContextMenuTreeCell() {
            MenuItem deleteMenuItem = new MenuItem("删除");
            contextMenu.getItems().add(deleteMenuItem);
            deleteMenuItem.setOnAction((ActionEvent t) -> {
                TreeItem node = getTreeView().getSelectionModel().getSelectedItem();
                if (node==null) return;
                Models.DBConfig item = (Models.DBConfig)node.getValue();
                System.out.println(item);
                TreeItem parent = node.getParent();
                if (parent==null) return;
                parent.getChildren().remove(node);
            });
        }

        /**
         * 重载此方法来自定义Cell的界面
         * @param item
         * @param empty
         */
        @Override
        protected void updateItem(Models.DBConfig item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.getName());
                if ("MYSQL".equals(item.getDbType())) {
                    setGraphic(new ImageView("/img/mysql.png"));
                } else if ("POSTGRES".equals(item.getDbType())) {
                    setGraphic(new ImageView("/img/postgres.png"));
                }
                setContextMenu(contextMenu);
            }
        }
    }
}
