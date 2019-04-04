/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class CPrescription {
    
      private String posologie;
    private int individuCode;
    private String individuLabel;
    private ArrayList<CDosage> listeDosage; 

    public CPrescription(String posologie, int individuCode, String individuLabel, ArrayList<CDosage> listeDosage) {
        this.setPosologie (posologie);
        this.setIndividuCode (individuCode);
        this.setIndividuLabel (individuLabel);
        this.setListeDosage (listeDosage);
    }
    
    public CPrescription() {
        
    }
    
    /**
     * @return the posologie
     */
    public String getPosologie() {
        return posologie;
    }

    /**
     * @param posologie the posologie to set
     */
    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    /**
     * @return the individuCode
     */
    public int getIndividuCode() {
        return individuCode;
    }

    /**
     * @param individuCode the individuCode to set
     */
    public void setIndividuCode(int individuCode) {
        this.individuCode = individuCode;
    }

    /**
     * @return the individuLabel
     */
    public String getIndividuLabel() {
        return individuLabel;
    }

    /**
     * @param individuLabel the individuLabel to set
     */
    public void setIndividuLabel(String individuLabel) {
        this.individuLabel = individuLabel;
    }

    /**
     * @return the listeDosage
     */
    public ArrayList<CDosage> getListeDosage() {
        return listeDosage;
    }

    /**
     * @param listeDosage the listeDosage to set
     */
    public void setListeDosage(ArrayList<CDosage> listeDosage) {
        this.listeDosage = listeDosage;
    }
    
}
