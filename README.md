# 에이블리-사전과제
+ 회원 가입 및 비밀번호 재설정이 가능한 API 기능을 구현한 서버입니다.
+ 대규모 트래픽에도 견고한 어플레이션을 구현할 수 있도록 하고 있습니다.

## 기능
+ 회원가입 기능
+ 로그인 기능(로그인 성공 후 세션 정보에 로그인 정보 저장)
+ 내정보 보기 기능(로그인 상태에서 보기)
+ 비밀번호 찾기 기능(로그인 상태가 아닌, sms 인증 번호를 콘솔로 입력하여 인증 후 비밀번호 변경)

### 사용 기술
+ Spring Boot, Gradle, Mysql, Java8, RestApI, Jpa

### 기술적인 집중 요소
+ 객체지향의 기본 원리와 spring의 IOC/DI , AOP, PSA 활용과 의미 있는 코드 작성
+ 라이브러리 및 기능 추가 시 이유있는 선택과 사용 목적 고려

### 브렌치 관리 전략
Git Flow를 사용하여 branch를 관리   
모든 branch는 pull request 리뷰 완료후 merge   


+ master: 개발, 테스트 완료후 검증이 완료된 코드가 있는 branch
+ develop: 개발이 끝난후 issue branch를 merge
+ issue(feature): develop에서 새로운 기능을 개발 진행
+ release: issue에서 develop으로 merge하여 master에 merge전 배포하여 테스트를 진행
+ hot-fix: release, master에서 발생한 버그를 수정

> #### 브렌치 관리 전략 참고 문헌
+ https://riptutorial.com/ko/git/example/4182/gitflow-%EC%9B%8C%ED%81%AC-%ED%94%8C%EB%A1%9C%EC%9A%B0

### swagger 접속
+ 서버 실행 후 브라우저에 http://localhost:8080/swagger-ui/index.html# 입력
+ 스웨거를 이용하여 각 API에 대한 설명을 보실 수 있습니다.

### 서버 실행 방법
+ 프로젝트 폴더에서 ./gradlew build 입력하여 빌드
+ jar가 생성된 폴더로 이동 cd build/libs
+ java -jar pre_task-0.0.1-SNAPSHOT.jar 입력하여 jar 실행
