package svy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class surveyDAO {
	private String driver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String user = "jspid";
	private String password = "jsppw";
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private static surveyDAO sdao;
	
	public static surveyDAO getInstance() {
		if(sdao == null) {
			sdao = new surveyDAO();
		}
		return sdao;
	}
	
	private surveyDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//getConnection
	
	public void insertSvy(surveyDTO survey) {
		getConnection();
		String sql = "insert into survey values(seqmy.nextval,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, survey.getName());
			ps.setString(2, survey.getCompany());
			ps.setString(3, survey.getEmail());
			ps.setString(4, survey.getSatisfaction());
			ps.setString(5, survey.getPart());
			ps.setString(6, survey.getHowto());
			ps.setInt(7, survey.getAgree());
			
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}//insertSvy
	
	public ArrayList<surveyDTO> surveyLists() {
		getConnection();
		String sql = "select * from survey order by no asc";
		ArrayList<surveyDTO> lists = new ArrayList<surveyDTO>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				surveyDTO sdto = new surveyDTO();
				sdto.setNo(rs.getInt("no"));
				sdto.setName(rs.getString("name"));
				sdto.setCompany(rs.getString("company"));
				sdto.setEmail(rs.getString("email"));
				sdto.setSatisfaction(rs.getString("satisfaction"));
				sdto.setHowto(rs.getString("howto"));
				sdto.setPart(rs.getString("part"));
				sdto.setAgree(rs.getInt("agree"));
				
				lists.add(sdto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}return lists;
	}//surveyLists
	
	public void deleteSurvey(String no) {
		getConnection();
		String sql = "delete from survey where no = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, no);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}//deleteSurvey
	
	public surveyDTO oneGetSurvey(String no) {
		getConnection();
		String sql = "select * from survey where no = ?";
		surveyDTO sdto = new surveyDTO();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				sdto.setNo(rs.getInt("no"));
				sdto.setName(rs.getString("name"));
				sdto.setCompany(rs.getString("company"));
				sdto.setEmail(rs.getString("email"));
				sdto.setSatisfaction(rs.getString("satisfaction"));
				sdto.setPart(rs.getString("part"));
				sdto.setHowto(rs.getString("howto"));
				sdto.setAgree(rs.getInt("agree"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}return sdto;
	}//oneGetSurvey
	
	public void updateSurvey(surveyDTO survey) {
		getConnection();
		String sql = "update survey set name = ?, company=?, email=?,satisfaction=?,part=?,howto=?,agree=? where no =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, survey.getName());
			ps.setString(2, survey.getCompany());
			ps.setString(3,  survey.getEmail());
			ps.setString(4, survey.getSatisfaction());
			ps.setString(5, survey.getPart());
			ps.setString(6,	survey.getHowto());
			ps.setInt(7, survey.getAgree());
			ps.setInt(8, survey.getNo());
			
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}//updateSurvey
}
