package org.ly.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Document("city")
public class City2 implements Serializable {

    private static final long serialVersionUID = 3984528925260974180L;
    /**
     * 城市编码
     */
    @Id
    private String id;

    /**
     * 省份编码
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;

}
