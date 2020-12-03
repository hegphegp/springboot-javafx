package com.codingfly;

import com.codingfly.config.Config;
import com.codingfly.model.MenuModel;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
        assemblyTabPane();
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


    public SplitPane codeGenerate() {
        SplitPane splitPane = new SplitPane();

        // TreeItem名字
        TreeItem<String> rootItem = new TreeItem("Inbox");
        rootItem.setExpanded(true);
        // 每个Item下又可以添加新的Item
        for (int i = 1; i < 36; i++) {
            TreeItem<String> item = new TreeItem("Message" + i);
            item.getChildren().add(new TreeItem("第三级"));
            rootItem.getChildren().add(item);
        }
        // 创建TreeView
        TreeView<String> tree = new TreeView(rootItem);
        tree.setShowRoot(false); // 隐藏根节点
        StackPane leftPane = new StackPane();
        BorderPane pane = new BorderPane();
        Button button1 = new Button("新增连接");
        pane.setTop(button1); //放置在上面
//        leftPane.getChildren().add(button1);
        pane.setCenter(tree);//放置在中间
        leftPane.getChildren().add(pane);

        StackPane rightPane = new StackPane(new Label("right"));
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
                Tab tab = new Tab("Boats" , codeGenerate());
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

    public void assemblyTabPane() {
        Tab tab1 = new Tab("Planes", new StackPane(new Label("Show all planes available")));
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
