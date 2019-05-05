package com.ronzhong.JSPH.imp;


import java.util.Arrays;
import java.util.Collection;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.client.ClientEndPoint;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

class SymbolRepoMogodb {
	Logger logger = LoggerFactory.getLogger(SymbolRepoMogodb.class);
	private MongoCollection<Document> collection;
	private MongoClient client = null;
	private String dbName = null;
	
	public SymbolRepoMogodb() {
		this.collection = connect( "javasymbolrepo", "default", "localhost", 27017);
	}
	
	public SymbolRepoMogodb(String dblocation) {
		this.collection = connect(dblocation);
	}
	
	public SymbolRepoMogodb(String databaseName, String collectionName,
            String hostName, int port) {
		this.collection = connect( databaseName, collectionName,hostName, port);
	}
	
    private MongoCollection<Document> connect(String databaseName, String collectionName,
            String hostName, int port) {
    	MongoCollection<Document> tempCollection = null;
        this.client = 
        		MongoClients.create(
        		        MongoClientSettings.builder()
        		                .applyToClusterSettings(builder ->
        		                        builder.hosts(Arrays.asList(new ServerAddress(hostName, port))))
        		                .build());
        this.dbName = databaseName;
        MongoDatabase db = client.getDatabase(databaseName);
        if(db != null) {
        	tempCollection = db.getCollection(collectionName);
        }else{
        	logger.error("No db found named {}", databaseName);
        }
        System.out.println(collection);
        return tempCollection;
    }
    
    public boolean insert(Symbol sym) {
    	String jsonStr = mapperObj.writeValueAsString(sym);
    	this.collection.insertOne(new Document().parse(jsonStr));
    	return true;
    }

    public Collection<Symbol> access(Symbol filtersym) {
    	//TODO: how to specify the conditions we want through the arguments?
    	return null;
    }

    public boolean updateOne(Symbol filtersym) {
    	//TODO: how to specify the conditions we want through the arguments?
    	return true;
    }

    public boolean updateMany(Symbol filtersym, Symbol target) {
    	//TODO: how to specify the conditions we want through the arguments?
    	return true;
    }

    public int getTotalNumber() {
    	return 0;
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
