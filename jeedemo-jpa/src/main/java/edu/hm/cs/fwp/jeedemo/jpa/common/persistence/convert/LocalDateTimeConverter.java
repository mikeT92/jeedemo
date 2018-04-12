package edu.hm.cs.fwp.jeedemo.jpa.common.persistence.convert;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * {@code JPA Attribute Converter} to have support for {@code LocalDateTime}
 * attributes and PostgreSQL {@code TIMESTAMPTZ} columns.
 *
 * @author Michael Theis (msg)
 * @version 1.0
 * @since release 18.2
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		Timestamp result = null;
		if (attribute != null) {
			result = Timestamp.valueOf(attribute);
		}
		return result;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		LocalDateTime result = null;
		if (dbData != null) {
			result = dbData.toLocalDateTime();
		}
		return result;
	}
}
