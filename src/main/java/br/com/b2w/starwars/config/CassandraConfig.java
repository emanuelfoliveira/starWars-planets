package br.com.b2w.starwars.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DataCenterReplication;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

/**
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
@Configuration
@EnableReactiveCassandraRepositories
@PropertySource(value = "classpath:application.properties")
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {

	@Value("${spring.data.cassandra.contactpoints}")
	private String contactPoints;

	@Value("${spring.data.cassandra.port}")
	private int port;

	@Value("${spring.data.cassandra.keyspacename}")
	private String keySpace;

	@Value("${spring.data.cassandra.basepackages}")
	private String basePackages;

	@Value("${spring.data.cassandra.username}")
	private String username;

	@Value("${spring.data.cassandra.password}")
	private String password;

	@Value("${spring.data.cassandra.script}")
	private String script;
	
	@Value("${spring.data.cassandra.tableplanet}")
	private String tablePlanet;

	@Bean
	@Override
	public CassandraCqlClusterFactoryBean cluster() {
		CassandraCqlClusterFactoryBean bean = new CassandraCqlClusterFactoryBean();
		bean.setKeyspaceCreations(getKeyspaceCreations());
		bean.setContactPoints(contactPoints);
		bean.setUsername(username);
		bean.setPassword(password);
		return bean;
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	@Override
	protected String getKeyspaceName() {
		return keySpace;
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { basePackages };
	}

	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		List<CreateKeyspaceSpecification> createKeyspaceSpecifications = new ArrayList<>();
		createKeyspaceSpecifications.add(getKeySpaceSpecification());
		return createKeyspaceSpecifications;
	}

	private CreateKeyspaceSpecification getKeySpaceSpecification() {
		DataCenterReplication dcr = DataCenterReplication.of("datacenter1", 3L);
		return CreateKeyspaceSpecification.createKeyspace(keySpace).ifNotExists().withNetworkReplication(dcr);
	}

	@Override
	protected List<String> getStartupScripts() {
		return Arrays.asList(script, tablePlanet);
	}
}