package com.palantir.indexer;

import org.apache.commons.vfs2.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Listener implements FileListener {
    private Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void fileCreated(FileChangeEvent event) throws Exception {
        index("created", event);
    }

    @Override
    public void fileChanged(FileChangeEvent event) throws Exception {
        index("changed", event);
    }

    @Override
    public void fileDeleted(FileChangeEvent event) throws Exception {
        FileType type = event.getFile().getType();
        if (FileType.FILE == type) {
            log.info("File deleted: " + event.getFile().getName() + " ignoring this...");
        }
    }

    private void index(String eventType, FileChangeEvent event) throws IOException, InterruptedException {
        FileObject fileObject = event.getFile();
        if (FileType.FILE == fileObject.getType() && fileObject.isReadable() /*&& !fileObject.isHidden()*/) {
            log.info("File " + eventType + " file: " + event.getFile().getName());
            File path = new File(fileObject.getName().getPath());
            ExecuteIndexer executeIndexer = new ExecuteIndexer(path);
            executeIndexer.start();
        }
    }
}
