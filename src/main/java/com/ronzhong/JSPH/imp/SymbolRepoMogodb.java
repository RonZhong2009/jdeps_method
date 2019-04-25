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

class SymbolRepoMogodb {
	private MongoCollection<Document> collection;
	
	public SymbolRepoMogodb() {
		collection = connect( "javasymbolrepo", "default", "localhost", 27017);
	}
	
	public SymbolRepoMogodb(String dblocation) {
		collection = connect(dblocation);
	}
	
	
	public SymbolRepoMogodb(String databaseName, String collectionName,
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
    
    private MongoCollection<Document> connect(String dblocation) {
    	MongoCollection<Document> tempCollection = null;
    	//TODO: extract the hostName and port number from dblocation string
    	//        MongoClient client = MongoClients.create("mongodb://"+hostName+":"+ String.valueOf(port));

        //TODO: extract the databaseName and collectionName from dblocation string
        //        MongoDatabase db = client.getDatabase(databaseName);
        //        tempCollection = db.getCollection(collectionName);
        System.out.println(collection);
        return tempCollection;
    }
    
    public void addSymbolRecord(Document doc) {
    	collection.insertOne(doc);
    }

    //define what attributes will be stored into the mogodb for each symbol
    //location(filepath) - statement - slovedOrNot - DeclarePackage - DeclareClass - DeclareMethod - DeclareMethodBody - DeclareField -
	
    
    

}
