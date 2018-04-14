package com.zeroized.spider.domain;

/**
 * Created by Zero on 2018/3/28.
 */
public class Column {
    private String column;
    private String rule;
    private String type;

    public Column() {
    }

    public Column(String column, String rule, String type) {
        this.column = column;
        this.rule = rule;
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "Column{" +
                "column='" + column + '\'' +
                ", rule='" + rule + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
