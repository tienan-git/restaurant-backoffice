-- Project Name : ChineseGourmet
-- Date/Time    : 2018/09/23 13:21:29
-- Author       : yuanm
-- RDBMS Type   : Oracle Database
-- Application  : A5:SQL Mk-2

-- 権限
create table authority (
  authority VARCHAR2(200) not null
  , role_id VARCHAR2(1) not null
  , authority_id bigint auto_increment not null
  , constraint authority_PKC primary key (authority_id)
) ;

-- ユーザー
create table user (
  user_id bigint auto_increment not null
  , user_name VARCHAR2(20)
  , email VARCHAR2(30)
  , password VARCHAR2(100)
  , role_id VARCHAR2(1)
  , constraint user_PKC primary key (user_id)
) ;

-- クーポン保持
create table coupon_hold (
  coupon_hold_id bigint auto_increment not null
  , coupon_id bigint not null
  , device_id VARCHAR2(50) not null
  , coupon_get_datetime DATE
  , constraint coupon_hold_PKC primary key (coupon_hold_id)
) ;

-- クーポン
create table coupon (
  coupon_id bigint auto_increment not null
  , restaurant_id bigint not null
  , title VARCHAR2(200)
  , detail VARCHAR2(200)
  , coupon_start_date DATE
  , coupon_end_date DATE
  , coupon_total_amount NUMBER(10)
  , coupon_status CHAR(1)
  , constraint coupon_PKC primary key (coupon_id)
) ;

-- 店舗
create table restaurant (
  restaurant_id bigint auto_increment not null
  , restaurant_name VARCHAR2(20)
  , restaurant_manager VARCHAR2(20)
  , restaurant_phone VARCHAR2(15)
  , restaurant_url VARCHAR2(100)
  , restaurant_image_url VARCHAR2(100)
  , latitude NUMBER
  , longitude NUMBER
  , restaurant_status CHAR(1)
  , restauranat_address VARCHAR2(200)
  , restaurant_memo VARCHAR2(200)
  , constraint restaurant_PKC primary key (restaurant_id)
) ;

-- フィードバック
create table feedback (
  feedback_id bigint auto_increment not null
  , device_id VARCHAR2(50) not null
  , type VARCHAR2(2)
  , detail VARCHAR2(200)
  , treatment_status CHAR(1)
  , treatment_memo VARCHAR2(200)
  , constraint feedback_PKC primary key (feedback_id)
) ;

-- 抽選応募履歴
create table "抽選応募履歴" (
  lottery_subs_history_id bigint auto_increment not null
  , lottery_event_id bigint
  , device_id VARCHAR2(50) not null
  , lottery_status CHAR(1)
  , validity_flag CHAR(1)
  , constraint "抽選応募履歴_PKC" primary key (lottery_subs_history_id)
) ;

-- 抽選イベント
create table lottery_event (
  lottery_event_id bigint auto_increment not null
  , lottery_detail VARCHAR2(200)
  , lottery_title VARCHAR2(80)
  , lottery_image VARCHAR2(2048)
  , start_datetime DATE
  , end_datetime DATE
  , announcement_datetime DATE
  , coupon_id bigint
  , constraint lottery_event_PKC primary key (lottery_event_id)
) ;

-- 顧客
create table customer (
  customer_id bigint auto_increment not null
  , device_id VARCHAR2(50) not null
  , nick_name VARCHAR2(20)
  , constraint customer_PKC primary key (customer_id)
) ;

comment on table authority is '権限';
comment on column authority.authority is '権限';
comment on column authority.role_id is 'ロールID';
comment on column authority.authority_id is '権限ID';

comment on table user is 'ユーザー';
comment on column user.user_id is 'ユーザーID';
comment on column user.user_name is 'ユーザー名';
comment on column user.email is 'メールアドレス';
comment on column user.password is 'パスワード';
comment on column user.role_id is 'ロールID';

comment on table coupon_hold is 'クーポン保持';
comment on column coupon_hold.coupon_hold_id is 'クーポン保持ID';
comment on column coupon_hold.coupon_id is 'クーポンID';
comment on column coupon_hold.device_id is 'デバイスID';
comment on column coupon_hold.coupon_get_datetime is 'クーポン取得日時';

comment on table coupon is 'クーポン';
comment on column coupon.coupon_id is 'クーポンID';
comment on column coupon.restaurant_id is '店舗ID';
comment on column coupon.title is 'タイトル';
comment on column coupon.detail is '詳細';
comment on column coupon.coupon_start_date is '有効開始日';
comment on column coupon.coupon_end_date is '有効終了日';
comment on column coupon.coupon_total_amount is 'クーポン枚数';
comment on column coupon.coupon_status is 'クーポンステータス';

comment on table restaurant is '店舗';
comment on column restaurant.restaurant_id is '店舗ID';
comment on column restaurant.restaurant_name is '店舗名';
comment on column restaurant.restaurant_manager is '店舗担当者';
comment on column restaurant.restaurant_phone is '店舗連絡先';
comment on column restaurant.restaurant_url is '店舗URL';
comment on column restaurant.restaurant_image_url is '店舗画像URL';
comment on column restaurant.latitude is '緯度';
comment on column restaurant.longitude is '経度';
comment on column restaurant.restaurant_status is '店舗ステータス';
comment on column restaurant.restauranat_address is '店舗アドレス';
comment on column restaurant.restaurant_memo is '店舗備考';

comment on table feedback is 'フィードバック';
comment on column feedback.feedback_id is 'フィードバックID';
comment on column feedback.device_id is 'デバイスID';
comment on column feedback.type is 'タイプ';
comment on column feedback.detail is '詳細';
comment on column feedback.treatment_status is '処理ステータス';
comment on column feedback.treatment_memo is '処理備考';

comment on table "抽選応募履歴" is '抽選応募履歴';
comment on column "抽選応募履歴".lottery_subs_history_id is '抽選応募履歴ID';
comment on column "抽選応募履歴".lottery_event_id is '抽選イベントID';
comment on column "抽選応募履歴".device_id is 'デバイスID';
comment on column "抽選応募履歴".lottery_status is '抽選ステータス:１：有効０：無効';
comment on column "抽選応募履歴".validity_flag is '有効フラグ:１：有効０：無効';

comment on table lottery_event is '抽選イベント:';
comment on column lottery_event.lottery_event_id is '抽選イベントID';
comment on column lottery_event.lottery_detail is '抽選詳細';
comment on column lottery_event.lottery_title is '抽選タイトル';
comment on column lottery_event.lottery_image is '抽選画像';
comment on column lottery_event.start_datetime is '応募開始日時';
comment on column lottery_event.end_datetime is '応募終了日時';
comment on column lottery_event.announcement_datetime is '結果発表日時';
comment on column lottery_event.coupon_id is 'クーポンID';

comment on table customer is '顧客';
comment on column customer.customer_id is '顧客ID';
comment on column customer.device_id is 'デバイスID';
comment on column customer.nick_name is 'ニックネーム';
