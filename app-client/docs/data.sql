-- 회원
insert into jeju_user(user_no, email, password, nickname )
values(1, 'root@test.com', password('0000'), '제주정승');

insert into jeju_user(user_no, email, password, nickname )
values(2, 'aaa', password('1111'), '제주천재');

insert into jeju_user(user_no, email, password, nickname )
values(3, 'bbb', password('1111'), '제주지기');

insert into jeju_user(user_no, email, password, nickname )
values(4, 'ccc', password('1111'), '제주바보');

insert into jeju_user(user_no, email, password, nickname )
values(5, 'ddd', password('1111'), '제주촌놈');

insert into jeju_user(user_no, email, password, nickname )
values(6, 'eee', password('1111'), '제주남자');

insert into jeju_user(user_no, email, password, nickname )
values(7, 'fff', password('1111'), '제주여자');

-- 카테고리
insert into jeju_theme_category(category_no, name) values(1, '식당');
insert into jeju_theme_category(category_no, name) values(2, '카페');
insert into jeju_theme_category(category_no, name) values(3, '관광명소');
insert into jeju_theme_category(category_no, name) values(4, '기타');

-- 테마
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(1, 7, '제주맛집', 0, 1, 1);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(2, 7, '멋진해변', 0, 1, 3);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(3, 7, '나만의 제주 장소', 0, 0, 4);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(4, 2, '흑돼지 맛집', 0, 1, 1);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(5, 2, '드라이브도로', 0, 1, 3);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(6, 2, '돌덩이', 0, 0, 4);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(7, 3, '커피가 맛있는 카페', 0, 1, 2);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(8, 3, '내 최애 제주', 0, 1, 3);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(9, 3, '화장실 깨끗한 장소', 0, 1, 4);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(10, 4, '감귤 따기 체험장', 0, 1, 3);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(11, 4, '바람많이 부는 곳', 0, 1, 3);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(12, 4, '해산물 맛집', 0, 1, 1);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(13, 5, '이쁜 산책로', 0, 1, 3);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(14, 5, '제주 야경 맛집', 0, 1, 3);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(15, 5, '여자친구와의 추억', 0, 0, 4);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(16, 6, '가까운 병원', 0, 1, 4);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(17, 6, '반려동물 동반 가능한 곳', 0, 1, 4);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no) values(18, 6, '뷰좋은 카페', 0, 1, 2);

-- 후기
insert into jeju_place_comment(comment_no, place_no, comment, user_no) values(1, 1, '깨끗하고 놀기 좋아요', 1);
insert into jeju_place_comment(comment_no, place_no, comment, user_no) values(2, 1, '아이들과 함께 놀기 좋아요', 1);

insert into jeju_place_comment(comment_no, place_no, comment, user_no) values(3, 2, '몸에 좋아요', 2);
insert into jeju_place_comment(comment_no, place_no, comment, user_no) values(4, 2, '가족과 같이 먹기 좋아요', 2);

insert into jeju_place_comment(comment_no, place_no, comment, user_no) values(5, 3, '라떼 짱맛', 3);
insert into jeju_place_comment(comment_no, place_no, comment, user_no) values(6, 3, '경치가 좋아요', 3);

-- 사진
insert into jeju_place_photo(place_photo_no, place_no, file_path, user_no) values(1, 1, 'a.gif', 1);
insert into jeju_place_photo(place_photo_no, place_no, file_path, user_no) values(2, 1, 'b.gif', 1);
insert into jeju_place_photo(place_photo_no, place_no, file_path, user_no) values(3, 1, 'c.gif', 1);

insert into jeju_place_photo(place_photo_no, place_no, file_path, user_no) values(4, 2, 'd.gif', 2);
insert into jeju_place_photo(place_photo_no, place_no, file_path, user_no) values(5, 2, 'e.gif', 2);
insert into jeju_place_photo(place_photo_no, place_no, file_path, user_no) values(6, 2, 'f.gif', 2);

insert into jeju_place_photo(place_photo_no, place_no, file_path, user_no) values(7, 3, 'g.gif', 3);
insert into jeju_place_photo(place_photo_no, place_no, file_path, user_no) values(8, 3, 'h.gif', 3);
insert into jeju_place_photo(place_photo_no, place_no, file_path, user_no) values(9, 3, 'i.gif', 3);

-- 좋아하는 유저
Insert into jeju_liked_user(user_no, user_no2) values(1,2);
Insert into jeju_liked_user(user_no, user_no2) values(1,3);
Insert into jeju_liked_user(user_no, user_no2) values(1,4);
Insert into jeju_liked_user(user_no, user_no2) values(2,5);
Insert into jeju_liked_user(user_no, user_no2) values(2,6);
Insert into jeju_liked_user(user_no, user_no2) values(3,1);
Insert into jeju_liked_user(user_no, user_no2) values(3,4);
Insert into jeju_liked_user(user_no, user_no2) values(3,5);

-- 해시태그
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(1,1,'고기');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(2,1,'흑돼지');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(3,1,'해산물');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(4,4,'흑돼지');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(5,12,'해산물');

-- 좋아하는 테마

Insert into jeju_liked_theme(user_no, theme_no) values(1,8);
Insert into jeju_liked_theme(user_no, theme_no) values(1,4);
Insert into jeju_liked_theme(user_no, theme_no) values(1,10);
Insert into jeju_liked_theme(user_no, theme_no) values(2,2);
Insert into jeju_liked_theme(user_no, theme_no) values(2,13);
Insert into jeju_liked_theme(user_no, theme_no) values(3,3);
Insert into jeju_liked_theme(user_no, theme_no) values(3,10);
Insert into jeju_liked_theme(user_no, theme_no) values(3,15);

-- 신고 상태
Insert into jeju_report_status(report_status_no, title) values(100,'대기');
Insert into jeju_report_status(report_status_no, title) values(101,'완료');

-- 유저 신고
Insert into jeju_report_user(user_no, user_no2, content, report_status_no)
values(2,3,'못생겼어요',101);

Insert into jeju_report_user(user_no, user_no2, content, report_status_no)
values(2,4,'잘생겼어요',100);

Insert into jeju_report_user(user_no, user_no2, content, report_status_no)
values(2,5,'너무 예뻐요',101);

Insert into jeju_report_user(user_no, user_no2, content, report_status_no)
values(3,6,'못생겼어요',101);

Insert into jeju_report_user(user_no, user_no2, content, report_status_no)
values(3,5,'못생겼어요',101);

-- 테마 신고
Insert into jeju_report_theme(user_no, theme_no, content, report_status_no)
values(2,1,'장소가 별로예여',101);

Insert into jeju_report_theme(user_no, theme_no, content, report_status_no)
values(2,2,'장소가 별로 없어요',100);

Insert into jeju_report_theme(user_no, theme_no, content, report_status_no)
values(2,3,'테마 이름이 별로예요',101);

Insert into jeju_report_theme(user_no, theme_no, content, report_status_no)
values(3,4,'그냥 오늘 기분이 안좋아요',101);

Insert into jeju_report_theme(user_no, theme_no, content, report_status_no)
values(3,5,'열받네',101);
