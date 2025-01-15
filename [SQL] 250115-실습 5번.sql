-- 고객 테이블에서 이름을 오름차순으로, 이메일을 내림차순으로 정렬하여 출력하시오
SELECT NAME, EMAIL
FROM CUSTOMERS
ORDER BY NAME, EMAIL DESC;

-- 계좌 테이블에서 잔액을 내림차순으로 정렬하되 동일 잔액일 경우 계좌 ID를 오름차순으로 정렬하여 출력하시오
SELECT ACCOUNT_ID, BALANCE
FROM ACCOUNTS
ORDER BY BALANCE DESC, ACCOUNT_ID;

-- 거래 테이블에서 거래 유형을 기준으로 오름차순, 거래 금액을 기준으로 내림차순으로 정렬하여 출력하시오
SELECT TRANSACTION_TYPE, AMOUNT
FROM TRANSACTIONS
ORDER BY TRANSACTION_TYPE, AMOUNT DESC;

-- 대출 테이블에서 대출 상태를 기준으로 오름차순, 대출금액을 기준으로 내림차순으로 정렬하여 출력하시오
SELECT STATUS, AMOUNT
FROM LOANS
ORDER BY STATUS, AMOUNT DESC;
