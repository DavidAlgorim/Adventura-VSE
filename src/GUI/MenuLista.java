package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;
/**
 *  Class MenuLista - třída spravuje horní lištu
 *
 *@author     David Voráček
 */

public class MenuLista extends MenuBar {

    private IHra Hra;
    private Main Main;
    /**
     *  Konstruktor, který vytváří panel okolních prostor
     */
    public MenuLista(IHra hra, Main main){
        Hra = hra;
        Main = main;
        init();
    }
    /**
     * init inicializuje komponenty okolních prostor
     */
    private void init(){
        Menu novySoubor = new Menu("Adventura");
        MenuItem novaHra = new Menu("Nová hra");//, new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png"))));

        Menu napoveda = new Menu("Help");

        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        MenuItem konecHry = new Menu("Konec hry");
        novySoubor.getItems().addAll(novaHra, konecHry);
        this.getMenus().addAll(novySoubor, napoveda);

        MenuItem oProgramu = new MenuItem("O Programu");
        MenuItem napovedaItem = new MenuItem("Nápověda");
        napoveda.getItems().addAll(napovedaItem, oProgramu);
        konecHry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Hra = new Hra();
                Main.getMapa().newGame(Hra);
                Main.setHra(Hra);
                Main.getCentralText().setText(Hra.vratUvitani());
                Main.enableTextField();
            }
        });

        oProgramu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);

                oProgramuAlert.setTitle("O Programu");
                oProgramuAlert.setHeaderText("Super adventura");
                oProgramuAlert.initOwner(Main.getStage());
                oProgramuAlert.showAndWait();
            }
        });

        napovedaItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Nápověda");

                WebView webView = new WebView();
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                stage.setScene(new Scene(webView, 500, 500));
                stage.show();
            }
        });
    }
}
