package com.bnamericas.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bnamericas.model.Data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.bnamericas.repository.*;


public class BnAmericasDao implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8023415186234324503L;
	
	

	public static void validate(String backend, BnAmericasRepository BnAmericasRepository) {
		
		// iniciamos el procesamiento de la data 
		
		// consultamos el endpoint 
		System.out.println(backend);
		HttpGet get = new HttpGet(backend);
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		Gson gson = new Gson();
		try {
			HttpResponse response = httpClient.execute(get);
			
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			System.out.println(responseString);
			
			
			//Json parser | JsonElements
	        JsonParser jsonParser = new JsonParser();
	        //Parsear el json string a JsonObject, a JsonArray, a JsonPrimitive o a JsonNull
	        JsonElement element = jsonParser.parse(responseString);
	        //verificamos si es un json array
	        if (element.isJsonArray())
	        {
	            //parseamos el elemento a json array
	            JsonArray jsonArray = element.getAsJsonArray();
	            //lista de tipo objeto para almacenar tus valores
	            
	            //recorremos el json array
	            for (int i=0;i<jsonArray.size();i++)
	            {
	                //obtenemos los objetos de la posición i
	                JsonObject  objectJ = jsonArray.get(i).getAsJsonObject();
	                //modelo para asignar tus valores
	                Data object = new Data();
	                //Obtenemos los valores
	                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	                Date fecha = formato.parse(objectJ.get("date").getAsString());
	                
	                object.setDate(fecha);
	                
	                object.setTitle(objectJ.get("title").getAsString());
	                object.setExtra(objectJ.get("extra").getAsString());
	                //agregamos a la lista
	                BnAmericasRepository.save(object);
	               
	            }
	            //verificamos la cantidad de registros obtenidos y agregados a la lista
	            
	        }
			
			
			
			
			
			
			
			
			//System.out .println(data.toString());		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}


	 
}
