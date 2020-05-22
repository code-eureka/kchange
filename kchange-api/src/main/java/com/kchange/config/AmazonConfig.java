package com.kchange.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ4X5JA2TZA3UOGTQ","jUYHy6MWE0C5fLq5J5xR+nd59DOtOYncV1lwfzze");
        return AmazonS3ClientBuilder
                .standard().withRegion(Regions.US_WEST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
    }
}
