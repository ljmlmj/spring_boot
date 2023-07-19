package com.javalab.product.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javalab.product.dto.UploadResultDTO;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

	/*
	 * @Value
	 *  - application.properties에 설정한 값 읽어오기
	 *  - ${com.javalab.upload.path}
	 *    - com.javalab.upload.path=C:\\filetest
	 */
    @Value("${com.javalab.upload.path}")
    private String uploadPath;

    /*
     * Ajax로 요청된 파일 업로드 처리 메소드
     * 반환타입 : ResponseEntity<List<UploadResultDTO>>
     *  - 첨부파일 갯수 만큼 처리 결과가 섬네일 이미지까지 포함해서 만들어짐.
     */
    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        /*
         * 첨부 파일 갯수만큼 반복
         */
        for (MultipartFile uploadFile: uploadFiles) {
        	// 첨부파일이 이미지 인지 체크
            if(uploadFile.getContentType().startsWith("image") == false) {
                log.warn("이미지 파일만 첨부할 수 있습니다.");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            //실제 파일 이름만 추출하기 위해서 경로 맨뒤의 "\" 뒤의 문자열 추출
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);
            
            //날짜 폴더 생성, 파일을 년/월/일 폴더에 나눠서 보관하게 됨.
            String folderPath = makeFolder();
            log.info("folderPath: " + folderPath);
            
            /*
             * UUID : 중복되지 않는 번호 생성 
             *  - 중복되지 않는 고유한 식별자(UUID)를 생성합니다. 이것은 파일 이름에 추가되어, 
             *    같은 이름의 파일을 업로드할 때 파일이 덮어씌워지는 것을 방지함.
             */
            String uuid = UUID.randomUUID().toString();

            /*
             * savePath
             *  - 저장될 파일의 전체 경로(폴더)와 파일명
             *  - File.separator : 윈도우즈("\"), 리눅스,맥("/") 
             *  - UUID와 저장할 파일 이름 중간에 "_"를 이용해서 구분
             *  - 절대 중복되지 않는 파일 이름이 만들어짐.
             */
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid +"_" + fileName;
            Path savePath = Paths.get(saveName);

            try {
                //위에서 만들어진 경로+파일명으로 서버에 업로드(원본 파일 저장)
                uploadFile.transferTo(savePath);

                //섬네일 파일명 생성(중간에 s_로 시작하도록)
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator
                        +"s_" + uuid +"_" + fileName;
                
                //섬네일 파일 객체 생성
                File thumbnailFile = new File(thumbnailSaveName);
                
                //섬네일 생성 - 원본 파일과 섬네일 파일 객체를 입력으로 받아, 지정된 크기의 섬네일을 생성
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile,100,100 );
                
                /*
                 * 결과 정보 추가: UploadResultDTO 객체를 생성하여 파일 이름, UUID,
                 * 폴더 경로를 저장하고, 이를 결과 목록에 추가.
                 * 이걸 화면으로 보내서 섬네일로 보여줘야 함.
                 */
                resultDTOList.add(new UploadResultDTO(fileName,uuid,folderPath));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }//end for
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }


    /**
     * 파일 저장 경로를 년/월/일 단위로 만들어주는 메소드
     */
    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath =  str.replace("//", File.separator);

        // make folder --------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName =  URLDecoder.decode(fileName,"UTF-8");

            log.info("fileName: " + srcFileName);

            File file = new File(uploadPath +File.separator+ srcFileName);

            log.info("file: " + file);

            HttpHeaders header = new HttpHeaders();

            //MIME타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName){

        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName,"UTF-8");
            File file = new File(uploadPath +File.separator+ srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(), "s_" + file.getName());

            result = thumbnail.delete();

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
