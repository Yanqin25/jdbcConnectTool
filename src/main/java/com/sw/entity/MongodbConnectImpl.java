package com.sw.entity;

import com.mongodb.*;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.sw.utils.StringUtils;
import lombok.Data;
import org.bson.Document;

@Data
public class MongodbConnectImpl implements Connect {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String database;
    private String authDatabase;
    private String sql;
    @Override
    public void connect() {
        MongoClient client = new MongoClient(host, port);
        try {
            mongoConnectCheckDatabase(client);
            System.out.println("无密登录");
        } catch (MongoCommandException e) {
            if (e.getMessage().contains("Unauthorized")) {
                MongoCredential credential = MongoCredential.createCredential(username, authDatabase, password.toCharArray());
                MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();
                client = new MongoClient(new ServerAddress(host, port), credential, options);
                mongoConnectCheckDatabase(client);
            } else {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
    private boolean databaseExist(MongoIterable<String> dbs, String database) {
        boolean flag = false;
        if (StringUtils.isEmpty(database)) {
            return true;
        }
        MongoCursor<String> iterator = dbs.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(database)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private void mongoConnectCheckDatabase(MongoClient client) {
        MongoIterable<String> dbs = client.listDatabaseNames();
        boolean flag = databaseExist(dbs, database);
        if (!flag) {
            System.out.println("连接成功，但是你所选择的数据库不存在：" + database);
        }
        System.out.println("command（sql）执行结果是：");
        System.out.println("--------------------------------------------------");
        MongoDatabase db = client.getDatabase(authDatabase);
        String[] split = sql.split("\\|");
        String command=split[0];
        String filter;
        if (split.length>1) {
            filter = split[1];
            System.out.println("\"" + filter + "\"" + ": " + db.runCommand(new Document(command, 1)).get(filter));
        } else {
            System.out.println(db.runCommand(new Document(command, 1)));
        }
        System.out.println("--------------------------------------------------");
    }
}
