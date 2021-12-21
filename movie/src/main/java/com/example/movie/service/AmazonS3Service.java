package com.example.movie.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;



@Log
@Service
@RequiredArgsConstructor
public class AmazonS3Service {

	//private HttpSession httpSession;

	@Value("${aws.s3.bucket}")
	private String bucket;


	@Value("${aws.s3.bucketURL}")
	private String bucketURL;

	private final AmazonS3 amazonS3;

	public void test() {
		
		//createFolder(bucket, "test");
		//deleteFile("images/56e93050-6e7a-421c-a121-2323d5c92520_detail_1598870_5_500.jpg");
	}
	
	//테스트용 메소드
	public String getUrlTest() {
		
		//폴더에 있는 파일의 이름을 다가져오는 메소드
		//getFileList(bucket,bucketURL);
		
		//실제 사용 시 디비에서 불러온 파일명으로 getFileURL 메소드 호출
		String fileName = bucketURL+"Pound_layer_cake.jpg";		
		return getFileURL(bucket, fileName);
	}
	
	public List<String> uploadFile(List<MultipartFile> multipartFiles)  {

		//String folderPath= httpSession.getServletContext().getRealPath("/temp");
		//File folder = new File(folderPath);			
		//if(folder.isDirectory() == false) {
		//	folder.mkdir();
		//}
		
		List<String> fileNameList = multipartFiles.stream().map(file -> {

			UUID uuid = UUID.randomUUID(); // 랜덤이름
			String fileName = bucketURL+uuid.toString()+"_"+file.getOriginalFilename();
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(file.getSize());
			objectMetadata.setContentType(file.getContentType());
			log.info(fileName);
		
			try (InputStream inputStream = file.getInputStream()){
				amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
						.withCannedAcl(CannedAccessControlList.PublicRead)
						);

			} catch (AmazonServiceException e) {
	            e.printStackTrace();
	        } catch (SdkClientException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	        	e.printStackTrace();
			}
			return fileName;
		}).collect(Collectors.toList());

		return fileNameList;
	}
	
	public void fileUpload(List<MultipartFile> multipartFiles) {
		
	}

	public void deleteFile(String fileName) {      
		try {
			amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
			log.info(String.format("[%s] deletion complete", fileName));

		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
	}
	
	  // 버킷 리스트를 가져오는 메서드이다.
    public List<Bucket> getBucketList() {
        return amazonS3.listBuckets();
    }
    // 버킷을 생성하는 메서드이다.
    public Bucket createBucket(String bucketName) {
        return amazonS3.createBucket(bucketName);
    }    
    // 폴더 생성 (폴더는 파일명 뒤에 "/"를 붙여야한다.)
    public void createFolder(String bucketName, String folderName) {
    	amazonS3.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
    }
    // 파일 한개URL
    public String getFileURL(String bucketName, String fileName) {
        log.info("넘어오는 파일명 : "+fileName);
        return amazonS3.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, fileName)).toString();
    }
    // 파일 리스트 URL
    public List<String> getFileList(String bucketName, String folder){
    	ObjectListing objectList =  amazonS3.listObjects(bucketName, folder);
    	List<String> url = new ArrayList<String>();
    	for (S3ObjectSummary obj : objectList.getObjectSummaries()) {
			log.info("getFileList  ======= "+ obj.getKey());
			url.add(getFileURL(bucketName, obj.getKey()));
		}    	
    	return url;
    }
    
 
	
}
