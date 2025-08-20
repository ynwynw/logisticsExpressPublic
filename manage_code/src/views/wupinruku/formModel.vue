<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="收件账号" prop="yonghuzhanghao">
							<el-input class="list_inp" v-model="form.yonghuzhanghao" placeholder="收件账号"
                                type="text"
								:readonly="!isAdd||disabledForm.yonghuzhanghao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="收件号码" prop="shoujihaoma">
							<el-input class="list_inp" v-model="form.shoujihaoma" placeholder="收件号码"
                                type="text"
								:readonly="!isAdd||disabledForm.shoujihaoma?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="用户姓名" prop="yonghuxingming">
							<el-input class="list_inp" v-model="form.yonghuxingming" placeholder="用户姓名"
                                type="text"
								:readonly="!isAdd||disabledForm.yonghuxingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="物品信息" prop="wupinxinxi">
							<el-input class="list_inp" v-model="form.wupinxinxi" placeholder="物品信息"
                                type="text"
								:readonly="!isAdd||disabledForm.wupinxinxi?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="物品名称" prop="wupinmingcheng">
							<el-input class="list_inp" v-model="form.wupinmingcheng" placeholder="物品名称"
                                type="text"
								:readonly="!isAdd||disabledForm.wupinmingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item prop="wupintupian"
									  label="物品图片"
						>
							<uploads
								:disabled="!isAdd||disabledForm.wupintupian?true:false"
								action="file/upload"
								tip="请上传物品图片"
								style="width: 100%;text-align: left;"
								:fileUrls="form.wupintupian?form.wupintupian:''" 
								@change="wupintupianUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="入库时间" prop="rukushijian">
							<el-date-picker
								class="list_date"
								v-model="form.rukushijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.rukushijian?true:false"
								placeholder="请选择入库时间" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="数量" prop="shuliang">
							<el-input class="list_inp" v-model="form.shuliang" placeholder="数量"
                                type="text"
								:readonly="!isAdd||disabledForm.shuliang?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="物品入库" prop="wupinruku">
							<el-input class="list_inp" v-model="form.wupinruku" placeholder="物品入库"
                                type="text"
								:readonly="!isAdd||disabledForm.wupinruku?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="寄件账号" prop="jijianzhanghao">
							<el-input class="list_inp" v-model="form.jijianzhanghao" placeholder="寄件账号"
                                type="text"
								:readonly="!isAdd||disabledForm.jijianzhanghao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="寄件姓名" prop="jijianxingming">
							<el-input class="list_inp" v-model="form.jijianxingming" placeholder="寄件姓名"
                                type="text"
								:readonly="!isAdd||disabledForm.jijianxingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="员工账号" prop="yuangongzhanghao">
							<el-input class="list_inp" v-model="form.yuangongzhanghao" placeholder="员工账号"
                                type="text"
								:readonly="!isAdd||disabledForm.yuangongzhanghao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="员工姓名" prop="yuangongxingming">
							<el-input class="list_inp" v-model="form.yuangongxingming" placeholder="员工姓名"
                                type="text"
								:readonly="!isAdd||disabledForm.yuangongxingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="物品描述" prop="wupinmiaoshu">
							<el-input v-model="form.wupinmiaoshu" placeholder="物品描述" type="textarea"
							:readonly="!isAdd||disabledForm.wupinmiaoshu?true:false"
							/>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer v-if="isAdd||type=='logistics'||type=='reply'">
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="closeClick">取消</el-button>
					<el-button class="confirm_btn" type="primary" @click="save"
						>
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
		defineEmits
	} from 'vue'
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;	
	const emit = defineEmits(['formModelChange'])
	//基础信息
	const tableName = 'wupinruku'
	const formName = '物品入库'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        yonghuzhanghao : false,
        shoujihaoma : false,
        yonghuxingming : false,
        wupinxinxi : false,
        wupinmingcheng : false,
        wupintupian : false,
        rukushijian : false,
        wupinmiaoshu : false,
        shuliang : false,
        wupinruku : false,
        jijianzhanghao : false,
        jijianxingming : false,
        yuangongzhanghao : false,
        yuangongxingming : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		yonghuzhanghao: [
		],
		shoujihaoma: [
		],
		yonghuxingming: [
		],
		wupinxinxi: [
		],
		wupinmingcheng: [
		],
		wupintupian: [
		],
		rukushijian: [
		],
		wupinmiaoshu: [
		],
		shuliang: [
		],
		wupinruku: [
		],
		jijianzhanghao: [
		],
		jijianxingming: [
		],
		yuangongzhanghao: [
		],
		yuangongxingming: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//物品图片上传回调
	const wupintupianUploadSuccess=(e)=>{
		form.value.wupintupian = e
	}

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			yonghuzhanghao: '',
			shoujihaoma: '',
			yonghuxingming: '',
			wupinxinxi: '',
			wupinmingcheng: '',
			wupintupian: '',
			rukushijian: '',
			wupinmiaoshu: '',
			shuliang: '',
			wupinruku: '',
			jijianzhanghao: '',
			jijianxingming: '',
			yuangongzhanghao: '',
			yuangongxingming: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
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
	const init=(formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null)=>{
		resetForm()
			form.value.rukushijian = context?.$toolUtil.getCurDateTime()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
			formTitle.value = '新增' + formName
			formVisible.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			formTitle.value = '查看' + formName
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			formTitle.value = '修改' + formName
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			formTitle.value = formNames
			// getInfo()
			for(let x in row){
				if(x=='yonghuzhanghao'){
					form.value.yonghuzhanghao = row[x];
					disabledForm.value.yonghuzhanghao = true;
					continue;
				}
				if(x=='shoujihaoma'){
					form.value.shoujihaoma = row[x];
					disabledForm.value.shoujihaoma = true;
					continue;
				}
				if(x=='yonghuxingming'){
					form.value.yonghuxingming = row[x];
					disabledForm.value.yonghuxingming = true;
					continue;
				}
				if(x=='wupinxinxi'){
					form.value.wupinxinxi = row[x];
					disabledForm.value.wupinxinxi = true;
					continue;
				}
				if(x=='wupinmingcheng'){
					form.value.wupinmingcheng = row[x];
					disabledForm.value.wupinmingcheng = true;
					continue;
				}
				if(x=='wupintupian'){
					form.value.wupintupian = row[x];
					disabledForm.value.wupintupian = true;
					continue;
				}
				if(x=='rukushijian'){
					form.value.rukushijian = row[x];
					disabledForm.value.rukushijian = true;
					continue;
				}
				if(x=='wupinmiaoshu'){
					form.value.wupinmiaoshu = row[x];
					disabledForm.value.wupinmiaoshu = true;
					continue;
				}
				if(x=='shuliang'){
					form.value.shuliang = row[x];
					disabledForm.value.shuliang = true;
					continue;
				}
				if(x=='wupinruku'){
					form.value.wupinruku = row[x];
					disabledForm.value.wupinruku = true;
					continue;
				}
				if(x=='jijianzhanghao'){
					form.value.jijianzhanghao = row[x];
					disabledForm.value.jijianzhanghao = true;
					continue;
				}
				if(x=='jijianxingming'){
					form.value.jijianxingming = row[x];
					disabledForm.value.jijianxingming = true;
					continue;
				}
				if(x=='yuangongzhanghao'){
					form.value.yuangongzhanghao = row[x];
					disabledForm.value.yuangongzhanghao = true;
					continue;
				}
				if(x=='yuangongxingming'){
					form.value.yuangongxingming = row[x];
					disabledForm.value.yuangongxingming = true;
					continue;
				}
			}
			if(row){
				crossRow.value = row
			}
			if(table){
				crossTable.value = table
			}
			if(tips){
				crossTips.value = tips
			}
			if(statusColumnName){
				crossColumnName.value = statusColumnName
			}
			if(statusColumnValue){
				crossColumnValue.value = statusColumnValue
			}
			formVisible.value = true
		}

		context?.$http({
			url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('yonghuzhanghao')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.yonghuzhanghao = json.yonghuzhanghao
				disabledForm.value.yonghuzhanghao = true;
			}
			if(json.hasOwnProperty('shoujihaoma')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.shoujihaoma = json.shoujihaoma
				disabledForm.value.shoujihaoma = true;
			}
			if(json.hasOwnProperty('yonghuxingming')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.yonghuxingming = json.yonghuxingming
				disabledForm.value.yonghuxingming = true;
			}
			if(json.hasOwnProperty('yuangongzhanghao')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.yuangongzhanghao = json.yuangongzhanghao
				disabledForm.value.yuangongzhanghao = true;
			}
			if(json.hasOwnProperty('yuangongxingming')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.yuangongxingming = json.yuangongxingming
				disabledForm.value.yuangongxingming = true;
			}
		})
	}
	//初始化
	//声明父级调用
	defineExpose({
		init
	})
	//关闭
	const closeClick = () => {
		formVisible.value = false
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
		if(form.value.wupintupian!=null) {
			form.value.wupintupian = form.value.wupintupian.replace(new RegExp(context?.$config.url,"g"),"");
		}
		var table = crossTable.value
		var objcross = JSON.parse(JSON.stringify(crossRow.value))
		let crossUserId = ''
		let crossRefId = ''
		let crossOptNum = ''
		if(type.value == 'cross'){
			if(crossColumnName.value!=''){
				if(!crossColumnName.value.startsWith('[')){
					for(let o in objcross){
						if(o == crossColumnName.value){
							objcross[o] = crossColumnValue.value
						}
					}
					//修改跨表数据
					changeCrossData(objcross)
				}else{
					crossUserId = user.value.id
					crossRefId = objcross['id']
					crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
				}
			}
		}
		formRef.value.validate((valid)=>{
			if(valid){
				if(crossUserId&&crossRefId){
					form.value.crossuserid = crossUserId
					form.value.crossrefid = crossRefId
					let params = {
						page: 1,
						limit: 1000, 
						crossuserid:form.value.crossuserid,
						crossrefid:form.value.crossrefid,
					}
					context?.$http({
						url: `${tableName}/page`,
						method: 'get', 
						params: params 
					}).then(res=>{
						if(res.data.data.total>=crossOptNum){
							context?.$toolUtil.message(`${crossTips.value}`,'error')
							return false
						}else{
							context?.$http({
								url: `${tableName}/${!form.value.id ? "save" : "update"}`,
								method: 'post', 
								data: form.value 
							}).then(async res=>{
								emit('formModelChange')
								context?.$toolUtil.message(`操作成功`,'success')
                                formVisible.value = false
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
						emit('formModelChange')
						context?.$toolUtil.message(`操作成功`,'success')
                        formVisible.value = false
					})
				}
			}else{
                context.$message.error('请完善信息')
            }
		})
	}
	//修改跨表数据
	const changeCrossData = async (row)=>{
        if(type.value == 'cross'){
            await context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: row
            }).then(res=>{})
        }
	}
</script>
<style lang="scss" scoped>
	// 表单
	.formModel_form{
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
				//日期选择器
				.list_date {
				}
				// 长文本
				.el-textarea__inner {
				}
				//图片上传样式
				.el-upload-list  {
					//提示语
					.el-upload__tip {
					}
					//外部盒子
					.el-upload--picture-card {
						//图标
						.el-icon{
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
