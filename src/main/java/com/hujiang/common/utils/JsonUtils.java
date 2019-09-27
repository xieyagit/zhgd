package com.hujiang.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils
{
    private final static ObjectMapper mapper = new ObjectMapper();
    /**
     * json得到对象
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static <T> T parse( String jsonString, Class<T> pojoClass ) throws JsonParseException, JsonMappingException, IOException
    {
        T pojo = null;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        pojo = mapper.readValue( jsonString, pojoClass );

        return pojo;
    }

    /**
     * json得到对象集合
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> List<T> toList( String json, Class<T> cls ) throws JsonParseException, JsonMappingException, IOException
    {
        ArrayList<T> mList = new ArrayList<T>();
        List array = mapper.readValue( json, List.class );
        for ( int i = 0; i < array.size(); i++ )
        {
            if(array.get( i ).getClass().equals(cls)) {
                mList.add( (T)array.get( i ) );
            }

            else {
                mList.add(mapper.readValue(String.valueOf(array.get(i)), cls));
            }
        }

        return mList;
    }

    /**
     * 获取请求体中的Json
     */
    public static byte[] readBytes( InputStream is, int contentLen ) throws IOException
    {
        if ( contentLen > 0 )
        {
            int readLen = 0;
            int readLengthThisTime = 0;
            byte[] message = new byte[contentLen];

            while ( readLen != contentLen )
            {
                readLengthThisTime = is.read( message, readLen, contentLen - readLen );
                if ( readLengthThisTime == -1 )
                {
                    break;
                }
                readLen += readLengthThisTime;
            }

            return message;

        }
        return new byte[] {};
    }

    /**
     * obj to json
     */
    public static String toJson( Object src )
    {
        try {
            return mapper.writeValueAsString( src );
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

    public static <T, S> T convert(S src, Class<T> pojoClass) throws JsonParseException, JsonMappingException, IOException
    {
        String json = toJson(src);
        T target = parse(json, pojoClass);

        return target;
    }
}