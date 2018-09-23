-- Project Name : ChineseGourmet
-- Date/Time    : 2018/09/23 17:54:32
-- Author       : luoq1
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2

-- 権限
create table authority (
  authority_id bigint auto_increment not null comment '権限ID'
  , role_id bigint not null comment 'ロールID'
  , authority VARCHAR(120) not null comment '権限'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint authority_PKC primary key (authority_id)
) comment '権限:' ;

-- ユーザー
create table user (
  user_id bigint auto_increment not null comment 'ユーザーID'
  , user_name VARCHAR(120) not null comment 'ユーザー名'
  , email VARCHAR(320) not null comment 'メールアドレス'
  , password VARCHAR(80) not null comment 'パスワード'
  , role_id bigint not null comment 'ロールID'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint user_PKC primary key (user_id)
) comment 'ユーザー:' ;

-- クーポン保持
create table coupon_hold (
  coupon_hold_id bigint auto_increment not null comment 'クーポン保持ID'
  , coupon_id bigint not null comment 'クーポンID'
  , device_id VARCHAR(50) not null comment 'デバイスID'
  , coupon_get_datetime DATETIME comment 'クーポン取得日時'
  , coupon_status CHAR(1) comment 'クーポンステータス'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint coupon_hold_PKC primary key (coupon_hold_id)
) comment 'クーポン保持:' ;

-- クーポン
create table coupon (
  coupon_id bigint auto_increment not null comment 'クーポンID'
  , restaurant_id bigint not null comment '店舗ID'
  , title VARCHAR(200) comment 'タイトル'
  , detail VARCHAR(200) comment '詳細'
  , coupon_start_date DATE comment '有効開始日'
  , coupon_end_date DATE comment '有効終了日'
  , coupon_total_amount INT comment 'クーポン枚数'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint coupon_PKC primary key (coupon_id)
) comment 'クーポン:' ;

-- 店舗
create table restaurant (
  restaurant_id bigint auto_increment not null comment '店舗ID'
  , restaurant_name VARCHAR(20) comment '店舗名'
  , restaurant_manager VARCHAR(20) comment '店舗担当者'
  , restaurant_phone VARCHAR(15) comment '店舗連絡先'
  , restaurant_open_time VARCHAR(200) comment '店舗営業時間'
  , restaurant_url VARCHAR(100) comment '店舗URL'
  , restaurant_image_url VARCHAR(100) comment '店舗画像URL'
  , latitude FLOAT comment '緯度'
  , longitude FLOAT comment '経度'
  , restaurant_status CHAR(1) comment '店舗ステータス'
  , restaurant_address VARCHAR(200) comment '店舗アドレス'
  , restaurant_memo VARCHAR(200) comment '店舗備考'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint restaurant_PKC primary key (restaurant_id)
) comment '店舗:' ;

-- フィードバック
create table feedback (
  feedback_id bigint auto_increment not null comment 'フィードバックID'
  , device_id VARCHAR(50) not null comment 'デバイスID'
  , type VARCHAR(2) comment 'タイプ'
  , detail VARCHAR(200) comment '詳細'
  , treatment_status CHAR(1) comment '処理ステータス'
  , treatment_memo VARCHAR(200) comment '処理備考'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint feedback_PKC primary key (feedback_id)
) comment 'フィードバック:' ;

-- 抽選応募履歴
create table lottery_history (
  lottery_subs_history_id bigint auto_increment not null comment '抽選応募履歴ID'
  , lottery_event_id bigint comment '抽選イベントID'
  , device_id VARCHAR(50) not null comment 'デバイスID'
  , lottery_status CHAR(1) comment '抽選ステータス:１：有効０：無効'
  , validity_flag CHAR(1) comment '有効フラグ:１：有効０：無効'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint lottery_history_PKC primary key (lottery_subs_history_id)
) comment '抽選応募履歴:' ;

-- 抽選イベント
create table lottery_event (
  lottery_event_id bigint auto_increment not null comment '抽選イベントID'
  , lottery_detail VARCHAR(200) comment '抽選詳細'
  , lottery_title VARCHAR(80) comment '抽選タイトル'
  , lottery_image_url VARCHAR(100) comment '抽選画像URL'
  , lottery_image VARCHAR(2048) comment '抽選画像'
  , start_datetime DATETIME comment '応募開始日時'
  , end_datetime DATETIME comment '応募終了日時'
  , announcement_datetime DATETIME comment '結果発表日時'
  , coupon_id bigint comment 'クーポンID'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint lottery_event_PKC primary key (lottery_event_id)
) comment '抽選イベント:' ;

-- 顧客
create table customer (
  customer_id bigint auto_increment not null comment '顧客ID'
  , device_id VARCHAR(50) not null comment 'デバイスID'
  , nick_name VARCHAR(20) comment 'ニックネーム'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint customer_PKC primary key (customer_id)
) comment '顧客:' ;
