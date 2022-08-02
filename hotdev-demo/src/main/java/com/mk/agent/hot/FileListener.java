package com.mk.agent.hot;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * @Desc TODO
 * @Author zhxy
 * @Version V1.0
 **/
public class FileListener extends FileAlterationListenerAdaptor {

    @Override
    public void onFileChange(File file) {
        if (file.getName().indexOf(".class") != -1) {
            try {
                HotAgent.reloadByFullName(HotAgent.pathToName.get(file.getAbsolutePath()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
