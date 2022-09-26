package com.lucasdev3.crudbasicospring.enums;

public enum Regex {

    EXPENSE("^(expense|EXPENSE)+$"),
    REVENUE("^(revenue|REVENUE)+$");

    Regex(String s) {
    }
}
