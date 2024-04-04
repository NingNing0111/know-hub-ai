
<template>
  <div class="block">
    <el-table class="table" table-layout="auto" border :data="data" v-loading="loading">
      <el-table-column prop="username" label="用户名"> </el-table-column>
      <el-table-column label="密码">
        <template #default> ******** </template>
      </el-table-column>
      <el-table-column label="权限">
        <template #default="scope">
          {{ showPrivilege(scope.row.privilege) }}
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template #header>
          <el-row justify="end" class="colRight">
            <el-col :span="18">
              <el-input
                  class="input"
                  size="small"
                  style="width: 50%"
                  placeholder="查询"
                  v-model.trim="search"
                  @change="handleSearchChange"
              />
            </el-col>
            <el-col :span="6">
              <el-button
                  style="width: 132px"
                  type="primary"
                  size="default"
                  @click="insertDialogVisible = true"
              >添加用户</el-button
              >
            </el-col>
          </el-row>
        </template>
        <template #default="scope">
          <el-button type="primary" size="default" @click="handleUpdate(scope.row)">编辑</el-button>
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
        class="insertDialog"
        title="添加用户"
        width="400px"
        align-center
        v-model="insertDialogVisible"
        @opened="handleDialogOpened"
    >
      <el-form
          :model="insertForm"
          :rules="rules"
          label-width="80"
          status-icon
          hide-required-asterisk
      >
        <el-form-item class="item" label="用户名" prop="username">
          <el-input
              v-model.trim="insertForm.username"
              ref="dialogInputRef"
              placeholder="用户名"
              @keyup.enter="handleConfirmInsert"
          />
        </el-form-item>
        <el-form-item class="item" label="密码" prop="password">
          <el-input
              v-model.trim="insertForm.password"
              show-password
              placeholder="密码"
              @keyup.enter="handleConfirmInsert"
          />
        </el-form-item>
        <el-form-item class="item" label="权限" prop="privilege" style="width: 230px">
          <el-select v-model="insertForm.privilege" placeholder="请选择">
            <el-option label="管理员" :value="0" />
            <el-option label="用户" :value="1" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleConfirmInsert">提交</el-button>
          <el-button @click="insertDialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
        v-model="updateDialogVisible"
        class="updateDialog"
        title="编辑用户"
        width="400px"
        align-center
        @opened="handleDialogOpened"
    >
      <el-form
          :model="updateForm"
          :rules="rules"
          label-width="80"
          status-icon
          hide-required-asterisk
      >
        <el-form-item class="item" label="用户名" prop="username">
          <el-input
              v-model.trim="updateForm.username"
              ref="dialogInputRef"
              placeholder="用户名"
              @keyup.enter="handleConfirmUpdate"
          />
        </el-form-item>
        <el-form-item class="item" label="密码" prop="password">
          <el-input
              v-model.trim="updateForm.password"
              show-password
              placeholder="密码"
              @keyup.enter="handleConfirmUpdate"
          />
        </el-form-item>
        <el-form-item class="item" label="权限" prop="privilege" style="width: 230px">
          <el-select v-model="updateForm.privilege" placeholder="请选择">
            <el-option label="管理员" :value="0" />
            <el-option label="用户" :value="1" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleConfirmUpdate">提交</el-button>
          <el-button @click="updateDialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
        class="deleteDialog"
        v-model="deleteDialogVisible"
        title="删除用户"
        width="400px"
        align-center
    >
      <span>确定删除该用户？</span>
      <template #footer>
        <span class="dialogFooter">
          <el-button type="primary" size="large" @click="handleConfirmDelete">确定</el-button>
          <el-button size="large" @click="deleteDialogVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
const rules = {}

const search = ref('')
const handleSearchChange = () => {
  getData()
}

const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const data = ref([])
const loading = ref(true)

const getData = async () => {
  const res = await listUserAPI(currentPage.value, pageSize.value, search.value)
  data.value = res.rows
  total.value = res.total
}
onMounted(async () => {
  // await getData()  // !!!!!!!!!!!!暂时关闭
  loading.value = false
})

const insertDialogVisible = ref(false)
const insertForm = ref({})
const handleConfirmInsert = async () => {
  await insertUserAPI(insertForm.value)
  await getData()
  insertDialogVisible.value = false
}

const updateDialogVisible = ref(false)
const updateForm = ref({})
const handleUpdate = async (form) => {
  updateForm.value = form
  updateDialogVisible.value = true
}
const handleConfirmUpdate = async () => {
  await updateUserAPI(updateForm.value)
  await getData()
  updateDialogVisible.value = false
}

const deleteDialogVisible = ref(false)
const deleteId = ref()
const handleDelete = async (id) => {
  deleteId.value = id
  deleteDialogVisible.value = true
}
const handleConfirmDelete = async () => {
  await deleteUserAPI(deleteId.value)
  await getData()
  deleteDialogVisible.value = false
}

const handleCurrentChange = () => {
  getData()
}

const dialogInputRef = ref()
const handleDialogOpened = async () => {
  dialogInputRef.value.focus()
}
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
