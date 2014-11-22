package points;

import com.mysql.management.MysqldResource;
import com.mysql.management.MysqldResourceI;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the life cycle of the embedded MySQL database.
 * This manager uses the MySQL Connector/MXJ and Mysql Connector/J JDBC driver to manage the
 * embedded MySQL DB instance.
 *
 * @see <a href="http://dev.mysql.com/doc/refman/5.1/en/connector-mxj.html">MySQL Connector/MXJ</a> for more details
 */
public class EmbeddedMysqlManager {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());


	private static EmbeddedMysqlManager ref;

	private MysqldResource mysqldResource;

	private String baseDatabaseDir = System.getProperty("java.io.tmpdir");

	private String databaseName = "points";

	private List<String> sqlCreationScripts = new ArrayList<String>();

	private String flywayLocations;

	private List<String> sqlDataScripts = new ArrayList<String>();

	private DatabaseClient dbClient=null;

	//-------------------------------------------------------------------------
	/**
	 * Starts the mysql database
	 * @return
	 * @throws java.sql.SQLException
	 * @throws java.io.IOException
	 */
	private static final String TASKLIST = "tasklist";

	private static final String KILL = "taskkill /F /IM ";

	private static final String MYSQL_PROCESS = "mysqld.exe";

	private final static String OS = System.getProperty("os.name").toLowerCase();

//	@PostConstruct
	public void init() throws Exception {
		synchronized (EmbeddedMysqlManager.class) {
			if (ref == null) {
				ref = this;
				ref.startDatabase();
			}
		}
	}

	@PreDestroy
	public void destroy() {
		synchronized (EmbeddedMysqlManager.class) {
			if (ref != null) {
				try {
					ref.shutdownDatabase();
				} finally {
					ref = null;
				}
			}
		}
	}

	public void startDatabase() throws SQLException, Exception {
		//problems on windows, sometimes the process remains even after the db was deleted. This should fix it. Didn't yet happened on linux.
		if (isWindows() && isProcessRunning(MYSQL_PROCESS)) {
			killProcess(MYSQL_PROCESS);
		}
        dbClient = DatabaseClient.get();
		if (logger.isDebugEnabled()) {
			logger.debug("=============== Starting Embedded MySQL using these parameters ===============");
			logger.debug("baseDatabaseDir : " + baseDatabaseDir);
			logger.debug("databaseName : " + databaseName);
			logger.debug("host : localhost (hardcoded)");
			logger.debug("url : " + dbClient.getUrl());
			logger.debug("username : " + dbClient.getUsername());
			logger.debug("password : " + dbClient.getPassword());
			logger.debug("=============================================================================");
		}

		File databaseDir = new File(new File(baseDatabaseDir), databaseName);

		mysqldResource = new MysqldResource(databaseDir);

		Map<String, String> database_options = new HashMap<String, String>();
		database_options.put(MysqldResourceI.PORT, dbClient.getPort());
		database_options.put(MysqldResourceI.INITIALIZE_USER, "true");
		database_options.put(MysqldResourceI.INITIALIZE_USER_NAME, dbClient.getUsername());
		database_options.put(MysqldResourceI.INITIALIZE_PASSWORD, dbClient.getPassword());

		mysqldResource.start("embedded-mysqld-thread-" + System.currentTimeMillis(), database_options);

		if (!mysqldResource.isRunning()) {
			throw new RuntimeException("MySQL did not start.");
		}

		logger.info("MySQL started successfully @ " + System.currentTimeMillis());
		dbClient.populateDb();
	}


	private static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	private static void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(KILL + serviceName);

	}

	private static boolean isProcessRunning(String serviceName) throws Exception {
		Process p = Runtime.getRuntime().exec(TASKLIST);
		boolean result = false;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains(serviceName)) {
					result = true;
					break;
				}
			}
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return result;

	}

	public void shutdownDatabase() {
		mysqldResource.shutdown();
		if (mysqldResource.isRunning() == false) {
			logger.info(">>>>>>>>>> DELETING MYSQL BASE DIR [" + mysqldResource.getBaseDir() + "] <<<<<<<<<<");
			try {
				FileUtils.forceDelete(mysqldResource.getBaseDir());
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @return the baseDatabaseDir
	 */
	public final String getBaseDatabaseDir() {
		return baseDatabaseDir;
	}

	public final void setBaseDatabaseDir(String baseDatabaseDir) {
		this.baseDatabaseDir = baseDatabaseDir;
	}

	public final String getDatabaseName() {
		return databaseName;
	}

	public final void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public final List<String> getSqlCreationScripts() {
		return sqlCreationScripts;
	}

	public void setSqlCreationScripts(List<String> sqlCreationScripts) {
		this.sqlCreationScripts = sqlCreationScripts;
	}

	public String getFlywayLocations() {
		return flywayLocations;
	}

	public void setFlywayLocations(String flywayLocations) {
		this.flywayLocations = flywayLocations;
	}

	public final List<String> getSqlDataScripts() {
		return sqlDataScripts;
	}

	public void setSqlDataScripts(List<String> sqlDataScripts) {
		this.sqlDataScripts = sqlDataScripts;
	}

	public DatabaseClient getDbClient() {
		return dbClient;
	}

	public void setDbClient(DatabaseClient dbClient) {
		this.dbClient = dbClient;
	}


}
