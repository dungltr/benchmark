package edu.uci.ics.tippers.schema.cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import edu.uci.ics.tippers.common.Database;
import edu.uci.ics.tippers.connection.cassandra.CassandraConnectionManager;
import edu.uci.ics.tippers.exception.BenchmarkException;
import edu.uci.ics.tippers.schema.BaseSchema;

import java.util.ArrayList;

public class CassandraSchema extends BaseSchema {

    private Session session;
    private String space_name;

    public CassandraSchema(int mapping, String dataDir) {
        super(mapping, dataDir);
        session = CassandraConnectionManager.getInstance().getSession();
        space_name = CassandraConnectionManager.getInstance().getKeySpaceName();
        create_keyspace(space_name, "SimpleStrategy");
    }

    public void create_keyspace(String spacename, String VarA){
        StringBuilder createspace = new StringBuilder();
        createspace.append("create keyspace if not exists ")
                .append(spacename)
                .append(" with replication={'class':'"+ VarA + "', 'replication_factor' : 1};");
        String create = createspace.toString();
        session.execute(create);
    }

    //FLAG is used to decide whether to order
    public void create_keycolmns(String primary_key, String order_condition, ArrayList<String> list,
                                 String space_name, String table_name, int FLAG) {
        int i;
        StringBuilder createtable = new StringBuilder();
        createtable.append("create table if not exists " + space_name + "." + table_name + "(");
        for (i = 0; i < list.size(); i++) {
            createtable.append(list.get(i) + ",");
        }
        createtable.append("PRIMARY KEY" + primary_key + ") ");
        if (FLAG == 1) {
            createtable.append("WITH CLUSTERING ORDER BY " + order_condition);
        }
        String tab = createtable.toString();
        session.execute(tab);
    }

    @Override
    public Database getDatabase() {
        return Database.CASSANDRA;
    }

    @Override
    public void createSchema() throws BenchmarkException {

        String primary_key;
        String order_condition;
        String table_name;
        ArrayList<String> list = new ArrayList<String>();

        //Add Group
        table_name = "Group";
        list.add("id varchar");
        list.add("name varchar");
        list.add("description varchar");
        primary_key = "(id)";
        order_condition = " ";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //Add User
        list.clear();
        table_name = "User";
        list.add("emailId varchar");
        list.add("name varchar");
        list.add("groupIds list<varchar>");
        list.add("id varchar");
        list.add("googleAuthToken varchar");
        primary_key = "(emailId)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //Add Location
        list.clear();
        table_name = "Location";
        list.add("id varchar");
        list.add("x double");
        list.add("y double");
        list.add("z double");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //InfrastructureType
        list.clear();
        table_name = "InfrastructureType";
        list.add("id varchar");
        list.add("name varchar");
        list.add("description varchar");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //Infrastructure
        list.clear();
        table_name = "Infrastructure";
        list.add("id varchar");
        list.add("name varchar");
        list.add("type_ varchar");
        list.add("floor INT");
        list.add("geometry list<varchar>");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //PlatformType
        list.clear();
        table_name = "PlatformType";
        list.add("id varchar");
        list.add("name varchar");
        list.add("description varchar");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //Platform
        list.clear();
        table_name = "Platform";
        list.add("id varchar");
        list.add("name varchar");
        list.add("ownerId varchar");
        list.add("typeId varchar");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //SensorType
        list.clear();
        table_name = "SensorType";
        list.add("id varchar");
        list.add("name varchar");
        list.add("description varchar");
        list.add("mobility varchar");
        list.add("captureFunctionality varchar");
        list.add("payloadSchema varchar");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //Sensor
        list.clear();
        table_name = "Sensor";
        list.add("id varchar");
        list.add("name varchar");
        list.add("sensorConfig varchar");
        list.add("typeId varchar");
        list.add("infrastructure varchar");
        list.add("platformId varchar");
        list.add("ownerId varchar");
        list.add("entitiesCovered list<varchar>");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //SemanticObservationType
        list.clear();
        table_name = "SemanticObservationType";
        list.add("id varchar");
        list.add("name varchar");
        list.add("description varchar");
        list.add("payloadschema varchar");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //VirtualSensorType
        list.clear();
        table_name = "VirtualSensorType";
        list.add("id varchar");
        list.add("name varchar");
        list.add("description varchar");
        list.add("inputTypeId varchar");
        list.add("semanticObservationTypeId varchar");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        //VirtualSensor
        list.clear();
        table_name = "VirtualSensor";
        list.add("id varchar");
        list.add("name varchar");
        list.add("typeId varchar");
        list.add("description varchar");
        list.add("language varchar");
        list.add("projectName varchar");
        primary_key = "(id)";
        order_condition = "";
        create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

        switch(mapping) {
            case 1:
                //Observation & SemanticObservation they are in one single table, mapping 1
                list.clear();
                table_name = "Observation";
                list.add("typeId varchar");
                list.add("sensorId varchar");
                list.add("timestamp varchar");
                list.add("payload text");
                primary_key = "((sensorId, typeId), timestamp)";
                order_condition = "";
                create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);
                break;
            case 2:
                //Observation & SemanticObservation they are in one single table, mapping 2
                list.clear();
                table_name = "Observation";
                list.add("id varchar");
                list.add("sensorId varchar");
                list.add("timeStamp varchar");
                list.add("payload varchar");
                primary_key = "(sensorId, timestamp)";
                order_condition = "";
                create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);
                break;

            case 3:
				list.clear();
				table_name = "Observation";
				list.add("id varchar");
				list.add("sensorId varchar");
				list.add("timestamp varchar");
				list.add("payload varchar");
				primary_key = "((sensor, id), timestamp)";
				order_condition = "";
				create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);

            case 4:
				list.clear();
				table_name = "Observation";
				list.add("id varchar");
				list.add("sensorId varchar");
				list.add("timestamp varchar");
				list.add("payload varchar");
				primary_key = "((sensor, id), timestamp)";
				order_condition = "";
				create_keycolmns(primary_key, order_condition, list, space_name, table_name, 0);
        }
    }

    @Override
    public void dropSchema() throws BenchmarkException {

    }
}
