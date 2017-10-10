/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 * @author
 */
public class AdventuraZakladni extends Application {

    IHra hra;
    private TextField commandTextField;
    private TextArea centerTextArea;
    private OknoProstoru oknoProstoru;
    private PanelVychodu panelVychodu;
    private MenuBar menuBar;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        hra = new Hra();

        BorderPane border = new BorderPane();

        Scene scene = new Scene(border, 850, 550);

        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();

        oknoProstoru = new OknoProstoru(hra);
        panelVychodu = new PanelVychodu(hra);

        border.setRight(panelVychodu.getListView());
        border.setLeft(oknoProstoru.getHorniPanel());

//        border.setRight(panelVychodu());

//        border.setTop(horniPanel());

        initMenu();

        border.setTop(menuBar);

        border.setCenter(centralniPanel());

        border.setBottom(dolniPanel());

        commandTextField.requestFocus();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else if (args[0].equals("-text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            System.out.println("Neplatny parametr");
        }

    }

//    private FlowPane horniPanel() {
//        FlowPane horniPanel = new FlowPane();
//
//        AnchorPane obrazekPane = new AnchorPane();
//        ImageView obrazek = new ImageView(new Image(AdventuraZakladni.class.getResourceAsStream("../zdroje/planek.jpg"), 400, 250, false, false));
//
//        Circle tecka = new Circle(10, Paint.valueOf("red"));
//
//        AnchorPane.setTopAnchor(tecka, 25.0);
//        AnchorPane.setLeftAnchor(tecka, 100.0);
//
//        obrazekPane.getChildren().addAll(obrazek, tecka);
//
//        TextArea popisProstoru = new TextArea();
//        popisProstoru.setPrefWidth(350);
//        popisProstoru.setEditable(false);
//        popisProstoru.setText(hra.getHerniPlan().getAktualniProstor().dlouhyPopis());
//        
//        
//        horniPanel.getChildren().addAll(popisProstoru, obrazekPane);
//
//        return horniPanel;
//
//    }

    private FlowPane dolniPanel() {

        FlowPane dolniFlowPane = new FlowPane();
        dolniFlowPane.setAlignment(Pos.CENTER);

        Label zadejPrikazLabel = new Label("Zadej prikaz ");
        dolniFlowPane.getChildren().add(zadejPrikazLabel);

        commandTextField = new TextField();

        dolniFlowPane.getChildren().add(commandTextField);

        commandTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String line = commandTextField.getText();
                String text = hra.zpracujPrikaz(line);
                centerTextArea.appendText("\n\n" + line + "\n");
                centerTextArea.appendText("\n" + text + "\n");
                commandTextField.setText("");
                if (hra.konecHry()) {
                    commandTextField.setEditable(false);
                    System.exit(0);
                }

            }
        });


        return dolniFlowPane;
    }

    private TextArea centralniPanel() {

        centerTextArea = new TextArea();
        centerTextArea.setEditable(false);
        centerTextArea.setText(hra.vratUvitani());

        return centerTextArea;
    }

//    private ListView panelVychodu() {
//
//        ListView<String> list = new ListView<>();
//        
//        ObservableList<String> dataListu = FXCollections.observableArrayList();
//        
//        list.setItems(dataListu);
//        list.setPrefWidth(100);
//        
//        String vychody = hra.getHerniPlan().getAktualniProstor().popisVychodu();
//        
//        String[] vychodyZProstoru = vychody.split(" ");
//        for (int i = 1; i < vychodyZProstoru.length; i++) {
//            dataListu.add(vychodyZProstoru[i]);
//            
//        }
//        
//        return list;
//    
//    }

    private void initMenu() {
//        menu lista
        menuBar = new MenuBar();
        menuBar.useSystemMenuBarProperty().set(true);

//        menu tlacitko
        Menu menuPolozkaSoubor = new Menu("_Soubor");

//        menu polozka
        MenuItem novaHra = new MenuItem("Nova hra");

        novaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                hra = new Hra();

                oknoProstoru.setHra(hra);
                oknoProstoru.aktualizuj();
                hra.getHerniPlan().registraceObserver(oknoProstoru);
                panelVychodu.setHra(hra);
                panelVychodu.aktualizuj();
                hra.getHerniPlan().registraceObserver(panelVychodu);
                centerTextArea.setText(hra.vratUvitani());


            }
        });

//        klavesova zkratka
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

        MenuItem konec = new MenuItem("_Konec");
        konec.setMnemonicParsing(true);

        konec.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                System.exit(0);

            }
        });

        menuPolozkaSoubor.getItems().addAll(novaHra, konec);

        Menu help = new Menu("Pomoc");

        MenuItem oProgramu = new MenuItem("O programu");

        oProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Alert alertOkno = new Alert(Alert.AlertType.INFORMATION);

                alertOkno.setTitle("O programu");
                alertOkno.setHeaderText("GUI adventura");
                alertOkno.setContentText("Skakal pes pres oves abcd ..... 12348564");
                alertOkno.initOwner(primaryStage);
                alertOkno.showAndWait();

            }
        });

        MenuItem napoveda = new MenuItem("Napoveda");

        napoveda.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                WebView webView = new WebView();

                webView.getEngine().load(AdventuraZakladni.class.getResource("/zdroje/napoveda.html").toExternalForm());

                stage.setScene(new Scene(webView, 500, 500));
                stage.show();

            }
        });

        help.getItems().addAll(oProgramu, napoveda);

        menuBar.getMenus().addAll(menuPolozkaSoubor, help);


    }

}
