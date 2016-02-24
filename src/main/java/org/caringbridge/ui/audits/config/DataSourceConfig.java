package org.caringbridge.ui.audits.config;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.util.StringUtils;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class DataSourceConfig extends AbstractMongoConfiguration {
    /**
     * Logger for this class.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * The host where the mongo db instance is running.
     */
    @Value("${org.caringbridge.services.db.host}")
    private String dbHost;

    /**
     * The port number where the mongo db instance is running.
     */
    @Value(value = "${org.caringbridge.services.db.port}")
    private int dbPort;

    /**
     * The user for the mongo db instance.
     */
    @Value(value = "${org.caringbridge.services.db.user}")
    private String user;

    /**
     * The database name where the user authenticates to.
     */
    @Value(value = "${org.caringbridge.services.db.userdb}")
    private String userDatabase;

    /**
     * The password for the user of the mongo db instance.
     */
    @Value(value = "${org.caringbridge.services.db.pwd}")
    private String password;

	@Override
	@Value(value = "${org.caringbridge.services.db.db}")
	protected String getDatabaseName() {
		return "test";
	}

	@Override
	public Mongo mongo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Returns the MongoDbFactory object that will be in charge of driving the
     * connection objects for the MongoDb database in the app.
     * 
     * @return MongoDbFactory with the value
     * @throws Exception
     *             in case something bad happens
     */
    @Override
    public MongoDbFactory mongoDbFactory() throws Exception {
        ArrayList<MongoCredential> creds = new ArrayList<>();
        if (!StringUtils.isEmpty(user) || !StringUtils.isEmpty(password)) {
            MongoCredential credential = MongoCredential.createCredential(user, userDatabase, password.toCharArray());
            creds.add(credential);
        }
        LOG.info("Using mongo details for configuration host: {}, database: {}, user: {}", dbHost, userDatabase, user);
        int port = dbPort;
        ServerAddress seeds = new ServerAddress(dbHost, port);
        MongoClient client = new MongoClient(seeds, creds);

        SimpleMongoDbFactory factory = new SimpleMongoDbFactory(client, userDatabase);
        return factory;
    }
}
