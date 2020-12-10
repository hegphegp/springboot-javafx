package com.codingfly.generator;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;

public class GenerateRightMenu extends ContextMenu {

    public GenerateRightMenu(TreeItem treeItem) {
        MenuItem closeMenuItem = new MenuItem("关闭");
        getItems().add(closeMenuItem);
        MenuItem deleteMenuItem = new MenuItem("删除");
        deleteMenuItem.setOnAction((event)-> treeItem.getParent().getChildren().remove(treeItem) );
        getItems().add(deleteMenuItem);
    }

}
