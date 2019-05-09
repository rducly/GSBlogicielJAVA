/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author admin
 */
public class CFicheFrais {
        
        private int idFicheFrais;
	private int mois;
	private int nbHorsClassif;
	private double montantHorsClassif;
	private String etape;
	private String matriculeVisiteur;
	private GregorianCalendar dateDerniereModif;
	private ArrayList<CFrais> listeFrais;

	public CFicheFrais(int idFicheFrais, int mois, int nbHorsClassif, double montantHorsClassif, String etape, String matriculeVisiteur, GregorianCalendar dateDerniereModif, ArrayList<CFrais> listeFrais) {
		this.setIdFicheFrais(idFicheFrais);
                this.setMois(mois);
		this.setNbHorsClassif(nbHorsClassif);
		this.setMontantHorsClassif(montantHorsClassif);
		this.setEtape(etape);
		this.setMatriculeVisiteur(matriculeVisiteur);
		this.setDateDerniereModif(dateDerniereModif);
		this.setListeFrais(listeFrais);
	}
	
	public CFicheFrais(){
	}
	
        
        public int getIdFicheFrais() {
            return idFicheFrais;
        }

         public void setIdFicheFrais(int idFicheFrais) {
            this.idFicheFrais = idFicheFrais;
         }
        
	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getNbHorsClassif() {
		return nbHorsClassif;
	}

	public void setNbHorsClassif(int nbHorsClassif) {
		this.nbHorsClassif = nbHorsClassif;
	}

	public double getMontantHorsClassif() {
		return montantHorsClassif;
	}

	public void setMontantHorsClassif(double montantHorsClassif) {
		this.montantHorsClassif = montantHorsClassif;
	}

	public String getEtape() {
		return etape;
	}

	public void setEtape(String etape) {
		this.etape = etape;
	}

	public String getMatriculeVisiteur() {
		return matriculeVisiteur;
	}

	public void setMatriculeVisiteur(String matriculeVisiteur) {
		this.matriculeVisiteur = matriculeVisiteur;
	}

	public GregorianCalendar getDateDerniereModif() {
		return dateDerniereModif;
	}

	public void setDateDerniereModif(GregorianCalendar dateDerniereModif) {
		this.dateDerniereModif = dateDerniereModif;
	}
	
	public ArrayList<CFrais> getListeFrais() {
		return listeFrais;
	}

	public void setListeFrais(ArrayList<CFrais> listeFrais) {
		this.listeFrais = listeFrais;
	}
}
