package com.example.bootcampodev.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    GENERIC_EXCEPTION(1,"bilinmeyen bir sorun oluştu"),

    MOVIE_NOT_FOUND_EXCEPTION(1001, "film bulunamadı"),
    ACTOR_NOT_FOUND_EXCEPTION(1001, "actor bulunamadı"),
    RATE_NOT_FOUND_EXCEPTION(1001, "puanlama bulunamadı"),
    WATCH_LIST_NOT_FOUND_EXCEPTION(1001, "izleme listesi bulunamadı"),
    MEMBER_NOT_FOUND_EXCEPTION(1001, "kullanıcı bulunamadı"),


    COLLECTION_SIZE_EXCEPTION(2001,"Liste boyutları uyuşmuyor");


    private final Integer code;
    private final String message;
}
