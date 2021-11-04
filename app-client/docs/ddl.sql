-- 유저
DROP TABLE IF EXISTS jeju_user RESTRICT;

-- 테마
DROP TABLE IF EXISTS jeju_theme RESTRICT;

-- 장소
DROP TABLE IF EXISTS jeju_place RESTRICT;

-- 신고상태
DROP TABLE IF EXISTS jeju_report_status RESTRICT;

-- 좋아하는 유저
DROP TABLE IF EXISTS jeju_liked_user RESTRICT;

-- 좋아하는 테마
DROP TABLE IF EXISTS jeju_liked_theme RESTRICT;

-- 유저 신고
DROP TABLE IF EXISTS jeju_report_user RESTRICT;

-- 테마 신고
DROP TABLE IF EXISTS jeju_report_theme RESTRICT;

-- 해시태그
DROP TABLE IF EXISTS jeju_theme_hashtag RESTRICT;

-- 장소 사진
DROP TABLE IF EXISTS jeju_place_photo RESTRICT;

-- 후기
DROP TABLE IF EXISTS jeju_place_comment RESTRICT;

-- 카테고리
DROP TABLE IF EXISTS jeju_theme_category RESTRICT;

-- 장소_유저_테마
DROP TABLE IF EXISTS jeju_place_user_theme RESTRICT;

-- 유저
CREATE TABLE jeju_user (
	user_no      INTEGER      NOT NULL COMMENT '유저번호', -- 유저번호
	email        VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
	password     VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
	nickname     VARCHAR(30)  NOT NULL COMMENT '닉네임', -- 닉네임
	created_dt   DATE         NOT NULL DEFAULT curdate() COMMENT '등록일', -- 등록일
	view_cnt     INTEGER      NULL     DEFAULT 0 COMMENT '조회수', -- 조회수
	reported_cnt INTEGER      NULL     DEFAULT 0 COMMENT '신고받은 횟수', -- 신고받은 횟수
	warned_cnt   INTEGER      NULL     DEFAULT 0 COMMENT '경고받은 횟수', -- 경고받은 횟수
	active       INTEGER      NULL     DEFAULT 1 COMMENT '탈퇴' -- 탈퇴
)
COMMENT '유저';

-- 유저
ALTER TABLE jeju_user
	ADD CONSTRAINT PK_jeju_user -- 유저 기본키
		PRIMARY KEY (
			user_no -- 유저번호
		);

-- 유저 유니크 인덱스
CREATE UNIQUE INDEX UIX_jeju_user
	ON jeju_user ( -- 유저
		email ASC,    -- 이메일
		nickname ASC  -- 닉네임
	);

ALTER TABLE jeju_user
	MODIFY COLUMN user_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '유저번호';

-- 테마
CREATE TABLE jeju_theme (
	theme_no     INTEGER      NOT NULL COMMENT '테마번호', -- 테마번호
	user_no      INTEGER      NOT NULL COMMENT '유저번호', -- 유저번호
	title        VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
	public       INTEGER      NOT NULL DEFAULT 1 COMMENT '공개여부', -- 공개여부
	share        INTEGER      NOT NULL COMMENT '공유여부', -- 공유여부
	category_no  INTEGER      NOT NULL COMMENT '카테고리번호', -- 카테고리번호
	view_cnt     INTEGER      NULL     DEFAULT 0 COMMENT '조회수', -- 조회수
	reported_cnt INTEGER      NULL     DEFAULT 0 COMMENT '신고받은 횟수' -- 신고받은 횟수
)
COMMENT '테마';

-- 테마
ALTER TABLE jeju_theme
	ADD CONSTRAINT PK_jeju_theme -- 테마 기본키
		PRIMARY KEY (
			theme_no -- 테마번호
		);

-- 테마 유니크 인덱스
CREATE UNIQUE INDEX UIX_jeju_theme
	ON jeju_theme ( -- 테마
		title ASC -- 제목
	);

ALTER TABLE jeju_theme
	MODIFY COLUMN theme_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '테마번호';

-- 장소
CREATE TABLE jeju_place (
	place_id      VARCHAR(255) NOT NULL COMMENT '장소번호', -- 장소번호
	place_name    VARCHAR(50)  NOT NULL COMMENT '장소이름', -- 장소이름
	place_address VARCHAR(255) NOT NULL COMMENT '장소주소', -- 장소주소
	y_coord       DOUBLE       NOT NULL COMMENT '경도', -- 경도
	x_coord       DOUBLE       NOT NULL COMMENT '위도' -- 위도
)
COMMENT '장소';

-- 장소
ALTER TABLE jeju_place
	ADD CONSTRAINT PK_jeju_place -- 장소 기본키
		PRIMARY KEY (
			place_id -- 장소번호
		);

