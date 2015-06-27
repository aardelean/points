package points.keycloak.social.facebook;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jboss.logging.Logger;
import org.keycloak.broker.provider.BrokeredIdentityContext;

/**
 * Created by alex on 5/10/2015.
 */
public class UserDao {
    private static final Logger logger = Logger.getLogger(UserDao.class);

    private DataSource ds;

    private TableMapping tableMapping=TableMapping.pointsInstance();

    public UserDao(DataSource ds) {
        this.ds = ds;
    }

    public void saveUser(BrokeredIdentityContext user){
        StringBuffer hql = new StringBuffer("SELECT ")
                .append(tableMapping.getEmail()).append(",")
                .append(tableMapping.getFirstName()).append(",")
                .append(tableMapping.getLastName())
                .append(" FROM ").append(tableMapping.getTableName())
                .append(" WHERE ").append(tableMapping.getEmail())
                .append("='").append(user.getEmail()).append("'");
        Connection con = null;
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(hql.toString());
            if(resultSet.next()){
                String firstName = resultSet.getString(tableMapping.getFirstName());
                String lastName = resultSet.getString(tableMapping.getLastName());
                if(firstName!=null && !firstName.equals(user.getFirstName())){
                    updateFirstName(user.getEmail(), firstName);
                }
                if(lastName!=null && lastName.equals(user.getLastName())){
                    updateLastName(user.getEmail(), lastName);
                }
            }else{
                insert(user);
            }
        }catch (SQLException e) {
                logger.error("could not retrieve user :", e);
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

    private void insert(BrokeredIdentityContext user){
        StringBuffer hql = new StringBuffer("INSERT INTO")
            .append(" ").append(tableMapping.getTableName())
            .append(" ( ")
            .append(tableMapping.getEmail()).append(", ")
            .append(tableMapping.getFirstName()).append(", ")
            .append(tableMapping.getLastName())
            .append(") VALUES(")
            .append("'").append(user.getEmail()).append("'").append(",")
            .append("'").append(user.getFirstName()).append("'").append(",")
            .append("'").append(user.getLastName()).append("'")
            .append(");");
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
    }

    private void updateLastName(String email, String lastName){
        StringBuffer sql = new StringBuffer("UPDATE " + tableMapping.getTableName() + " SET  ");
        sql.append(tableMapping.getLastName());
        sql.append("='");
        sql.append(lastName);
        sql.append("' WHERE ");
        sql.append(tableMapping.getEmail()).append("='").append(email).append("';");
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

    private void updateFirstName(String email, String firstName){
        StringBuffer sql = new StringBuffer("UPDATE " + tableMapping.getTableName() + " SET  ");
        sql.append(tableMapping.getFirstName());
        sql.append("='");
        sql.append(firstName);
        sql.append("' WHERE ");
        sql.append(tableMapping.getEmail()).append("='").append(email).append("';");
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
