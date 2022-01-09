-- review : rv / reply : re /report : rp 
-- seq는 num

drop table reservation cascade constraints;
CREATE TABLE reservation (
	rsrv_no	NUMBER	NOT NULL,
	sch_no	NUMBER		NULL,
	m_id	NVARCHAR2(50)		NULL,
	rsrv_date	DATE		NULL,
	adult_cnt	NUMBER		NULL,
	youth_cnt	NUMBER		NULL,
	price	NUMBER		NULL
);

drop table notice cascade constraints;
CREATE TABLE notice (
	notice_no	number		NOT NULL,
	notice_title	nvarchar2(50)		NULL,
	notice_content	NCLOB		NULL,
	reg_date	DATE		NULL,
	view_cnt	NUMBER		NULL,
	notice_class	NUMBER		NULL
);

drop table question cascade constraints;
CREATE TABLE question (
	ques_no	NUMBER	NOT NULL,
	m_id	NVARCHAR2(50)		NULL,
	ques_title	NVARCHAR2(100)		NULL,
	ques_cont	NCLOB		NULL,
	ques_state	NUMBER		NULL,
	ques_date	DATE		NULL
);

drop table review cascade constraints;
CREATE TABLE review (
	review_num	NUMBER		NOT NULL,
	th_code	NUMBER		NULL,
	m_id	NVARCHAR2(50)		NULL,
	review_title	NVARCHAR2(50)		NULL,
	review_content	NCLOB		NULL,
	review_date	DATE		NULL,
	review_view	NUMBER		NULL,
	review_like	NUMBER		NULL
);

drop table question_reply cascade constraints;
CREATE TABLE question_reply (
	ques_reply_no	NUMBER		NOT NULL,
	ques_no	NUMBER		NULL,
	ques_reply_cont	NCLOB		NULL,
	ques_reply_date	DATE		NULL,
	ques_reply_state	NUMBER		NULL
);

drop table reply cascade constraints;
CREATE TABLE reply (
	reply_num	NUMBER		NOT NULL,
	review_num	NUMBER		NULL,
	m_id	NVARCHAR2(50)		NULL,
	reply_content	NVARCHAR2(200)		NULL,
	reply_date	DATE		NULL,
	reply_like	NUMBER		NULL
);

drop table movie cascade constraints;
CREATE TABLE movie (
	mv_seq	number		NOT NULL,
	th_code	number		NOT NULL,
	movie_cd	nvarchar2(50)		NULL,
	movie_nm	nvarchar2(50)		NULL,
	show_tm	number		NULL,
	open_dt	date		NULL,
	genre_nm	nvarchar2(50)		NULL,
	directors	nvarchar2(50)		NULL,
	actors	nvarchar2(50)		NULL,
	show_types	nvarchar2(50)		NULL,
	watch_grade_nm	number		NULL,
	poster	nvarchar2(500)	null
);

COMMENT ON COLUMN movie.th_code IS '외래키';

drop table schedule cascade constraints;
CREATE TABLE schedule (
	sch_code	number		NOT NULL,
	movie_cd	number		NULL,
	th_code	number		NULL,
	room_no	number		NULL,
	sch_date	date		NULL,
	sch_time	number		NULL
);

COMMENT ON COLUMN schedule.movie_cd IS '외래키';

COMMENT ON COLUMN schedule.th_code IS '외래키';

COMMENT ON COLUMN schedule.room_no IS '외래키';

drop table event_file cascade constraints;
CREATE TABLE event_file (
	ev_file_num	number		NOT NULL,
	event_num	number		NULL,
	ev_file_oriname	nvarchar2(50)		NULL,
	ev_file_sysname	nvarchar2(50)		NULL
);

COMMENT ON COLUMN event_file.event_num IS '외래키';

drop table schedule_detail cascade constraints;
CREATE TABLE schedule_detail (
	sch_detail_seq	number		NOT NULL,
	sch_code	number		NULL,
	sch_detail_start	nvarchar2(20)		NULL,
	sch_detail_end	nvarchar2(20)		NULL
);

COMMENT ON COLUMN schedule_detail.sch_code IS '외래키';

drop table member cascade constraints;
CREATE TABLE member (
	m_id	nvarchar2(50)		NOT NULL,
	m_pw	nvarchar2(100)		NULL,
	m_birth	date		NULL,
	m_tel	nvarchar2(50)		NULL,
	m_like	nvarchar2(50)		NULL,
	m_email	nvarchar2(50)		NULL,
	m_name	nvarchar2(50)		NULL,
	m_gender	number		NULL,
	m_point	number	DEFAULT 0	NULL,
	m_grade	number	DEFAULT 0	NULL,
	m_warning	number	DEFAULT 0	NULL
);

