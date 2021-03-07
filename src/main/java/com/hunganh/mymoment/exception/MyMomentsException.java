package com.hunganh.mymoment.exception;

/**
 * @Author: Tran Tuan Anh
 * @Created: Saturday, 3/6/2021 4:22 PM
 **/

public class MyMomentsException extends RuntimeException {
    public MyMomentsException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public MyMomentsException(String exMessage) {
        super(exMessage);
    }
}
