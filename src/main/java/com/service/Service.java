package com.service;

import com.palantir.indexer.ExecuteIndexer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static spark.Spark.*;

public class Service {
    private static Logger log = LogManager.getLogger("Service");

    public static void main(String[] args) {
        get("/index", (request, response) -> {
            String path = request.queryParams("path");
            log.debug("Path: " + path);
            
            if(path==null || path.isEmpty()){
                halt(400, "You must provide query param path=/path/to");
            } else {
                index(path);
            }
            return "Start index: " + path;
        });
    }

    private static void index(String path) {
        ExecuteIndexer executeIndexer = new ExecuteIndexer(new File(path));
        executeIndexer.start();
    }
}
