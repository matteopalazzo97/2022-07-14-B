package it.polito.tdp.nyc.model;

import java.util.List;
import java.util.LinkedList;

public class NTASsid {
	
	private String NTA;
	private List<String> ListaSSID;
	
	public NTASsid(String nTA) {
		super();
		NTA = nTA;
		ListaSSID = new LinkedList<String>();
	}
	public String getNTA() {
		return NTA;
	}
	public void setNTA(String nTA) {
		NTA = nTA;
	}
	public List<String> getListaSSID() {
		return ListaSSID;
	}
	public void setListaSSID(List<String> listaSSID) {
		ListaSSID = listaSSID;
	}
	
	

}
