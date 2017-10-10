/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import util.ObserverZmenyProstoru;

/**
 *
 * @author xzenj02
 */
public class OknoProstoru implements ObserverZmenyProstoru{
    private IHra hra;
    private FlowPane horniPanel;
    private TextArea popisProstoru;
    Circle tecka;
    
    public OknoProstoru(IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registraceObserver(this);
        initGUI();
    }
    
    public void setHra(IHra hra){
        this.hra = hra;
    }
    
    public FlowPane getHorniPanel(){
        return horniPanel;
    }

    private void initGUI() {
        horniPanel = new FlowPane();
        horniPanel.setPrefWidth(350);

        AnchorPane obrazekPane = new AnchorPane();
        ImageView obrazek = new ImageView(new Image(AdventuraZakladni.class.getResourceAsStream("../zdroje/planek.jpg"), 350, 250, false, false));

        tecka = new Circle(10, Paint.valueOf("red"));

        AnchorPane.setTopAnchor(tecka, 25.0);
        AnchorPane.setLeftAnchor(tecka, 100.0);

        obrazekPane.getChildren().addAll(obrazek, tecka);

        popisProstoru = new TextArea();
        popisProstoru.setPrefWidth(350);
        popisProstoru.setEditable(false);
        popisProstoru.setText(hra.getHerniPlan().getAktualniProstor().dlouhyPopis());
        
        
        horniPanel.getChildren().addAll(popisProstoru, obrazekPane);
        aktualizuj();
    }

    @Override
    public void aktualizuj() {
        popisProstoru.setText(hra.getHerniPlan().getAktualniProstor().dlouhyPopis());
        
        AnchorPane.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getTop());
        AnchorPane.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getLeft());
        
    }
    
    
}
