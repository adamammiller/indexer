package com.file.indexer;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;

import java.io.File;

public class Monitor {

    public Monitor(String pathToMonitor, long delay) throws FileSystemException, InterruptedException {
        File folder = new File(pathToMonitor);
        if (!folder.exists()) {
            // Test to see if monitored folder exists
            throw new RuntimeException("Directory not found: " + pathToMonitor);
        }
        FileSystemManager fsManager = VFS.getManager();
        FileObject listendir = fsManager.resolveFile(pathToMonitor);

        DefaultFileMonitor fm = new DefaultFileMonitor(new Listener());
        fm.setDelay(delay);
        fm.setRecursive(true);
        fm.addFile(listendir);
        fm.start();

        synchronized (fm) {
            fm.wait();
        }
    }
}
