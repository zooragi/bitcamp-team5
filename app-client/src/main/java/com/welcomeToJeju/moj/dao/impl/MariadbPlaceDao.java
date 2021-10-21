package com.welcomeToJeju.moj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Place;

public class MariadbPlaceDao implements PlaceDao{
	Connection con;
	
	public MariadbPlaceDao(Connection con) {
		this.con = con;
	}
	
	@Override
	public void insert(Place place) throws Exception {
		try(PreparedStatement stmt = con.prepareStatement(
				"Insert into jeju_place( theme_no, place_name, place_address, x_coord, y_coord)"
				+ " values(?,?,?,?,?)")){
			
			stmt.setInt(1, place.getThemeNo());
			stmt.setString(2, place.getStoreName());
			stmt.setString(3, place.getStoreAddress());
			stmt.setDouble(4, Double.parseDouble(place.getxCoord()));
			stmt.setDouble(5, Double.parseDouble(place.getyCoord()));
			
			if(stmt.executeUpdate() == 0) {
				throw new Exception("장소 데이터 저장 실패!");
			}
		}
	}
	
	@Override
	public List<Place> findByThemeNo(int themeNo) throws Exception{
		try(PreparedStatement stmt = con.prepareStatement(
				"select place_name, place_address, x_coord, y_coord from jeju_place where theme_no="+themeNo);
				ResultSet rs = stmt.executeQuery()){
			ArrayList<Place> list = new ArrayList<>();
			
			while(rs.next()) {
				Place place = new Place();
				
				place.setStoreName(rs.getString("place_name"));
				place.setStoreAddress(rs.getString("place_address"));
				place.setxCoord(rs.getString("x_coord"));
				place.setyCoord(rs.getString("y_coord"));
				
				list.add(place);
			}
			return list;
		}
	}

}
