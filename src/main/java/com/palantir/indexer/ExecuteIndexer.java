package com.palantir.indexer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ExecuteIndexer extends Thread {
    private Logger log = LogManager.getLogger(this.getClass());
    private final File pathToIndex;


    public ExecuteIndexer(File pathToIndex) {
        this.pathToIndex = pathToIndex;
    }

    @Override
    public void run() {
        try {
            log.debug("Start indexer for path: " + pathToIndex.getAbsolutePath());
            Thread.sleep(2000l);
            log.debug("Done indexer for path: " + pathToIndex.getAbsolutePath());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
