INSERT INTO USER 
(USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, ROW_NUM, IDX) 
VALUES
('puregyu', '장민규', 'aaa', 'puregyu@naver.com', 0, 'aaaaa');

INSERT INTO USER 
(USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, ROW_NUM, IDX) 
VALUES
('test', '테스터', 'aaa', 'test@naver.com', 1, 'bbbbb');

INSERT INTO QUESTION 
(idx, contents, date, row_num, title, user_idx, count_of_answer)
VALUES
('ccccc', '내용입니다', '2020-11-11 13:13:13',0, '제목', 'aaaaa', 1);

INSERT INTO ANSWER 
(idx, contents, date, row_num, question_idx, user_idx)
VALUES
('ddddd', '댓글입니다', '2020-11-12 13:13:13', 0, 'ccccc','aaaaa');