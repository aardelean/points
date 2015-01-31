package points;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

/**
 * A simple client to access the database with plain old sql in integration tests.
 * Contains hardcoded information about the embedded test-database.
 *
 * @author Alexandru Ardelean <alexandru.ardelean@amiando.com>
 * @author Jochen Rieß <jochen.riess@amiando.com>
 * @since 23.05.13
 */

public class DatabaseClient {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private String driver;

	private String url;

	private String username;

	private String password;

    private String creationSql;

    private String dataSql;

	/**
	 * No need until now to externalize this in a property file
	 */
	private String port = "13306";

	private MysqlConnectionPoolDataSource datasource = null;

    private static DatabaseClient ref;


	/**
	 * Gets a {@link javax.sql.DataSource} for the embedded DB managed by this manager
	 * @return
	 */

    public DatabaseClient(){

    }

    public static DatabaseClient get(){
        if(ref==null){
            ref = new DatabaseClient();
            ref.autoSetup();
        }
        return ref;
    }

	public DataSource getDatasource() {

		if (datasource == null) {
			datasource = new MysqlConnectionPoolDataSource();
			datasource.setCreateDatabaseIfNotExist(true);
			String url = this.url
					+ "?createDatabaseIfNotExist=true&sessionVariables=FOREIGN_KEY_CHECKS=0&useUnicode=true&connectionCollation=utf8_general_ci&characterSetResults=utf8&characterEncoding=UTF-8&autoCommit=false&autoReconnect=true";
			datasource.setUrl(url);
			datasource.setUser(username);
			datasource.setPassword(password);
		}
		return datasource;
	}

    public void populateDb(){
        try {
            run(creationSql);
            run(dataSql);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void run(String fileName) throws SQLException, IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        InputStream sqlStream = DatabaseClient.class.getClassLoader().getResourceAsStream(fileName);
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            List<String> sqlFragments = createSqlfragments(sqlStream);
            for (String toRun : sqlFragments) {
                if (toRun.length() > 0) {
                    statement.execute(toRun);
                }
            }
        }
        finally {
            if(sqlStream!=null){
                IOUtils.closeQuietly(sqlStream);
            }
            if(statement!=null){
                statement.close();
            }

        }
    }

    private static List<String> createSqlfragments(InputStream sqlStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(sqlStream));

        List<String> ret = new ArrayList<String>();
        String line;
        StringBuilder script = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (line.equals("/")) {
                ret.add(removeMultilineComments(script));
                script = new StringBuilder();
            } else {
                //strip comments
                final int indexComment = line.indexOf("--");
                String lineWithoutComments = (indexComment != -1) ? line.substring(0, indexComment) : line;
                script.append(lineWithoutComments).append(" ");
                if(script.toString().trim().endsWith(";")){
                    if (script.length() > 0) {
                        ret.add(removeMultilineComments(script));
                        script = new StringBuilder();
                    }
                }
            }
        }

        return ret;
    }

    private static String removeMultilineComments(StringBuilder script) {
        return script.toString().replaceAll("/\\*(.*?)\\*/", "").trim();
    }

	public Map<String, Object> fetchFromDb(String table, Long id) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, IOException {
		Connection conn = null;
		Map<String, Object> row = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			statement = conn.prepareStatement("select * from " + table + " where id=" + id);
			resultSet = statement.executeQuery();

			ResultSetMetaData md = resultSet.getMetaData();
			int columns = md.getColumnCount();
			while (resultSet.next()) {
				row = new HashMap<String, Object>(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), resultSet.getObject(i));
				}
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return row;
	}

	private Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public Map<String, Object> getLastFromDb(String table) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, IOException {
		Connection conn = null;
		Map<String, Object> row = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			statement = conn.prepareStatement("select * from " + table + " order by id desc limit 1");
			resultSet = statement.executeQuery();

			ResultSetMetaData md = resultSet.getMetaData();
			int columns = md.getColumnCount();
			while (resultSet.next()) {
				row = new HashMap<String, Object>(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), resultSet.getObject(i));
				}
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return row;
	}

	public Map<String, Object> getFirstFromDb(String table) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, IOException {
		Connection conn = null;
		Map<String, Object> row = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			statement = conn.prepareStatement("select * from " + table + " order by id asc limit 1");
			resultSet = statement.executeQuery();

			ResultSetMetaData md = resultSet.getMetaData();
			int columns = md.getColumnCount();
			while (resultSet.next()) {
				row = new HashMap<String, Object>(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), resultSet.getObject(i));
				}
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return row;
	}

	public void truncateDabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			statement = conn.prepareStatement("SELECT Concat('TRUNCATE TABLE ', TABLE_NAME, ';') FROM INFORMATION_SCHEMA.TABLES");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				try {
					statement = conn.prepareStatement(resultSet.getString(1));
					statement.execute();
				} catch (Exception e) {

				}
			}

		} finally {
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void update(String script) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			statement = conn.prepareStatement(script);
			statement.executeUpdate();

		} finally {
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public long count(String table) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		Connection conn = null;
		PreparedStatement statement = null;
		long result = 0;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			statement = conn.prepareStatement("select count(id) from " + table);
			resultSet = statement.executeQuery();
			resultSet.next();
			result = (Long) resultSet.getObject(1);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return result;
	}

	/**
	 * if this class is used outside of the spring context, we still need to make use of it and configure it.
	 */
	public void autoSetup() {
		ResourceBundle bundle = ResourceBundle.getBundle("database-sql");
		setDriver(bundle.getString("database.driver"));
		setUrl(bundle.getString("database.url"));
		setPassword(bundle.getString("database.password"));
		setUsername(bundle.getString("database.username"));
        setCreationSql(bundle.getString("database.sqlCreation"));
        setDataSql(bundle.getString("database.data"));

	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

    public String getCreationSql() {
        return creationSql;
    }

    public void setCreationSql(String creationSql) {
        this.creationSql = creationSql;
    }

    public String getDataSql() {
        return dataSql;
    }

    public void setDataSql(String dataSql) {
        this.dataSql = dataSql;
    }
}
