-- 유저
DROP TABLE IF EXISTS user RESTRICT;

-- 테마
DROP TABLE IF EXISTS theme RESTRICT;

-- 장소
DROP TABLE IF EXISTS place RESTRICT;

-- 신고
DROP TABLE IF EXISTS report RESTRICT;

-- 신고 유형
DROP TABLE IF EXISTS TABLE RESTRICT;

-- 신고상태
DROP TABLE IF EXISTS report_status RESTRICT;

-- 좋아하는 유저
DROP TABLE IF EXISTS liked_user RESTRICT;

-- 좋아하는 테마
DROP TABLE IF EXISTS liked_theme RESTRICT;

-- 유저 신고 횟수
DROP TABLE IF EXISTS report_cnt RESTRICT;

-- 유저 신고
DROP TABLE IF EXISTS report_user RESTRICT;

-- 테마 신고
DROP TABLE IF EXISTS report_theme RESTRICT;

-- 해시태그
DROP TABLE IF EXISTS hashtag RESTRICT;

-- 장소 사진
DROP TABLE IF EXISTS place_photo RESTRICT;

-- 후기
DROP TABLE IF EXISTS comment RESTRICT;

-- 유저
CREATE TABLE user (
	user_no      INTEGER      NOT NULL COMMENT '유저번호', -- 유저번호
	email        VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
	password     VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
	nickname     VARCHAR(30)  NOT NULL COMMENT '닉네임', -- 닉네임
	created_dt   DATE         NOT NULL DEFAULT curdate() COMMENT '등록일', -- 등록일
	view_cnt     INTEGER      NULL     DEFAULT 0 COMMENT '조회수', -- 조회수
	reported_cnt INTEGER      NULL     DEFAULT 0 COMMENT '신고받은 횟수', -- 신고받은 횟수
	warned_cnt   INTEGER      NULL     DEFAULT 0 COMMENT '경고받은 횟수', -- 경고받은 횟수
	active       INTEGER      NULL     COMMENT '탈퇴' -- 탈퇴
)
COMMENT '유저';

-- 유저
ALTER TABLE user
	ADD CONSTRAINT PK_user -- 유저 기본키
		PRIMARY KEY (
			user_no -- 유저번호
		);

-- 유저 유니크 인덱스
CREATE UNIQUE INDEX UIX_user
	ON user ( -- 유저
		email ASC,    -- 이메일
		nickname ASC  -- 닉네임
	);

ALTER TABLE user
	MODIFY COLUMN user_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '유저번호';

-- 테마
CREATE TABLE theme (
	theme_no     INTEGER      NOT NULL COMMENT '테마번호', -- 테마번호
	user_no      INTEGER      NOT NULL COMMENT '유저번호', -- 유저번호
	title        VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
	public       INTEGER      NOT NULL DEFAULT 1 COMMENT '공개여부', -- 공개여부
	category     VARCHAR(30)  NOT NULL COMMENT '카테고리', -- 카테고리
	view_cnt     INTEGER      NULL     DEFAULT 0 COMMENT '조회수', -- 조회수
	reported_cnt INTEGER      NULL     DEFAULT 0 COMMENT '신고받은 횟수' -- 신고받은 횟수
)
COMMENT '테마';

-- 테마
ALTER TABLE theme
	ADD CONSTRAINT PK_theme -- 테마 기본키
		PRIMARY KEY (
			theme_no -- 테마번호
		);

-- 테마 유니크 인덱스
CREATE UNIQUE INDEX UIX_theme
	ON theme ( -- 테마
		title ASC -- 제목
	);

ALTER TABLE theme
	MODIFY COLUMN theme_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '테마번호';

-- 장소
CREATE TABLE place (
	place_no      INTEGER      NOT NULL COMMENT '장소번호', -- 장소번호
	theme_no      INTEGER      NOT NULL COMMENT '테마번호', -- 테마번호
	place_name    VARCHAR(50)  NOT NULL COMMENT '장소이름', -- 장소이름
	place_address VARCHAR(255) NOT NULL COMMENT '장소주소', -- 장소주소
	y_coord       DOUBLE       NOT NULL COMMENT '경도', -- 경도
	x_coord       DOUBLE       NOT NULL COMMENT '위도' -- 위도
)
COMMENT '장소';

-- 장소
ALTER TABLE place
	ADD CONSTRAINT PK_place -- 장소 기본키
		PRIMARY KEY (
			place_no -- 장소번호
		);

ALTER TABLE place
	MODIFY COLUMN place_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '장소번호';

