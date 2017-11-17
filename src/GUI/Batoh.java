package GUI;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.Main;
import util.ObserverBatoh;

public class Batoh extends AnchorPane implements ObserverBatoh {

    private TextField Title;
    private ImageView maliny;
    private logika.Batoh Batoh;
    private boolean malinyInventar = false;

    public Batoh(logika.Batoh batoh){
        Batoh = batoh;
        batoh.registerObserver(this);
        init();
    }

    private void init(){
        maliny = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/maliny.jpg"),100,100,false,true));
        Title = new TextField("Batoh");
        Title.setEditable(false);
        this.getChildren().add(Title);
    }

    @Override
    public void update(String vec) {
        if (!malinyInventar){
            this.getChildren().add(maliny);
            malinyInventar = true;
        }
        else {
            this.getChildren().remove(maliny);
            malinyInventar = false;
        }
    }
}
