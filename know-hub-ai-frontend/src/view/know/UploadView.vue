<template>
  <div>
    <el-upload
      ref="uploadRef"
      class="upload-demo"
      action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
      :auto-upload="false"
    >
      <template #trigger>
        <el-button type="primary">选择文件</el-button>
      </template>

      <el-button class="ml-3" type="success" @click="submitUpload">
        上传至知识库
      </el-button>

      <template #tip>
        <div class="el-upload__tip">文件大小最多支持20MB</div>
      </template>
    </el-upload>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue";
</script>

<style scoped lang="less"></style>
