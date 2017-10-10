/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import logika.IHra;
import util.ObserverZmenyProstoru;

/**
 *
 * @author xzenj02
 */
public class PanelVychodu implements ObserverZmenyProstoru{
    
    private IHra hra;
    private ListView<String> list;
    private ObservableList<String> data;
    
    public PanelVychodu(IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registraceObserver(this);
        initGUI();
    }
    
    public void setHra(IHra hra){
        this.hra = hra;
    }

    private void initGUI() {

        list = new ListView<>();
        
        data = FXCollections.observableArrayList();
        
        list.setItems(data);
        list.setPrefWidth(100);
        
        String vychody = hra.getHerniPlan().getAktualniProstor().popisVychodu();
        
        String[] vychodyZProstoru = vychody.split(" ");
        for (int i = 1; i < vychodyZProstoru.length; i++) {
            data.add(vychodyZProstoru[i]);
            
        }
        
        aktualizuj();
        
    }
    
    public ListView<String> getListView(){
        return list;
    }

    @Override
    public void aktualizuj() {
        String vychody = hra.getHerniPlan().getAktualniProstor().popisVychodu();
        data.clear();
        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
            
        }
    }
    
}
