
CREATE TABLE TippersBigTable (

  ID BIGINT RANGE,

  LOCATION_ID char(255),
  LOCATION_X BIGINT,
  LOCATION_Y BIGINT,
  LOCATION_Z BIGINT,

  INFRASTRUCTURE_TYPE_ID char(255) RANGE,
  INFRASTRUCTURE_TYPE_NAME char(255),
  INFRASTRUCTURE_TYPE_DESCRIPTION char(255) ,

  INFRASTRUCTURE_ID char(255) RANGE,
  INFRASTRUCTURE_NAME char(255) ,
  INFRASTRUCTURE_INFRASTRUCTURE_TYPE_ID char(255),
  INFRASTRUCTURE_FLOOR BIGINT,

  INFRASTRUCTURE_LOCATION_INFRASTRUCTURE_ID char(255) RANGE,
  INFRASTRUCTURE_LOCATION_LOCATION_ID char(255),

  USER_GROUP_ID char(255) RANGE,
  USER_GROUP_NAME char(255),
  USER_GROUP_DESCRIPTION char(255) ,

  USER_ID char(255) RANGE,
  USER_EMAIL char(255)  ,
  USER_NAME char(255) ,
  USER_GOOGLE_AUTH_TOKEN char(255) ,

  USER_GROUP_MEMBERSHIP_USER_ID char(255) RANGE,
  USER_GROUP_MEMBERSHIP_USER_GROUP_ID char(255),

  SENSOR_TYPE_ID char(255) RANGE,
  SENSOR_TYPE_NAME char(255) ,
  SENSOR_TYPE_DESCRIPTION char(255) ,
  SENSOR_TYPE_MOBILITY char(255) ,
  SENSOR_TYPE_CAPTURE_FUNCTIONALITY char(255) ,
  SENSOR_TYPE_PAYLOAD_SCHEMA char(255),

  SENSOR_ID char(255) RANGE,
  SENSOR_NAME char(255) ,
  SENSOR_INFRASTRUCTURE_ID char(255) ,
  SENSOR_USER_ID char(255) ,
  SENSOR_SENSOR_TYPE_ID char(255) ,
  SENSOR_SENSOR_CONFIG char(255),

  COVERAGE_INFRASTRUCTURE_SENSOR_ID char(255),
  COVERAGE_INFRASTRUCTURE_INFRASTRUCTURE_ID char(255),

  PLATFORM_TYPE_ID char(255) RANGE,
  PLATFORM_TYPE_NAME char(255),
  PLATFORM_TYPE_DESCRIPTION char(255),

  PLATFORM_ID char(255) RANGE,
  PLATFORM_NAME char(255) ,
  PLATFORM_USER_ID char(255) ,
  PLATFORM_PLATFORM_TYPE_ID char(255) ,
  PLATFORM_HASHED_MAC char(255),

  OBSERVATION_ID char(255) RANGE,
  OBSERVATION_TIMESTAMP datetime RANGE,
  OBSERVATION_SENSOR_ID char(255) RANGE,
  OBSERVATION_temperature BIGINT ,
  OBSERVATION_clientId char(255) RANGE,
  OBSERVATION_currentMilliWatts BIGINT ,
  OBSERVATION_onTodaySeconds BIGINT ,


  SEMANTIC_OBSERVATION_TYPE_ID char(255) RANGE,
  SEMANTIC_OBSERVATION_TYPE_DESCRIPTION char(255) ,
  SEMANTIC_OBSERVATION_TYPE_NAME char(255),

  VIRTUAL_SENSOR_TYPE_ID char(255) RANGE,
  VIRTUAL_SENSOR_TYPE_NAME char(255) ,
  VIRTUAL_SENSOR_TYPE_DESCRIPTION char(255) ,
  VIRTUAL_SENSOR_TYPE_INPUT_TYPE_ID char(255) ,
  VIRTUAL_SENSOR_TYPE_SEMANTIC_OBSERVATION_TYPE_ID char(255),

  VIRTUAL_SENSOR_ID char(255) RANGE,
  VIRTUAL_SENSOR_NAME char(255) ,
  VIRTUAL_SENSOR_DESCRIPTION char(255) ,
  VIRTUAL_SENSOR_LANGUAGE char(255) ,
  VIRTUAL_SENSOR_PROJECT_NAME char(255) ,
  VIRTUAL_SENSOR_VIRTUAL_SENSOR_TYPE_ID char(255),

  ROW_TYPE char(64) RANGE

) ;

