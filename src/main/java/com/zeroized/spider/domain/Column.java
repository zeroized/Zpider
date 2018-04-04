package com.zeroized.spider.domain;

/**
 * Created by Zero on 2018/3/28.
 */
public class Column {
    private String column;
    private String rule;

    public Column() {
    }

    public Column(String column, String rule) {
        this.column = column;
        this.rule = rule;
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
                '}';
    }
}
