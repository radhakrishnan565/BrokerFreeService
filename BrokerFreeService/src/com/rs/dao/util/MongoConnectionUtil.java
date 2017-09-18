package com.rs.dao.util;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoConnectionUtil {

	public static MongoClient getClient(){
		MongoCredential credential = MongoCredential.createCredential("user", "database", "passwd".toCharArray());
	//	MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential));
		MongoClient mongoClient = new MongoClient("localhost");
		MongoDatabase db = mongoClient.getDatabase( "radhakrishnan" );
		MongoCollection collection = db.getCollection("dais_menu");
		FindIterable fi = collection.find();
		MongoCursor cursor = fi.iterator();
		System.out.println("Wooow mongo client connected successfully..........");
		while(cursor.hasNext()){
			Document document = (Document) cursor.next();
			System.out.println(document.toJson());
		}
		return mongoClient;
	}
}
