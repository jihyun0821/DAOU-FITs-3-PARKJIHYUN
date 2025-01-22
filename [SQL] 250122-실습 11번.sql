-- 쇼핑 카테고리 데이터의 순방향 계층 구조에서 동일한 부모를 가진 카테고리에 대해 이름순으로 정렬하시오
SELECT CATEGORY_NAME, LEVEL
FROM SHOPPING_CATEGORIES
START WITH PARENT_CATEGORY_ID IS NULL
CONNECT BY PRIOR CATEGORY_ID = PARENT_CATEGORY_ID
ORDER SIBLINGS BY CATEGORY_NAME
;

-- 이름에 'Phones'가 포함된 카테고리와 해당 계층 구조를 순방향으로 출력하시오
SELECT CATEGORY_NAME, LEVEL, CATEGORY_ID, PARENT_CATEGORY_ID
FROM SHOPPING_CATEGORIES
START WITH CATEGORY_NAME LIKE '%Phones%'
CONNECT BY PRIOR CATEGORY_ID = PARENT_CATEGORY_ID
;

-- 쇼핑 카테고리 데이터의 순방향 계층 구조에서 depth가 가장 깊은 LEVEL 값을 구하시오
SELECT DISTINCT MAX(LEVEL) OVER () MAX_LEVEL
FROM SHOPPING_CATEGORIES
START WITH PARENT_CATEGORY_ID IS NULL
CONNECT BY PRIOR CATEGORY_ID = PARENT_CATEGORY_ID
; 


