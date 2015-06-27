package points.keycloak.social.facebook;

/**
 * Created by alex on 5/3/2015.
 */
public class TableMapping {
    private String tableName;
    private String firstName;
    private String lastName;
    private String email;

    private String dataSourceLookupName;

    private static TableMapping pointsInstance;

    public static TableMapping pointsInstance(){
        if(pointsInstance==null){
            pointsInstance = new TableMapping();
            pointsInstance.setEmail("email");
            pointsInstance.setFirstName("firstName");
            pointsInstance.setLastName("lastName");
            pointsInstance.setTableName("user");
            pointsInstance.setDataSourceLookupName("java:jboss/datasources/pointsDS");

        }
        return pointsInstance;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTableName() {

        return tableName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDataSourceLookupName() {
        return dataSourceLookupName;
    }

    public void setDataSourceLookupName(String dataSourceLookupName) {
        this.dataSourceLookupName = dataSourceLookupName;
    }

    @Override
    public String toString() {
        return "TableMapping{" +
                "tableName='" + tableName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
