package com.clay.boot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author clay
 */
@Schema(title = "部门信息")
@Data
public class Dept {

    @Schema(title = "部门id")
    private Long id;

    @Schema(title = "部门名字")
    private String deptName;

}
