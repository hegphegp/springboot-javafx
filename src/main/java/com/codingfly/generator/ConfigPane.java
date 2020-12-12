package com.codingfly.generator;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ConfigPane extends GridPane {
    public ConfigPane() {
        super.setPadding(new Insets(5));
        super.setHgap(3);
        super.setVgap(3);
        ColumnConstraints column1 = new ColumnConstraints(80);
        ColumnConstraints column2 = new ColumnConstraints(500);
        ColumnConstraints column3 = new ColumnConstraints(500);
        column2.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);
        super.getColumnConstraints().addAll(column1, column2, column3);

        Label fNameLbl = new Label("生成目录");
        TextField fNameFld = new TextField();
        Label lNameLbl = new Label("Last Name");
        TextField lNameFld = new TextField();

        Button saveButt = new Button("Save");

        super.setHalignment(fNameLbl, HPos.RIGHT);
        super.add(fNameLbl, 0, 0);

        super.setHalignment(lNameLbl, HPos.RIGHT);
        super.add(lNameLbl, 0, 1);

        Button btChooseDirectory = new Button("选择目录");
        super.add(btChooseDirectory, 0, 2);

        super.setHalignment(fNameFld, HPos.LEFT);
        super.add(fNameFld, 1, 0);

        super.setHalignment(lNameFld, HPos.LEFT);
        super.add(lNameFld, 1, 1);

        super.setHalignment(saveButt, HPos.RIGHT);
        super.add(saveButt, 1, 2);
    }
}
