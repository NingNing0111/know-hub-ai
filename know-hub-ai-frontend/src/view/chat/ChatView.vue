<template>
  <div id="chat-view">
    <el-scrollbar ref="myScrollbar" always>
      <div ref="innerElement">
        <el-card v-for="item in messageList" class="scrollbar-item">
          <template #header>
            <div class="card-header" v-if="item.role === 'system'">
              <el-image :src="System" style="width: 50px; margin-right: 20px" />
              <span style="font-size: large; font-weight: 800">系统设置</span>
            </div>
            <div class="card-header" v-if="item.role === 'assistant'">
              <el-image :src="Robot" style="width: 50px; margin-right: 20px" />
              <div>
                <el-button type="primary" size="small">复制</el-button>
                <el-button type="warning" size="small">删除</el-button>
              </div>
            </div>
            <div class="card-header" v-if="item.role === 'user'">
              <el-image :src="User" style="width: 50px; margin-right: 20px" />
              <div>
                <el-button type="primary" size="small">复制</el-button>
                <el-button type="warning" size="small">删除</el-button>
              </div>
            </div>
          </template>
          <MDView v-if="item.content !== ''" :content="item.content" />
          <!-- <div v-if="item.content !== ''" v-html="item.content"></div> -->
          <div v-else>AI思考中...</div>
        </el-card>
      </div>
    </el-scrollbar>

    <div class="chat-footer">
      <el-input
        v-model="input"
        type="textarea"
        autofocus
        :autosize="{ minRows: 1, maxRows: 4 }"
        resize="none"
        placeholder="请输入对话内容，换行请使用Shift+Enter"
        class="input-box"
        @keydown="handleKeydown"
      />
      <div style="display: flex; justify-content: right; margin: 10px">
        <el-button type="warning" @click="cleanMessage">清除对话</el-button>
        <el-button type="primary" @click="submitChat">发送</el-button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { streamChatApi } from "@/api/ChatApi.ts";
import { useChatMessageStore } from "@/store/message";
import Robot from "@/assets/robot.svg";
import User from "@/assets/user.svg";
import System from "@/assets/system.svg";
const chatMessageStore = useChatMessageStore();
import { ElMessage, ElScrollbar } from "element-plus";

const input = ref<string>("");
const ready = ref<boolean>(true);
const messageList = computed(() => {
  return chatMessageStore.globalMessage;
});
const innerElement = ref<HTMLElement>();
const myScrollbar = ref<InstanceType<typeof ElScrollbar>>();

const updateToBottom = () => {
  // console.log(myScrollbar.value?.);
  console.log(innerElement.value?.scrollHeight);

  myScrollbar.value?.setScrollTop(innerElement.value?.clientHeight as number);
};

onMounted(() => {
  updateToBottom();
});

onUpdated(() => {
  updateToBottom();
});

const submitChat = () => {
  streamChatApi(input.value);
  input.value = "";
};

const cleanMessage = () => {
  chatMessageStore.resetGlobalMessage();
};

const handleKeydown = (e: KeyboardEvent) => {
  if (!e.shiftKey && e.key === "Enter") {
    e.preventDefault();
    if (input.value === "") {
      ElMessage({
        type: "error",
        message: "请输入内容",
      });
    } else {
      submitChat();
    }
    // if (ready.value) {
    // ready.value = false;
    // } else {
    //   ElMessage.error("请等待机器人回复后再发送哦");
    // }
  }
};
</script>

<style scoped lang="less">
#chat-view {
  height: 100%;
}

:deep(.el-scrollbar) {
  max-height: calc(100vh - 190px);
}

.scrollbar-item {
  margin-bottom: 30px;
}
.chat-footer {
  background-color: transparent;
  height: 120px;
  width: 100%;
  border-top: 2px solid #cecccc;
}
.chat-footer .input-box {
  margin-top: 50px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
