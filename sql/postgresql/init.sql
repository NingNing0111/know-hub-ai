-- 基础字段
-- create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     deleted     BOOLEAN               DEFAULT FALSE,
--     creator     VARCHAR(255),
--     updater     VARCHAR(255)

CREATE EXTENSION IF NOT EXISTS vector;
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
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
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
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
);

COMMENT ON TABLE system_role_permission IS '角色-权限关联表';
COMMENT ON COLUMN system_role_permission.role_id IS '角色ID';
COMMENT ON COLUMN system_role_permission.permission_id IS '权限ID';
COMMENT ON COLUMN system_role_permission.create_time IS '创建时间';
COMMENT ON COLUMN system_role_permission.creator IS '创建人';

CREATE TABLE origin_file_source
(
    id           TEXT PRIMARY KEY,                           -- 文件唯一标识
    file_name    TEXT    NOT NULL,                           -- 文件名
    path         TEXT    NOT NULL,                           -- 文件存储路径
    is_image     BOOLEAN NOT NULL,                           -- 是否为图片文件
    bucket_name  TEXT    NOT NULL,                           -- 对象存储桶名称
    object_name  TEXT    NOT NULL,                           -- 对象存储中的文件名
    content_type TEXT    NOT NULL,                           -- 文件的 MIME 类型
    size         BIGINT  NOT NULL,                           -- 文件大小（字节）
    md5          TEXT    NOT NULL,                           -- 文件 MD5 哈希值
    images       TEXT    NOT NULL DEFAULT '[]',              -- 文档内包含的图片列表（JSON 数组）
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
);

COMMENT ON TABLE origin_file_source IS '存储原始文件资源的表';
COMMENT ON COLUMN origin_file_source.id IS '文件唯一标识';
COMMENT ON COLUMN origin_file_source.file_name IS '文件名';
COMMENT ON COLUMN origin_file_source.path IS '文件存储路径';
COMMENT ON COLUMN origin_file_source.is_image IS '是否为图片文件';
COMMENT ON COLUMN origin_file_source.bucket_name IS '对象存储桶名称';
COMMENT ON COLUMN origin_file_source.object_name IS '对象存储中的文件名';
COMMENT ON COLUMN origin_file_source.content_type IS '文件的 MIME 类型';
COMMENT ON COLUMN origin_file_source.size IS '文件大小（字节）';
COMMENT ON COLUMN origin_file_source.md5 IS '文件 MD5 哈希值';
COMMENT ON COLUMN origin_file_source.images IS '文档内包含的图片列表（JSON 数组）';
COMMENT ON COLUMN origin_file_source.create_time IS '记录创建时间';
COMMENT ON COLUMN origin_file_source.update_time IS '记录更新时间';
COMMENT ON COLUMN origin_file_source.deleted IS '是否被逻辑删除（软删除）';


CREATE TABLE chat_message
(
    id              TEXT PRIMARY KEY,                           -- 信息ID，唯一标识
    conversation_id TEXT    NOT NULL,                           -- 对话ID
    message_no      INT     NOT NULL,                           -- 对话序号
    has_media       BOOLEAN NOT NULL,                           -- 是否携带附件
    content         TEXT    NOT NULL,                           -- 对话内容
    role            TEXT    NOT NULL,                           -- 角色
    resource_ids    TEXT    NOT NULL DEFAULT '[]',              -- 附件列表
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
);

COMMENT ON TABLE chat_message IS '对话消息';
COMMENT ON COLUMN chat_message.id IS '信息ID，唯一标识';
COMMENT ON COLUMN chat_message.conversation_id IS '对话ID';
COMMENT ON COLUMN chat_message.message_no IS '消息序列号';
COMMENT ON COLUMN chat_message.has_media IS '是否携带附件';
COMMENT ON COLUMN chat_message.content IS '内容';
COMMENT ON COLUMN chat_message.role IS '角色';
COMMENT ON COLUMN chat_message.resource_ids IS '资源ID';
COMMENT ON COLUMN chat_message.create_time IS '记录创建时间';
COMMENT ON COLUMN chat_message.update_time IS '记录更新时间';
COMMENT ON COLUMN chat_message.deleted IS '是否被逻辑删除（软删除）';

CREATE TABLE chat_conversation
(
    id          TEXT PRIMARY KEY,                    -- 对话ID
    title       TEXT   NOT NULL,                     -- 标题
    userId      BIGINT NOT NULL,                     -- 发起人
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
);

COMMENT ON TABLE chat_conversation IS '对话消息';
COMMENT ON COLUMN chat_conversation.id IS '信息ID，唯一标识';
COMMENT ON COLUMN chat_message.create_time IS '记录创建时间';
COMMENT ON COLUMN chat_message.update_time IS '记录更新时间';
COMMENT ON COLUMN chat_message.deleted IS '是否被逻辑删除（软删除）';


CREATE TABLE knowledge_base
(
    id          varchar(32)  PRIMARY KEY       NOT NULL,
    name        varchar(100) NOT NULL,
    description TEXT,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
);

CREATE TABLE document_entity
(
    id        BIGSERIAL PRIMARY KEY,
    file_name VARCHAR(512) NOT NULL,
    path      TEXT NOT NULL,
    base_id   varchar(32)         NOT NULL,
    is_embedding BOOLEAN DEFAULT FALSE,
    resource_id varchar(64) NOT NULL ,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted     BOOLEAN               DEFAULT FALSE,
    creator     VARCHAR(255),
    updater     VARCHAR(255)
);
