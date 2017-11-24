package GUI;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.Main;
import util.ObserverBatoh;
/**
 *  Class Batoh - třída představující batoh a věci v něm.
 *
 *@author     David Voráček
 */
public class Batoh extends AnchorPane implements ObserverBatoh {

    private TextField Title;
    private ImageView maliny;
    private logika.Batoh Batoh;
    private boolean malinyInventar = false;

    /**
     *  Konstruktor, který vytváří panel batohu
     */
    public Batoh(logika.Batoh batoh){
        Batoh = batoh;
        batoh.registerObserver(this);
        init();
    }
    /**
     * init inicializuje komponenty batohu
     */
    private void init(){
        maliny = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/maliny.jpg"),100,100,false,true));
        Title = new TextField("Batoh");
        Title.setEditable(false);
        this.getChildren().add(Title);
    }
    /**
     * Aktualizování předmětů v batohu
     */
    @Override
    public void update(String vec) {
        if (vec.toString() == "maliny")
        {
            if (!malinyInventar){
                this.getChildren().add(maliny);
                this.setTopAnchor(maliny, 25.0);
                malinyInventar = true;
            }
            else {
                this.getChildren().remove(maliny);
                malinyInventar = false;
            }
        }
    }
}
