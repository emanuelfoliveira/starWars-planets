package br.com.b2w.starwars.model;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity Planet - Cassandra Table
 * 
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("Planet")
public class Planet {

	@PrimaryKey
	@CassandraType(type = DataType.Name.UUID)
	private UUID id;

	@NotEmpty(message = "Name cannot be null")
	private String name;

	@NotEmpty(message = "Climate cannot be null")
	private String climate;

	@NotEmpty(message = "Terrain cannot be null")
	private String terrain;
	
	private Integer movieAppearance;
}
