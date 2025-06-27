package shop.main;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MainDAO {
	private static MainDAO instance = new MainDAO();

	// DAO를 가져오는 역할
	public static MainDAO getInstance() {
		return instance;
	}

	// 객체 생성
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// DAO 생성자
	private MainDAO() {
	}

	// 커넥션 연결
	private Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.219.198:1521:orcl";
			conn = DriverManager.getConnection(url, "team02", "1234");
		} catch (Exception e) {
			System.out.print(e.toString());
			e.printStackTrace();
		}

		return conn;
	}

	// 연결을 끊어주는 메소드
	private void endConnection() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<GoodsDTO> getNewGoods() { // 신상품 목록의 상품번호,상품이름을 가져오는 메소드
		List<GoodsDTO> goodsList = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select gnum,gname from goods ORDER BY reg_date ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GoodsDTO dto = new GoodsDTO();
				dto.setGnum(rs.getInt("gnum"));
				dto.setGname(rs.getString("gname"));
				goodsList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			endConnection();
		}
		return goodsList;
	}

	public List<GoodsDTO> getGoods(int gnum) { // 상품의 모든정보를 가져오는 메소드
		List<GoodsDTO> list = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select * from goods where gnum=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gnum);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				GoodsDTO dto = new GoodsDTO();
				dto.setGnum(rs.getInt("gnum"));
				dto.setCanum(rs.getInt("canum"));
				dto.setGname(rs.getString("gname"));
				dto.setGprice(rs.getInt("gprice"));
				dto.setGcontent(rs.getString("gcontent"));
				dto.setGinum(rs.getInt("ginum"));
				dto.setDiscount(rs.getInt("discount"));
				dto.setGread(rs.getInt("gread"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			endConnection();
		}
		return list;
	}

	public List<GoodsDTO> search(String key) { // 서치실행 메소드
		List<GoodsDTO> list = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select * from goods where gname like=? or gcontent like=?";
			pstmt = conn.prepareStatement(sql);
			for (int i = 1; i <= 2; i++)
				pstmt.setString(i, "%" + key + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				GoodsDTO dto = new GoodsDTO();
				dto.setGnum(rs.getInt("gnum"));
				dto.setCanum(rs.getInt("canum"));
				dto.setGname(rs.getString("gname"));
				dto.setGprice(rs.getInt("gprice"));
				dto.setGcontent(rs.getString("gcontent"));
				dto.setGinum(rs.getInt("ginum"));
				dto.setDiscount(rs.getInt("discount"));
				dto.setGread(rs.getInt("gread"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			endConnection();
		}
		return list;
	}

	public List<CategoryDTO> getCate() { // 카테고리명들을 리스트로 받아오는 작업
		List<CategoryDTO> list = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select * from category";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCanum(rs.getInt("canum"));
				dto.setCaname(rs.getString("caname"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			endConnection();
		}
		return list;
	}
	public List<GoodsOptionDTO> getGoodsOption() { // 상품의 옵션정보를 가져오는 메소드
		List<GoodsOptionDTO> list = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select * from goods_option";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				GoodsOptionDTO dto = new GoodsOptionDTO();
				dto.setGonum(rs.getInt("gonum"));
				dto.setGnum(rs.getInt("gnum"));
				dto.setCanum(rs.getInt("canum"));
				dto.setGinum(rs.getInt("ginum"));
				dto.setGocolor(rs.getString("gocolor"));
				dto.setGosize(rs.getString("gosize"));
				dto.setGocount(rs.getString("gocount"));  
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			endConnection();
		}
		return list;
	}
	public void increaseGread(int gnum) {  //조회수 증가
		try {
			conn = getConnection();
			String sql = "update goods set gread=gread+1 where gnum=? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, gnum);
			rs = pstmt.executeQuery();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			endConnection();
		}
	}
}
