package com.itamnesia.bhcrud.service;

public interface ConfirmationSender {

    void send(String phoneNumber, String code);

}
