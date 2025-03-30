CREATE TABLE system_user
(
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
);

COMMENT ON TABLE system_user IS '系统用户表';
COMMENT ON COLUMN system_user.id IS '用户id';
COMMENT ON COLUMN system_user.username IS '用户名';
COMMENT ON COLUMN system_user.password IS '密码';
COMMENT ON COLUMN system_user.create_time IS '创建时间';
COMMENT ON COLUMN system_user.update_time IS '更新时间';
COMMENT ON COLUMN system_user.deleted IS '是否删除（false-未删除，true-已删除）';
COMMENT ON COLUMN system_user.creator IS '创建人';
COMMENT ON COLUMN system_user.updater IS '更新人';


CREATE TABLE system_role
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
);

COMMENT ON TABLE system_role IS '系统角色表';
COMMENT ON COLUMN system_role.id IS '角色id';
COMMENT ON COLUMN system_role.name IS '角色名';
COMMENT ON COLUMN system_role.description IS '角色描述';
COMMENT ON COLUMN system_role.create_time IS '创建时间';
COMMENT ON COLUMN system_role.update_time IS '更新时间';
COMMENT ON COLUMN system_role.deleted IS '是否删除（false-未删除，true-已删除）';
COMMENT ON COLUMN system_role.creator IS '创建人';
COMMENT ON COLUMN system_role.updater IS '更新人';

CREATE TABLE system_permission
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
);

COMMENT ON TABLE system_permission IS '系统权限表';
COMMENT ON COLUMN system_permission.id IS '权限ID';
COMMENT ON COLUMN system_permission.name IS '权限名称';
COMMENT ON COLUMN system_permission.description IS '权限描述';
COMMENT ON COLUMN system_permission.create_time IS '创建时间';
COMMENT ON COLUMN system_permission.update_time IS '更新时间';
COMMENT ON COLUMN system_permission.deleted IS '是否删除（false-未删除，true-已删除）';
COMMENT ON COLUMN system_permission.creator IS '创建人';
COMMENT ON COLUMN system_permission.updater IS '更新人';

CREATE TABLE system_user_role
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT    NOT NULL,
    role_id     BIGINT    NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    creator     VARCHAR(255)
);

COMMENT ON TABLE system_user_role IS '用户-角色关联表';
COMMENT ON COLUMN system_user_role.user_id IS '用户ID';
COMMENT ON COLUMN system_user_role.role_id IS '角色ID';
COMMENT ON COLUMN system_user_role.create_time IS '创建时间';
COMMENT ON COLUMN system_user_role.creator IS '创建人';

CREATE TABLE system_role_permission
(
    id            BIGSERIAL PRIMARY KEY,
    role_id       BIGINT    NOT NULL,
    permission_id BIGINT    NOT NULL,
    create_time   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    creator       VARCHAR(255)
);

COMMENT ON TABLE system_role_permission IS '角色-权限关联表';
COMMENT ON COLUMN system_role_permission.role_id IS '角色ID';
COMMENT ON COLUMN system_role_permission.permission_id IS '权限ID';
COMMENT ON COLUMN system_role_permission.create_time IS '创建时间';
COMMENT ON COLUMN system_role_permission.creator IS '创建人';


