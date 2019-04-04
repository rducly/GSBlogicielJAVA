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
public class CTravail {
    
       
	private String role;
	private GregorianCalendar date;
	//private ArrayList<CRegion> listeRegion;
        private CRegion region;
        

	public CTravail( String role, GregorianCalendar date, CRegion region) {
          //  public CTravail( String role, GregorianCalendar date, ArrayList<CRegion> listeRegion) {
		
                
                this.setDate(date);
		this.setRegion(region);
		this.setRole(role);
	}
	
	public CTravail(){
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public CRegion getRegion() {
		return region;
	}

	public void setRegion(CRegion region) {
		this.region = region;
	}

     
}
