-- Project Name : ChineseGourmet
-- Date/Time    : 2018/09/24 15:38:48
-- Author       : beyon
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2

/*
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
*/

-- 権限
--* RestoreFromTempTable
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
) comment '権限' ;

-- ユーザー
--* RestoreFromTempTable
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
) comment 'ユーザー' ;

-- クーポン保有
--* RestoreFromTempTable
create table coupon_hold (
  coupon_hold_id bigint auto_increment not null comment 'クーポン保有ID'
  , coupon_id bigint not null comment 'クーポンID'
  , customer_id bigint not null comment '顧客ID'
  , get_datetime DATETIME comment '取得日時'
  , coupon_hold_status CHAR(1) comment 'クーポン保有ステータス:０：追加済み　１：削除（利用）済み'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint coupon_hold_PKC primary key (coupon_hold_id)
) comment 'クーポン保有' ;

-- クーポン
--* RestoreFromTempTable
create table coupon (
  coupon_id bigint auto_increment not null comment 'クーポンID'
  , restaurant_id bigint not null comment '店舗ID'
  , title VARCHAR(200) comment 'タイトル'
  , detail VARCHAR(200) comment '詳細'
  , start_date DATE comment '有効開始日'
  , end_date DATE comment '有効終了日'
  , total_amount INT comment 'クーポン枚数'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint coupon_PKC primary key (coupon_id)
) comment 'クーポン' ;

-- 店舗
--* RestoreFromTempTable
create table restaurant (
  restaurant_id bigint auto_increment not null comment '店舗ID'
  , restaurant_name VARCHAR(20) comment '店舗名'
  , manager VARCHAR(20) comment '店舗担当者'
  , telephone_phone VARCHAR(15) comment '店舗連絡先'
  , business_hours VARCHAR(120) comment '店舗営業時間'
  , site_url VARCHAR(100) comment '店舗URL'
  , image_url VARCHAR(100) comment '店舗画像URL'
  , latitude DOUBLE comment '緯度'
  , longitude DOUBLE comment '経度'
  , restaurant_status CHAR(1) comment '店舗ステータス:０：有効　１：無効'
  , address VARCHAR(200) comment '店舗アドレス'
  , memo VARCHAR(200) comment '店舗備考'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint restaurant_PKC primary key (restaurant_id)
) comment '店舗' ;

-- フィードバック
--* RestoreFromTempTable
create table feedback (
  feedback_id bigint auto_increment not null comment 'フィードバックID'
  , customer_id bigint not null comment '顧客ID'
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
) comment 'フィードバック' ;

-- 抽選応募
--* RestoreFromTempTable
create table lottery_application (
  lottery_application_id bigint auto_increment not null comment '抽選応募ID'
  , lottery_id bigint comment '抽選ID'
  , customer_id bigint not null comment '顧客ID'
  , lottery_application_status CHAR(1) comment '抽選応募ステータス:０：応募済み　１：あたり'
  , validity_flag CHAR(1) comment '有効フラグ:０：無効１：有効'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint lottery_application_PKC primary key (lottery_application_id)
) comment '抽選応募' ;

-- 抽選
--* RestoreFromTempTable
create table lottery (
  lottery_id bigint auto_increment not null comment '抽選ID'
  , lottery_title VARCHAR(80) comment '抽選タイトル'
  , lottery_detail VARCHAR(80) comment '抽選詳細'
  , lottery_image_url VARCHAR(100) comment '抽選画像URL'
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
  , constraint lottery_PKC primary key (lottery_id)
) comment '抽選' ;

-- 顧客
--* RestoreFromTempTable
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
) comment '顧客' ;