drop table payment cascade constraints;
CREATE TABLE payment (
	tid	nvarchar2(50)		NOT NULL,
	rsrv_no	VARCHAR(255)		NULL,
	cid	VARCHAR(255)		NULL,
	status	VARCHAR(255)		NULL,
	payment_method_type	VARCHAR(255)		NULL,
	amount	VARCHAR(255)		NULL,
	quantity	VARCHAR(255)		NULL,
	qpproved_at	VARCHAR(255)		NULL
);

drop table payment_cancel cascade constraints;
CREATE TABLE payment_cancel (
	Key	VARCHAR(255)		NOT NULL,
	Field	VARCHAR(255)		NULL
);

drop table report_review cascade constraints;
CREATE TABLE report_review (
	rp_rv_seq	NUMBER		NOT NULL,
	review_num	NUMBER		NULL,
	rp_m_id	NVARCHAR2(50)		NULL,
	rpt_m_id	NVARCHAR2(50)		NULL,
	rp_why	NVARCHAR2(50)		NULL,
	rp_date	DATE		NULL,
	rp_state	NUMBER		NULL
);

drop table report_mv_review cascade constraints;
CREATE TABLE report_mv_review (
	rp_mv_seq	NUMBER		NOT NULL,
	movie_review	NUMBER		NULL,
	rp_m_id	NVARCHAR2(50)		NULL,
	rpt_m_id	NVARCHAR2(50)		NULL,
	rp_why	NVARCHAR2(50)		NULL,
	rp_date	DATE		NULL,
	rp_state	NUMBER		NULL
);

drop table question_file cascade constraints;
CREATE TABLE question_file (
	ques_file_no	NUMBER		NOT NULL,
	ques_no	NUMBER		NULL,
	ques_file_prename	NVARCHAR2(200)		NULL,
	ques_file_newname	NVARCHAR2(200)		NULL
);

drop table report_reply cascade constraints;
CREATE TABLE report_reply (
	rp_rp_seq	NUMBER		NOT NULL,
	rp_reply_no	NUMBER		NULL,
	review_num	NUMBER		NULL,
	rp_m_id	NVARCHAR2(50)		NULL,
	rpt_m_id	NVARCHAR2(50)		NULL,
	rp_why	NVARCHAR2(50)		NULL,
	rp_date	DATE		NULL,
	rp_state	NUMBER		NULL
);

drop table movie_official cascade constraints;
CREATE TABLE movie_official (
	movie_Cd	NVARCHAR2(50)		NULL,
	movie_Nm	NVARCHAR2(50)		NULL,
	movie_Content	NCLOB		NULL,
	show_Tm	NUMBER		NULL,
	open_Dt	DATE		NULL,
	genre_Nm	NVARCHAR2(50)		NULL,
	directors	NVARCHAR2(100)		NULL,
	actors	NVARCHAR2(100)		NULL,
	show_Types	NVARCHAR2(50)		NULL,
	watch_Grade_Nm	NVARCHAR2(50)		NULL,
	poster	NVARCHAR2(200)		NULL,
	stillcut1	NVARCHAR2(200)		NULL,
	stillcut2	NVARCHAR2(200)		NULL,
	stillcut3	NVARCHAR2(200)		NULL,
	stillcut4	NVARCHAR2(200)		NULL,
	stillcut5	NVARCHAR2(200)		NULL
);

COMMENT ON COLUMN movie_official.movie_Cd IS '영화코드를 출력합니다.';

drop table	seat cascade constraints;
CREATE TABLE seat (
	seat_seq	number		NOT NULL,
	th_code	number		NULL,
	room_no	number		NULL,
	seat_col	number		NULL,
	seat_row	number		NULL,
	seat_status	number		NULL,
	seat_no	nvarchar2(50)		NULL
);

COMMENT ON COLUMN seat.th_code IS '외래키';

COMMENT ON COLUMN seat.room_no IS '외래키';

COMMENT ON COLUMN seat.seat_status IS '예 ) 0 - 통로, 1 - 좌석';

drop table reservation_seat cascade constraints;
CREATE TABLE reservation_seat (
	rsrv_seat_seq	NUMBER		NOT NULL,
	rsrv_no	NUMBER		NULL,
	seat_no	NVARCHAR2(50)		NOT NULL
);

