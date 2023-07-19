[프로젝트 특이사항]

1. file upload
2. Spring Data Jpa
3. 데이터베이스 처리까지 완료


[주요 의존성]

   	// 썸네일 관련
	implementation group: 'org.imgscalr', name: 'imgscalr-lib', version: '4.2'
	implementation group: 'net.coobird', name: 'thumbnailator', version: '0.4.17'
	
	// 타임리프 레이아웃 관련 의존성
	implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect' 	
	
[데이터베이스]

1. 데이터베이스명 : fileupload
2. application.properties 파일에 다음 추가
 - spring.datasource.url=jdbc:log4jdbc:mariadb://localhost:3306/fileupload?serverTimezone=UTC&characterEncoding=UTF-8

[테이블]
 - m_member, movie, movie_image, review
 
[파일 업로드 설정]
1. application.properties 파일에 다음 추가
 - com.javalab.upload.path=C:\\filetest


[테스트 방법]
1. 서브 구동
2. 주소창에 다음 Url 입력
 - http://localhost:8080/uploadEx
3. 파일 업로드후 다음 경로에서 확인
 - C:\filetest\2023\07\03 
 