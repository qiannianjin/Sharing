package top.arexstorm.sharing.utils;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
	
	public static String toJSON(Object value) {
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		StringWriter writer = new StringWriter();
		try {
			om.writeValue(writer, value);
			return writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object fromJSON(String content, Class clazz) {
		ObjectMapper om = new ObjectMapper();
		try {
			Object readValue = om.readValue(content, clazz);
			return readValue;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		
		String fromJSON = (String) JSONUtils.fromJSON("\"GOOD BOY\"", String.class);
		System.out.println(fromJSON);
		
		
	}
}
