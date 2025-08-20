<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="物资名称" prop="wuzimingcheng">
							<el-input class="list_inp" v-model="form.wuzimingcheng" placeholder="物资名称"
                                type="text"
								:readonly="!isAdd||disabledForm.wuzimingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="到达数量" prop="daodashuliang">
							<el-input class="list_inp" v-model.number="form.daodashuliang" placeholder="到达数量"
                                type="text"
								:readonly="!isAdd||disabledForm.daodashuliang?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="物流" prop="wuliu">
							<el-input class="list_inp" v-model="form.wuliu" placeholder="物流"
                                type="text"
								:readonly="!isAdd||disabledForm.wuliu?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="运送地点" prop="yunsongdidian">
							<el-input class="list_inp" v-model="form.yunsongdidian" placeholder="运送地点"
                                type="text"
								:readonly="!isAdd||disabledForm.yunsongdidian?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="运输日期" prop="yunshuriqi">
							<el-date-picker
								class="list_date"
								v-model="form.yunshuriqi"
								format="YYYY 年 MM 月 DD 日"
								value-format="YYYY-MM-DD"
								type="date"
								:readonly="!isAdd||disabledForm.yunshuriqi?true:false"
								placeholder="请选择运输日期" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="是否到达" prop="shifoudaoda">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.shifoudaoda?true:false"
								v-model="form.shifoudaoda" 
								placeholder="请选择是否到达"
								>
								<el-option v-for="(item,index) in shifoudaodaLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="用户账号" prop="yonghuzhanghao">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.yonghuzhanghao?true:false"
								v-model="form.yonghuzhanghao" 
								placeholder="请选择用户账号"
								@change="yonghuzhanghaoChange">
								<el-option v-for="(item,index) in yonghuzhanghaoLists" :label="item" :value="item">
								</el-option>
							</el-select>
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
						<el-form-item label="运输司机" prop="yunshusiji">
							<el-input class="list_inp" v-model="form.yunshusiji" placeholder="运输司机"
                                type="text"
								:readonly="!isAdd||disabledForm.yunshusiji?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="司机电话" prop="sijidianhua">
							<el-input class="list_inp" v-model="form.sijidianhua" placeholder="司机电话"
                                type="text"
								:readonly="!isAdd||disabledForm.sijidianhua?true:false" />
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
	const tableName = 'yunshuxinxi'
	const formName = '运输信息'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        wuzimingcheng : false,
        daodashuliang : false,
        wuliu : false,
        yunsongdidian : false,
        yunshuriqi : false,
        shifoudaoda : false,
        yonghuzhanghao : false,
        yonghuxingming : false,
        yunshusiji : false,
        sijidianhua : false,
        yuangongzhanghao : false,
        yuangongxingming : false,
        sfsh : false,
        shhf : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		wuzimingcheng: [
		],
		daodashuliang: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		wuliu: [
		],
		yunsongdidian: [
		],
		yunshuriqi: [
		],
		shifoudaoda: [
		],
		yonghuzhanghao: [
		],
		yonghuxingming: [
		],
		yunshusiji: [
		],
		sijidianhua: [
			{ validator: context.$toolUtil.validator.mobile, trigger: 'blur' },
		],
		yuangongzhanghao: [
		],
		yuangongxingming: [
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
	//是否到达列表
	const shifoudaodaLists = ref([])
	//用户账号列表
	const yonghuzhanghaoLists = ref([])

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			wuzimingcheng: '',
			daodashuliang: '',
			wuliu: '',
			yunsongdidian: '',
			yunshuriqi: '',
			shifoudaoda: '',
			yonghuzhanghao: '',
			yonghuxingming: '',
			yunshusiji: '',
			sijidianhua: '',
			yuangongzhanghao: '',
			yuangongxingming: '',
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
			form.value.yunshuriqi = context?.$toolUtil.getCurDate()
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
				if(x=='wuzimingcheng'){
					form.value.wuzimingcheng = row[x];
					disabledForm.value.wuzimingcheng = true;
					continue;
				}
				if(x=='daodashuliang'){
					form.value.daodashuliang = row[x];
					disabledForm.value.daodashuliang = true;
					continue;
				}
				if(x=='wuliu'){
					form.value.wuliu = row[x];
					disabledForm.value.wuliu = true;
					continue;
				}
				if(x=='yunsongdidian'){
					form.value.yunsongdidian = row[x];
					disabledForm.value.yunsongdidian = true;
					continue;
				}
				if(x=='yunshuriqi'){
					form.value.yunshuriqi = row[x];
					disabledForm.value.yunshuriqi = true;
					continue;
				}
				if(x=='shifoudaoda'){
					form.value.shifoudaoda = row[x];
					disabledForm.value.shifoudaoda = true;
					continue;
				}
				if(x=='yonghuzhanghao'){
					form.value.yonghuzhanghao = row[x];
					disabledForm.value.yonghuzhanghao = true;
					continue;
				}
				if(x=='yonghuxingming'){
					form.value.yonghuxingming = row[x];
					disabledForm.value.yonghuxingming = true;
					continue;
				}
				if(x=='yunshusiji'){
					form.value.yunshusiji = row[x];
					disabledForm.value.yunshusiji = true;
					continue;
				}
				if(x=='sijidianhua'){
					form.value.sijidianhua = row[x];
					disabledForm.value.sijidianhua = true;
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
			if(json.hasOwnProperty('yuangongzhanghao')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.yuangongzhanghao = json.yuangongzhanghao
				disabledForm.value.yuangongzhanghao = true;
			}
			if(json.hasOwnProperty('yuangongxingming')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.yuangongxingming = json.yuangongxingming
				disabledForm.value.yuangongxingming = true;
			}
		})
		shifoudaodaLists.value = "已到达,未到达".split(',')
		context?.$http({
			url: `option/yonghu/yonghuzhanghao`,
			method: 'get'
		}).then(res=>{
			yonghuzhanghaoLists.value = res.data.data
		})
		//由上级字段带出不可改
		disabledForm.value.yonghuxingming = true;
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
	const yonghuzhanghaoChange=()=>{
		context?.$http({
			url: `follow/yonghu/yonghuzhanghao?columnValue=` + form.value.yonghuzhanghao,
			method: 'get'
		}).then(res=>{
			if(res.data.data.yonghuxingming){
				form.value.yonghuxingming = res.data.data.yonghuxingming
			}
		})
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
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
				// 下拉框
				.list_sel {
					//去掉默认样式
					.select-trigger{
						height: 100%;
						.el-input{
							height: 100%;
						}
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
