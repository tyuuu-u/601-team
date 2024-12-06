package com.shijiuAPI.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParam {

    private String code;
    private String encryptedData;
    private String rawData;
    private String signature;
    private String iv;

}