-- 신고상태
CREATE TABLE jeju_report_status (
	report_status_no INTEGER     NOT NULL COMMENT '신고상태번호', -- 신고상태번호
	title            VARCHAR(50) NOT NULL COMMENT '상태명' -- 상태명
)
COMMENT '신고상태';

-- 신고상태
ALTER TABLE jeju_report_status
	ADD CONSTRAINT PK_jeju_report_status -- 신고상태 기본키
		PRIMARY KEY (
			report_status_no -- 신고상태번호
		);

-- 좋아하는 유저
CREATE TABLE jeju_liked_user (
	user_no  INTEGER NOT NULL COMMENT '유저번호', -- 유저번호
	user_no2 INTEGER NOT NULL COMMENT '팔로잉' -- 팔로잉
)
COMMENT '좋아하는 유저';

-- 좋아하는 유저
ALTER TABLE jeju_liked_user
	ADD CONSTRAINT PK_jeju_liked_user -- 좋아하는 유저 기본키
		PRIMARY KEY (
			user_no,  -- 유저번호
			user_no2  -- 팔로잉
		);

-- 좋아하는 유저 유니크 인덱스
CREATE UNIQUE INDEX UIX_jeju_liked_user
	ON jeju_liked_user ( -- 좋아하는 유저
		user_no ASC,  -- 유저번호
		user_no2 ASC  -- 팔로잉
	);

-- 좋아하는 테마
CREATE TABLE jeju_liked_theme (
	user_no  INTEGER NOT NULL COMMENT '유저번호', -- 유저번호
	theme_no INTEGER NOT NULL COMMENT '테마번호' -- 테마번호
)
COMMENT '좋아하는 테마';

-- 좋아하는 테마
ALTER TABLE jeju_liked_theme
	ADD CONSTRAINT PK_jeju_liked_theme -- 좋아하는 테마 기본키
		PRIMARY KEY (
			user_no,  -- 유저번호
			theme_no  -- 테마번호
		);

-- 유저 신고
CREATE TABLE jeju_report_user (
	report_user_no   INTEGER NOT NULL COMMENT '유저신고번호', -- 유저신고번호
	user_no          INTEGER NOT NULL COMMENT '신고자', -- 신고자
	user_no2         INTEGER NOT NULL COMMENT '피신고자', -- 피신고자
	content          TEXT    NOT NULL COMMENT '내용', -- 내용
	created_dt       DATE    NOT NULL DEFAULT curdate() COMMENT '신고일', -- 신고일
	report_status_no INTEGER NOT NULL COMMENT '신고상태번호' -- 신고상태번호
)
COMMENT '유저 신고';

-- 유저 신고
ALTER TABLE jeju_report_user
	ADD CONSTRAINT PK_jeju_report_user -- 유저 신고 기본키
		PRIMARY KEY (
			report_user_no -- 유저신고번호
		);

-- 유저 신고 유니크 인덱스
CREATE UNIQUE INDEX UIX_jeju_report_user
	ON jeju_report_user ( -- 유저 신고
		user_no ASC,  -- 신고자
		user_no2 ASC  -- 피신고자
	);

ALTER TABLE jeju_report_user
	MODIFY COLUMN report_user_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '유저신고번호';

-- 테마 신고
CREATE TABLE jeju_report_theme (
	report_theme_no  INTEGER NOT NULL COMMENT '테마신고번호', -- 테마신고번호
	user_no          INTEGER NOT NULL COMMENT '유저번호', -- 유저번호
	theme_no         INTEGER NOT NULL COMMENT '테마번호', -- 테마번호
	content          TEXT    NOT NULL COMMENT '내용', -- 내용
	created_dt       DATE    NOT NULL DEFAULT curdate() COMMENT '신고일', -- 신고일
	report_status_no INTEGER NOT NULL COMMENT '신고상태번호' -- 신고상태번호
)
COMMENT '테마 신고';

-- 테마 신고
ALTER TABLE jeju_report_theme
	ADD CONSTRAINT PK_jeju_report_theme -- 테마 신고 기본키
		PRIMARY KEY (
			report_theme_no -- 테마신고번호
		);

-- 테마 신고 유니크 인덱스
CREATE UNIQUE INDEX UIX_jeju_report_theme
	ON jeju_report_theme ( -- 테마 신고
		user_no ASC,  -- 유저번호
		theme_no ASC  -- 테마번호
	);

ALTER TABLE jeju_report_theme
	MODIFY COLUMN report_theme_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '테마신고번호';

