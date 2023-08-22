-- 1. SEQUENCES 테이블 생성
CREATE TABLE SEQUENCES
(
    NAME    VARCHAR(32),
    CURRVAL BIGINT UNSIGNED
) ;
-- 2. 프로시저 등록 (create_sequence)
DELIMITER $$
CREATE PROCEDURE `create_sequence`(IN the_name text)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
    DELETE FROM sequences WHERE name = the_name;
    INSERT INTO sequences VALUES (the_name, 0);
END ;
-- 3. 프로시저 등록 (nextval)
DELIMITER $$
CREATE FUNCTION `nextval`(the_name VARCHAR(32))
    RETURNS BIGINT UNSIGNED
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
    DECLARE ret BIGINT UNSIGNED;
    UPDATE sequences SET currval = currval + 1 WHERE name = the_name;
    SELECT currval INTO ret FROM sequences WHERE name = the_name LIMIT 1;
    RETURN ret;
END;
-- --------------------------------------------------------------------------------------------------------------------

-- [(create_sequence) 명령어 예제]
-- 설명 : SEQUENCES 테이블의 컬럼명 : name 의 값이 sch 생성 및 CURRVAL 의 0 값이 생성
CALL create_sequence('sch');
CALL create_sequence('cal');
CALL create_sequence('emp');
CALL create_sequence('dept');
CALL create_sequence('comp');


-- [(nextval) 명령어 예제]
-- 설명 : sch 이름의 currval 값의 다음값을 호출
-- 부가 설명 : nextval 함수 앞에 , 디비 명이 있어야 함
SELECT practice.nextval('sch')
FROM DUAL;


