
<template>
  <div class="block">
    <el-table class="table" table-layout="auto" border :data="data" v-loading="loading">
      <el-table-column prop="id" label="id" />
      <el-table-column prop="apiKey" label="apiKey" />
      <el-table-column prop="baseUrl" label="baseUrl" />
      <el-table-column prop="describe" label="describe" />
      <el-table-column align="right">
        <template #header>
<!--          <el-row justify="end" class="colRight">-->
<!--            <el-col :span="18">-->
<!--              <el-input-->
<!--                  class="input"-->
<!--                  size="small"-->
<!--                  style="width: 50%"-->
<!--                  placeholder="查询"-->
<!--                  v-model.trim="search"-->
<!--                  @change="handleSearchChange"-->
<!--              />-->
<!--            </el-col>-->
<!--            <el-col :span="6">-->
              <el-button
                  style="width: 132px"
                  type="primary"
                  size="default"
                  @click="handleInsert"
              >添加API</el-button
              >
<!--            </el-col>-->
<!--          </el-row>-->
        </template>
        <template #default="scope">
          <el-button type="primary" size="default" @click="handleUpdate(scope.row.id)">编辑</el-button>
          <el-button
              class="rightButton"
              type="danger"
              size="default"
              @click="handleDelete(scope.row.id)"
          >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-block">
      <el-pagination
          :page-size="pageSize"
          :total="total"
          :pager-count="9"
          background
          layout="total, prev, pager, next, jumper"
          v-model:current-page="currentPage"
          @current-change="handleCurrentChange"
      />
    </div>


    <el-dialog
        class="deleteDialog"
        v-model="deleteDialogVisible"
        title="删除API"
        width="400px"
        align-center
    >
      <span>确定删除该API？</span>
      <template #footer>
        <span class="dialogFooter">
          <el-button type="primary" size="large" @click="handleConfirmDelete">确定</el-button>
          <el-button size="large" @click="deleteDialogVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup lang="ts">

import {addOneApi, changeApi, deleteById, selectApi} from "@/api/ManageApi.ts";

const rules = {}

// const search = ref('')
const handleSearchChange = () => {
  getData()
}

const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const data = ref([])
const loading = ref(true)

const getData = async () => {
  const res = await selectApi(currentPage.value, pageSize.value)
  console.log(res)
  data.value = res.data
  // total.value =
}
onMounted(async () => {
  await getData()
  loading.value = false
})

const handleInsert = async () => {
  await addOneApi()
  await getData()
}

// const insertDialogVisible = ref(false)
// const insertForm = ref({})
// const handleConfirmInsert = async () => {
//   await addOneApi()
//   await getData()
//   insertDialogVisible.value = false
// }

// const updateDialogVisible = ref(false)
// const updateId = ref<number>()
const handleUpdate = async (id) => {
  await changeApi(id)
  // updateId.value = id
  // updateDialogVisible.value = true
}
// const handleConfirmUpdate = async () => {
//   await changeApi(updateId.value)
//   await getData()
//   updateDialogVisible.value = false
// }

const deleteDialogVisible = ref<boolean>(false)
const deleteId = ref<number>()
const handleDelete = async (id) => {
  deleteId.value = id
  deleteDialogVisible.value = true
}
const handleConfirmDelete = async () => {
  await deleteById(deleteId.value)
  await getData()
  deleteDialogVisible.value = false
}

const handleCurrentChange = () => {
  getData()
}

// const dialogInputRef = ref()
// const handleDialogOpened = async () => {
//   dialogInputRef.value.focus()
// }
</script>

<style scoped>
.item {
  margin-bottom: 40px;
  width: 280px;
}

.pagination-block {
  display: flex;
  justify-content: center;
  margin-top: 40px;
}
</style>
