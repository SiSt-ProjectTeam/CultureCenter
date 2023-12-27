# CultureCenter
> 문화센터에서 제공되는 강좌를 신청하는 서비스를 제공합니다. <br><br>
다음과 같은 목표로 프로젝트를 진행했습니다.<br>1. 스프링과 마이바티스를 이해하고 원하는 기능을 구현할 수 있다.<br>2. 보안 기능 강화, 예외처리, 트랜잭션 관리 등을 고려하여 안정적인 웹을 만들 수 있다.<br>3. 사용자 경험을 향상시키기 위해 레이아웃, 디자인 등을 개선할 수 있다.

※ 개발기간 : 2023/12/06 ~ 2023/12/26 (총 21일) <br>
※ 더 자세한 내용은 Wiki를 확인해주세요!
<br><br>
***
# ⚙ Main Feature
### ▶ 장바구니 및 결제
<img src="https://github.com/SiSt-ProjectTeam/CultureCenter/assets/41100813/39033745-c84a-4cfb-af7a-7e9f1e45a76b" width="120%" height="120%"></img>

<br><br>
***
# 🛠 Architecture

   ![Architecture](https://github.com/SiSt-ProjectTeam/CultureCenter/assets/41100813/489eceee-2163-4290-89a4-bf267fd1d347)

<img src="https://img.shields.io/badge/Spring 5.0-6DB33F?style=flat-square&logo=Spring&logoColor=white"/> <img src="https://img.shields.io/badge/ApacheTomcat v8.5-F8DC75?style=flat-square&logo=ApacheTomcat&logoColor=white"/> <img src="https://img.shields.io/badge/Java JDK 11-13ADC7?style=flat-square&logo=Java&logoColor=white"/>

<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/JSTL 1.2-F0D010?style=flat-square&logoColor=white"/> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=white"/> <img src="https://img.shields.io/badge/jQuery 3.7.1-0769AD?style=flat-square&logo=jQuery&logoColor=white"/>

<img src="https://img.shields.io/badge/Oracle Database 11g XE-F80000?style=flat-square&logo=Oracle&logoColor=white"/> <img src="https://img.shields.io/badge/PL/SQL 11.2.0.2.0-666666?style=flat-square&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/>
<br><br>
***
# 👩🏻‍💻 Members
|이름|역할|담당기능|
|:-|:-|:-|
|유희진<br><https://github.com/yuheejin>|팀장<br>(프로젝트 총괄)|- 시큐리티 적용<br> - 메인페이지 : 추천/카테고리/신규 강좌 및 공지사항/이벤트 출력<br> - 마이페이지 : 회원 활동 정보 출력 및 관심지점 저장<br> - 회원가입 : 비밀번호 암호화, 휴대폰 인증, 가입 환영 이메일 발송<br> - 수강내역 : 수강내역 및 취소내역 조회 (검색, 필터링, 페이징처리), 내역 상세페이지 조회|
|이경서<br><https://github.com/gseoo>|팀원|- 강좌 검색 : 지점으로 찾기, 강좌로 찾기 메뉴 구성 및 카테고리별 강좌 목록 출력(검색, 필터링, 페이징처리)<br> - 강좌 상세 페이지 : 강좌 상세 정보 및 강사별 수강 후기 조회, 수강신청 및 대기신청 페이지 구현<br> - 메인페이지 : 강좌 검색(검색, 필터링, 페이징처리)|
|박정호<br><https://github.com/P-JHo>|팀원| - 장바구니페이지 : 장바구니 추가,삭제 및 ajax처리와 스케줄러를 통한 자동삭제, 인터셉터 기능구현<br> - 결제페이지 : restTemplate api를 활용한 서버간통신, iframe을 활용한 결제api 기능 구현, 무결성검증과 형상유지 및 에러페이지 처리. <br> - 마이페이지 : 수강내역처리(영수증조회, 환불처리)|
|송해영<br><https://github.com/haeyng>|팀원|- 지점안내페이지 : 지점 대소분류 목록, 각 지점의 정보, 사진, 지도 조회<br> - 강사지원페이지 : 회원이 강사 신청시 신청 정보와 사진 저장, 제출, 신청서 작성 중 임시저장, 삭제 구현<br> - 자주하는문의페이지 : 카테고리별 문의 조회, 문의 검색, 페이징처리|
|이준희<br><https://github.com/JunHee0207>|팀원|- 수강후기 : 수강후기 정보 가져오기(검색, 필터링, 페이징처리), 댓글등록/삭제<br> - 공지사항 : 공지사항및이벤트 정보가져오기(검색, 필터링, 페이징처리)|
|김성준<br><https://github.com/SungJun545412>|팀원|- 아이디,비밀번호 찾기 : 이름과 전화번호로 아이디 찾기, 비밀번호 찾기(암호화) <br> - 회원정보변경 : 회원정보 조회, 비밀번호(암호화)/개인정보/차량정보 변경, 동반수강자정보 추가,삭제 <br> - 회원탈퇴  | 
|주강민<br><https://github.com/KangMinJoo97>|팀원|- 대기자 조회 : 회원 정보 및 대기순번 출력, 목록 필터 작업, 대기순번 취소 작업, 더보기 ajax 처리<br>- 1:1문의 : 문의 리스트 출력 및 상태 필터 작업, 문의 디테일 출력, 문의 삭제 및 작성 처리|

<br><br>
***
# 📖 <a href="https://github.com/SiSt-ProjectTeam/CultureCenter.wiki.git">Wiki</a>

## 프로젝트 문서
   1. <a href= "https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD-%EB%AA%85%EC%84%B8%EC%84%9C">요구사항명세서</a>
   2. <a href= "https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/ERD-(%EA%B0%9C%EB%85%90%EC%A0%81-%EB%AA%A8%EB%8D%B8%EB%A7%81)-%E2%80%90-drawio">ERD (개념적 모델링)</a>
   3. <a href= "https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/%ED%85%8C%EC%9D%B4%EB%B8%94-%EB%AA%85%EC%84%B8%EC%84%9C">테이블 명세서</a>
   4. <a href ="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/ERD-(%EB%85%BC%EB%A6%AC%EC%A0%81-%EB%AA%A8%EB%8D%B8%EB%A7%81)#-%EC%A0%84%EC%B2%B4%EA%B5%AC%EC%A1%B0"> ERD (논리적 모델링) </a>
   5. <a href="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/dump-%ED%8C%8C%EC%9D%BC">오라클 Dump
   6. <a href="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/API-%EB%AA%85%EC%84%B8%EC%84%9C">API 명세서</a>
   7. <a href="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%8B%9C%EC%97%B0%EC%98%81%EC%83%81">시연영상

## <a href= "https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%ED%9B%84%EA%B8%B0">후기</a>

## 회의록
   1. <a href="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/CultureCenter-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-1%EC%B0%A8-%ED%9A%8C%EC%9D%98%EB%A1%9D#%ED%9A%8C%EC%9D%98-%EC%9D%BC%EC%8B%9C--2023%EB%85%84-12%EC%9B%94-6%EC%9D%BC">2023-12-06 회의</a>
   2. <a href="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/CultureCenter-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-2%EC%B0%A8-%ED%9A%8C%EC%9D%98%EB%A1%9D">2023-12-11 회의</a>
   3. <a href="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/CultureCenter-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-3%EC%B0%A8-%ED%9A%8C%EC%9D%98%EB%A1%9D">2023-12-14 회의</a>
   4. <a href="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/CultureCenter-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-4%EC%B0%A8-%ED%9A%8C%EC%9D%98%EB%A1%9D">2023-12-18 회의</a>
   5. <a href="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/CultureCenter-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-5%EC%B0%A8-%ED%9A%8C%EC%9D%98%EB%A1%9D">2023-12-21 회의</a>
   6. <a href="https://github.com/SiSt-ProjectTeam/CultureCenter/wiki/CultureCenter-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-6%EC%B0%A8-%ED%9A%8C%EC%9D%98%EB%A1%9D">2023-12-26 회의</a>


