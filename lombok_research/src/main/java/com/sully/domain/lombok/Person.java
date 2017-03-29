package com.sully.domain.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Created by lei.s on 2017/3/28.
 */
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String id;
    private String name;
    private String mail;
}
