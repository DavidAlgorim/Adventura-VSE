
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author xzenj02
 */
public class Main extends Application {

    private TextArea centralText;
    private IHra hra;
    private TextField zadejPrikazTextArea;

    @Override
    public void start(Stage primaryStage) {
        IHra hra = new Hra();
        BorderPane borderPane = new BorderPane();


        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);

        Label zadejPrikaz = new Label("Zadej prikaz: ");
        zadejPrikaz.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                zadejPrikazTextArea.setText("");

                if (hra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });

        FlowPane obrazekFlowPane = new FlowPane();
        obrazekFlowPane.setPrefSize(200, 200);
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/planek.jpg"),200,200,false,true));
        obrazekFlowPane.setAlignment(Pos.CENTER);
        obrazekFlowPane.getChildren().add(obrazekImageView);

        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikaz,zadejPrikazTextArea);

        borderPane.setLeft(obrazekFlowPane);
        borderPane.setBottom(dolniLista);
        Scene scene = new Scene(borderPane, 750, 450);
        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

}

