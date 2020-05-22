package com.kchange.api.bucket;

public enum BucketName {

    PROFILE_IMAGE("kchange-image-upload");

    private final String bucketName;

    BucketName(String s) {
        this.bucketName = s;
    }
}
