/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author xzenj02
 */
public interface SubjecZmenyProstoru {
    
    public void registraceObserver(ObserverZmenyProstoru observer);
    
    public void odebraniObserver(ObserverZmenyProstoru observer);
    
    public void upozorniPozorovatele();
    
}
