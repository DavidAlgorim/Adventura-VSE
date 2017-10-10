package logika;

import java.util.ArrayList;
import java.util.List;
import util.ObserverZmenyProstoru;
import util.SubjecZmenyProstoru;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Alena Buchalcevova
 *@version    z kurzu 4IT101 pro školní rok 2014/2015
 */
public class HerniPlan implements SubjecZmenyProstoru{
    
    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private List<ObserverZmenyProstoru> seznamObservers;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        seznamObservers = new ArrayList<>();
        zalozProstoryHry();

    }
    
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor domecek = new Prostor("domecek","domeček, ve kterém bydlí Karkulka", 12.5, 80);
        Prostor chaloupka = new Prostor("chaloupka", "chaloupka, ve které bydlí babička Karkulky", 45 ,86);
        Prostor jeskyne = new Prostor("jeskyne","stará plesnivá jeskyně", 78, 65);
        Prostor les = new Prostor("les","les s jahodami, malinami a pramenem vody", 41, 10);
        Prostor hlubokyLes = new Prostor("hluboky_les","temný les, ve kterém lze potkat vlka", 30, 96);
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        domecek.setVychod(les);
        les.setVychod(domecek);
        les.setVychod(hlubokyLes);
        hlubokyLes.setVychod(les);
        hlubokyLes.setVychod(jeskyne);
        hlubokyLes.setVychod(chaloupka);
        jeskyne.setVychod(hlubokyLes);
        chaloupka.setVychod(hlubokyLes);
                
        aktualniProstor = domecek;  // hra začíná v domečku  
        viteznyProstor = chaloupka ;
        les.vlozVec(new Vec("maliny", true));
        les.vlozVec(new Vec("strom", false));  
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       upozorniPozorovatele();
    }
    /**
     *  Metoda vrací odkaz na vítězný prostor.
     *
     *@return     vítězný prostor
     */
    
    public Prostor getViteznyProstor() {
        return viteznyProstor;
    }

    @Override
    public void registraceObserver(ObserverZmenyProstoru observer) {
        seznamObservers.add(observer);
    }

    @Override
    public void odebraniObserver(ObserverZmenyProstoru observer) {
        seznamObservers.remove(observer);
    }

    @Override
    public void upozorniPozorovatele() {
        for (ObserverZmenyProstoru seznamObserver : seznamObservers) {
            seznamObserver.aktualizuj();
        }
    }

}