-- 해시태그
CREATE TABLE jeju_theme_hashtag (
	hashtag_no INTEGER     NOT NULL COMMENT '해시태그번호', -- 해시태그번호
	theme_no   INTEGER     NOT NULL COMMENT '테마번호', -- 테마번호
	name       VARCHAR(50) NOT NULL COMMENT '해시태그명' -- 해시태그명
)
COMMENT '해시태그';

-- 해시태그
ALTER TABLE jeju_theme_hashtag
	ADD CONSTRAINT PK_jeju_theme_hashtag -- 해시태그 기본키
		PRIMARY KEY (
			hashtag_no -- 해시태그번호
		);

ALTER TABLE jeju_theme_hashtag
	MODIFY COLUMN hashtag_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '해시태그번호';

-- 장소 사진
CREATE TABLE jeju_place_photo (
	place_photo_no INTEGER      NOT NULL COMMENT '장소사진번호', -- 장소사진번호
	place_id       VARCHAR(255) NOT NULL COMMENT '장소번호', -- 장소번호
	file_path      VARCHAR(255) NOT NULL COMMENT '장소사진', -- 장소사진
	user_no        INTEGER      NULL     COMMENT '유저번호' -- 유저번호
)
COMMENT '장소 사진';

-- 장소 사진
ALTER TABLE jeju_place_photo
	ADD CONSTRAINT PK_jeju_place_photo -- 장소 사진 기본키
		PRIMARY KEY (
			place_photo_no -- 장소사진번호
		);

ALTER TABLE jeju_place_photo
	MODIFY COLUMN place_photo_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '장소사진번호';

-- 후기
CREATE TABLE jeju_place_comment (
	comment_no INTEGER      NOT NULL COMMENT '후기번호', -- 후기번호
	place_id   VARCHAR(255) NOT NULL COMMENT '장소번호', -- 장소번호
	comment    TEXT         NOT NULL COMMENT '후기', -- 후기
	user_no    INTEGER      NULL     COMMENT '유저번호' -- 유저번호
)
COMMENT '후기';

-- 후기
ALTER TABLE jeju_place_comment
	ADD CONSTRAINT PK_jeju_place_comment -- 후기 기본키
		PRIMARY KEY (
			comment_no -- 후기번호
		);

ALTER TABLE jeju_place_comment
	MODIFY COLUMN comment_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '후기번호';

-- 카테고리
CREATE TABLE jeju_theme_category (
	category_no INTEGER     NOT NULL COMMENT '카테고리번호', -- 카테고리번호
	name        VARCHAR(50) NOT NULL COMMENT '카테고리명' -- 카테고리명
)
COMMENT '카테고리';

-- 카테고리
ALTER TABLE jeju_theme_category
	ADD CONSTRAINT PK_jeju_theme_category -- 카테고리 기본키
		PRIMARY KEY (
			category_no -- 카테고리번호
		);

-- 장소_유저_테마
CREATE TABLE jeju_place_user_theme (
	theme_no INTEGER      NOT NULL COMMENT '테마번호', -- 테마번호
	place_id VARCHAR(255) NOT NULL COMMENT '장소번호', -- 장소번호
	user_no  INTEGER      NOT NULL COMMENT '유저번호' -- 유저번호
)
COMMENT '장소_유저_테마';

-- 장소_유저_테마
ALTER TABLE jeju_place_user_theme
	ADD CONSTRAINT PK_jeju_place_user_theme -- 장소_유저_테마 기본키
		PRIMARY KEY (
			theme_no, -- 테마번호
			place_id, -- 장소번호
			user_no   -- 유저번호
		);

-- 테마
ALTER TABLE jeju_theme
	ADD CONSTRAINT FK_jeju_user_TO_jeju_theme -- 유저 -> 테마
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);

-- 테마
ALTER TABLE jeju_theme
	ADD CONSTRAINT FK_jeju_theme_category_TO_jeju_theme -- 카테고리 -> 테마
		FOREIGN KEY (
			category_no -- 카테고리번호
		)
		REFERENCES jeju_theme_category ( -- 카테고리
			category_no -- 카테고리번호
		);

-- 좋아하는 유저
ALTER TABLE jeju_liked_user
	ADD CONSTRAINT FK_jeju_user_TO_jeju_liked_user -- 유저 -> 좋아하는 유저
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);

-- 좋아하는 유저
ALTER TABLE jeju_liked_user
	ADD CONSTRAINT FK_jeju_user_TO_jeju_liked_user2 -- 유저 -> 좋아하는 유저2
		FOREIGN KEY (
			user_no2 -- 팔로잉
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);

-- 좋아하는 테마
ALTER TABLE jeju_liked_theme
	ADD CONSTRAINT FK_jeju_user_TO_jeju_liked_theme -- 유저 -> 좋아하는 테마
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);

