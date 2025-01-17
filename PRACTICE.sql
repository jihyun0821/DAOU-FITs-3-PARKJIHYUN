-- 1. 직원의 직무 변경 기록과 상태

-- 직원의 직무 변경 기록에서 직원의 이름과 JOB_ID를 표시하고, 직무변경이 없는 경우 'NO CHANGE'로 표시


SELECT E.EMPLOYEE_ID, E.JOB_ID PREV, NVL(J.JOB_ID, 'No Change') CURR
FROM EMPLOYEES E
LEFT OUTER JOIN JOB_HISTORY J
ON E.EMPLOYEE_ID=J.EMPLOYEE_ID;


-- 2. 특정 패턴의 직원과 부서 정보 조회

-- 직원 이름에 "Employee 1"이라는 문자열이 포함된 데이터를 조회하세요. 해당직원의 급여가 NULL인경우 0으로 표시하고, 소속부서 이름도 함께 출력합니다. 결과는 부서 이름순으로 정렬하세요.


SELECT [E.NAME](http://e.name/), NVL(E.SALARY, 0), D.DEPARTMENT_NAME
FROM EMPLOYEES E
LEFT OUTER JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
WHERE [E.NAME](http://e.name/) LIKE '%Employee 1%'
ORDER BY LENGTH(D.DEPARTMENT_NAME), D.DEPARTMENT_NAME;


-- 3. 최근 6개월 거래 내역 조회

-- 최근 6개월 동안 발생한 거래 계좌 ID와 거래 금액, 거래일, 고객 ID, 고객명을 조회하세요


SELECT [C.NAME](http://c.name/), C.CUSTOMER_ID, T.AMOUNT, A.ACCOUNT_ID, 
				TO_CHAR(T.TRANSACTION_DATE,'YYYY-MM-DD HH24:MM:SS')
FROM ACCOUNTS A, TRANSACTIONS T, CUSTOMERS C
WHERE A.ACCOUNT_ID = T.ACCOUNT_ID 
		AND C.CUSTOMER_ID = A.CUSTOMER_ID 
		AND MONTHS_BETWEEN(T.TRANSACTION_DATE, SYSDATE) <= 6;


-- 4. 특정 부서의 직무별 직원 수

-- 'Department 3', 'Department 4', 'Department 5' 부서에 속하는 직원들의 직무별 직원 수 조회하세요


SELECT E.JOB_ID, COUNT(*)
FROM EMPLOYEES E, DEPARTMENTS D
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID 
	AND D.DEPARTMENT_NAME IN ('Department 3', 'Department 4', 'Department 5')
GROUP BY E.JOB_ID;


-- 5. 계좌 유형별 평균 잔액

-- 계좌 유형별 평균 잔액을 조회한 뒤 버림하여 소수점 아래 둘재 자리까지 출력하세요(평균 잔액으로 내림차순, 출력 컬럼은 계좌 유형, 평균 잔액)


SELECT ACCOUNT_TYPE, TRUNC(AVG(BALANCE),2)
FROM ACCOUNTS A
GROUP BY ACCOUNT_TYPE
ORDER BY AVG(BALANCE) DESC;


-- 6. 각 계좌별 거래 횟수

-- 각 계좌 거래 횟수를 조회하고 거래 횟수로 오름차순 하여 출력하세요


SELECT A.ACCOUNT_ID, NVL(COUNT(T.ACCOUNT_ID),0)
FROM ACCOUNTS A
LEFT OUTER JOIN TRANSACTIONS T
ON A.ACCOUNT_ID = T.ACCOUNT_ID
GROUP BY A.ACCOUNT_ID
ORDER BY COUNT(T.ACCOUNT_ID);


-- 7. 특정 기간의 고객별 총 거래 내역

-- 2024년 1월 1일부터 2025년 12월 31일까지 발생한 거래 데이터 기준으로 고객별 총 거래 금액을 계산하세요. 결과는 거래 금액이 높은 순으로 정렬하고, 
상위 10명 고객만 조회하세요


SELECT *
FROM (SELECT [C.NAME](http://c.name/), SUM(T.AMOUNT) TRAN_AMOUNT
			FROM TRANSACTIONS T, CUSTOMERS C, ACCOUNTS A
			WHERE T.ACCOUNT_ID = A.ACCOUNT_ID
					AND A.CUSTOMER_ID = C.CUSTOMER_ID
					AND TRANSACTION_DATE BETWEEN '20240101' AND '20251231'
			GROUP BY [C.NAME](http://c.name/),C.CUSTOMER_ID
			ORDER BY TRAN_AMOUNT DESC)
WHERE ROWNUM <= 10;


-- 8. 급여가 NULL인 직원

-- 급여 정보가 없는 직원의 이름과 부서 이름을 조회

sql
SELECT D.DEPARTMENT_NAME, [E.NAME](http://e.name/)
FROM EMPLOYEES E
LEFT OUTER JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
WHERE SALARY IS NULL;


-- 9. 고객별 가장 높은 대출 금액 조회

-- 각 고객이 받은 대출 중 가장 높은 대출 금액을 조회하세요. 최대 대출 금액이 높은 순으로 정렬하고, 상위 15명 데이터를 출력하세요


SELECT *
FROM(SELECT [C.NAME](http://c.name/), MAX(L.AMOUNT) MAX_AMOUNT
			FROM CUSTOMERS C
			INNER JOIN LOANS L
				ON C.CUSTOMER_ID = L.CUSTOMER_ID
			GROUP BY L.CUSTOMER_ID, [C.NAME](http://c.name/)
			ORDER BY MAX_AMOUNT DESC)
WHERE ROWNUM <= 15;


-- 10. 특정 급여 구간과 부서별 직원 분포

-- 급여가 7000 이상 15000 이하인 직원 데이터를 기준으로, 각 부서별 직원 수를 계산하세요. 직원 수가 5명 이상인 부서만 출력하고, 직원 수가 많은 순으로 정렬하세요.


SELECT D.DEPARTMENT_NAME, COUNT(E.EMPLOYEE_ID) EMP_CNT
FROM EMPLOYEES E, DEPARTMENTS D
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
		AND SALARY BETWEEN 7000 AND 15000
GROUP BY D.DEPARTMENT_NAME, D.DEPARTMENT_ID
HAVING COUNT(E.EMPLOYEE_ID) >= 5
ORDER BY EMP_CNT DESC;
