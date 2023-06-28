package it.polito.tdp.nyc.model;

import java.util.List;
import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private NYCDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		super();
		this.dao = new NYCDao();
	}
	
	public void creaGrafo(String boro) {
		
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, this.dao.getVertici(boro));
		
		Map<String, NTASsid> mappaNTA = new HashMap<>();
		for(String s: this.dao.getVertici(boro)) {
			
		}
		//per gli archi faccio una query stupida perchè mi sembra davvero troppo un cancro
		
		//devo passare al getNTA del dao una mappa con gli nta per poter fare l'aggiunta dell ssid alla lista di 
		//quel nta
		
	}
	
	public List<String> getTendina(){
		return this.dao.getTendina();
	}

	public int getNumVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumArchi() {
		return this.grafo.edgeSet().size();
	}
	
	
}
