-- 회원
insert into jeju_user(user_no, email, password, nickname )
values(1, 'aaa', password('1111'), '제주천재');

insert into jeju_user(user_no, email, password, nickname )
values(2, 'bbb', password('1111'), '제주지기');

insert into jeju_user(user_no, email, password, nickname )
values(3, 'ccc', password('1111'), '제주바보');

insert into jeju_user(user_no, email, password, nickname )
values(4, 'ddd', password('1111'), '제주촌놈');

insert into jeju_user(user_no, email, password, nickname )
values(5, 'eee', password('1111'), '제주남자');

insert into jeju_user(user_no, email, password, nickname )
values(6, 'fff', password('1111'), '제주여자');

-- 테마
insert into jeju_theme(theme_no, user_no, title, public, category) values(1, 1, '제주맛집', 1, '식당');
insert into jeju_theme(theme_no, user_no, title, public, category) values(2, 1, '멋진해변', 1, '관광명소');
insert into jeju_theme(theme_no, user_no, title, public, category) values(3, 1, '나만의 제주 장소', 0, '기타');

insert into jeju_theme(theme_no, user_no, title, public, category) values(4, 2, '흑돼지 맛집', 1, '식당');
insert into jeju_theme(theme_no, user_no, title, public, category) values(5, 2, '드라이브도로', 1, '관광명소');
insert into jeju_theme(theme_no, user_no, title, public, category) values(6, 2, '돌덩이', 0, '기타');

insert into jeju_theme(theme_no, user_no, title, public, category) values(7, 3, '커피가 맛있는 카페', 1, '카페');
insert into jeju_theme(theme_no, user_no, title, public, category) values(8, 3, '내 최애 제주', 1, '관광명소');
insert into jeju_theme(theme_no, user_no, title, public, category) values(9, 3, '화장실 깨끗한 장소', 1, '기타');

insert into jeju_theme(theme_no, user_no, title, public, category) values(10, 4, '감귤 따기 체험장', 1, '관광명소');
insert into jeju_theme(theme_no, user_no, title, public, category) values(11, 4, '바람많이 부는 곳', 1, '관광명소');
insert into jeju_theme(theme_no, user_no, title, public, category) values(12, 4, '해산물 맛집', 1, '식당');

insert into jeju_theme(theme_no, user_no, title, public, category) values(13, 5, '이쁜 산책로', 1, '관광명소');
insert into jeju_theme(theme_no, user_no, title, public, category) values(14, 5, '제주 야경 맛집', 1, '관광명소');
insert into jeju_theme(theme_no, user_no, title, public, category) values(15, 5, '여자친구와의 추억', 0, '기타');

insert into jeju_theme(theme_no, user_no, title, public, category) values(16, 6, '가까운 병원', 1, '기타');
insert into jeju_theme(theme_no, user_no, title, public, category) values(17, 6, '반려동물 동반 가능한 곳', 1, '기타');
insert into jeju_theme(theme_no, user_no, title, public, category) values(18, 6, '뷰좋은 카페', 1, '카페');

-- 장소
insert into jeju_place(place_no, theme_no, place_name, place_address, x_coord, y_coord) 
values(1, 2, '함덕해수욕장', '제주특별자치도 제주시 조천읍 함덕리 1004-10', 126.669238934013,33.5430615661113);

insert into jeju_place(place_no, theme_no, place_name, place_address, x_coord, y_coord) 
values(2, 4, '모메존흑돼지', '제주특별자치도 제주시 구좌읍 세화리 3645-8', 126.854387077099, 33.5252450907986);

insert into jeju_place(place_no, theme_no, place_name, place_address, x_coord, y_coord) 
values(3, 7, '울트라마린', '제주특별자치도 제주시 한경면 판포리 1611', 126.20606435314039, 33.36946377010182);

-- 후기
insert into jeju_comment(comment_no, place_no, comment) values(1, 1, '깨끗하고 놀기 좋아요');
insert into jeju_comment(comment_no, place_no, comment) values(2, 1, '아이들과 함께 놀기 좋아요');

insert into jeju_comment(comment_no, place_no, comment) values(3, 2, '몸에 좋아요');
insert into jeju_comment(comment_no, place_no, comment) values(4, 2, '가족과 같이 먹기 좋아요');

insert into jeju_comment(comment_no, place_no, comment) values(5, 3, '라떼 짱맛');
insert into jeju_comment(comment_no, place_no, comment) values(6, 3, '경치가 좋아요');

-- 사진
insert into jeju_place_photo(place_photo_no, place_no, file_path) values(1, 1, 'a.gif');
insert into jeju_place_photo(place_photo_no, place_no, file_path) values(2, 1, 'b.gif');
insert into jeju_place_photo(place_photo_no, place_no, file_path) values(3, 1, 'c.gif');

insert into jeju_place_photo(place_photo_no, place_no, file_path) values(4, 2, 'd.gif');
insert into jeju_place_photo(place_photo_no, place_no, file_path) values(5, 2, 'e.gif');
insert into jeju_place_photo(place_photo_no, place_no, file_path) values(6, 2, 'f.gif');

insert into jeju_place_photo(place_photo_no, place_no, file_path) values(7, 3, 'g.gif');
insert into jeju_place_photo(place_photo_no, place_no, file_path) values(8, 3, 'h.gif');
insert into jeju_place_photo(place_photo_no, place_no, file_path) values(9, 3, 'i.gif');

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
Insert into jeju_hashtag(hashtag_no, theme_no, name) value(1,1,'고기');
Insert into jeju_hashtag(hashtag_no, theme_no, name) value(2,1,'흑돼지');
Insert into jeju_hashtag(hashtag_no, theme_no, name) value(3,1,'해산물');

Insert into jeju_hashtag(hashtag_no, theme_no, name) value(4,4,'흑돼지');

Insert into jeju_hashtag(hashtag_no, theme_no, name) value(5,12,'해산물');



