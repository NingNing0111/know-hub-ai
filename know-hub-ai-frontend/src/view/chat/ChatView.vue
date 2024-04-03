<template>
  <div id="chat-view">
    <el-scrollbar>
      <el-card v-for="item in chatList" class="scrollbar-item">
        {{ item }}
      </el-card>
    </el-scrollbar>
    <div id="floor">

      <el-input
          v-model="input"
          type="textarea"
          autofocus
          :autosize="{ minRows: 1, maxRows: 4}"
          resize="none"
          placeholder="请输入对话内容，换行请使用Shift+Enter"
          @keydown="handleInput"
      />

    </div>
<!--    :suffix-icon=""-->
  </div>
</template>

<script setup lang="ts">

import {ElMessage} from "element-plus";
import axios from "axios";
import {simpleChatApi} from "@/api/ChatApi.ts";

const chatList = ref<string[]>([])
const input = ref<string>('')
const ready = ref<boolean>(true)

const handleInput = (e : KeyboardEvent) => {
  if (!e.shiftKey && e.key === 'Enter') {
    e.preventDefault()
    if (ready.value) {
      chatList.value.push(input.value)
      ready.value = false

      simpleChatApi(input.value).then((res) => {
        console.log(res.data)
        chatList.value.push()
        ready.value = true
      }).catch(() => {
        chatList.value.push('')
        ready.value = true
      })
    } else {
      ElMessage.error('请等待机器人回复后再发送哦')
    }
  }
}

</script>

<style scoped lang="less">
//:deep(el-scrollbar) {
//  height: 95%;
//}
#chat-view {
  height: 100%;
  //max-height: 800px;
}
:deep(.el-card__body) {
  //background-color: #f3f3f3;
}
:deep(.el-scrollbar) {
  max-height: calc(100vh - 190px);
  //max-height: 70vh;
}
//rgb(223, 223, 223)

#floor {
  height: 150px;
  width: 100%;
  //width: calc(100% - 190px);
  //min-width: 400px;
  background-color: rgb(235, 240, 255);
  z-index: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  left: 0;
  bottom: 0;
}
:deep(.el-textarea) {
  margin-left: 220px;
  width: calc(100% - 350px);
  //max-width: 1200px;
  //min-width: 500px;
}
.scrollbar-item {
  display: flex;
  align-items: center;
  justify-content: start;
  margin: 10px;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  //color: var(--el-color-primary);

}
.scrollbar-item:nth-child(odd) {
  background-color: var(--el-color-primary-light-7);
}
</style>
