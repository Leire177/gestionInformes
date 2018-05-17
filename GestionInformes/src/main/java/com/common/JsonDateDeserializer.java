package com.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class JsonDateDeserializer extends JsonDeserializer<Date> {
	// ISO 8601
	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
		Date date = null;
		try {
			date = dateFormat.parse(jsonParser.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}