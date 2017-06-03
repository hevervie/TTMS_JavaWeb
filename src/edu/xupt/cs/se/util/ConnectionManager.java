package edu.xupt.cs.se.util;

/**
 * Created by zhoupan on 17-5-30.
 */


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class ConnectionManager {
    private static ConnectionManager instance;
    private static ComboPooledDataSource ds;

    // 初始化,只执行一次
    static {
        ResourceBundle rb = ResourceBundle.getBundle("c3p0");
        ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(rb.getString("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ds.setJdbcUrl(rb.getString("url"));
        ds.setUser(rb.getString("username"));
        ds.setPassword(rb.getString("password"));
    }

    /**
     * 获取数据库实例
     *
     * @return 连接对象ConnectionManager
     */
    public synchronized static final ConnectionManager getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接对象Connection
     */
    public synchronized final Connection getConnection() {
        try {
            ds.getNumBusyConnections();
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接
     *
     * @return void
     */
    public static void close(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放数据库资源
     *
     * @return void
     */
    @Override
    protected void finalize() throws Throwable {
        // 关闭datasource
        DataSources.destroy(ds);
        super.finalize();
    }
}
