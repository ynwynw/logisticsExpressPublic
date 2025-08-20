<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="快递编号" prop="kuaidibianhao">
							<el-input class="list_inp" v-model="form.kuaidibianhao" placeholder="快递编号"
                                type="text"
								:readonly="!isAdd||disabledForm.kuaidibianhao?true:false" />
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
						<el-form-item label="物品描述" prop="wupinmiaoshu">
							<el-input class="list_inp" v-model="form.wupinmiaoshu" placeholder="物品描述"
                                type="text"
								:readonly="!isAdd||disabledForm.wupinmiaoshu?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="收件地址" prop="shoujiandizhi">
							<el-input class="list_inp" v-model="form.shoujiandizhi" placeholder="收件地址"
                                type="text"
								:readonly="!isAdd||disabledForm.shoujiandizhi?true:false" />
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
						<el-form-item label="预约取件地址" prop="yuyuequjiandizhi">
							<el-input class="list_inp" v-model="form.yuyuequjiandizhi" placeholder="预约取件地址"
                                type="text"
								:readonly="!isAdd||disabledForm.yuyuequjiandizhi?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="预约时间" prop="yuyueshijian">
							<el-date-picker
								class="list_date"
								v-model="form.yuyueshijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.yuyueshijian?true:false"
								placeholder="请选择预约时间" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="手机" prop="shouji">
							<el-input class="list_inp" v-model="form.shouji" placeholder="手机"
                                type="text"
								:readonly="!isAdd||disabledForm.shouji?true:false" />
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
	const tableName = 'yuyuequjian'
	const formName = '预约取件'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        kuaidibianhao : false,
        wupinmingcheng : false,
        wupintupian : false,
        wupinmiaoshu : false,
        shoujiandizhi : false,
        shuliang : false,
        yuyuequjiandizhi : false,
        yuyueshijian : false,
        shouji : false,
        jijianzhanghao : false,
        jijianxingming : false,
        sfsh : false,
        shhf : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		kuaidibianhao: [
		],
		wupinmingcheng: [
		],
		wupintupian: [
		],
		wupinmiaoshu: [
		],
		shoujiandizhi: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		shuliang: [
		],
		yuyuequjiandizhi: [
		],
		yuyueshijian: [
		],
		shouji: [
			{ validator: context.$toolUtil.validator.mobile, trigger: 'blur' },
		],
		jijianzhanghao: [
		],
		jijianxingming: [
		],
		sfsh: [
		],
		shhf: [
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
			kuaidibianhao: '',
			wupinmingcheng: '',
			wupintupian: '',
			wupinmiaoshu: '',
			shoujiandizhi: '',
			shuliang: '',
			yuyuequjiandizhi: '',
			yuyueshijian: '',
			shouji: '',
			jijianzhanghao: '',
			jijianxingming: '',
			shhf: '',
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
			form.value.yuyueshijian = context?.$toolUtil.getCurDateTime()
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
				if(x=='kuaidibianhao'){
					form.value.kuaidibianhao = row[x];
					disabledForm.value.kuaidibianhao = true;
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
				if(x=='wupinmiaoshu'){
					form.value.wupinmiaoshu = row[x];
					disabledForm.value.wupinmiaoshu = true;
					continue;
				}
				if(x=='shoujiandizhi'){
					form.value.shoujiandizhi = row[x];
					disabledForm.value.shoujiandizhi = true;
					continue;
				}
				if(x=='shuliang'){
					form.value.shuliang = row[x];
					disabledForm.value.shuliang = true;
					continue;
				}
				if(x=='yuyuequjiandizhi'){
					form.value.yuyuequjiandizhi = row[x];
					disabledForm.value.yuyuequjiandizhi = true;
					continue;
				}
				if(x=='yuyueshijian'){
					form.value.yuyueshijian = row[x];
					disabledForm.value.yuyueshijian = true;
					continue;
				}
				if(x=='shouji'){
					form.value.shouji = row[x];
					disabledForm.value.shouji = true;
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
			if(json.hasOwnProperty('wupinmingcheng')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.wupinmingcheng = json.wupinmingcheng
				disabledForm.value.wupinmingcheng = true;
			}
			if(json.hasOwnProperty('yonghuzhanghao')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.jijianzhanghao = json.yonghuzhanghao
				disabledForm.value.jijianzhanghao = true;
			}
			if(json.hasOwnProperty('yonghuxingming')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.jijianxingming = json.yonghuxingming
				disabledForm.value.jijianxingming = true;
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
