package points.user.federation;

import org.apache.log4j.Logger;
import org.keycloak.models.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by alex on 5/2/2015.
 */
public class DBUserFederationProvider implements UserFederationProvider {
    private static final Logger logger = Logger.getLogger(DBUserFederationProvider.class);

    private TableMapping tableMapping;
    private DataSource ds;

    private KeycloakSession session;

    public DBUserFederationProvider(KeycloakSession session,DataSource ds, TableMapping tableMapping) {
        this.tableMapping = tableMapping;
        this.ds=ds;
        this.session = session;
    }

    @Override
    public UserModel proxy(UserModel local) {
        return new DBWritableUserModelProxy(local, this);
    }

    @Override
    public boolean synchronizeRegistrations() {
        return true;
    }

    @Override
    public UserModel register(RealmModel realm, UserModel user) {
        StringBuffer hql = new StringBuffer("INSERT INTO " + tableMapping.getTableName() + " ( ");
        hql.append(tableMapping.getUsername());
        hql.append(") VALUES('");
        hql.append(user.getUsername());
        hql.append("');");
        logger.warn("federation: "+hql.toString());
        Connection con = null;
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(hql.toString());
        } catch (SQLException e) {
            logger.error("could not insert registered user :", e);
        }finally {
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.error("could not even close the non null connection!");
                }
            }
        }
        return user;
    }

    @Override
    public boolean removeUser(RealmModel realm, UserModel user) {
        return session.userStorage().removeUser(realm, user);
    }

    @Override
    public UserModel getUserByUsername(RealmModel realm, String username) {
        return null;//session.userStorage().addUser(realm, username);
    }

    @Override
    public UserModel getUserByEmail(RealmModel realm, String email) {
        return null;
    }

    @Override
    public List<UserModel> searchByAttributes(Map<String, String> attributes, RealmModel realm, int maxResults) {
        return null;
    }

    @Override
    public void preRemove(RealmModel realm) {

    }

    @Override
    public void preRemove(RealmModel realm, RoleModel role) {

    }

    @Override
    public boolean isValid(UserModel local) {
        return true;
    }

    @Override
    public Set<String> getSupportedCredentialTypes(UserModel user) {
        return null;
    }

    @Override
    public Set<String> getSupportedCredentialTypes() {
        return null;
    }

    @Override
    public boolean validCredentials(RealmModel realm, UserModel user, List<UserCredentialModel> input) {
        return false;
    }

    @Override
    public boolean validCredentials(RealmModel realm, UserModel user, UserCredentialModel... input) {
        return false;
    }

    @Override
    public CredentialValidationOutput validCredentials(RealmModel realmModel, UserCredentialModel userCredentialModel) {
        return null;
    }

    @Override
    public void close() {

    }

    public void updateEmail(String username, String email){
        StringBuffer sql = new StringBuffer("UPDATE " + tableMapping.getTableName() + " SET  ");
        sql.append(tableMapping.getEmail());
        sql.append("='");
        sql.append(email);
        sql.append("' WHERE ");
        sql.append(tableMapping.getUsername()).append("='").append(username).append("';");
        logger.warn("federation: " + sql.toString());
        Connection con = null;
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            logger.error("could not update user :", e);
        }finally {
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.error("could not even close the non null connection!");
                }
            }
        }
    }

    public void updateLastName(String username, String lastName){
        StringBuffer sql = new StringBuffer("UPDATE " + tableMapping.getTableName() + " SET  ");
        sql.append(tableMapping.getLastName());
        sql.append("='");
        sql.append(lastName);
        sql.append("' WHERE ");
        sql.append(tableMapping.getUsername()).append("='").append(username).append("';");
        logger.warn("federation: " + sql.toString());
        Connection con = null;
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            logger.error("could not update user :", e);
        }finally {
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.error("could not even close the non null connection!");
                }
            }
        }
    }

    public void updateFirstName(String username, String firstName){
        StringBuffer sql = new StringBuffer("UPDATE " + tableMapping.getTableName() + " SET  ");
        sql.append(tableMapping.getFirstName());
        sql.append("='");
        sql.append(firstName);
        sql.append("' WHERE ");
        sql.append(tableMapping.getUsername()).append("='").append(username).append("';");
        logger.warn("federation: " + sql.toString());
        Connection con = null;
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            logger.error("could not update user :", e);
        }finally {
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.error("could not even close the non null connection!");
                }
            }
        }
    }
}
