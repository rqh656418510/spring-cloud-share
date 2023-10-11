package com.clay.acl.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色
 *
 * @author clay
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("acl_user_role")
@ApiModel(value = "UserRole对象", description = "用户角色")
public class UserRole implements Serializable {

	@ApiModelProperty(value = "主键id")
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;

	@ApiModelProperty(value = "角色id")
	private String roleId;

	@ApiModelProperty(value = "用户id")
	private String userId;

	@ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
	private Boolean isDeleted;

	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;

	@ApiModelProperty(value = "更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;

}
