<template>
  <div id="chat-view">
    <el-scrollbar>
      <el-card v-for="item in messageList" class="scrollbar-item">
        <template #header>
          <div
            class="card-header"
            v-if="
              (item.role === 'system' || item.role === 'assistant') &&
              item.content !== ''
            "
          >
            <span>{{ "AI" }}</span>
          </div>
          <div
            class="card-header"
            v-if="item.role === 'user' && item.content !== ''"
          >
            <span>{{ item.role }}</span>
          </div>
        </template>
        <div v-html="item.content"></div>
      </el-card>
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
      />
      <div style="display: flex; justify-content: right; margin: 10px">
        <el-button type="warning" @click="cleanMessage">清除对话</el-button>
        <el-button type="primary" @click="submitChat">发送</el-button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ElMessage } from "element-plus";
import { simpleChatApi, streamChatApi } from "@/api/ChatApi.ts";
import { useChatMessageStore } from "@/store/message";
import { Message } from "@/api/dto";

const chatMessageStore = useChatMessageStore();

const input = ref<string>("");
const ready = ref<boolean>(true);
const messageList = computed(() => {
  return chatMessageStore.getGlobalMessage;
});
const submitChat = () => {
  streamChatApi(input.value);
};

const cleanMessage = () => {
  chatMessageStore.cleanMessage();
};

// const handleInput = (e: KeyboardEvent) => {
//   if (!e.shiftKey && e.key === "Enter") {
//     e.preventDefault();
//     if (ready.value) {
//       chatList.value.push(input.value);
//       ready.value = false;

//       simpleChatApi(input.value)
//         .then((res) => {
//           console.log(res.data);
//           chatList.value.push();
//           ready.value = true;
//         })
//         .catch(() => {
//           chatList.value.push("");
//           ready.value = true;
//         });
//     } else {
//       ElMessage.error("请等待机器人回复后再发送哦");
//     }
//   }
// };
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
</style>
