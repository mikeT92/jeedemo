package edu.hm.cs.fwp.jeedemo.jpa.common.persistence.convert;

import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * {@code JPA Attribute Converter} to have support for UUID attribute and
 * PostgreSQL UUID columns.
 * <p>
 * Actually, the implementation looks a little awkward (same java type and db
 * type) but this is the only way to convince the PostgreSQL JDBC driver to map
 * {@code UUID} attribute values to {@code UUID} db column values.
 * </p>
 *
 * @author Michael Theis (msg)
 * @version 1.0
 * @since release 18.2
 */
@Converter(autoApply = true)
public class UuidConverter implements AttributeConverter<UUID, UUID> {

	@Override
	public UUID convertToDatabaseColumn(UUID attribute) {
		return attribute;
	}

	@Override
	public UUID convertToEntityAttribute(UUID dbData) {
		return dbData;
	}

}
