<template>
  <div>
    <el-dialog
      modal-class="edit_form_modal"
      class="edit_form"
      v-model="formVisible"
      :title="formTitle"
      width="50%"
      destroy-on-close
      :fullscreen="false"
    >
      <el-form
        class="formModel_form"
        ref="formRef"
        :model="form"
        :rules="rules"
      >
        <el-row>
          <el-col :span="24">
            <el-form-item label="站点名称" prop="zhandianmingcheng">
              <el-input
                class="list_inp"
                v-model="form.zhandianmingcheng"
                placeholder="站点名称"
                type="text"
                :readonly="
                  !isAdd || disabledForm.zhandianmingcheng ? true : false
                "
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item prop="zhandianzhaopian" label="站点照片">
              <uploads
                :disabled="
                  !isAdd || disabledForm.zhandianzhaopian ? true : false
                "
                action="file/upload"
                tip="请上传站点照片"
                style="width: 100%; text-align: left"
                :fileUrls="form.zhandianzhaopian ? form.zhandianzhaopian : ''"
                @change="zhandianzhaopianUploadSuccess"
              >
              </uploads>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="道路名称" prop="daolumingcheng">
              <el-input
                class="list_inp"
                v-model="form.daolumingcheng"
                placeholder="道路名称"
                type="text"
                :readonly="!isAdd || disabledForm.daolumingcheng ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="道路地址" prop="daoludizhi">
              <el-input
                class="list_inp"
                v-model="form.daoludizhi"
                placeholder="道路地址"
                type="text"
                :readonly="!isAdd || disabledForm.daoludizhi ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="员工账号" prop="yuangongzhanghao">
              <el-input
                class="list_inp"
                v-model="form.yuangongzhanghao"
                placeholder="员工账号"
                type="text"
                :readonly="
                  !isAdd || disabledForm.yuangongzhanghao ? true : false
                "
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="员工姓名" prop="yuangongxingming">
              <el-input
                class="list_inp"
                v-model="form.yuangongxingming"
                placeholder="员工姓名"
                type="text"
                :readonly="
                  !isAdd || disabledForm.yuangongxingming ? true : false
                "
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="途径线路" prop="tujingxianlu">
              <el-input
                v-model="form.tujingxianlu"
                placeholder="途径线路"
                type="textarea"
                :readonly="!isAdd || disabledForm.tujingxianlu ? true : false"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="站点详情" prop="zhandianxiangqing">
              <editor
                :value="form.zhandianxiangqing"
                placeholder="请输入站点详情"
                :readonly="
                  !isAdd || disabledForm.zhandianxiangqing ? true : false
                "
                class="list_editor"
                @change="(e) => editorChange(e, 'zhandianxiangqing')"
              ></editor>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer v-if="isAdd || type == 'logistics' || type == 'reply'">
        <span class="formModel_btn_box">
          <el-button class="cancel_btn" @click="closeClick">取消</el-button>
          <el-button class="confirm_btn" type="primary" @click="save">
            提交
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import {
  reactive,
  ref,
  getCurrentInstance,
  nextTick,
  computed,
  defineEmits,
} from 'vue'
import { useStore } from 'vuex'
const store = useStore()
const user = computed(() => store.getters['user/session'])
const context = getCurrentInstance()?.appContext.config.globalProperties
const emit = defineEmits(['formModelChange'])
//基础信息
const tableName = 'zhandianxinxi'
const formName = '站点信息'
//基础信息
//form表单
const form = ref({})
const disabledForm = ref({
  zhandianmingcheng: false,
  zhandianzhaopian: false,
  daolumingcheng: false,
  daoludizhi: false,
  tujingxianlu: false,
  zhandianxiangqing: false,
  yuangongzhanghao: false,
  yuangongxingming: false,
  storeupNumber: false,
  sfsh: false,
  shhf: false,
})
const formVisible = ref(false)
const isAdd = ref(false)
const formTitle = ref('')

