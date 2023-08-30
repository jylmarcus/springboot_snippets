package snippets.springboot.springboot_snippets.repositories;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import snippets.springboot.springboot_snippets.utilities.Utils;

@Repository
public class S3Repository {
    
    @Autowired
    private AmazonS3 s3;

    public String upload(String contentType, InputStream is) {
        String id = Utils.generateID();
        String key = "directory/%s".formatted(id);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);

        PutObjectRequest putReq = new PutObjectRequest("<bucketName", key, is, metadata);
        putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);

        s3.putObject(putReq);

        return id;
    }

    public String getURL(String id) {
        String key = "directory/%s".formatted(id);
        return s3.getUrl("<bucketName>", key).toExternalForm();
    }
}
