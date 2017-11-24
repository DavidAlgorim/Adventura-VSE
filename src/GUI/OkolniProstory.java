package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import util.ObserverProstor;
import java.util.ArrayList;
/**
 *  Class OkolniProstory - třída spravuje panel s okolními prostory
 *
 *@author     David Voráček
 */

public class OkolniProstory extends AnchorPane implements ObserverProstor {

    private TextField Title;
    private ComboBox<String> ComboBoxProstory = new ComboBox<>();
    private HerniPlan Plan;
    private ObservableList<String> ComboListProstory;
    private IHra Hra;

    /**
     *  Konstruktor, který vytváří panel okolních prostor
     */
    public OkolniProstory(IHra hra){
        Hra = hra;
        Plan = Hra.getHerniPlan();
        Plan.registerObserver(this);
        init();
        update(Plan.getAktualniProstor());
    }
    /**
     * init inicializuje komponenty okolních prostor
     */
    private void init(){
        Title = new TextField("Okolní prostory");
        Title.setEditable(false);
        ComboBoxProstory = new ComboBox<>(ComboListProstory);
        this.setTopAnchor(ComboBoxProstory, 25.0);
        this.getChildren().addAll(Title, ComboBoxProstory);
    }
    /**
     * Aktualizování comboboxu
     */
    @Override
    public void update(Prostor prostor) {
        prostor.getNazev();
        ComboListProstory = FXCollections.observableArrayList(prostor.okoliSeznam());
        ComboBoxProstory.setItems(ComboListProstory);
    }
}
