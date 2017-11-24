package GUI;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import util.ObserverProstor;
import java.util.ArrayList;

/**
 *  Class Mapa - třída představující mapu a pozici na mapě.
 *
 *@author     David Voráček
 */

public class Mapa extends AnchorPane implements ObserverProstor {

    private IHra Hra;
    private Main Main;
    private Circle tecka;
    private ArrayList<String> veci;
    private String vecis;
    private ImageView maliny;
    private ImageView strom;

    /**
     *  Konstruktor, který vytváří mapu
     */
    public Mapa(IHra hra, Main main){
        Main = main;
        Hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    /**
     * init inicializuje komponenty mapy
     */
    private void init(){

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/planek.png"),400,400,false,true));

        tecka = new Circle(10, Paint.valueOf("red"));
        maliny = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/maliny.jpg"),50,50,false,true));
        strom = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/strom.JPG"),50,50,false,true));
        maliny.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.centralText.appendText("\n" + Hra.zpracujPrikaz("seber maliny") + "\n");
                odstranMaliny();
            }
        });
        strom.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.centralText.appendText("\n" + Hra.zpracujPrikaz("seber strom") + "\n");
            }
        });
        this.setTopAnchor(tecka, 55.0);
        this.setLeftAnchor(tecka, 100.0);

        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }
    /**
     * Resetování pozice na mapě při spuštění nové hry
     */
    public void newGame (IHra novaHra){
        Hra.getHerniPlan().removeObserver(this);
        Hra = novaHra;
        Hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Aktualizování pozice na mapě a věcí v místnosti
     */
    @Override
    public void update() {
        veci = Hra.getHerniPlan().getAktualniProstor().veciSeznam();
        this.getChildren().removeAll(maliny, strom);
        for (String vec: veci)
        {
            if (vec == "maliny"){
                this.getChildren().add(maliny);
                this.setTopAnchor(maliny, 10.0);
                this.setLeftAnchor(maliny, 340.0);
            } else if (vec == "strom"){
                this.getChildren().add(strom);
                this.setTopAnchor(strom, 10.0);
                this.setLeftAnchor(strom, 280.0);
            }
        }

        this.setTopAnchor(tecka, Hra.getHerniPlan().getAktualniProstor().getTop());
        this.setLeftAnchor(tecka, Hra.getHerniPlan().getAktualniProstor().getLeft());
    }

    /**
     * Metoda odstraňující ikonu malin z místnosti
     */
    private void odstranMaliny(){
        this.getChildren().remove(maliny);
    }
}
