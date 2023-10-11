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
 * 用户
 *
 * @author clay
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("acl_user")
@ApiModel(value = "User对象", description = "用户")
public class User implements Serializable {

	@ApiModelProperty(value = "会员id")
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;

	@ApiModelProperty(value = "微信openid")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "昵称")
	private String nickName;

	@ApiModelProperty(value = "用户头像")
	private String salt;

	@ApiModelProperty(value = "用户签名")
	private String token;

	@ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
	private Boolean isDeleted;

	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;

	@ApiModelProperty(value = "更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;

}
