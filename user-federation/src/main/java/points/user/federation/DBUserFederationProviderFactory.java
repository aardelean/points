package points.user.federation;

import org.apache.log4j.Logger;
import org.keycloak.Config;
import org.keycloak.models.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.*;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import java.util.Map;
/**
 * Created by alex on 5/2/2015.
 */
public class DBUserFederationProviderFactory implements UserFederationProviderFactory{

    public static final String PROVIDER_NAME = "mysql-db-userFederation";

    static final Set<String> configOptions = new HashSet<String>();

    private static final String DATASOURCE_KEY = "dataSource";
    private static final String TABLE_KEY = "target-table";
    private static final String USERNAME_KEY = "username";
    private static final String EMAIL_KEY = "email";
    private static final String FIRSTNAME_KEY = "firstName";
    private static final String LASTNAME_KEY = "lastName";

    private static final Logger logger = Logger.getLogger(DBUserFederationProviderFactory.class);
    static {
        configOptions.add(DATASOURCE_KEY);
        configOptions.add(TABLE_KEY);
        configOptions.add(USERNAME_KEY);
        configOptions.add(EMAIL_KEY);
        configOptions.add(FIRSTNAME_KEY);
        configOptions.add(LASTNAME_KEY);
    }

    private TableMapping tableMapping;

    private Config.Scope scopeConfig;

    private Map<String, String> config;

    private DataSource ds;

    @Override
    public DBUserFederationProvider create(KeycloakSession session) {
        lazyInit();
        return new DBUserFederationProvider(session, ds, tableMapping);
    }

    @Override
    public void close() {
    }

    @Override
    public UserFederationProvider getInstance(KeycloakSession session, UserFederationProviderModel model) {
        config = model.getConfig();
        return create(session);
    }

    @Override
    public Set<String> getConfigurationOptions() {
       return configOptions;
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    @Override
    public void syncAllUsers(KeycloakSessionFactory sessionFactory, final String realmId, final UserFederationProviderModel model) {
    }

    @Override
    public void syncChangedUsers(KeycloakSessionFactory sessionFactory, final String realmId, final UserFederationProviderModel model, Date lastSync) {
        syncAllUsers(sessionFactory, realmId, model);
    }

    @Override
    public void init(Config.Scope config) {
        this.scopeConfig = config;
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    private void lazyInit() {
        if (tableMapping == null) {
            synchronized (this) {
                if(tableMapping==null){
                    tableMapping = new TableMapping();
                    tableMapping.setTableName(config.get(TABLE_KEY));
                    tableMapping.setUsername(config.get(USERNAME_KEY));
                    tableMapping.setFirstName(config.get(FIRSTNAME_KEY));
                    tableMapping.setLastName(config.get(LASTNAME_KEY));
                    tableMapping.setEmail(config.get(EMAIL_KEY));
                }
                if(ds==null){
                    ds = getDataSource();
                }
                logger.warn("TABLE MAPPING :" + tableMapping.toString());
            }
        }
    }

    private DataSource getDataSource() {
        try {
            String dataSourceLookup = config.get(DATASOURCE_KEY);
            logger.info("DATA SOURCE LOOKUP: "+dataSourceLookup);
            if (dataSourceLookup != null) {
                DataSource dataSource = (DataSource) new InitialContext().lookup(dataSourceLookup);
                return dataSource;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
        throw new IllegalArgumentException("could not retrieve datasource from configuration of User Federation Keycloak scopeConfig");
    }
}
