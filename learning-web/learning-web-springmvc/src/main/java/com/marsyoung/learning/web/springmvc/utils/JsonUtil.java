package com.marsyoung.learning.web.springmvc.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();

	static{
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static ObjectMapper getMapper() {
		return mapper;
	}

	/**
	 * objectToJson(java对象转json格式) (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param data
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String objectToJson(final Object data) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(data);
			return jsonString;
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * jsonToObject(json格式转java对象) (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param json
	 * @param typeReference
	 * @return Object
	 * @exception
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(final String json, TypeReference<?> typeReference) {
		try {
			return (T)mapper.readValue(json, typeReference);
		} catch (JsonParseException e) {
			throw new IllegalArgumentException(e);
		} catch (JsonMappingException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * jsonToObject(jsonNode格式转java对象) (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param jsonNode
	 * @param typeReference
	 * @return Object
	 * @exception
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(final JsonNode jsonNode, TypeReference<?> typeReference) {
		try {
			return (T)mapper.readValue(jsonNode, typeReference);
		} catch (JsonParseException e) {
			throw new IllegalArgumentException(e);
		} catch (JsonMappingException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