const rules = ref({
  zhandianmingcheng: [{ required: true, message: '请输入', trigger: 'blur' }],
  zhandianzhaopian: [],
  daolumingcheng: [{ required: true, message: '请输入', trigger: 'blur' }],
  daoludizhi: [],
  tujingxianlu: [{ required: true, message: '请输入', trigger: 'blur' }],
  zhandianxiangqing: [],
  yuangongzhanghao: [],
  yuangongxingming: [],
  storeupNumber: [
    { validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
  ],
  sfsh: [],
  shhf: [],
})
//表单验证

const formRef = ref(null)
const id = ref(0)
const type = ref('')
//站点照片上传回调
const zhandianzhaopianUploadSuccess = (e) => {
  form.value.zhandianzhaopian = e
}

//获取唯一标识
const getUUID = () => {
  return new Date().getTime()
}
//重置
const resetForm = () => {
  form.value = {
    zhandianmingcheng: '',
    zhandianzhaopian: '',
    daolumingcheng: '',
    daoludizhi: '',
    tujingxianlu: '',
    zhandianxiangqing: '',
    yuangongzhanghao: '',
    yuangongxingming: '',
    shhf: '',
  }
}
//获取info
const getInfo = () => {
  context
    ?.$http({
      url: `${tableName}/info/${id.value}`,
      method: 'get',
    })
    .then((res) => {
      let reg = new RegExp('../../../file', 'g')
      res.data.data.zhandianxiangqing = res.data.data.zhandianxiangqing
        ? res.data.data.zhandianxiangqing.replace(
            reg,
            '../../../logisticsExpress/file'
          )
        : ''
      form.value = res.data.data
      formVisible.value = true
    })
}
const crossRow = ref('')
const crossTable = ref('')
const crossTips = ref('')
const crossColumnName = ref('')
const crossColumnValue = ref('')
//初始化
const init = (
  formId = null,
  formType = 'add',
  formNames = '',
  row = null,
  table = null,
  statusColumnName = null,
  tips = null,
  statusColumnValue = null
) => {
  resetForm()
  if (formId) {
    id.value = formId
    type.value = formType
  }
  if (formType == 'add') {
    isAdd.value = true
    formTitle.value = '新增' + formName
    formVisible.value = true
  } else if (formType == 'info') {
    isAdd.value = false
    formTitle.value = '查看' + formName
    getInfo()
  } else if (formType == 'edit') {
    isAdd.value = true
    formTitle.value = '修改' + formName
    getInfo()
  } else if (formType == 'cross') {
    isAdd.value = true
    formTitle.value = formNames
    // getInfo()
    for (let x in row) {
      if (x == 'zhandianmingcheng') {
        form.value.zhandianmingcheng = row[x]
        disabledForm.value.zhandianmingcheng = true
        continue
      }
      if (x == 'zhandianzhaopian') {
        form.value.zhandianzhaopian = row[x]
        disabledForm.value.zhandianzhaopian = true
        continue
      }
      if (x == 'daolumingcheng') {
        form.value.daolumingcheng = row[x]
        disabledForm.value.daolumingcheng = true
        continue
      }
      if (x == 'daoludizhi') {
        form.value.daoludizhi = row[x]
        disabledForm.value.daoludizhi = true
        continue
      }
      if (x == 'tujingxianlu') {
        form.value.tujingxianlu = row[x]
        disabledForm.value.tujingxianlu = true
        continue
      }
      if (x == 'zhandianxiangqing') {
        form.value.zhandianxiangqing = row[x]
        disabledForm.value.zhandianxiangqing = true
        continue
      }
      if (x == 'yuangongzhanghao') {
        form.value.yuangongzhanghao = row[x]
        disabledForm.value.yuangongzhanghao = true
        continue
      }
      if (x == 'yuangongxingming') {
        form.value.yuangongxingming = row[x]
        disabledForm.value.yuangongxingming = true
        continue
      }
      if (x == 'storeupNumber') {
        form.value.storeupNumber = row[x]
        disabledForm.value.storeupNumber = true
        continue
      }
    }
    if (row) {
      crossRow.value = row
    }
    if (table) {
      crossTable.value = table
    }
    if (tips) {
      crossTips.value = tips
    }
    if (statusColumnName) {
      crossColumnName.value = statusColumnName
    }
    if (statusColumnValue) {
      crossColumnValue.value = statusColumnValue
    }
    formVisible.value = true
  }

  context
    ?.$http({
      url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
      method: 'get',
    })
    .then((res) => {
      var json = res.data.data
      if (
        json.hasOwnProperty('yuangongzhanghao') &&
        context?.$toolUtil.storageGet('role') != '管理员'
      ) {
        form.value.yuangongzhanghao = json.yuangongzhanghao
        disabledForm.value.yuangongzhanghao = true
      }
      if (
        json.hasOwnProperty('yuangongxingming') &&
        context?.$toolUtil.storageGet('role') != '管理员'
      ) {
        form.value.yuangongxingming = json.yuangongxingming
        disabledForm.value.yuangongxingming = true
      }
    })
}
//初始化
//声明父级调用
defineExpose({
  init,
})
//关闭
const closeClick = () => {
  formVisible.value = false
}
//富文本
const editorChange = (e, name) => {
  form.value[name] = e
}
//提交
const save = async () => {
  if (form.value.zhandianzhaopian != null) {
    form.value.zhandianzhaopian = form.value.zhandianzhaopian.replace(
      new RegExp(context?.$config.url, 'g'),
      ''
    )
  }
  var table = crossTable.value
  var objcross = JSON.parse(JSON.stringify(crossRow.value))
  let crossUserId = ''
  let crossRefId = ''
  let crossOptNum = ''
  if (type.value == 'cross') {
    if (crossColumnName.value != '') {
      if (!crossColumnName.value.startsWith('[')) {
        for (let o in objcross) {
          if (o == crossColumnName.value) {
            objcross[o] = crossColumnValue.value
          }
        }
        //修改跨表数据
        changeCrossData(objcross)
      } else {
        crossUserId = user.value.id
        crossRefId = objcross['id']
        crossOptNum = crossColumnName.value.replace(/\[/, '').replace(/\]/, '')
      }
    }
  }
  formRef.value.validate((valid) => {
    if (valid) {
      if (crossUserId && crossRefId) {
        form.value.crossuserid = crossUserId
        form.value.crossrefid = crossRefId
        let params = {
          page: 1,
          limit: 1000,
          crossuserid: form.value.crossuserid,
          crossrefid: form.value.crossrefid,
        }
        context
          ?.$http({
            url: `${tableName}/page`,
            method: 'get',
            params: params,
          })
          .then((res) => {
            if (res.data.data.total >= crossOptNum) {
              context?.$toolUtil.message(`${crossTips.value}`, 'error')
              return false
            } else {
              context
                ?.$http({
                  url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
                  method: 'post',
                  data: form.value,
                })
                .then(async (res) => {
                  emit('formModelChange')
                  context?.$toolUtil.message(`操作成功`, 'success')
                  formVisible.value = false
                })
            }
          })
      } else {
        context
          ?.$http({
            url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
            method: 'post',
            data: form.value,
          })
          .then(async (res) => {
            emit('formModelChange')
            context?.$toolUtil.message(`操作成功`, 'success')
            formVisible.value = false
          })
      }
    } else {
      context.$message.error('请完善信息')
    }
  })
}
//修改跨表数据
const changeCrossData = async (row) => {
  if (type.value == 'cross') {
    await context
      ?.$http({
        url: `${crossTable.value}/update`,
        method: 'post',
        data: row,
      })
      .then((res) => {})
  }
}
</script>
<style lang="scss" scoped>
// 表单
.formModel_form {
  // form item
  :deep(.el-form-item) {
    //label
    .el-form-item__label {
    }
    // 内容盒子
    .el-form-item__content {
      // 输入框
      .list_inp {
      }
      // 富文本
      .list_editor {
      }
      // 长文本
      .el-textarea__inner {
      }
      //图片上传样式
      .el-upload-list {
        //提示语
        .el-upload__tip {
        }
        //外部盒子
        .el-upload--picture-card {
          //图标
          .el-icon {
          }
        }
        .el-upload-list__item {
        }
      }
    }
  }
}
// 按钮盒子
.formModel_btn_box {
  .cancel_btn {
  }
  .cancel_btn:hover {
  }

  .confirm_btn {
  }
  .confirm_btn:hover {
  }
}
</style>
