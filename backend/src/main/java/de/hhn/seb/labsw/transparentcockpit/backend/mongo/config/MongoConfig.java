package de.hhn.seb.labsw.transparentcockpit.backend.mongo.config;

public class MongoConfig {

    private final String url;
    private final int port;

    private final String database;

    private final String user;
    private final String password;

    public MongoConfig(String url, int port,
                       String database,
                       String user, String password) {
        this.url = url;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