drop table	event cascade constraints;
CREATE TABLE event (
	event_num	number		NOT NULL,
	b_id	nvarchar2(50)		NULL,
	th_code	number		NULL,
	event_title	nvarchar2(50)		NULL,
	event_content	nvarchar2(100)		NULL,
	event_start	date		NULL,
	event_end	date		NULL,
	event_target	number		NULL,
	event_category	nvarchar2(50)		NULL,
	event_sale	number		NULL
);

COMMENT ON COLUMN event.b_id IS '외래키';

COMMENT ON COLUMN event.th_code IS '외래키';

drop table	business cascade constraints;
CREATE TABLE business (
	b_id	nvarchar2(50)		NOT NULL,
	b_pw	nvarchar2(200)		NULL,
	b_name	nvarchar2(50)		NULL,
	b_num	nvarchar2(50)		NOT NULL,
	b_tel	nvarchar2(50)		NULL,
	b_email	nvarchar2(50)		NULL
);

COMMENT ON COLUMN business.b_tel IS '외래키';

drop table	theater	cascade constraints;
CREATE TABLE theater (
	th_code	number		NOT NULL,
	b_id	nvarchar2(50)		NULL,
	th_name	nvarchar2(20)		NULL,
	th_logo	nvarchar2(50)		NULL,
	th_location	nvarchar2(50)		NULL,
	th_introduce	nclob		NULL,
	th_areacode	nvarchar2(10)		NULL,
	th_parking	nvarchar2(50)		NULL,
	th_image	nvarchar2(50)		NULL,
	th_tel	nvarchar2(20)		NULL
);

COMMENT ON COLUMN theater.b_id IS '외래키';

COMMENT ON COLUMN theater.th_location IS '영화관 소개';

COMMENT ON COLUMN theater.th_introduce IS '위치정보';

COMMENT ON COLUMN theater.th_parking IS '오시는 길 위치 등등';

drop table	room	cascade constraints;
CREATE TABLE room (
	room_seq number NOT NULL,
	room_no	number		NULL,
	th_code	number		NULL,
	room_class	nvarchar2(20)		NULL,
	room_name	nvarchar2(20)		NULL,
	room_row	number		NULL,
	room_col	number		NULL,
	seat_cnt	number		NULL
);

COMMENT ON COLUMN room.th_code IS '외래키';

drop table	review_movie cascade constraints;
CREATE TABLE review_movie (
	mv_review	NUMBER		NOT NULL,
	mv_review_id	NVARCHAR2(50)		NULL,
	mv_review_movieCd	NVARCHAR2(50)		NULL,
	mv_review_score	NUMBER		NULL,
	mv_review_comment	NCLOB		NULL,
	mv_review_date	DATE	default sysdate	NULL
);

drop table review_file cascade constraints;
CREATE TABLE review_file (
	review_file_no	NUMBER		NOT NULL,
	review_num	NUMBER		NULL,
	review_file_prevname	NVARCHAR2(200)		NULL,
	review_file_newname	NVARCHAR2(200)		NULL
);

drop table temp cascade constraints;
create table temp (
    movieCd nvarchar2(50), -- 영화 코드
    movieNm nvarchar2(50), -- 영화명 
    openDt  nvarchar2(50), -- 개봉일
    repGenreNm  nvarchar2(50), -- 대표장르
    genreAlt    nvarchar2(50) -- 장르 전체
);

ALTER TABLE reservation ADD CONSTRAINT pk_movieCd PRIMARY KEY (
	movieCd
);
    
--------------------------------------------------------------


ALTER TABLE reservation ADD CONSTRAINT PK_RESERVATION PRIMARY KEY (
	rsrv_no
);
drop sequence rsrv_no_seq;
CREATE SEQUENCE rsrv_no_seq;

ALTER TABLE notice ADD CONSTRAINT PK_NOTICE_NO PRIMARY KEY (
	notice_no
);
drop sequence notice_no_seq;
CREATE SEQUENCE notice_no_seq;

ALTER TABLE question ADD CONSTRAINT PK_QUESTION PRIMARY KEY (
	ques_no
);
drop sequence ques_no_seq;
CREATE SEQUENCE ques_no_seq;

ALTER TABLE review ADD CONSTRAINT PK_REVIEW PRIMARY KEY (
	review_num
);
drop sequence review_num_seq;
CREATE SEQUENCE review_num_seq;

