create table STANDARD
(
  id           NUMBER(10) not null,
  std_num      VARCHAR2(50) not null,
  zhname       VARCHAR2(40) not null,
  version      VARCHAR2(10) not null,
  keys         VARCHAR2(50) not null,
  release_date DATE,
  impl_date    DATE,
  package_path VARCHAR2(100) not null
)
tablespace MYDOLL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column STANDARD.id
  is '标准id';
comment on column STANDARD.std_num
  is '标准号';
comment on column STANDARD.zhname
  is '中文名称';
comment on column STANDARD.version
  is '版本';
comment on column STANDARD.keys
  is '关键字/词';
comment on column STANDARD.release_date
  is '发布日期';
comment on column STANDARD.impl_date
  is '实施日期';
comment on column STANDARD.package_path
  is '附件的相对地址';
alter table STANDARD
  add constraint PK_STD primary key (ID)
  using index 
  tablespace MYDOLL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STANDARD disable all triggers;
delete from STANDARD;
commit;

insert into STANDARD (id, std_num, zhname, version, keys, release_date, impl_date, package_path)
values (1, 'GB 6657.1-2014', '玩具安全 第1部分：基本规范', '2014', '玩具、基本规范', to_date('01-04-2017', 'dd-mm-yyyy'), to_date('01-05-2017', 'dd-mm-yyyy'), '1491625014069.jpg');
commit;

-- Create sequence 
create sequence STANDARD_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 2
increment by 1
nocache
order;