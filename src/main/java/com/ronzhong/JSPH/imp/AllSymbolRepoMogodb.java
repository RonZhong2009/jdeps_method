package com.ronzhong.JSPH.imp;


import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

public class AllSymbolRepoMogodb {
	private MongoCollection<Document> collection;
	
	public void AllSymbolRepoMogodb() {
		collection = connect( "javasymbolrepo", "default", "localhost", 27017);
	}
	
	
	public void AllSymbolRepoMogodb(String databaseName, String collectionName,
            String hostName, int port) {
		collection = connect( databaseName, collectionName,hostName, port);
	}
	
    private MongoCollection<Document> connect(String databaseName, String collectionName,
            String hostName, int port) {
    	MongoCollection<Document> tempCollection = null;
        MongoClient client = MongoClients.create("mongodb://"+hostName+":"+ String.valueOf(port));
        MongoDatabase db = client.getDatabase(databaseName);
        tempCollection = db.getCollection(collectionName);
        System.out.println(collection);
        return tempCollection;
    }

    //define what attributes will be stored into the mogodb for each symbol
    //location(filepath) - statement - slovedOrNot - DeclarePackage - DeclareClass - DeclareMethod - DeclareMethodBody - DeclareField -
	
    
    

}
