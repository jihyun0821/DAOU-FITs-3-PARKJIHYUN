-- 실습과제 3
SELECT NAME, EMAIL
FROM CUSTOMERS
WHERE PHONE LIKE '555-1000%';

SELECT ACCOUNT_ID, BALANCE
FROM ACCOUNTS
WHERE ACCOUNT_TYPE = 'SAVINGS' AND BALANCE >= 10000;

SELECT TRANSACTION_ID, AMOUNT
FROM TRANSACTIONS
WHERE TRANSACTION_TYPE = 'DEPOSIT' AND AMOUNT >= 1000;

SELECT LOAN_ID, AMOUNT
FROM LOANS
WHERE AMOUNT >= 50000 AND STATUS = 'PENDING';
