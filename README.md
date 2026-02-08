# spring-boot-jpa-querydsl-api

Spring Boot + JPA + Querydsl을 활용하여
결제 트랜잭션 조회를 위한 조건 검색 API를 구현한 포트폴리오 프로젝트입니다.

실제 결제/운영 도메인을 가정하여
동적 조건 검색, 페이징, 정렬, DTO 반환 구조를 중심으로 설계했습니다.

---

## 프로젝트 목적
- Querydsl 기반 동적 조건 검색 패턴 정리
- 실무에서 자주 사용하는 Custom Repository 구조 예제
- Controller → Service → Repository 명확한 MVC 계층 분리
- 페이징/정렬을 포함한 운영 친화적인 조회 API 설계

---

## 패키지 구조
com.github.gseobi.kpos
- config
  - QuerydslConfig.java
- controller
  - StoreController.java
  - TransactionController.java
- domain
  - enumtype
    - PaymentMethod.java
    - TxStatus.java
  - BaseTimeEntity.java
  - PaymentTransaction.java
  - Store.java
  - Terminal.java
- dto
  - store
    - StoreCreateRequest.java
    - StoreResponse.java
  - tx
    - TransactionCreateRequest.java
    - TransactionResponse.java
    - TransactionSearchCondition.java
- repository
  - query
    - TransactionQueryRepository.java
    - TransactionQueryRepositoryBean.java
    - TransactionQueryRepositoryImpl.java 
  - StoreRepository.java
  - TerminalRepository.java
  - TransactionRepository.java
- service
  - StoreService.java
  - TransactionService.java
- KposApplication.java

---

## 핵심 설계 포인트
✔ Querydsl 조건 검색
- TransactionSearchCondition DTO를 통해 검색 조건 전달
- BooleanBuilder를 사용해 조건이 있는 항목만 동적 적용
- 키워드 검색 (txId, buyerId, description) 지원
✔ Custom QueryRepository 패턴
- Spring Data JPA 기본 Repository + Querydsl 전용 Repository 분리
- 조회 전용 로직은 TransactionQueryRepositoryImpl에 집중
✔ 페이징 / 정렬
- Pageable 기반 페이징 처리
- 기본 정렬: requestedAt DESC

---

## Querydsl 조건 검색 예시
**🔹 요청 (Request)**
- EndPoint : GET /transactions/search
- JSON : {
  "storeId": 1,
  "method": "CARD",
  "status": "APPROVED",
  "from": "2024-01-01T00:00:00+09:00",
  "to": "2024-01-31T23:59:59+09:00",
  "minAmount": 1000,
  "maxAmount": 50000,
  "keyword": "ORDER"
}
- Query Parameter 예시 : /transactions/search?storeId=1&status=APPROVED&page=0&size=20

**🔹 응답 (Response)**
- Response JSON : {
  "content": [
  {
      "txId": "TX202401010001",
      "storeId": 1,
      "terminalId": 3,
      "amount": 15000,
      "currency": "KRW",
      "method": "CARD",
      "status": "APPROVED",
      "buyerId": "user_123",
      "description": "POS 결제",
      "requestedAt": "2024-01-10T10:15:30+09:00",
      "approvedAt": "2024-01-10T10:15:35+09:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20
  },
  "totalElements": 1,
  "totalPages": 1
}

---

## 사용 기술
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Querydsl
- Pageable / PageImpl
- H2 (개발용)
- Logback

---

## 정리
실무에서 자주 사용되는
결제 트랜잭션 조회 + Querydsl 조건 검색 구조를
단순 예제가 아닌 운영 관점에서 재구성한 포트폴리오입니다.

