package com.codingfly;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;

public class GlobalMenu extends ContextMenu {
    /** 单例 */
    private static GlobalMenu INSTANCE = null;
    private static TreeItem treeItem;

    /** 私有构造函数 */
    private GlobalMenu() {
        MenuItem deleteMenuItem = new MenuItem("删除");
        deleteMenuItem.setOnAction((event)-> GlobalMenu.treeItem.getParent().getChildren().remove(GlobalMenu.treeItem) );
        getItems().add(deleteMenuItem);
    }

    /** 获取实例 */
    public static GlobalMenu getInstance(TreeItem treeItem) {
        if (INSTANCE == null) {
            INSTANCE = new GlobalMenu();
        }
        GlobalMenu.treeItem=treeItem;
        return INSTANCE;
    }

}
