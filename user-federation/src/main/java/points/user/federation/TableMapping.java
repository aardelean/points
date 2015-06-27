package points.user.federation;

/**
 * Created by alex on 5/3/2015.
 */
public class TableMapping {
    private String tableName;
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }


    @Override
    public String toString() {
        return "TableMapping{" +
                "tableName='" + tableName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
