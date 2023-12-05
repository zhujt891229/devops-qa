package com.zjt.qas.common;

public interface Constant {
    String TOKEN_PREFIX_LOGIN_ID = "qas:qa_api_loginId:";
    String TOKEN_PREFIX_USER_ID = "qas:qa_api_userId";

    Integer DEFAULT_PAGE_SIZE=10;
    Integer DEFAULT_PAGE_NUM = 1;

    String TXT = ".txt";
    String XLS= ".xls";
    String XLSX=".xlsx";
    String CSV=".csv";

    Integer SIZE=20*1024*1024;

    Integer SUCCESS=0;
    Integer ERROR = 1;

    String SALT = "1a2b3c4d";
}
