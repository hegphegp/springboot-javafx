package com.codingfly.generator.test;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class FxEditableCheckBox extends Application {

   @Override
   public void start( Stage stage ) throws Exception {
      final TableView<Os> view = new TableView();
      final ObservableList<TableColumn<Os, ?>> columns = view.getColumns();

      final TableColumn<Os, String> nameColumn = new TableColumn("Name");
      nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
      columns.add(nameColumn);

      final TableColumn<Os, Boolean> loadedColumn = new TableColumn("Delete");
      loadedColumn.setCellValueFactory(param-> {
         return param.getValue().deleteProperty();
      });
      loadedColumn.setCellFactory(tc -> new CheckBoxTableCell());
      columns.add(loadedColumn);

      final TableColumn<Os, Boolean> xmlColumn = new TableColumn("xml");
      xmlColumn.setCellValueFactory(param-> {
         return param.getValue().xmlProperty();
      });
      xmlColumn.setCellFactory(tc -> new CheckBoxTableCell());
      columns.add(xmlColumn);

      final ObservableList<Os> items = FXCollections.observableArrayList(
            new Os( "Microsoft Windows 3.1"    , true  ),
            new Os( "Microsoft Windows 3.11"   , true  ),
            new Os( "Microsoft Windows 95"     , true  ),
            new Os( "Microsoft Windows NT 3.51", true  ),
            new Os( "Microsoft Windows NT 4"   , true  ),
            new Os( "Microsoft Windows 2000"   , true  ),
            new Os( "Microsoft Windows Vista"  , true  ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven000000000"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ),
           new Os( "Microsoft Windows Seven"  , false ));
      view.setItems(items);
      view.setEditable(true);

      final Button delBtn = new Button("Delete");
      delBtn.setMaxWidth(Double.MAX_VALUE );
      delBtn.setOnAction(e -> {
         final Set<Os> del = new HashSet();
         for(final Os os : view.getItems()) {
            os.deleteProperty().setValue(true);
//            if(os.deleteProperty().get()) {
//               del.add(os);
//            }
         }
//         view.getItems().removeAll(del);
//         view.getItems().removeAll(del);
//         System.out.println("=======");
//         view.setItems(items);
      });
      stage.setScene(new Scene(new BorderPane(view, null, null, delBtn, null)));
      BorderPane.setAlignment(delBtn, Pos.CENTER);
      stage.show();
   }

   public class Os {

      private final StringProperty name   = new SimpleStringProperty();
      private final BooleanProperty delete = new SimpleBooleanProperty();
      private final BooleanProperty xml = new SimpleBooleanProperty();

      public Os(String nm, boolean del) {
         name.set(nm);
         delete.set(del);
      }

      public StringProperty nameProperty() { return name;   }
      public BooleanProperty deleteProperty() { return delete; }
      public BooleanProperty xmlProperty() { return xml; }
   }

   public static void main( String[] args ) {
      launch( args );
   }
}