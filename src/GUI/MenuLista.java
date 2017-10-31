package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import logika.Hra;
import logika.IHra;
import main.Main;

public class MenuLista extends MenuBar {

    private IHra Hra;
    private Main Main;

    public MenuLista(IHra hra, Main main){
        Hra = hra;
        Main = main;
        init();
    }

    private void init(){
        Menu novySoubor = new Menu("Adventura");
        MenuItem novaHra = new Menu("Nová hra");//, new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png"))));
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        MenuItem konecHry = new Menu("Konec hry");
        novySoubor.getItems().addAll(novaHra, konecHry);
        this.getMenus().addAll(novySoubor);
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
            }
        });
    }
}
