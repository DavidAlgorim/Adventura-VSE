package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import logika.Prostor;
import main.Main;
import util.Observer;

public class Mapa extends AnchorPane implements Observer {

    private IHra Hra;
    private Circle tecka;
    private Prostor prostor;
    private HerniPlan plan;

    public Mapa(IHra hra){
        Hra = hra;
        plan = hra.getHerniPlan();
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    private void init(){

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/planek.png"),400,400,false,true));

        tecka = new Circle(10, Paint.valueOf("red"));

        this.setTopAnchor(tecka, 55.0);
        this.setLeftAnchor(tecka, 100.0);

        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }

    public void newGame (IHra novaHra){
        Hra.getHerniPlan().removeObserver(this);
        Hra = novaHra;
        Hra.getHerniPlan().registerObserver(this);
        update();
    }

    @Override
    public void update() {
        this.setTopAnchor(tecka, Hra.getHerniPlan().getAktualniProstor().getTop());
        this.setLeftAnchor(tecka, Hra.getHerniPlan().getAktualniProstor().getLeft());
    }
}
