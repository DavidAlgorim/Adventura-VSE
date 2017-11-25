package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import main.Main;
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
    private Main Main;

    /**
     *  Konstruktor, který vytváří panel okolních prostor
     */
    public OkolniProstory(IHra hra, Main main){
        Hra = hra;
        Main = main;
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
        ComboBoxProstory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == "domecek")
                    Main.centralText.appendText("\n" + Hra.zpracujPrikaz("jdi domecek") + "\n");
                else if (newValue == "les")
                    Main.centralText.appendText("\n" + Hra.zpracujPrikaz("jdi les") + "\n");
                else if (newValue == "hluboky_les")
                    Main.centralText.appendText("\n" + Hra.zpracujPrikaz("jdi hluboky_les") + "\n");
                else if (newValue == "jeskyne")
                    Main.centralText.appendText("\n" + Hra.zpracujPrikaz("jdi jeskyne") + "\n");
                else if (newValue == "chaloupka")
                    Main.centralText.appendText("\n" + Hra.zpracujPrikaz("jdi chaloupka") + "\n");
            }
        });
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
