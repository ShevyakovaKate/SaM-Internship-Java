CREATE TABLE GIFT_CERTIFICATE (
  id		                  BIGINT  IDENTITY ,
  gc_name                 varchar(80) NOT NULL,
  gc_description          varchar(2000),
  gc_price                numeric(5, 2),
  gc_date_of_creation     DATE NOT NULL,
  gc_date_of_modification DATE NOT NULL,
  gc_duration             int,
  PRIMARY KEY(id)
);

CREATE TABLE GIFT_TAG (
  id		                  BIGINT  IDENTITY ,
  gt_name                 varchar(80),
  PRIMARY KEY(id)
);

CREATE TABLE GIFT_CERTIFICATE_HAS_TAG (
  id		              BIGINT  IDENTITY ,
  gc_id               int NOT NULL,
  gt_id               int NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(gc_id) REFERENCES GIFT_CERTIFICATE (id),
  FOREIGN KEY(gt_id) REFERENCES GIFT_TAG (id)
);

CREATE TABLE ROLE (
  id                 BIGINT  IDENTITY ,
  name               varchar(80) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE USER (
  id                  BIGINT  IDENTITY ,
  login                varchar(80) NOT NULL,
  email                varchar(256) NOT NULL,
  password            varchar(60) NOT NULL,
  role_id             INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(role_id) REFERENCES ROLE (id)
);

CREATE TABLE "PAY_STATUS" (
  id                  BIGINT  IDENTITY ,
  status_name         varchar(80) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE "ORDER" (
  id                  BIGINT  IDENTITY ,
  gc_id               INT NOT NULL,
  user_id             INT NOT NULL,
  pay_status_id       INT NOT NULL,
  datetime            TIMESTAMP WITH TIME ZONE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(gc_id) REFERENCES GIFT_CERTIFICATE (id),
  FOREIGN KEY (user_id) REFERENCES USER (id),
  FOREIGN KEY (pay_status_id) REFERENCES PAY_STATUS (id)
);




