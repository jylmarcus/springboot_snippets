package snippets.springboot.springboot_snippets.controllers;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import snippets.springboot.springboot_snippets.repositories.S3Repository;

@RestController
@RequestMapping
public class UploadController {
    
    @Autowired
    private S3Repository s3Repo;

    @PostMapping(path="/directory", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postFile(@RequestPart MultipartFile file) {
        try{ 
            String contentType = file.getContentType();
            InputStream is = file.getInputStream();
            String id = s3Repo.upload(contentType, is);
            JsonObject resp = Json.createObjectBuilder()
                .add("id", id)
                .build();
            return ResponseEntity.ok(resp.toString());
        } catch (Exception ex) {
            JsonObject resp = Json.createObjectBuilder()
                .add("error", ex.getMessage())
                .build();
            return ResponseEntity.status(500).body(resp.toString());
        }
    }
}
