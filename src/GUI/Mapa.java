package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.Hra;
import logika.IHra;
import main.Main;
import util.Observer;

public class Mapa extends AnchorPane implements Observer {

    private IHra Hra;
    private Circle tecka;

    public Mapa(IHra hra){
        Hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    private void init(){

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/planek.jpg"),200,200,false,true));

        tecka = new Circle(10, Paint.valueOf("red"));

        //this.setTopAnchor(tecka, 25.0);
        //this.setLeftAnchor(tecka, 100.0);

        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }

    @Override
    public void update() {
        this.setTopAnchor(this, Hra.getHerniPlan().getAktualniProstor().getTop());
        this.setLeftAnchor(this, Hra.getHerniPlan().getAktualniProstor().getLeft());
    }
}
