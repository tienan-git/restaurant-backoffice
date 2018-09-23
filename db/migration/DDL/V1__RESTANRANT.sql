
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

-- ゆたぽん捕獲情報
create table yutapon_history (
    yutapon_history_id bigint auto_increment not null comment 'ゆたぽん捕獲ID'
  , device_id VARCHAR(40) not null comment '端末識別ID'
  , yutapon_type VARCHAR(20) not null comment 'ゆたぽんタイプ'
  , spot_name VARCHAR(320) not null comment '施設名'
  , date_time VARCHAR(80) not null comment '捕獲日時'
  , status INT(1) not null comment 'ステータス'
  , version_no bigint not null comment 'バージョン番号'
  , created_at DATETIME(6) not null comment '登録日時'
  , created_by VARCHAR(15) not null comment '登録者'
  , updated_at DATETIME(6) not null comment '更新日時'
  , updated_by VARCHAR(15) not null comment '更新者'
  , is_actived INT(1) not null comment 'アクティブフラグ:0：無効、1：有効'
  , constraint yutapon_history_PKC primary key (yutapon_history_id)
) comment 'ゆたぽん捕獲情報:' ;
