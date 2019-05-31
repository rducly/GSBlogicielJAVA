/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author admin
 */
public class CFrais { 
    
    //table inclure dans la BDD
    private int idFicheFrais;
    private int quantite;
    private double montant;
    private CTypeFrais typeFrais;
    
    public CFrais(int idFicheFrais, int quantite, double montant, CTypeFrais typeFrais) {
       this.idFicheFrais = idFicheFrais;
       this.quantite = quantite;
       this.montant = montant;
       this.typeFrais = typeFrais;
    }
    
     public CFrais( int quantite, double montant, CTypeFrais typeFrais) {
       this.quantite = quantite;
       this.montant = montant;
       this.typeFrais = typeFrais;
    }
    
    public int getIdFicheFrais() {
        return idFicheFrais;
    }

    public void setIdFicheFrais(int idFicheFrais) {
        this.idFicheFrais = idFicheFrais;
    }
    
    /**
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * @return the montant
     */
    public double getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }

    /**
     * @return the typeFrais
     */
    public CTypeFrais getTypeFrais() {
        return typeFrais;
    }

    /**
     * @param typeFrais the typeFrais to set
     */
    public void setTypeFrais(CTypeFrais typeFrais) {
        this.typeFrais = typeFrais;
    }
  
}
