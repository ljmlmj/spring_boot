[특이사항]

 1. Thymeleaf 뷰 템플릿 엔진
 
 2. 프로젝트를 복사하지 않고 새롭게 만듦.
 
 3. 스프링 부트는 부모의 의존성을 상속 받기 때문에
 	대부분의 의존성이 기본으로 다운로드 되기 때문에
 	중복으로 pom.xml에 추가할 필요 없으면 그 외
 	추가로 필요한 의존성만 추가해주면 됨.
 	
  1) 대표적으로 하키리CP도 기본적으로 들어옴.
 
 	<!--스프링 부트 부모 의존성
		spring-boot-starter-parent 에 자주 사용하는 의존성을
		세팅해놓고 그걸 상속 받겠다는 의미임. -->
		
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.12</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>