ALTER TABLE question_reply ADD CONSTRAINT PK_QUESTION_REPLY PRIMARY KEY (
	ques_reply_no
);
drop sequence ques_reply_no;
CREATE SEQUENCE ques_reply_no;

ALTER TABLE reply ADD CONSTRAINT PK_REPLY PRIMARY KEY (
	reply_num
);
drop sequence reply_num_seq;
CREATE SEQUENCE reply_num_seq;

ALTER TABLE movie ADD CONSTRAINT PK_MOVIE PRIMARY KEY (
	mv_seq
);
drop sequence movie_seq;
CREATE SEQUENCE movie_seq;

ALTER TABLE schedule ADD CONSTRAINT PK_SCHEDULE PRIMARY KEY (
	sch_code
);
drop sequence sch_code_seq;
CREATE SEQUENCE sch_code_seq;

ALTER TABLE event_file ADD CONSTRAINT PK_EVENT_FILE PRIMARY KEY (
	ev_file_num
);
drop sequence ev_file_num_seq;
CREATE SEQUENCE ev_file_num_seq;

ALTER TABLE schedule_detail ADD CONSTRAINT PK_SCHEDULE_DETAIL PRIMARY KEY (
	sch_detail_seq
);
drop sequence sch_detail_seq;
CREATE SEQUENCE sch_detail_seq;

ALTER TABLE member ADD CONSTRAINT PK_MEMBER PRIMARY KEY (
	m_id
);

ALTER TABLE payment ADD CONSTRAINT PK_PAYMENT PRIMARY KEY (
	tid
);

ALTER TABLE payment_cancel ADD CONSTRAINT PK_PAYMENT_CANCEL PRIMARY KEY (
	Key
);

ALTER TABLE report_review ADD CONSTRAINT PK_REPORT_REVIEW PRIMARY KEY (
	rp_rv_seq
);
drop sequence rp_rv_seq;
CREATE SEQUENCE rp_rv_seq;

ALTER TABLE report_mv_review ADD CONSTRAINT PK_REPORT_MV_REVIEW PRIMARY KEY (
	rp_mv_seq
);
drop sequence rp_mv_seq;
CREATE SEQUENCE rp_mv_seq;

ALTER TABLE question_file ADD CONSTRAINT PK_QUESTION_FILE PRIMARY KEY (
	ques_file_no
);
drop sequence ques_file_no_seq;
CREATE SEQUENCE ques_file_no_seq;

ALTER TABLE report_reply ADD CONSTRAINT PK_REPORT_REPLY PRIMARY KEY (
	rp_rp_seq
);
drop sequence rp_rp_seq;
CREATE SEQUENCE rp_rp_seq;

ALTER TABLE movie_official ADD CONSTRAINT PK_MV_OFFICIAL PRIMARY KEY (
	movie_Cd
);

ALTER TABLE seat ADD CONSTRAINT PK_SEAT PRIMARY KEY (
	seat_seq
);
drop sequence seat_seq;
CREATE SEQUENCE seat_seq;

ALTER TABLE reservation_seat ADD CONSTRAINT PK_RESERVATION_SEAT PRIMARY KEY (
	rsrv_seat_seq
);
drop sequence rsrv_seat_seq;
CREATE SEQUENCE rsrv_seat_seq;

ALTER TABLE event ADD CONSTRAINT PK_EVENT PRIMARY KEY (
	event_num
);
drop sequence event_num_seq;
CREATE SEQUENCE event_num_seq;

ALTER TABLE business ADD CONSTRAINT PK_BUSINESS PRIMARY KEY (
	b_id
);

ALTER TABLE theater ADD CONSTRAINT PK_THEATER PRIMARY KEY (
	th_code
);
drop sequence th_code_seq;
CREATE SEQUENCE th_code_seq;

ALTER TABLE room ADD CONSTRAINT PK_ROOM_SEQ PRIMARY KEY (
	room_seq
);
drop sequence room_no_seq;
CREATE SEQUENCE room_no_seq;

ALTER TABLE review_movie ADD CONSTRAINT PK_REVIEW_MOVIE PRIMARY KEY (
	mv_review
);
drop sequence mv_review_seq;
CREATE SEQUENCE mv_review_seq;

ALTER TABLE review_file ADD CONSTRAINT PK_REVIEW_FILE PRIMARY KEY (
	review_file_no
);
drop sequence review_file_no_seq;
CREATE SEQUENCE review_file_no_seq;

