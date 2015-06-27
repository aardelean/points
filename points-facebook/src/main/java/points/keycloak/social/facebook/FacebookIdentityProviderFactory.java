/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package points.keycloak.social.facebook;

import org.keycloak.Config;
import org.keycloak.broker.oidc.OAuth2IdentityProviderConfig;
import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.social.SocialIdentityProviderFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Pedro Igor
 */
public class FacebookIdentityProviderFactory extends AbstractIdentityProviderFactory<FacebookIdentityProvider> implements SocialIdentityProviderFactory<FacebookIdentityProvider> {

    public static final String PROVIDER_ID = "facebook";

    private static final String CONNECTION_FACTORY = "java:/LocalFactory";
    private static final String QUEUE_NAME = "java:/jms/queue/UsersLogin";

    private TableMapping tableMapping = TableMapping.pointsInstance();

    private DataSource ds;

    private ConnectionFactory connectionFactory;

    private Destination queue;


    @Override
    public String getName() {
        return "Points-Facebook";
    }

    @Override
    public FacebookIdentityProvider create(IdentityProviderModel model) {
        lazyInit();
        return new FacebookIdentityProvider(new OAuth2IdentityProviderConfig(model), connectionFactory, queue);
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    private void lazyInit() {
        if(ds==null){
            ds = getDataSource(tableMapping.getDataSourceLookupName());
            // Gets the JNDI context
            try {
                Context jndiContext = new InitialContext();
                // Looks up the administered objects
                connectionFactory = (ConnectionFactory)jndiContext.lookup(CONNECTION_FACTORY);
                queue = (Destination) jndiContext.lookup(QUEUE_NAME);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    private DataSource getDataSource(String dataSourceLookup) {
        try {
            DataSource dataSource = (DataSource) new InitialContext().lookup(dataSourceLookup);
            return dataSource;
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }
}
