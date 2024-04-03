<template>
  <el-upload
    class="upload-demo"
    drag
    multiple
    v-model:file-list="fileList"
    :auto-upload="false"
  >
    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
    <div class="el-upload__text">
      拖拽文件至此或<em>点击选择文件</em>进行上传
    </div>
    <template #tip>
      <div style="text-align: center">
        <el-text>文件最大可上传<em style="color: blue">20MB</em></el-text>
      </div>
    </template>
  </el-upload>
  <div style="display: flex; justify-content: right">
    <el-button type="primary" size="large" @click="uploadFile">
      全部上传
    </el-button>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue";
import {
  type UploadProps,
  type UploadUserFile,
  type UploadRawFile,
  ElMessage,
} from "element-plus";
import { uploadFileApi } from "@/api/KnowHubApi";
const fileList = ref<UploadUserFile[]>();

const uploadFile = () => {
  const files: File[] = [];
  fileList.value?.forEach((e) => {
    files.push(e.raw as File);
  });
  console.log("上传的文件:", files);

  uploadFileApi(files)
    .then((res) => {
      console.log(res);
      ElMessage({
        type: "success",
        message: res.data.data,
      });
    })
    .catch((err) => {
      console.log(err);
    });
};
</script>

<style scoped lang="less"></style>
