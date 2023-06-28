package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.nyc.model.Hotspot;
import it.polito.tdp.nyc.model.NTASsid;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getTendina(){
		String sql = "SELECT DISTINCT n.`Borough` "
				+ "FROM `nyc_wifi_hotspot_locations` n";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("Borough"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getVertici(String boro){
		String sql = "SELECT DISTINCT n.`NTAName` "
				+ "FROM `nyc_wifi_hotspot_locations` n "
				+ "WHERE n.`Borough`=?";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, boro);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("NTAName"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	/*
	 * nel while devo prendere nta e tutti i suoi ssid quindi la riga dopo nta1 ssid1
	 * può essere o nta2 ssidx o nta1 ssidx
	 * quindi devo tenere memorizzato l'ultimo nta e se nta = prec aggiungo ssid 
	 * alla sua lista di ssid se nta != prec creo nuovo oggetto 
	 */
	
	public List<NTASsid> getNTA(String boro){
		String sql = "SELECT DISTINCT n.`NTAName`, n.`SSID` "
				+ "FROM `nyc_wifi_hotspot_locations` n "
				+ "WHERE n.`Borough`=? "
				+ "ORDER BY n.`NTAName`";
		List<NTASsid> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, boro);
			ResultSet res = st.executeQuery();
			
			String prec = null;

			while (res.next()) {
				
				if(res.getString("NTAName").equals(prec)) {
					
				}
				if(!res.getString("NTAName").equals(prec)) {
					result.add(new NTASsid(res.getString("NTAName")));
				}
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	
}
