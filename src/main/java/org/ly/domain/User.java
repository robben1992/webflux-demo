package org.ly.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.ly.controller.function.EnumValue;

@Data
@RequiredArgsConstructor
public class User {

    private String userName;
    @Range(min = 1, max = 100, message = "age must between 1 and 100")
    private int age;
    @EnumValue(intValues = {0, 1, 2}, message = "Invalid type value")
    private int type;
}
