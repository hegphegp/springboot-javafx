package com.codingfly.generator;

public class Models {

    public static class DBConfig {
        private String id;
        private String dbType;   // MYSQL,POSTGRES
        private String name;
        private String username;
        private String password;
        private String url;

        public DBConfig() { }

        public DBConfig(String id, String dbType, String name, String username, String password, String url) {
            this.id = id;
            this.dbType = dbType;
            this.name = name;
            this.username = username;
            this.password = password;
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDbType() {
            return dbType;
        }

        public void setDbType(String dbType) {
            this.dbType = dbType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DBConfig{" +
                    "id='" + id + '\'' +
                    ", dbType='" + dbType + '\'' +
                    ", name='" + name + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }


}
