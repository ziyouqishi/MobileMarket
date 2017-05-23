package com.css.utitls;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl=new ThreadLocal<>();
	
	/**
	 * 鑾峰彇鏁版嵁婧�
	 * @return 杩炴帴姹�
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 浠庡綋鍓嶇嚎绋嬩笂鑾峰彇杩炴帴
	 * @return 杩炴帴
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();
		if(conn==null){
			//绗竴娆¤幏鍙� 鍒涘缓涓�涓繛鎺� 鍜屽綋鍓嶇殑绾跨▼缁戝畾
			 conn=ds.getConnection();
			 
			 //缁戝畾
			 tl.set(conn);
		}
		return conn;
	}
	
	
	
	/**
	 * 閲婃斁璧勬簮
	 * 
	 * @param conn
	 *            杩炴帴
	 * @param st
	 *            璇彞鎵ц鑰�
	 * @param rs
	 *            缁撴灉闆�
	 */
	public static void closeResource(Connection conn, Statement st, ResultSet rs) {
		closeResource(st, rs);
		closeConn(conn);
	}
	
	 
	public static void closeResource(Statement st, ResultSet rs) {
			closeResultSet(rs);
			closeStatement(st);
	}

	/**
	 * 閲婃斁杩炴帴
	 * 
	 * @param conn
	 *            杩炴帴
	 */
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				//鍜屽綋鍓嶇殑绾跨▼瑙ｇ粦
				tl.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}

	}

	/**
	 * 閲婃斁璇彞鎵ц鑰�
	 * 
	 * @param st
	 *            璇彞鎵ц鑰�
	 */
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			st = null;
		}

	}

	/**
	 * 閲婃斁缁撴灉闆�
	 * 
	 * @param rs
	 *            缁撴灉闆�
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}

	}
	
	/**
	 * 寮�鍚簨鍔�
	 * @throws SQLException
	 */
	public static void startTransaction() throws SQLException{
		//鑾峰彇杩炴帴//寮�鍚簨鍔�
		getConnection().setAutoCommit(false);;
	}
	
	/**
	 * 浜嬪姟鎻愪氦
	 */
	public static void commitAndClose(){
		try {
			//鑾峰彇杩炴帴
			Connection conn = getConnection();
			//鎻愪氦浜嬪姟
			conn.commit();
			//閲婃斁璧勬簮
			conn.close();
			//瑙ｉ櫎缁戝畾
			tl.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 浜嬪姟鍥炴粴
	 */
	public static void rollbackAndClose(){
		try {
			//鑾峰彇杩炴帴
			Connection conn = getConnection();
			//浜嬪姟鍥炴粴
			conn.rollback();
			//閲婃斁璧勬簮
			conn.close();
			//瑙ｉ櫎缁戝畾
			tl.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
