package com.file.indexer;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class MonitorTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testMonitor() throws Exception {
        long delay = 1000l;
        String FOLDER = "/tmp/";
        Monitor monitor = new Monitor(FOLDER, delay);
    }
}