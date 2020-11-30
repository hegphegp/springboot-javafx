package com.codingfly;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        Scene scene = new Scene(vBox, 600, 600);
//        scene.getStylesheets().add(Hgp.class.getResource("/css/jfoenix-main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX App");
        primaryStage.show();
    }

    public MenuBar getMenuBar() {
        Menu menu = new Menu("Menu 1");
        Menu subMenu = new Menu("Menu 1.1");
        MenuItem menuItem11 = new MenuItem("Item 1.1.1");
        subMenu.getItems().add(menuItem11);
        menuItem11.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Tab tab = new Tab("Boats" , new Label("Show all boats available"));
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

        menu.getItems().add(subMenu);

        MenuItem menuItem1 = new MenuItem("Item 1");
        menu.getItems().add(menuItem1);

        MenuItem menuItem2 = new MenuItem("Item 2");
        menu.getItems().add(menuItem2);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    public ToolBar getToolBar() {
        Button button1 = new Button("Button1");
        Button button2 = new Button("Button2");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Tab tab = new Tab("Boats" , new Label("Show all boats available"));
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
        Tab tab1 = new Tab("Planes", new Label("Show all planes available"));
        Tab tab2 = new Tab("Cars"  , new Label("Show all cars available"));
        Tab tab3 = new Tab("Boats" , new Label("Show all boats available"));

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
    }

}
