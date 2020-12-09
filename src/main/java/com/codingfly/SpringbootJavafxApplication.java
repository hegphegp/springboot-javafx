package com.codingfly;

import com.codingfly.config.Config;
import com.codingfly.model.MenuModel;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ObjectUtils;

import java.util.List;

@SpringBootApplication
public class SpringbootJavafxApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
//        SpringApplication.run(SpringbootJavafxApplication.class, args);
        launch(args);
    }

    private TabPane tabPane = new TabPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        createTabPane();
        VBox vBox = new VBox(getMenuBar(), getToolBar(), tabPane);
        Scene scene = new Scene(vBox, Config.width, Config.height);
//        scene.getStylesheets().add(Hgp.class.getResource("/css/jfoenix-main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX App");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public MenuBar getMenuBar() {
        List<MenuModel> menuModels = Config.menuModels;
        MenuBar menuBar = new MenuBar();

        for (MenuModel menuModel:menuModels) {
            Menu menu = new Menu(menuModel.getName());
            if (!ObjectUtils.isEmpty(menuModel.getChildren())) {
                createMenu(menu, menuModel.getChildren());
            }
            menuBar.getMenus().add(menu);
        }
        return menuBar;
    }

    public void createMenu(Menu parent, List<MenuModel> menuModels) {
        for (MenuModel menuModel:menuModels) {
            if (!ObjectUtils.isEmpty(menuModel.getChildren())) {
                Menu menu = new Menu(menuModel.getName());
                parent.getItems().add(menu);
                createMenu(menu, menuModel.getChildren());
            } else {
                MenuItem menuItem = new MenuItem(menuModel.getName());
                parent.getItems().add(menuItem);
            }
        }
    }


    public SplitPane codeGeneratePane() {
        SplitPane splitPane = new SplitPane();

        // TreeItem名字
        TreeItem rootItem = new TreeItem("Inbox");
        // 创建TreeView
        TreeView<Object> tree = new TreeView(rootItem);
        tree.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            if (event.getButton() == MouseButton.SECONDARY) { // 鼠标右键事件
                /** 删除
                TreeItem c = tree.getSelectionModel().getSelectedItem();
                boolean remove = c.getParent().getChildren().remove(c);
                 */
                TreeItem treeItem = tree.getSelectionModel().getSelectedItem();
                if (treeItem==null) return;
                // 给node对象添加下来菜单；
                Node node = event.getPickResult().getIntersectedNode();
                GlobalMenu.getInstance(treeItem).show(node, javafx.geometry.Side.BOTTOM, event.getX(), 0);
//                String name = (String) ((TreeItem) tree.getSelectionModel().getSelectedItem()).getValue();
            }
        });
        rootItem.setExpanded(true);
        tree.setShowRoot(false); // 隐藏根节点
        // 每个Item下又可以添加新的Item
        for (int i = 1; i < 24; i++) {
            TreeItem item = new TreeItem("Message" + i);
            item.getChildren().add(new TreeItem("第三级"));
            rootItem.getChildren().add(item);
        }
        Button button1 = new Button("新增连接");

        VBox leftPane = new VBox();
        leftPane.setSpacing(10);
        leftPane.setPadding(new Insets(5, 5, 5, 5));
        leftPane.getChildren().addAll(button1, tree);
        tree.setPrefHeight(Config.height);

        TabPane rightPane = new TabPane();
        Tab tab1 = new Tab("Planes", new StackPane(new Label("Show all Planes available")));
        Tab tab2 = new Tab("Cars"  , new StackPane(new Label("Show all cars available")));
        Tab tab3 = new Tab("Boats" , new StackPane(new Label("Show all boats available")));

        rightPane.setPrefWidth(Config.width);
        rightPane.setPrefHeight(Config.height);
        rightPane.setStyle("-fx-background-color: red");

        rightPane.getTabs().add(tab1);
        rightPane.getTabs().add(tab2);
        rightPane.getTabs().add(tab3);
        leftPane.setMinWidth(200);
        leftPane.setMaxWidth(200);
        splitPane.getItems().add(leftPane);
        splitPane.getItems().add(rightPane);
//        splitPane.setDividerPositions(0.25);
        return splitPane;
    }

    public ToolBar getToolBar() {
        Button button1 = new Button("代码生成器");
        Button button2 = new Button("代码生成器");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Tab tab = new Tab("Boats" , codeGeneratePane());
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
                /**
                 * 下面是设置指定选项卡选中
                 * SingleSelectionModel selectionModel = tabPane.getSelectionModel();
                 * 将其存储在某个局部变量中后，您可以使用不同的选项来选择选项卡。
                 * selectionModel.select(tab1); //select by object
                 * selectionModel.select(1); //select by index starting with 0
                 * selectionModel.clearSelection(); //clear your selection
                 */
            }
        });
        ToolBar toolBar = new ToolBar(button1, button2);
        return toolBar;
    }

    public void createTabPane() {
        Tab tab1 = new Tab("Planes", codeGeneratePane());
        Tab tab2 = new Tab("Cars"  , new StackPane(new Label("Show all cars available")));
        Tab tab3 = new Tab("Boats" , new StackPane(new Label("Show all boats available")));

        tabPane.setPrefWidth(Config.width);
        tabPane.setPrefHeight(Config.height);
//        tabPane.setStyle("-fx-background-color: red");

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
    }

}
