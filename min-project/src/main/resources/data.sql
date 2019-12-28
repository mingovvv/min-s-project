INSERT INTO USER 
(USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, ROW_NUM) 
VALUES
('puregyu', '장민규', 'aaa', 'puregyu@naver.com', 0);

INSERT INTO USER 
(USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, ROW_NUM) 
VALUES
('test', '테스터', 'aaa', 'test@naver.com', 1);

INSERT INTO QUESTION 
(idx, contents, date, row_num, title, user_row_num)
VALUES
('7', '내용', '2020-11-11 13:13:13',0, '제목', 0);

INSERT INTO ANSWER 
(idx, contents, date, row_num, question_idx, user_row_num)
VALUES
('1', '댓글', '2020-11-12 13:13:13', 0, '7',0);