-- 좋아하는 테마
ALTER TABLE jeju_liked_theme
	ADD CONSTRAINT FK_jeju_theme_TO_jeju_liked_theme -- 테마 -> 좋아하는 테마
		FOREIGN KEY (
			theme_no -- 테마번호
		)
		REFERENCES jeju_theme ( -- 테마
			theme_no -- 테마번호
		);

-- 유저 신고
ALTER TABLE jeju_report_user
	ADD CONSTRAINT FK_jeju_user_TO_jeju_report_user -- 유저 -> 유저 신고
		FOREIGN KEY (
			user_no -- 신고자
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);

-- 유저 신고
ALTER TABLE jeju_report_user
	ADD CONSTRAINT FK_jeju_user_TO_jeju_report_user2 -- 유저 -> 유저 신고2
		FOREIGN KEY (
			user_no2 -- 피신고자
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);

-- 유저 신고
ALTER TABLE jeju_report_user
	ADD CONSTRAINT FK_jeju_report_status_TO_jeju_report_user -- 신고상태 -> 유저 신고
		FOREIGN KEY (
			report_status_no -- 신고상태번호
		)
		REFERENCES jeju_report_status ( -- 신고상태
			report_status_no -- 신고상태번호
		);

-- 테마 신고
ALTER TABLE jeju_report_theme
	ADD CONSTRAINT FK_jeju_user_TO_jeju_report_theme -- 유저 -> 테마 신고
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);

-- 테마 신고
ALTER TABLE jeju_report_theme
	ADD CONSTRAINT FK_jeju_theme_TO_jeju_report_theme -- 테마 -> 테마 신고
		FOREIGN KEY (
			theme_no -- 테마번호
		)
		REFERENCES jeju_theme ( -- 테마
			theme_no -- 테마번호
		);

-- 테마 신고
ALTER TABLE jeju_report_theme
	ADD CONSTRAINT FK_jeju_report_status_TO_jeju_report_theme -- 신고상태 -> 테마 신고
		FOREIGN KEY (
			report_status_no -- 신고상태번호
		)
		REFERENCES jeju_report_status ( -- 신고상태
			report_status_no -- 신고상태번호
		);

-- 해시태그
ALTER TABLE jeju_theme_hashtag
	ADD CONSTRAINT FK_jeju_theme_TO_jeju_theme_hashtag -- 테마 -> 해시태그
		FOREIGN KEY (
			theme_no -- 테마번호
		)
		REFERENCES jeju_theme ( -- 테마
			theme_no -- 테마번호
		);

-- 장소 사진
ALTER TABLE jeju_place_photo
	ADD CONSTRAINT FK_jeju_place_TO_jeju_place_photo -- 장소 -> 장소 사진
		FOREIGN KEY (
			place_id -- 장소번호
		)
		REFERENCES jeju_place ( -- 장소
			place_id -- 장소번호
		);

-- 장소 사진
ALTER TABLE jeju_place_photo
	ADD CONSTRAINT FK_jeju_user_TO_jeju_place_photo -- 유저 -> 장소 사진
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);

-- 후기
ALTER TABLE jeju_place_comment
	ADD CONSTRAINT FK_jeju_place_TO_jeju_place_comment -- 장소 -> 후기
		FOREIGN KEY (
			place_id -- 장소번호
		)
		REFERENCES jeju_place ( -- 장소
			place_id -- 장소번호
		);

-- 후기
ALTER TABLE jeju_place_comment
	ADD CONSTRAINT FK_jeju_user_TO_jeju_place_comment -- 유저 -> 후기
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);

-- 장소_유저_테마
ALTER TABLE jeju_place_user_theme
	ADD CONSTRAINT FK_jeju_theme_TO_jeju_place_user_theme -- 테마 -> 장소_유저_테마
		FOREIGN KEY (
			theme_no -- 테마번호
		)
		REFERENCES jeju_theme ( -- 테마
			theme_no -- 테마번호
		);

-- 장소_유저_테마
ALTER TABLE jeju_place_user_theme
	ADD CONSTRAINT FK_jeju_place_TO_jeju_place_user_theme -- 장소 -> 장소_유저_테마
		FOREIGN KEY (
			place_id -- 장소번호
		)
		REFERENCES jeju_place ( -- 장소
			place_id -- 장소번호
		);

-- 장소_유저_테마
ALTER TABLE jeju_place_user_theme
	ADD CONSTRAINT FK_jeju_user_TO_jeju_place_user_theme -- 유저 -> 장소_유저_테마
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES jeju_user ( -- 유저
			user_no -- 유저번호
		);