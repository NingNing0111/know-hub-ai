CREATE TABLE `system_user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`    VARCHAR(255) NOT NULL COMMENT '用户名',
    `password`    VARCHAR(255) NOT NULL COMMENT '密码',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT(1)            DEFAULT 0 COMMENT '是否删除（0-未删除，1-已删除）',
    `creator`     VARCHAR(255) COMMENT '创建人',
    `updater`     VARCHAR(255) COMMENT '更新人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统用户表';

CREATE TABLE `system_role`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `name`        VARCHAR(255) NOT NULL COMMENT '角色名',
    `description` VARCHAR(500) COMMENT '角色描述',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT(1)            DEFAULT 0 COMMENT '是否删除（0-未删除，1-已删除）',
    `creator`     VARCHAR(255) COMMENT '创建人',
    `updater`     VARCHAR(255) COMMENT '更新人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统角色表';

CREATE TABLE `system_permission`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    `name`        VARCHAR(255) NOT NULL COMMENT '权限名称',
    `description` VARCHAR(500) COMMENT '权限描述',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT(1)            DEFAULT 0 COMMENT '是否删除（0-未删除，1-已删除）',
    `creator`     VARCHAR(255) COMMENT '创建人',
    `updater`     VARCHAR(255) COMMENT '更新人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统权限表';


-- 用户-角色关联表
CREATE TABLE `system_user_role`
(
    `id`          BIGINT    NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT    NOT NULL COMMENT '用户ID',
    `role_id`     BIGINT    NOT NULL COMMENT '角色ID',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `creator`     VARCHAR(255) COMMENT '创建人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户-角色关联表';

-- 角色-权限关联表
CREATE TABLE `system_role_permission`
(
    `id`            BIGINT    NOT NULL AUTO_INCREMENT,
    `role_id`       BIGINT    NOT NULL COMMENT '角色ID',
    `permission_id` BIGINT    NOT NULL COMMENT '权限ID',
    `create_time`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `creator`       VARCHAR(255) COMMENT '创建人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色-权限关联表';