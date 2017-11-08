/* Table creation statements for PostgreSQL */

CREATE TABLE LOCATION (
  ID varchar(255) NOT NULL,
  X float NOT NULL,
  Y float NOT NULL,
  Z float NOT NULL,
  PRIMARY KEY (ID)
) ;

CREATE TABLE INFRASTRUCTURE_TYPE (
  ID varchar(255) NOT NULL,
  DESCRIPTION varchar(255) DEFAULT NULL,
  NAME varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID)
) ;

CREATE TABLE INFRASTRUCTURE (
  NAME varchar(255) DEFAULT NULL,
  INFRASTRUCTURE_TYPE_ID varchar(255) DEFAULT NULL,
  ID varchar(255) NOT NULL,
  FLOOR integer NOT NULL,
  PRIMARY KEY (ID),
   FOREIGN KEY (INFRASTRUCTURE_TYPE_ID) REFERENCES INFRASTRUCTURE_TYPE (ID)
) ;

CREATE TABLE INFRASTRUCTURE_LOCATION (
  LOCATION_ID varchar(255) NOT NULL,
  INFRASTRUCTURE_ID varchar(255) NOT NULL,
   PRIMARY KEY(LOCATION_ID, INFRASTRUCTURE_ID),
   FOREIGN KEY (LOCATION_ID) REFERENCES LOCATION (ID),
   FOREIGN KEY (INFRASTRUCTURE_ID) REFERENCES INFRASTRUCTURE (ID)
) ;

CREATE TABLE PLATFORM_TYPE (
  ID varchar(255) NOT NULL,
  DESCRIPTION varchar(255) DEFAULT NULL,
  NAME varchar(255) DEFAULT NULL UNIQUE,
  PRIMARY KEY (ID)
) ;

CREATE TABLE USERS (
  EMAIL varchar(255) DEFAULT NULL UNIQUE,
  GOOGLE_AUTH_TOKEN varchar(255) DEFAULT NULL,
  NAME varchar(255) DEFAULT NULL,
  ID varchar(255) NOT NULL,
  PRIMARY KEY (ID)
 ) ;

CREATE TABLE USER_GROUP (
  ID varchar(255) NOT NULL,
  DESCRIPTION varchar(255) DEFAULT NULL,
  NAME varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID)
) ;

CREATE TABLE USER_GROUP_MEMBERSHIP (
  USER_ID varchar(255) NOT NULL,
  USER_GROUP_ID varchar(255) NOT NULL,
  PRIMARY KEY (USER_GROUP_ID, USER_ID),
   FOREIGN KEY (USER_ID) REFERENCES USERS (ID),
   FOREIGN KEY (USER_GROUP_ID) REFERENCES USER_GROUP (ID)
) ;

CREATE TABLE PLATFORM (
  ID varchar(255) NOT NULL,
  NAME varchar(255) DEFAULT NULL,
  USER_ID varchar(255) DEFAULT NULL,
  PLATFORM_TYPE_ID varchar(255) DEFAULT NULL,
  HASHED_MAC varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID),
   FOREIGN KEY (USER_ID) REFERENCES USERS (ID),
   FOREIGN KEY (PLATFORM_TYPE_ID) REFERENCES PLATFORM_TYPE (ID)
) ;

CREATE TABLE SENSOR_TYPE (
  ID varchar(255) NOT NULL,
  DESCRIPTION varchar(255) DEFAULT NULL,
  MOBILITY varchar(255) DEFAULT NULL,
  NAME varchar(255) DEFAULT NULL,
  CAPTURE_FUNCTIONALITY varchar(255) DEFAULT NULL,
  PAYLOAD_SCHEMA varchar(255),
  PRIMARY KEY (ID)
) ;

CREATE TABLE SENSOR (
  ID varchar(255) NOT NULL,
  NAME varchar(255) DEFAULT NULL,
  INFRASTRUCTURE_ID varchar(255) DEFAULT NULL,
  USER_ID varchar(255) DEFAULT NULL,
  SENSOR_TYPE_ID varchar(255) DEFAULT NULL,
  SENSOR_CONFIG varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID),
   FOREIGN KEY (SENSOR_TYPE_ID) REFERENCES SENSOR_TYPE (ID),
   FOREIGN KEY (INFRASTRUCTURE_ID) REFERENCES INFRASTRUCTURE (ID),
   FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
) ;

CREATE TABLE COVERAGE_INFRASTRUCTURE (
  SENSOR_ID varchar(255) NOT NULL,
  INFRASTRUCTURE_ID varchar(255) NOT NULL,
  PRIMARY KEY (INFRASTRUCTURE_ID, SENSOR_ID),
   FOREIGN KEY (INFRASTRUCTURE_ID) REFERENCES INFRASTRUCTURE (ID),
   FOREIGN KEY (SENSOR_ID) REFERENCES SENSOR (ID)
) ;

CREATE TABLE OBSERVATION (
  id varchar(255) NOT NULL,
  payload json DEFAULT NULL,
  timeStamp timestamp NOT NULL,
  sensor_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
   FOREIGN KEY (sensor_id) REFERENCES SENSOR (ID)
) ;

CREATE TABLE SEMANTIC_OBSERVATION_TYPE (
  ID varchar(255) NOT NULL,
  DESCRIPTION varchar(255) DEFAULT NULL,
  NAME varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID)
) ;

CREATE TABLE VIRTUAL_SENSOR_TYPE (
  ID varchar(255) NOT NULL,
  NAME varchar(255) DEFAULT NULL,
  DESCRIPTION varchar(255) DEFAULT NULL,
  INPUT_TYPE_ID varchar(255) DEFAULT NULL,
  SEMANTIC_OBSERVATION_TYPE_ID varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID),
   FOREIGN KEY (INPUT_TYPE_ID) REFERENCES SENSOR_TYPE (ID),
   FOREIGN KEY (SEMANTIC_OBSERVATION_TYPE_ID) REFERENCES SEMANTIC_OBSERVATION_TYPE (ID)
) ;

CREATE TABLE VIRTUAL_SENSOR (
  ID varchar(255) NOT NULL,
  NAME varchar(255) DEFAULT NULL,
  DESCRIPTION varchar(255) DEFAULT NULL,
  LANGUAGE varchar(255) DEFAULT NULL,
  PROJECT_NAME varchar(255) DEFAULT NULL,
  TYPE_ID varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID),
   FOREIGN KEY (TYPE_ID) REFERENCES VIRTUAL_SENSOR_TYPE (ID)
) ;

CREATE TABLE SEMANTIC_OBSERVATION (
  id varchar(255) NOT NULL,
  semantic_entity_id varchar(255) NOT NULL,
  payload json DEFAULT NULL,
  timeStamp timestamp NOT NULL,
  virtual_sensor_id varchar(255) DEFAULT NULL,
  type_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
   FOREIGN KEY (virtual_sensor_id) REFERENCES VIRTUAL_SENSOR (ID),
   FOREIGN KEY (type_id) REFERENCES SEMANTIC_OBSERVATION_TYPE (ID)
) ;
