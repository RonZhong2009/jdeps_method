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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

class SymbolRepoMogodb {
	Logger logger = LoggerFactory.getLogger(SymbolRepoMogodb.class);
	private MongoCollection<Document> collection;
	private MongoClient client = null;
	private String dbName = null;
  	private ObjectMapper objectMapper = new ObjectMapper();
  	
	public SymbolRepoMogodb() {
		this.collection = connect( "localhost", 27017, "javasymbolrepo", "default");
	}
	
	public SymbolRepoMogodb(String dblocation) {
		this.collection = connect(dblocation);
	}
	
	public SymbolRepoMogodb(String hostName, int port, String databaseName, String collectionName) {
		this.collection = connect( hostName, port, databaseName, collectionName);
	}
	
    private MongoCollection<Document> connect(
            String hostName, int port, String databaseName, String collectionName) {
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
    	String jsonStr = null;
    	try {
	    	jsonStr = objectMapper.writeValueAsString(sym);
	    	this.collection.insertOne(Document.parse(jsonStr));
    	}catch(Exception e) {
    		logger.error("insert {} got exception: {}",jsonStr, e.getMessage());
       		return false;
    	}
    	return true;
    }

    public Collection<Symbol> access(Symbol filtersym) {
    	//TODO: how to specify the conditions we want through the arguments?
    	return null;
    }

    public boolean updateOne(Symbol condition, Symbol updatevalues) {
    	//TODO: how to make up the update statement
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
    	
    	String words[] = dblocation.split(":");
    	String hostName = words[0];
    	int    port = Integer.valueOf(words[1]);
    	String databaseName = words[2];
    	String collectionName = words[3];
    	//TODO: should check the values correct or not here
    	tempCollection = connect( hostName, port, databaseName, collectionName);
    	logger.info("open the Mogodb hostname {} -port {}-databasename {}-collectionName {}", hostName,
    			port, databaseName, collectionName);
        return tempCollection;
    }
    
    public void addSymbolRecord(Document doc) {
    	collection.insertOne(doc);
    }

    //define what attributes will be stored into the mogodb for each symbol
    //location(filepath) - statement - slovedOrNot - DeclarePackage - DeclareClass - DeclareMethod - DeclareMethodBody - DeclareField -
	
    
    

}
