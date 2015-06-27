package points.user.federation;

import org.keycloak.models.UserModel;
import org.keycloak.models.utils.UserModelDelegate;


/**
 * Proxy that will synchronize password updates
 *
 */
public class DBWritableUserModelProxy extends UserModelDelegate {
    protected DBUserFederationProvider provider;

    public DBWritableUserModelProxy(UserModel delegate, DBUserFederationProvider provider) {
        super(delegate);
        this.provider = provider;
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
        provider.updateEmail(getUsername(), email);
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
        provider.updateLastName(getUsername(), lastName);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
        provider.updateFirstName(getUsername(), firstName);
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public void setAttribute(String name, String value) {
        super.setAttribute(name, value);
    }
}