-- 신고
CREATE TABLE report (
	report_no  INTEGER  NOT NULL COMMENT '신고번호', -- 신고번호
	content    TEXT     NOT NULL COMMENT '내용', -- 내용
	created_dt DATETIME NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '신고';

-- 신고
ALTER TABLE report
	ADD CONSTRAINT PK_report -- 신고 기본키
		PRIMARY KEY (
			report_no -- 신고번호
		);

ALTER TABLE report
	MODIFY COLUMN report_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

-- 신고 유형
CREATE TABLE TABLE (
	report_no INTEGER NOT NULL COMMENT '신고번호' -- 신고번호
)
COMMENT '신고 유형';

-- 신고상태
CREATE TABLE report_status (
	report_no        INTEGER     NOT NULL COMMENT '신고번호', -- 신고번호
	report_status_no INTEGER     NOT NULL COMMENT '신고상태번호', -- 신고상태번호
	title            VARCHAR(50) NOT NULL COMMENT '상태명' -- 상태명
)
COMMENT '신고상태';

-- 좋아하는 유저
CREATE TABLE liked_user (
	user_no       INTEGER     NOT NULL COMMENT '유저번호', -- 유저번호
	user_nickname VARCHAR(30) NOT NULL COMMENT '유저이름' -- 유저이름
)
COMMENT '좋아하는 유저';

-- 좋아하는 유저
ALTER TABLE liked_user
	ADD CONSTRAINT PK_liked_user -- 좋아하는 유저 기본키
		PRIMARY KEY (
			user_no -- 유저번호
		);

-- 좋아하는 유저 유니크 인덱스
CREATE UNIQUE INDEX UIX_liked_user
	ON liked_user ( -- 좋아하는 유저
		user_nickname ASC -- 유저이름
	);

-- 좋아하는 테마
CREATE TABLE liked_theme (
	user_no  INTEGER NOT NULL COMMENT '유저번호', -- 유저번호
	theme_no INTEGER NOT NULL COMMENT '테마번호' -- 테마번호
)
COMMENT '좋아하는 테마';

-- 좋아하는 테마
ALTER TABLE liked_theme
	ADD CONSTRAINT PK_liked_theme -- 좋아하는 테마 기본키
		PRIMARY KEY (
			user_no,  -- 유저번호
			theme_no  -- 테마번호
		);

-- 유저 신고 횟수
CREATE TABLE report_cnt (
	COL  <데이터 타입 없음> NULL COMMENT '경고횟수', -- 경고횟수
	COL2 <데이터 타입 없음> NULL COMMENT '신고횟수' -- 신고횟수
)
COMMENT '유저 신고 횟수';

-- 유저 신고
CREATE TABLE report_user (
	report_no INTEGER NOT NULL COMMENT '유저신고번호', -- 유저신고번호
	user_no   INTEGER NOT NULL COMMENT '유저번호' -- 유저번호
)
COMMENT '유저 신고';

-- 유저 신고
ALTER TABLE report_user
	ADD CONSTRAINT PK_report_user -- 유저 신고 기본키
		PRIMARY KEY (
			report_no -- 유저신고번호
		);

-- 테마 신고
CREATE TABLE report_theme (
	report_no INTEGER NOT NULL COMMENT '테마신고번호', -- 테마신고번호
	user_no   INTEGER NOT NULL COMMENT '유저번호' -- 유저번호
)
COMMENT '테마 신고';

-- 테마 신고
ALTER TABLE report_theme
	ADD CONSTRAINT PK_report_theme -- 테마 신고 기본키
		PRIMARY KEY (
			report_no -- 테마신고번호
		);

-- 해시태그
CREATE TABLE hashtag (
	hashtag_no INTEGER     NOT NULL COMMENT '해시태그번호', -- 해시태그번호
	theme_no   INTEGER     NOT NULL COMMENT '테마번호', -- 테마번호
	name       VARCHAR(50) NULL     COMMENT '해시태그명' -- 해시태그명
)
COMMENT '해시태그';

-- 해시태그
ALTER TABLE hashtag
	ADD CONSTRAINT PK_hashtag -- 해시태그 기본키
		PRIMARY KEY (
			hashtag_no -- 해시태그번호
		);

ALTER TABLE hashtag
	MODIFY COLUMN hashtag_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '해시태그번호';

-- 장소 사진
CREATE TABLE place_photo (
	place_photo_no INTEGER      NOT NULL COMMENT '장소사진번호', -- 장소사진번호
	place_no       INTEGER      NOT NULL COMMENT '장소번호', -- 장소번호
	file_path      VARCHAR(255) NULL     COMMENT '장소사진' -- 장소사진
)
COMMENT '장소 사진';

-- 장소 사진
ALTER TABLE place_photo
	ADD CONSTRAINT PK_place_photo -- 장소 사진 기본키
		PRIMARY KEY (
			place_photo_no -- 장소사진번호
		);

ALTER TABLE place_photo
	MODIFY COLUMN place_photo_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '장소사진번호';

-- 후기
CREATE TABLE comment (
	comment_no INTEGER NOT NULL COMMENT '후기번호', -- 후기번호
	place_no   INTEGER NOT NULL COMMENT '장소번호', -- 장소번호
	comment    TEXT    NULL     COMMENT '후기' -- 후기
)
COMMENT '후기';

-- 후기
ALTER TABLE comment
	ADD CONSTRAINT PK_comment -- 후기 기본키
		PRIMARY KEY (
			comment_no -- 후기번호
		);

ALTER TABLE comment
	MODIFY COLUMN comment_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '후기번호';

-- 테마
ALTER TABLE theme
	ADD CONSTRAINT FK_user_TO_theme -- 유저 -> 테마
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES user ( -- 유저
			user_no -- 유저번호
		);

-- 장소
ALTER TABLE place
	ADD CONSTRAINT FK_theme_TO_place -- 테마 -> 장소
		FOREIGN KEY (
			theme_no -- 테마번호
		)
		REFERENCES theme ( -- 테마
			theme_no -- 테마번호
		);

-- 신고 유형
ALTER TABLE TABLE
	ADD CONSTRAINT FK_report_TO_TABLE -- 신고 -> 신고 유형
		FOREIGN KEY (
			report_no -- 신고번호
		)
		REFERENCES report ( -- 신고
			report_no -- 신고번호
		);

-- 신고상태
ALTER TABLE report_status
	ADD CONSTRAINT FK_report_TO_report_status -- 신고 -> 신고상태
		FOREIGN KEY (
			report_no -- 신고번호
		)
		REFERENCES report ( -- 신고
			report_no -- 신고번호
		);

-- 좋아하는 유저
ALTER TABLE liked_user
	ADD CONSTRAINT FK_user_TO_liked_user -- 유저 -> 좋아하는 유저
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES user ( -- 유저
			user_no -- 유저번호
		);

-- 좋아하는 테마
ALTER TABLE liked_theme
	ADD CONSTRAINT FK_user_TO_liked_theme -- 유저 -> 좋아하는 테마
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES user ( -- 유저
			user_no -- 유저번호
		);

-- 좋아하는 테마
ALTER TABLE liked_theme
	ADD CONSTRAINT FK_theme_TO_liked_theme -- 테마 -> 좋아하는 테마
		FOREIGN KEY (
			theme_no -- 테마번호
		)
		REFERENCES theme ( -- 테마
			theme_no -- 테마번호
		);

-- 유저 신고
ALTER TABLE report_user
	ADD CONSTRAINT FK_report_TO_report_user -- 신고 -> 유저 신고
		FOREIGN KEY (
			report_no -- 유저신고번호
		)
		REFERENCES report ( -- 신고
			report_no -- 신고번호
		);

-- 유저 신고
ALTER TABLE report_user
	ADD CONSTRAINT FK_user_TO_report_user -- 유저 -> 유저 신고
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES user ( -- 유저
			user_no -- 유저번호
		);

-- 테마 신고
ALTER TABLE report_theme
	ADD CONSTRAINT FK_report_TO_report_theme -- 신고 -> 테마 신고
		FOREIGN KEY (
			report_no -- 테마신고번호
		)
		REFERENCES report ( -- 신고
			report_no -- 신고번호
		);

-- 테마 신고
ALTER TABLE report_theme
	ADD CONSTRAINT FK_user_TO_report_theme -- 유저 -> 테마 신고
		FOREIGN KEY (
			user_no -- 유저번호
		)
		REFERENCES user ( -- 유저
			user_no -- 유저번호
		);

-- 해시태그
ALTER TABLE hashtag
	ADD CONSTRAINT FK_theme_TO_hashtag -- 테마 -> 해시태그
		FOREIGN KEY (
			theme_no -- 테마번호
		)
		REFERENCES theme ( -- 테마
			theme_no -- 테마번호
		);

-- 장소 사진
ALTER TABLE place_photo
	ADD CONSTRAINT FK_place_TO_place_photo -- 장소 -> 장소 사진
		FOREIGN KEY (
			place_no -- 장소번호
		)
		REFERENCES place ( -- 장소
			place_no -- 장소번호
		);

-- 후기
ALTER TABLE comment
	ADD CONSTRAINT FK_place_TO_comment -- 장소 -> 후기
		FOREIGN KEY (
			place_no -- 장소번호
		)
		REFERENCES place ( -- 장소
			place_no -- 장소번호
		);