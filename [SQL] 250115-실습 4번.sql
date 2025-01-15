-- 고객 테이블에서 고객 이름을 오름차순 정렬하여 출력하시오
SELECT NAME
FROM CUSTOMERS
ORDER BY NAME;

-- 계좌 테이블에서 잔액이 높은 순으로 계좌 ID와 잔액을 출력하시오
SELECT ACCOUNT_ID, BALANCE
FROM ACCOUNTS
ORDER BY BALANCE DESC;

-- 거래 테이블에서 거래 금액이 낮은 순으로 거래 ID와 금액을 출력하시오
SELECT TRANSACTION_ID, AMOUNT
FROM TRANSACTIONS
ORDER BY AMOUNT;

-- 대출 테이블에서 대출 금액이 높은 순으로 대출 ID와 고객 ID를 출력하시오
SELECT LOAN_ID, CUSTOMER_ID
FROM LOANS
ORDER BY AMOUNT DESC;
