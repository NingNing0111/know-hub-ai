<template>
  <div style="display: flex; justify-content: right; margin: 20px">
    <el-button
      type="warning"
      size="large"
      @click="uploadFile"
      :disabled="isUploading"
    >
      全部上传
    </el-button>
  </div>
  <el-upload
    class="upload-demo"
    drag
    multiple
    v-model:file-list="fileList"
    :auto-upload="false"
    v-loading="isUploading"
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

  <el-form
    style="display: flex; justify-content: space-between"
    :model="queryFileDto"
  >
    <el-form-item label="文件名:">
      <el-input placeholder="请输入文件名称" v-model="queryFileDto.fileName" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="loadStoreFileData" :disabled="isLoading"
        >搜索</el-button
      >
    </el-form-item>
  </el-form>
  <el-table :data="storeFileData" border v-loading="isLoading">
    <el-table-column prop="id" label="ID" width="180" />
    <el-table-column prop="fileName" label="文件名" width="180" />
    <el-table-column label="上传时间">
      <template #default="scope">
        {{ format(new Date(scope.row.createTime), "yyyy-MM-dd HH:mm") }}
      </template>
    </el-table-column>
    <el-table-column label="更新时间">
      <template #default="scope">
        {{ format(new Date(scope.row.updateTime), "yyyy-MM-dd HH:mm") }}
      </template>
    </el-table-column>
    <el-table-column label="操作" width="150">
      <template #default="scope">
        <el-button
          @click="deleteStoreFile(scope.row)"
          type="danger"
          size="small"
          >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup lang="ts">
import { type UploadUserFile, ElMessage, ElMessageBox } from "element-plus";
import { uploadFileApi, queryFileApi, deleteFileApi } from "@/api/KnowHubApi";
import { StoreFile } from "@/api/data";
import { QueryFileDto } from "@/api/dto";
import { format } from "date-fns";

const storeFileData = ref<StoreFile[]>([]);
const queryFileDto = ref<QueryFileDto>({
  page: 0,
  pageSize: 10,
  fileName: "",
});
const isUploading = ref(false);
const isLoading = ref(false);
const storeFileTotal = ref(0);
// 查询知识库文件
const loadStoreFileData = () => {
  isLoading.value = true;
  queryFileApi(queryFileDto.value)
    .then((res) => {
      let code = res.code;
      if (code == 0) {
        const data = res.data;
        storeFileTotal.value = data.totalElements;
        storeFileData.value = data.content;
      } else {
        // 接口异常
        ElMessage({
          type: "error",
          message: res.message,
        });
      }
    })
    .catch((err) => {
      ElMessage({
        type: "error",
        message: err,
      });
    })
    .finally(() => {
      isLoading.value = false;
    });
};

const fileList = ref<UploadUserFile[]>();

const uploadFile = () => {
  const files: File[] = [];
  fileList.value?.forEach((e) => {
    files.push(e.raw as File);
  });
  isUploading.value = true;
  uploadFileApi(files)
    .then((res) => {
      let code = res.data.code;
      if (code == 0) {
        ElMessage({
          type: "success",
          message: res.data.data,
        });
        fileList.value = [];
        loadStoreFileData();
      } else {
        ElMessage({
          type: "error",
          message: res.data.message,
        });
      }
    })
    .catch((err) => {
      console.log(err);
      ElMessage({
        type: "error",
        message: err,
      });
    })
    .finally(() => {
      isUploading.value = false;
    });
};
const deleteStoreFile = (e: any) => {
  ElMessageBox.confirm("确定要删除这个知识库吗？", "Warning", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      deleteFileApi({
        ids: e.id,
      })
        .then((res) => {
          let code = res.code;
          if (code == 0) {
            ElMessage({
              type: "success",
              message: res.data,
            });
          } else {
            ElMessage({
              type: "error",
              message: res.message,
            });
          }
        })
        .catch((err) => {
          ElMessage({
            type: "error",
            message: err,
          });
        });
    })
    .catch(() => {});
  loadStoreFileData();
};

onMounted(() => {
  loadStoreFileData();
});
</script>

<style scoped lang="less"></style>
