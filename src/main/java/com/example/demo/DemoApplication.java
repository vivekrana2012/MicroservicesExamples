package com.example.demo;

import java.util.Arrays;

import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/x-www-form-urlencoded")
	String login() {
		
		ObjectMapper mapper = new ObjectMapper();
	   
	    // Creating Credentials 
	    MongoCredential credential = MongoCredential.createCredential("app", "users", "app_password".toCharArray()); 
	    
	    // Creating a Mongo client 
	    MongoClient mongo = new MongoClient( new ServerAddress("localhost" , 27017 ), Arrays.asList(credential)); 
	     
	    // Accessing the database 
	    MongoDatabase database = mongo.getDatabase("users"); 
	    MongoCollection<Document> collection = database.getCollection("authInfo");
	    
	    // running find query
	    FindIterable<Document> cursor = collection.find(new BasicDBObject("username", "vivek"));
	    
		try {
			OneDocument response = mapper.readValue(cursor.first().toJson(), OneDocument.class);
			return response.getStatus();
			
		} catch (Exception e) {
			mongo.close();
			e.printStackTrace();
		}
		mongo.close();
		return null;
	}
}
