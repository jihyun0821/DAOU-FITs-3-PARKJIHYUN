-- 고객 테이블에서 특정 주소('Address 100')를 가진 고객의 이름과 전화번호를 출력하시오
SELECT NAME, PHONE
FROM CUSTOMERS
WHERE ADDRESS = 'Address 100';

-- 계좌 테이블에서 잔액이 50000 이상인 계좌의 계좌ID와 잔액을 출력하시오
SELECT ACCOUNT_ID, BALANCE 
FROM ACCOUNTS
WHERE BALANCE >= 50000;

-- 거래 테이블에서 거래 금액이 음수인 거래의 ID와 금액을 출력하시오
SELECT TRANSACTION_ID, AMOUNT 
FROM TRANSACTIONS
WHERE AMOUNT < 0;

-- 대출 테이블에서 상태가 'APPROVED'인 대출의 대출 금액과 고객 ID를 출력하시오
SELECT AMOUNT, CUSTOMER_ID
FROM LOANS
WHERE STATUS = 'APPROVED';
