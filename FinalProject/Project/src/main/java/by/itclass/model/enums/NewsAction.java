package by.itclass.model.enums;

import by.itclass.model.db.SQLRequest;

public enum NewsAction {
    ADD(SQLRequest.INSERT_NEWS),
    EDIT(SQLRequest.UPDATE_NEWS_BY_ID),
    OTHER;

    private String sql;

    NewsAction() {
    }

    NewsAction(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
