package com.kchange.api.store;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }


    public void save(String path,
                     String fileName,
                     Optional<Map<String, String>> metaDataMap,
                     InputStream is) {
        try {
            ObjectMetadata objMetaData = new ObjectMetadata();
            metaDataMap.ifPresent(map->{
                if (!map.isEmpty()) {
                    map.forEach(objMetaData::addUserMetadata);
                }
            });
            s3.putObject(path, fileName, is, objMetaData);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file to s3", e);
        }
    }


}
