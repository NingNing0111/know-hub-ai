<template>
  <div id="setting-page">
    <el-form
      :model="chatOptions"
      label-width="auto"
      label-position="top"
      class="setting-form"
    >
      <el-form-item label="模型">
        <el-select
          v-model="chatOptions.model"
          size="large"
          style="width: 240px"
        >
          <el-option v-for="item in models" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="聊天历史长度">
        <el-input-number
          v-model="chatOptions.maxHistoryLength"
          :min="1"
          :max="50"
          @change="handleChangeHistoryLength"
        />
      </el-form-item>
      <el-form-item label="对话模式">
        <el-radio-group v-model="chatOptions.chatType" class="ml-4">
          <el-radio value="rag" size="large">RAG</el-radio>
          <el-radio value="simple" size="large">Simple</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="随机性(temperature)">
        <el-slider
          style="max-width: 400px"
          v-model="floatNumber"
          :format-tooltip="formatTooltip"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="saveSetting" type="primary"> 保存 </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { useChatOptionsStore } from "@/store/options";
import { getModelsApi } from "@/api/ChatApi";
const chatOptionsStore = useChatOptionsStore();
const chatOptions = chatOptionsStore.getChatOptions;

const models = ref<string[]>([]);
const handleChangeHistoryLength = (e: any) => {
  chatOptions.maxHistoryLength = e;
};
const floatNumber = ref(chatOptions.temperature * 100);
const loadModels = () => {
  getModelsApi()
    .then((res) => {
      console.log(res.code);
      if (res.code == 0) {
        models.value = res.data;
      }
    })
    .catch((err) => {});
};
onMounted(() => {
  loadModels();
});

const formatTooltip = (e: any) => {
  return e / 100;
};

const saveSetting = () => {
  chatOptions.temperature = floatNumber.value / 100;
  console.log(chatOptions);

  // chatOptions.temperature = 0.5;
  // chatOptionsStore.setChatOptions(chatOptions);
};
</script>

<style scoped lang="less">
#setting-page {
  // background-color: pink;
  // height: 100%;
}

.setting-form {
  // height: 100%;
  // width: 100%;
}
</style>
